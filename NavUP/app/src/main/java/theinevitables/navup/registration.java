package theinevitables.navup;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class registration extends AppCompatActivity {

    private EditText et_studentName,et_studentNumber,et_studentSurname,et_userPassword,et_cpassword;
    private String studentName, studentSurname,studentNumber,studentPassword,cpassword;
    Button regbtn;
    View view;
    TextView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        et_studentName = (EditText) findViewById(R.id.name);
        et_studentNumber = (EditText) findViewById(R.id.StudentNumber);
        et_studentSurname = (EditText) findViewById(R.id.surname);
        et_userPassword = (EditText) findViewById(R.id.password);
       // et_userDisabled = (EditText) findViewById(R.id.confirmPassword);
        regbtn = (Button) findViewById(R.id.regbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validate()) {

                    sendPost();
                    goToMainMenu(view);
                }else{
                    System.out.print("There has been an error");
                }
                // utton is clicked to validate the input
            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
    public void register() {
        initialize();
        if(!validate()){
            Toast.makeText(this,"Signup has failed",Toast.LENGTH_SHORT).show();
        }else{
            onSignupSuccess();
        }

        URL url;
        HttpURLConnection urlConnection = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


    }


    public void onSignupSuccess(){
        //
    }

    public void goToMainMenu(View view){
        Intent nextPage1 = new Intent(registration.this,Login.class);
        startActivity(nextPage1);
    }

    public boolean validate(){
        boolean valid = true;
        initialize();
        if(studentName.isEmpty() || studentName.length() > 32){
            et_studentName.setError("Please Enter Valid Name");
            valid = false;
        }
        /*if(studentSurname.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(studentSurname).matches()){
            studentSurname.setError("Please Enter Email Address Name");
            valid = false;
        }*/
        if(studentNumber.isEmpty()){
            et_userPassword.setError("Please valid student Number");
            valid = false;
        }
        if(studentPassword.isEmpty()){
            et_userPassword.setError("Please Enter Valid Password");
            valid = false;
        }
        return valid;
    }

    public void initialize() {
        studentName = et_studentName.getText().toString().trim();
        studentSurname = et_studentSurname.getText().toString().trim();
        studentNumber = et_studentNumber.getText().toString().trim();
        studentPassword = et_userPassword.getText().toString().trim();
        //cpassword = et_cpassword.getText().toString().trim();
    }





    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://35.202.5.111:11080/NavUPRest/api/user/");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("studentName", studentName);
                    jsonParam.put("userSurname", studentSurname);
                    jsonParam.put("studentNumber", studentNumber);
                    jsonParam.put("userPassword", studentPassword);



                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}


