package international.rst.com.rstsimplified.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import international.rst.com.rstsimplified.R;

/**
 * Created by ashish on 13/1/17.
 */

public class Fragment extends android.support.v4.app.Fragment{
    TextView tv1;
    String title;
    View view;

    public Fragment() {
    }
    public static Fragment newInstance(String title){
        Fragment fragment1 = new Fragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment1.setArguments(args);
        return fragment1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        tv1 = (TextView)view.findViewById(R.id.text1);
        title = getArguments().getString("title");
        if(title.equalsIgnoreCase("visa")){
            tv1.setText("Title: Visa Services");
        }
        else if(title.equalsIgnoreCase("hotel")){
            tv1.setText("Title: Hotel Services");
        }
        else {
            tv1.setText("Title: Airport Services");
        }


        return view;
    }

}
