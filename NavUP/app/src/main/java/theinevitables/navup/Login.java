package theinevitables.navup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {
    private EditText et_studentNumber,et_userPassword;
    private String studentNumber,studentPassword,cpassword;
    Button regbtn;
    public void initialize() {
        studentNumber = et_studentNumber.getText().toString().trim();
        studentPassword = et_userPassword.getText().toString().trim();
        //cpassword = et_cpassword.getText().toString().trim();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regbtn = (Button) findViewById(R.id.regbtn);
        et_studentNumber = (EditText) findViewById(R.id.StudentNumber);
        et_userPassword = (EditText) findViewById(R.id.password);
        regbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    initialize();
                    verifyLogin();



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

    public void verifyLogin() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://35.202.5.111:11080/NavUPRest/api/user/verify");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
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
                    InputStream is = null;
                    try {
                        is = conn.getInputStream();
                        int ch;
                        StringBuffer sb = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                            sb.append((char) ch);
                        }

                        if(sb.toString().equals("true")){
                            System.out.println(sb.toString());
                            Intent nextPage = new Intent(Login.this,MainMenu.class);
                            startActivity(nextPage);
                        }else{
                            System.out.println(sb.toString() +"wrong");
                            Intent nextPage = new Intent(Login.this,Login.class);
                            startActivity(nextPage);
                        }
                    } catch (IOException e) {
                        throw e;
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
