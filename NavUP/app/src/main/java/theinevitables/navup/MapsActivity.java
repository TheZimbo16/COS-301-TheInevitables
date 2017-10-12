package theinevitables.navup;

import android.*;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {
    DirectionsResult result;
    float zoomLevel = 18f;
    private static final Location TODO = null;
    GeoJsonLayer layer;
    GeoJsonLayer layer1;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final String[] INITIAL_PERMS = {
            android.Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final String[] LOCATION_PERMS = {
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };
    CountDownTimer timer;
    private LatLng location;
    Location mLocation;
    LocationManager locationManager;
    Marker mCurrLocationMarker;
    private View view;
    Button navBtn;
    EditText et_startLoc, et_endLoc;
    String startLoc, endLoc;
    String X, Y, endX, endY;
    double lat, lng;
    JSONObject lectureWalls;
    JSONObject my;
    private GoogleMap mMap;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private LocationListener mLocationListener;
    Marker mMarker;
    boolean isZooming = false;
    private String endLocation;
    Marker m;
    LatLng geography = new LatLng(-25.7536717, 28.230221);
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

        initGoogleApiClient();
        try {
            getGeoJSONHallObjects();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //


    public void initialize() {
        startLoc = et_startLoc.getText().toString().trim();
        endLoc = et_endLoc.getText().toString().trim();
        endLocation = endLoc;
        //cpassword = et_cpassword.getText().toString().trim();
    }

    public void getCurrentLocation(View view){




        System.out.println(mLocation + "mloc");
        if (mLocation != null) {

            //onLocationChanged(mLocation);
        }
        else location = new LatLng(-25.7536717, 28.230221);
        System.out.println(mLocation + "mloc");
        m.setPosition(new LatLng(getMyLocation().getLatitude(),getMyLocation().getLongitude()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraChangeListener(getCameraChangeListener());

        // Add a marker in Sydney and move the camera



         //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geography, zoomLevel));
        mMap.setMyLocationEnabled(true);
        mMap.setIndoorEnabled(true);
        MarkerOptions a = new MarkerOptions()
                .position(new LatLng(getMyLocation().getLatitude(),getMyLocation().getLongitude()));
        m = mMap.addMarker(a);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        System.out.println(getMyLocation() + "getmyloc");
        mLocation = getMyLocation();
        System.out.println(mLocation + "mloc");
        if (mLocation != null) {

            location = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());

        }

        else location = new LatLng(-25.7536717, 28.230221);

        timer = new CountDownTimer(5000, 1000)
        {



            public void onTick(long millisUntilFinished)
            {
                long scnds=0;
                scnds=(millisUntilFinished/1000);

            }


            public void onFinish()
            {
                System.out.println(getMyLocation());

                onLocationChanged(getMyLocation());
                timer.start();

            }
        }.start();



       // mMap.addMarker(new MarkerOptions().position(location).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(location));



        System.out.println("my location" + mMap.getMyLocation());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        JSONObject jsonobject = null;
        try {
            jsonobject = new JSONObject(getGeoJSONObjects());
            JSONArray pilot = jsonobject.getJSONArray("features");
            JSONObject jsono = new JSONObject(pilot.getString(1));

            JSONObject geom = jsono.getJSONObject("geometry");

            JSONArray json = geom.getJSONArray("coordinates");

            ArrayList<String> listdata = new ArrayList<String>();
            if (json != null) {
                for (int i = 0; i < json.length(); i++) {
                    listdata.add(json.getString(i));
                }
            }

            //String[] array = listdata.toString().split("],");
            listdata.toString().replaceAll("[\\[\\](){}]", "");
            String[] tokens = listdata.toString().replaceAll("[\\[\\](){}]", "").split(",0,", -1);

            //LatLng [] floatValues = new LatLng[tokens.length];
            ArrayList<LatLng> coords = new ArrayList<>();
            for (int i = 0; i < tokens.length; i++) {
                //System.out.println(Float.parseFloat(tokens[i]));
                String[] latlong = tokens[i].split(",");
                double latitude = Double.parseDouble(latlong[0]);
                double longitude = Double.parseDouble(latlong[1]);
                LatLng obj = new LatLng(longitude, latitude);
                coords.add(i, obj);
            }
            my = jsonobject;
            LatLng insideBuilding = coords.get(0);
            mMap.addMarker(new MarkerOptions().position(geography).title("Geography Building"));

            JSONObject jsonobject1 = null;
            jsonobject1 = new JSONObject(getGeoJSONHallObjects());
            lectureWalls = jsonobject1;
            //System.out.println(tokens[0]);
            //tokens = tokens.
            //System.out.println(listdata);
            // ArrayList<LatLng> listdata1 = new ArrayList<LatLng>();
            // for(int i = 0; i < listdata.size();i++){
            //  listdata1.add(listdata.get(i));
            // }
            ;
            //Polygon polygon = mMap.addPolygon(new PolygonOptions().addAll(listdata));
            //boolean contain = PolyUtil.containsLocation(new LatLng(-25.7545,28.2314), new LatLng(), true);
            // System.out.println(contain);

            if (PolyUtil.containsLocation(geography, coords, true) && lectureWalls != null) {
                System.out.println("you have succeeded dawie");


            } else {
                System.out.println("failed");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //layer = new GeoJsonLayer(mMap, jsonobject);
        //layer.addLayerToMap();

        List<LatLng> polygonList = new ArrayList<LatLng>();
        // To draw boundray on map

        //mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng( 28.2314,25.7545) , 14.0f) );
    }

    public GoogleMap.OnCameraChangeListener getCameraChangeListener()
    {

        return new GoogleMap.OnCameraChangeListener()
        {
            @Override
            public void onCameraChange(CameraPosition position)
            {
                mMap.clear();
                if(result != null) {
                    addPolyline(result, mMap);
                    addMarkersToMap(result, mMap);
                    getEndLocationTitle(result);
                }
                MarkerOptions a = new MarkerOptions()
                        .position(new LatLng(getMyLocation().getLatitude(),getMyLocation().getLongitude()));
                m = mMap.addMarker(a);
                Log.d("Zoom", "Zoom: " + position.zoom);
                layer = new GeoJsonLayer(mMap, lectureWalls);
                layer1 = new GeoJsonLayer(mMap, my);
                JSONArray pilot = null;
                try {
                    pilot = lectureWalls.getJSONArray("features");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject jsono1 = null;
                JSONObject jsono2 = null;
                JSONObject jsono3 = null;
                try {
                    jsono1 = new JSONObject(pilot.getString(0));
                    jsono2 = new JSONObject(pilot.getString(1));
                    jsono3 = new JSONObject(pilot.getString(2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject geom1 = null;
                JSONObject geom2 = null;
                JSONObject geom3 = null;
                try {
                    geom1 = jsono1.getJSONObject("geometry");
                    geom2 = jsono2.getJSONObject("geometry");
                    geom3 = jsono3.getJSONObject("geometry");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray json1 = null;
                JSONArray json2 = null;
                JSONArray json3 = null;
                try {
                    json1 = geom1.getJSONArray("coordinates");
                    json2 = geom2.getJSONArray("coordinates");
                    json3 = geom3.getJSONArray("coordinates");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayList<String> listdata = new ArrayList<String>();

                if (json1 != null && json2 !=null && json3!=null) {

                        try {
                            listdata.add(json1.getString(0));
                            listdata.add(json2.getString(0));
                            listdata.add(json3.getString(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                //System.out.println(listdata.toString());
                //String[] array = listdata.toString().split("],");
                //listdata.toString().replaceAll("[\\[\\](){}]", "");
                String[] tokens = listdata.toString().replaceAll("[\\[\\](){}]", "").split("],", -1);
                //LatLng [] floatValues = new LatLng[tokens.length];
                ArrayList<LatLng> coords = new ArrayList<>();
                for (int i = 0; i < tokens.length; i++) {
                    //System.out.println(Float.parseFloat(tokens[i]));
                    String[] latlong = tokens[i].split(",");
                    double latitude = Double.parseDouble(latlong[0]);
                    double longitude = Double.parseDouble(latlong[1]);

                    double latitude1 = Double.parseDouble(latlong[24]);
                    double longitude1 = Double.parseDouble(latlong[25]);

                    double latitude2 = Double.parseDouble(latlong[14]);
                    double longitude2 = Double.parseDouble(latlong[15]);
                    LatLng obj = new LatLng(longitude, latitude);
                    LatLng obj1 = new LatLng(longitude1, latitude1);
                    LatLng obj2 = new LatLng(longitude2, latitude2);
                    coords.add(0, obj);
                    coords.add(1, obj1);
                    coords.add(2, obj2);

                }

                System.out.println(coords.get(0));
                System.out.println(coords.get(1));
                System.out.println(coords.get(2));



                if(position.zoom <= 18)
                {

                    isZooming = true;
                    layer1.addLayerToMap();
                    mMap.addMarker(new MarkerOptions().position(geography).title("University of Pretoria"));
                    if(result!=null) {

                    }

                    if (mLocation != null) {

                        location = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());

                    }
                    //onLocationChanged(mLocation);
                }else{

                 layer.addLayerToMap();
                    mMap.addMarker(new MarkerOptions().position(coords.get(0)).title("ROOM 1-2"));
                    mMap.addMarker(new MarkerOptions().position(coords.get(1)).title("ROOM 1-17"));
                    mMap.addMarker(new MarkerOptions().position(coords.get(2)).title("ROOM 1-11"));
                    mMap.addMarker(new MarkerOptions().position(geography).title("University of Pretoria"));
                }

                zoomLevel = position.zoom;
            }
        };
    }

    private Location getMyLocation() {


        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return TODO;
        }
        Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER );

        if (myLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            String provider = lm.getBestProvider(criteria, true);
            myLocation = lm.getLastKnownLocation(provider);
        }
        return myLocation;
    }
    private void drawPolygon() {
        PolygonOptions polygonOptions = new PolygonOptions(); // consider new options

        LatLng[] drawCoordinates = new LatLng[0];
        for (LatLng latLng : drawCoordinates) {
            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(latLng.toString()));
            marker.setVisible(false);

            polygonOptions.add(marker.getPosition()).strokeColor(Color.RED);
            polygonOptions.fillColor(Color.TRANSPARENT);
            polygonOptions.visible(true);
        }
        List<LatLng> points = polygonOptions.getPoints();
        if (!points.isEmpty()) {
            Polygon polygon = mMap.addPolygon(polygonOptions);
            Log.i("Poly lines", "Successfully added polyline on map");
        }
    }


    public String getGeoJSONHallObjects() throws InterruptedException {
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
                    HttpResponse httpResponse = httpclient.execute(new HttpGet("http://35.202.5.111:11080/NavUPRest/api/lecture-hall/get/geoJSON"));

                    // receive response as inputStream
                    inputStream = httpResponse.getEntity().getContent();

                    // convert inputstream to string
                    if (inputStream != null)
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
                    if (inputStream != null)
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

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public void drawPath() {

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
                    conn.setRequestProperty("Accept", "application/json");
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

                        System.out.println(data);

                        String[] temp;
                        String delimiter = ", ";

                        temp = data.split(delimiter);
                        X = temp[0];
                        Y = temp[1];

                        //System.out.println(X);
                        //System.out.println(Y);


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
                    conn.setRequestProperty("Accept", "application/json");
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

                        //System.out.println(data);

                        String[] temp;
                        String delimiter = ", ";

                        temp = data.split(delimiter);
                        endX = temp[0];
                        endY = temp[1];

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
                                    DateTime now = new DateTime();
                                    try {
                                        Double lat = location.latitude;
                                        Double lng = location.longitude;
                                        LatLng origin = new LatLng(lat,lng);

                                        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                                        String latitude =Double.toString(m.getPosition().latitude);
                                        String longitude =Double.toString(m.getPosition().longitude);
                                        System.out.println(latitude + longitude + "poes werk net");
                                        result = DirectionsApi.newRequest(getGeoContext()).mode(TravelMode.WALKING).origin(latitude+ "," + longitude).destination(endY + "," + endX).departureTime(now).await();
                                       // System.out.println("HERE" + result.routes[0].legs[0].startLocation.lat);
                                        //System.out.println("HERE" + result.routes[0].legs[0].startLocation.lng);
                                        addMarkersToMap(result, mMap);
                                        getEndLocationTitle(result);
                                        addPolyline(result, mMap);


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
        return geoApiContext.setQueryRateLimit(3).setApiKey("AIzaSyDjgvY-aqagM3mne_OyNo8eOZKrhmtqRLo").setConnectTimeout(1, TimeUnit.SECONDS).setReadTimeout(1, TimeUnit.SECONDS).setWriteTimeout(1, TimeUnit.SECONDS);
    }

    private void addMarkersToMap(DirectionsResult results, GoogleMap mMap) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[0].legs[0].startLocation.lat, results.routes[0].legs[0].startLocation.lng)).title(results.routes[0].legs[0].startAddress));
        mMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[0].legs[0].endLocation.lat, results.routes[0].legs[0].endLocation.lng)).title(results.routes[0].legs[0].startAddress).snippet(getEndLocationTitle(results)));
    }

    private String getEndLocationTitle(DirectionsResult results) {
        return "Time :" + results.routes[0].legs[0].duration.humanReadable + " Distance :" + results.routes[0].legs[0].distance.humanReadable;
    }

    private void addPolyline(DirectionsResult results, final GoogleMap mMap) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[0].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath).color(Color.RED));

    }


    private void initGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(Bundle bundle) {
                mLocationRequest = LocationRequest.create();
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                mLocationRequest.setInterval(1000);

                setLocationListener();
            }

            @Override
            public void onConnectionSuspended(int i) {
                Log.i("LOG_TAG", "onConnectionSuspended");
            }
        }).build();

        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();

    }

    private void setLocationListener() {
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String lat = String.valueOf(location.getLatitude());
                String lon = String.valueOf(location.getLongitude());
                Log.i("LOG_TAG", "Latitude = " + lat + " Longitude = " + lon);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, mLocationListener);
    }


    @Override
    public void onLocationChanged(Location location) {

        Log.i("called", "onLocationChanged");

        location = getMyLocation();

        //when the location changes, update the map by zooming to the location
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude()));
        //mMap.moveCamera(center);
        LatLng ltlng = new LatLng(location.getLatitude(),location.getLongitude());
        m.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
        if(result != null) {
            addPolyline(result, mMap);
            addMarkersToMap(result, mMap);
            getEndLocationTitle(result);
        }


        CameraUpdate zoom=CameraUpdateFactory.zoomTo(18);

    }
}




