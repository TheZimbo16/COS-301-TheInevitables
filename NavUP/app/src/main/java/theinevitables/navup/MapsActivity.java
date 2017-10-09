package theinevitables.navup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.R.attr.value;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public GeoJsonLayer layer;
    private GoogleMap mMap;
    Button navBtn;
    private EditText et_startLoc,et_endLoc;
    private String startLoc, endLoc;
    String X,Y,endX,endY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        navBtn = (Button) findViewById(R.id.nav);
        et_startLoc = (EditText) findViewById(R.id.startLoc);
        et_endLoc = (EditText) findViewById(R.id.endLoc);
       //
    }
    public void initialize() {
        startLoc = et_startLoc.getText().toString().trim();
        endLoc = et_endLoc.getText().toString().trim();

        //cpassword = et_cpassword.getText().toString().trim();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-25.7545,28.2314);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        float zoomLevel = 18.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

        JSONObject jsonobject = null;
        try {
            jsonobject = new JSONObject(getGeoJSONObjects());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        layer = new GeoJsonLayer(mMap, jsonobject);
        layer.addLayerToMap();
        //mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng( 28.2314,25.7545) , 14.0f) );
    }

    public String getGeoJSONObjects() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] value = new String[1];
        Thread thread = new Thread(new Runnable() {
            @Override

            public void run() {
                try {
                    InputStream inputStream = null;
                    String result = "";

                        // create HttpClient
                        HttpClient httpclient = new DefaultHttpClient();

                        // make GET request to the given URL
                        HttpResponse httpResponse = httpclient.execute(new HttpGet("http://35.202.5.111:11080/NavUPRest/api/building/get/geoJSON"));

                        // receive response as inputStream
                        inputStream = httpResponse.getEntity().getContent();

                        // convert inputstream to string
                        if(inputStream != null)
                            result = convertInputStreamToString(inputStream);
                        else
                            result = "Did not work!";
                    value[0] = result;
                    latch.countDown();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        latch.await();
        return value[0];

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public void drawPath(){

    }

    public void sendPost(View view) {

        initialize();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://35.202.5.111:11080/NavUPRest/api/nav/getSingleUser");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("locationName", startLoc);




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


                        JSONObject jObject  = new JSONObject(sb.toString()); // json
                        String data = jObject.getString("locationCoordinates"); // get data object
                       // String xCoord = data.getString("locationCoordinates");

                        System.out.println(data);

                        String[] temp;
                        String delimiter = ", ";

                        temp = data.split(delimiter);
                        X = temp[0];
                         Y = temp[1];

                        System.out.println(X);
                        System.out.println(Y);


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

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://35.202.5.111:11080/NavUPRest/api/nav/getSingleUser");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("locationName", endLoc);




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


                        JSONObject jObject  = new JSONObject(sb.toString()); // json
                        String data = jObject.getString("locationCoordinates"); // get data object
                        // String xCoord = data.getString("locationCoordinates");

                        System.out.println(data);

                        String[] temp;
                        String delimiter = ", ";

                        temp = data.split(delimiter);
                        endX = temp[0];
                       endY = temp[1];

                        System.out.println(endX);
                        System.out.println(endY);


                    } catch (IOException e) {
                        throw e;
                    } finally {
                        if (is != null) {
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    DateTime now = new DateTime();
                                    try {
                                        LatLng origin = new LatLng(Float.parseFloat(Y),Float.parseFloat(X));
                                        DirectionsResult result = DirectionsApi.newRequest(getGeoContext()).mode(TravelMode.WALKING).origin(Y+","+X).destination(endY+","+endX).departureTime(now).await();
                                        System.out.println("HERE"+result.routes[0].legs[0].startLocation.lat);
                                        System.out.println("HERE"+result.routes[0].legs[0].startLocation.lng);
                                        addMarkersToMap(result,mMap);
                                        getEndLocationTitle(result);
                                        addPolyline(result,mMap);
                                    } catch (ApiException e) {
                                        e.printStackTrace();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                  /*  Polyline line = mMap.addPolyline(new PolylineOptions()
                                            .add(new LatLng(Float.parseFloat(Y), Float.parseFloat(X)), new LatLng(Float.parseFloat(endY), Float.parseFloat(endX)))
                                            .width(5)
                                            .color(Color.RED));*/
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


        thread.start();
        thread1.start();

    }
    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3).setApiKey(getString(R.string.directionsApiKey)).setConnectTimeout(1, TimeUnit.SECONDS).setReadTimeout(1, TimeUnit.SECONDS).setWriteTimeout(1, TimeUnit.SECONDS);
    }

    private void addMarkersToMap(DirectionsResult results, GoogleMap mMap) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[0].legs[0].startLocation.lat,results.routes[0].legs[0].startLocation.lng)).title(results.routes[0].legs[0].startAddress));
        mMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[0].legs[0].endLocation.lat,results.routes[0].legs[0].endLocation.lng)).title(results.routes[0].legs[0].startAddress).snippet(getEndLocationTitle(results)));
    }

    private String getEndLocationTitle(DirectionsResult results){
        return  "Time :"+ results.routes[0].legs[0].duration.humanReadable + " Distance :" + results.routes[0].legs[0].distance.humanReadable;
    }
    private void addPolyline(DirectionsResult results, GoogleMap mMap) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[0].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath).color(Color.RED));
    }


}
