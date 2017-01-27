package international.rst.com.rstsimplified.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import international.rst.com.rstsimplified.Adapter.VisaAdapter;
import international.rst.com.rstsimplified.R;

/**
 * Created by Ashish on 27-01-2017.
 */

public class FragmentForm extends android.support.v4.app.Fragment {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        if (title.equalsIgnoreCase("visa")) {
            view = inflater.inflate(R.layout.fragment_form, container, false);

        }
        else {
            view = inflater.inflate(R.layout.fragment_form2,container,false);
        }
        return view;
    }
}
