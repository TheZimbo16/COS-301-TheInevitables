package theinevitables.navup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dawie on 10/17/2017.
 */

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    List<Integer> flags;
    List<String> countryNames;
    LayoutInflater inflter;

    public SpinnerAdapter(Context applicationContext, List<Integer> flags, List<String> countryNames1) {
        this.context = applicationContext;
        this.flags = flags;
        countryNames = countryNames1;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_layout, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(flags.get(i));
        names.setText(countryNames.get(i));
        return view;
    }
}