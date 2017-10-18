package theinevitables.navup;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by dawie on 10/17/2017.
 */

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public static int position2;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

       position2 = pos;
        System.out.println("POS1" + position2);

    }



    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
