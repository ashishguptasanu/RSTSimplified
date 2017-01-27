package international.rst.com.rstsimplified.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import international.rst.com.rstsimplified.Adapter.VisaAdapter;

/**
 * Created by Ashish on 27-01-2017.
 */

public class FragmentForm extends android.support.v4.app.Fragment {
    TextView tv1;
    String title;
    View view;


    public FragmentForm() {
    }
    public static FragmentForm newFormInstance(String title){
        FragmentForm fragmentForm = new FragmentForm();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentForm.setArguments(args);
        return fragmentForm;
    }
}
