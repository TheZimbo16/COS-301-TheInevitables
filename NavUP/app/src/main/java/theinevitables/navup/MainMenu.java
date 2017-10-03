package theinevitables.navup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


    }

    public void goToLoginPage(View view){
        Intent nextPage = new Intent(MainMenu.this,Login.class);
        startActivity(nextPage);
    }

    public void goToNextDisabilityMenu(View view){
        Intent nextPage1 = new Intent(MainMenu.this,disability_menu.class);
        startActivity(nextPage1);
    }
}
