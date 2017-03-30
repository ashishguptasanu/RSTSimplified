package international.rst.com.rstsimplified.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import international.rst.com.rstsimplified.R;


public class FragementUsaVisaType extends android.support.v4.app.Fragment{
    View view;
    String title;
    public FragementUsaVisaType() {
    }
    public static FragementUsaVisaType newFormInstance(String title) {
        FragementUsaVisaType fragmentUsaVisaType = new FragementUsaVisaType();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentUsaVisaType.setArguments(args);
        return fragmentUsaVisaType;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        Bundle bundle  = getArguments();
        if(title.equalsIgnoreCase("consult")){
            view = inflater.inflate(R.layout.fragment_usa_visa1, container, false);
        }
        return view;
    }
}
