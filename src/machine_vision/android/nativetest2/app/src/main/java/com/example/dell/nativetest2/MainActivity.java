package com.example.dell.nativetest2;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.w3c.dom.Text;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Vibrator;
import android.os.Handler;
import android.speech.tts.TextToSpeech;

import java.util.HashSet;
import java.util.Locale;
import android.os.Build;
import android.content.Context;//required by SharedPreferences for MODE types
import android.content.SharedPreferences;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Cache;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements CvCameraViewListener2 {

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("opencv_java3");
    }

    private static final String TAG = "OCVSample::Activity";
    private static final String PREFERENCENAME = "navupBlind";

    private CameraBridgeViewBase mOpenCvCameraView;
    private boolean              mIsJavaCamera = true;
    private MenuItem             mItemSwitchCamera = null;

    private SharedPreferences sharedpreferences;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public MainActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    private Handler mHandler;
    Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(15, TimeUnit.SECONDS).build();
    String raw_ids = "";
    private TextToSpeech tts;
    private TextView text;

    private void speak(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
        }else{
            tts.speak(text, TextToSpeech.QUEUE_ADD, null);
        }
        while(tts.isSpeaking()){}
    }


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sharedpreferences = getSharedPreferences(PREFERENCENAME, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("0","The textured walkway is to your right.");
        editor.putString("1","Follow the textured walkway up to a tea intersection. The humanities building will be to your right and the informatorium to your left.");
        editor.putString("2","You have reached the tea intersection. Be careful for the road straight ahead");
        editor.putString("3","You are nearing the informatorium entrance on your left.");
        editor.putString("4","The purple labs and lavatories are on your right and the orange lab is to your left. The help desk is behind you.");
        editor.putString("5","Straight ahead is a tea intersection where you may find the other labs.");
        editor.putString("6","The C B T and project labs are to your right. The blue labs one to three as well as the orange, brown, maroon, and grey labs are to your left.");
        editor.putString("7","You are approaching the first blue lab to your right.");
        editor.putString("8","You are approaching the second blue lab to your right.");
        editor.putString("9","You are approaching the end of the passage. The orange labs are straight ahead. The third blue lab is around the right hand corner of the passage. The stairs are to your right.");
        editor.putString("10","Follow the stairs to reach the brown and grey labs.");
        editor.putString("11","Walk straight along the walkway to reach the brown and grey labs. The passage is in a zigzag form.");
        editor.putString("12","The passage curves to the right. Be careful for the table straight ahead.");
        editor.putString("13","The grey lab is behind you to the right and the brown lab is in front of you to the right. Be careful for the pillar straight ahead.");
        editor.commit();

        setContentView(R.layout.tutorial1_surface_view);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial1_activity_java_surface_view);

        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

        mOpenCvCameraView.setCvCameraViewListener(this);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.UK);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });

        text = (TextView)findViewById(R.id.feedback);
        mHandler = new Handler();
        mHandler.post(mUpdate);
    }
    private Runnable mUpdate = new Runnable() {
        public void run() {
            text.setText(raw_ids);
            String[] tokens = raw_ids.split(" ");
            raw_ids = "";
            for(String token:tokens)
            {
                if(token!=null && token!="" && token!="null")
                    if(cache.getIfPresent(token)==null)
                    {
                        String feedback = sharedpreferences.getString(token,"");
                        if(feedback != "")
                        {
                            speak(feedback);
                            text.setText(token);
                            cache.put(token,token);
                        }
                    }
            }
            mHandler.postDelayed(this, 200);
        }
    };

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    public void onCameraViewStarted(int width, int height) {
    }

    public void onCameraViewStopped() {
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        Mat m = inputFrame.gray();
        raw_ids+=parseImg(m.getNativeObjAddr());
 /*       Vibrator v = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        if(!raw_ids.isEmpty())
        {
            v.vibrate(30);
        }*/
        return null;
    }

    public native String parseImg(long matAddrRgba);
}

