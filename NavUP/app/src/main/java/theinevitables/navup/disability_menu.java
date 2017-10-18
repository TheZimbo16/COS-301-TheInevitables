package theinevitables.navup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class disability_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disability_menu);


    }

    public void goToNextPage(View view){
        Intent nextPage = new Intent(disability_menu.this,disability_navigation.class);
        startActivity(nextPage);
    }

    public void goToLoginPage(View view){
        Intent nextPage = new Intent(disability_menu.this,Login.class);
        startActivity(nextPage);
    }

    public void goToNextDisabilityMenu(View view){
        Intent nextPage1 = new Intent(disability_menu.this,disability_menu.class);
        startActivity(nextPage1);
    }

    public void goToMap(View view){
        Intent nextPage1 = new Intent(disability_menu.this,MapsActivity.class);
        startActivity(nextPage1);
    }

    public void goToRecentVisitedLocations(View view){
        Intent nextPage1 = new Intent(disability_menu.this,recentLocations.class);
        startActivity(nextPage1);
    }

    public void goToPOI(View view){
        Intent nextPage1 = new Intent(disability_menu.this,POI.class);
        startActivity(nextPage1);
    }

    public void goToRegistration(View view){
        Intent nextPage1 = new Intent(disability_menu.this,registration.class);
        startActivity(nextPage1);
    }
}
