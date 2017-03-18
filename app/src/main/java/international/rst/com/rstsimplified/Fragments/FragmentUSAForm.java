package international.rst.com.rstsimplified.Fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import international.rst.com.rstsimplified.R;
public class FragmentUSAForm extends android.support.v4.app.Fragment {
    String title;
    View view;
    public static FragmentUSAForm newFormInstance( String title) {
        FragmentUSAForm fragmentUsaForm = new FragmentUSAForm();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentUsaForm.setArguments(args);
        return fragmentUsaForm;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        if(title.equalsIgnoreCase("form1")){
            view = inflater.inflate(R.layout.consult_form,container, false);
        }
        else if(title.equalsIgnoreCase("form2")){
            view = inflater.inflate(R.layout.contact_form,container,false);
        }
        else if(title.equalsIgnoreCase("form3")){
            view = inflater.inflate(R.layout.usa_form3,container,false);
        }
        else if(title.equalsIgnoreCase("form4")){
            view = inflater.inflate(R.layout.usa_form4,container,false);
        }
        else if(title.equalsIgnoreCase("form5")){
            view = inflater.inflate(R.layout.usa_form5,container,false);
        }
        else if(title.equalsIgnoreCase("form6")){
            view = inflater.inflate(R.layout.usa_form6,container,false);
        }
        else if(title.equalsIgnoreCase("form7")){
            view = inflater.inflate(R.layout.usa_form7,container,false);
        }
        else if(title.equalsIgnoreCase("form8")){
            view = inflater.inflate(R.layout.usa_form8,container,false);
        }
        else if(title.equalsIgnoreCase("form9")){
            view = inflater.inflate(R.layout.usa_form9,container,false);
        }
        else if(title.equalsIgnoreCase("form10")){
            view = inflater.inflate(R.layout.usa_form10,container,false);
        }
        else if(title.equalsIgnoreCase("form11")){
            view = inflater.inflate(R.layout.usa_form11,container,false);
        }
        else if(title.equalsIgnoreCase("form12")){
            view = inflater.inflate(R.layout.usa_form12,container,false);
        }
        else if(title.equalsIgnoreCase("form13")){
            view = inflater.inflate(R.layout.usa_form13,container,false);
        }
        else if(title.equalsIgnoreCase("form14")){
            view = inflater.inflate(R.layout.usa_form14,container,false);
        }
        return view;
    }
}
