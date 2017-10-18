package theinevitables.navup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.DirectionsApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static theinevitables.navup.R.id.spinner1;

public class recentLocations extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner1, spinner2;
    private Button btnSubmit;
    private ArrayList<Integer> flags;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_locations);

        Bundle b = getIntent().getExtras();
        List<String> myList = new ArrayList<String>();
        if (b != null) {
            myList = b.getStringArrayList("KEY");
        }
        flags = new ArrayList<Integer>();
        System.out.println(MapsActivity.array + "MYLIST");
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        for(int i = 0; i < MapsActivity.array.size();i++){
            if(MapsActivity.array.get(i).equals("Geography")){
                flags.add(R.drawable.rsz_geography);
            }if(MapsActivity.array.get(i).equals("IT")){
                flags.add(R.drawable.rsz_it);
            }
            if(MapsActivity.array.get(i).equals("Old Arts")){
                flags.add(R.drawable.rsz_old_arts);
            }
            if(MapsActivity.array.get(i).equals("Oom Gerts")){
                flags.add(R.drawable.rsz_oomgert);
            }
            if(MapsActivity.array.get(i).equals("Tribecca")){
                flags.add(R.drawable.rsz_tribecca);
            }
            if(MapsActivity.array.get(i).equals("Steers")){
                flags.add(R.drawable.rsz_steers);
            }
            if(MapsActivity.array.get(i).equals("Zoology")){
                flags.add(R.drawable.rsz_1zoology);
            }
            if(MapsActivity.array.get(i).equals("HB/SCC")){
                flags.add(R.drawable.rsz_student_service_centre);
            }

        }
        System.out.println(MapsActivity.array + "MAPARRAY");
        System.out.println(flags + "FLAGARRAY");
        /*ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, MapsActivity.array);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);*/
        SpinnerAdapter customAdapter=new SpinnerAdapter(getApplicationContext(),flags,MapsActivity.array);
        spinner2.setAdapter(customAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        //spinner1 = (Spinner) findViewById(spinner1);
       // spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        addItemsOnSpinner2();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        addItemsOnSpinner2();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addListenerOnButton() {

       // spinner1 = (Spinner) findViewById(spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(recentLocations.this,

                               // "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nNavigating you to : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                sendGeographyCoords(this);
            }

        });
    }
    public void sendGeographyCoords(final View.OnClickListener view) {

        final Intent nextPage = new Intent(recentLocations.this, MapsActivity.class);
        final String[] message = new String[1];

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://35.202.5.111:11080/NavUPRest/api/nav/getSingleUser");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    System.out.println("SPINNER 2" + MapsActivity.array.get(CustomOnItemSelectedListener.position2));
                    jsonParam.put("locationName", MapsActivity.array.get(CustomOnItemSelectedListener.position2));


                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    InputStream is = null;
                    try {
                        is = conn.getInputStream();
                        int ch;
                        StringBuffer sb = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                            sb.append((char) ch);
                        }


                        JSONObject jObject = new JSONObject(sb.toString()); // json
                        String data = jObject.getString("locationCoordinates"); // get data object
                        // String xCoord = data.getString("locationCoordinates");
                        message[0] = data;
                        //System.out.println(data);

                        String[] temp;
                        String delimiter = ", ";

                        temp = data.split(delimiter);

                        // endX = temp[0];
                        //endY = temp[1];

                        //System.out.println(endX);
                        //System.out.println(endY);


                    } catch (IOException e) {
                        throw e;
                    } finally {
                        if (is != null) {
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {


                                    // System.out.println("HERE" + result.routes[0].legs[0].startLocation.lat);
                                    //System.out.println("HERE" + result.routes[0].legs[0].startLocation.lng);




                                    /*  Polyline line = mMap.addPolyline(new PolylineOptions()
                                            .add(new LatLng(Float.parseFloat(Y), Float.parseFloat(X)), new LatLng(Float.parseFloat(endY), Float.parseFloat(endX)))
                                            .width(5)
                                            .color(Color.RED));*/
                                    nextPage.putExtra("message", message[0]);
                                    startActivity(nextPage);
                                }
                            });

                            is.close();
                        }
                    }


                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //thread.start();

        thread1.start();



    }


    public void sendGeographyCoords(final View view) {

        final Intent nextPage = new Intent(recentLocations.this, MapsActivity.class);
        final String[] message = new String[1];

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://35.202.5.111:11080/NavUPRest/api/nav/getSingleUser");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    String buttonText = ((Button)view).getText().toString();
                    System.out.println("BUTTON" + buttonText);
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("locationName", buttonText);


                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    InputStream is = null;
                    try {
                        is = conn.getInputStream();
                        int ch;
                        StringBuffer sb = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                            sb.append((char) ch);
                        }


                        JSONObject jObject = new JSONObject(sb.toString()); // json
                        String data = jObject.getString("locationCoordinates"); // get data object
                        // String xCoord = data.getString("locationCoordinates");
                        message[0] = data;
                        //System.out.println(data);

                        String[] temp;
                        String delimiter = ", ";

                        temp = data.split(delimiter);

                       // endX = temp[0];
                        //endY = temp[1];

                        //System.out.println(endX);
                        //System.out.println(endY);


                    } catch (IOException e) {
                        throw e;
                    } finally {
                        if (is != null) {
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {


                                    // System.out.println("HERE" + result.routes[0].legs[0].startLocation.lat);
                                    //System.out.println("HERE" + result.routes[0].legs[0].startLocation.lng);




                                    /*  Polyline line = mMap.addPolyline(new PolylineOptions()
                                            .add(new LatLng(Float.parseFloat(Y), Float.parseFloat(X)), new LatLng(Float.parseFloat(endY), Float.parseFloat(endX)))
                                            .width(5)
                                            .color(Color.RED));*/
                                    nextPage.putExtra("message", message[0]);
                                    startActivity(nextPage);
                                }
                            });

                            is.close();
                        }
                    }


                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //thread.start();

        thread1.start();



    }

    public void goToNextDisabilityMenu(View view) {
        Intent nextPage1 = new Intent(recentLocations.this, disability_menu.class);
        startActivity(nextPage1);
    }

    public void goToMap(View view) {
        Intent nextPage1 = new Intent(recentLocations.this, MapsActivity.class);
        startActivity(nextPage1);
    }

    public void goToRecentVisitedLocations(View view) {
        Intent nextPage1 = new Intent(recentLocations.this, recentLocations.class);
        startActivity(nextPage1);
    }

    public void goToPOI(View view) {
        Intent nextPage1 = new Intent(recentLocations.this, POI.class);
        startActivity(nextPage1);
    }

    public void goToRegistration(View view) {
        Intent nextPage1 = new Intent(recentLocations.this, registration.class);
        startActivity(nextPage1);
    }
}