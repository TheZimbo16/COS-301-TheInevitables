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

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class POI extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner1, spinner2;
    private Button btnSubmit;
    List<String> list = new ArrayList<String>();
    List<Integer> flags = new ArrayList<Integer>();
    View view;
    static int position1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi);



        addListenerOnButton();
        addListenerOnSpinnerItemSelection();



    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        System.out.println("SPINNER 1 " + spinner1.getSelectedItem());
        if(spinner1.getSelectedItem().equals("Restaurants")) {
            list.clear();
            flags.clear();
            flags.add(R.drawable.rsz_steers);
            flags.add(R.drawable.rsz_oomgert);
            flags.add(R.drawable.rsz_tribecca);
            list.add("Steers");
            list.add("Oom Gerts");
            list.add("Tribecca");

        }else if(spinner1.getSelectedItem().equals("Administration")){
            list.clear();
            flags.clear();
            list.add("HB/SCC");
            flags.add(R.drawable.rsz_student_service_centre);
        }else if(spinner1.getSelectedItem().equals("Public Restrooms")){
            list.clear();
            flags.clear();
            list.add("FABI Toilets");
            list.add("IT Toilets");
            flags.add(R.drawable.rsz_fabi);
            flags.add(R.drawable.rsz_it);
        }
        else if(spinner1.getSelectedItem().equals("Religion")){
            list.clear();
            flags.clear();
           list.add("TUKS Chapel");
            flags.add(R.drawable.rsz_chapel);

        }
        SpinnerAdapter customAdapter=new SpinnerAdapter(getApplicationContext(),flags,list);
        spinner2.setAdapter(customAdapter);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinner2.setSelection(0, false);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        addItemsOnSpinner2();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        System.out.println(position + "position");
        CustomOnItemSelectedListener listen = new CustomOnItemSelectedListener();
        //Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_LONG).show();
        addItemsOnSpinner2();
        position1 = position;


    }

    public void onNothingSelected(AdapterView<?> adapterView) {

        }

public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(POI.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(list.get(CustomOnItemSelectedListener.position2)),
                        Toast.LENGTH_SHORT).show();
                sendGeographyCoords(this);
            }

        });
    }
    public void sendGeographyCoords(final View.OnClickListener view) {

        final Intent nextPage = new Intent(POI.this, MapsActivity.class);
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
                    System.out.println(position1+"position 1");
                    int p = position1;

                    System.out.println("SPINNER 2" + CustomOnItemSelectedListener.position2);
                    jsonParam.put("locationName", list.get(CustomOnItemSelectedListener.position2));


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


}
