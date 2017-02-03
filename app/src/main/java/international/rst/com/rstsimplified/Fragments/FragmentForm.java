package international.rst.com.rstsimplified.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import international.rst.com.rstsimplified.Activities.FormActivity;
import international.rst.com.rstsimplified.Adapter.VisaAdapter;
import international.rst.com.rstsimplified.R;


public class FragmentForm extends android.support.v4.app.Fragment {
    String title;
    View view;
    EditText edtDate1,edtDate2;


    public FragmentForm() {
    }
    public static FragmentForm newFormInstance(String title){
        FragmentForm fragmentForm = new FragmentForm();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentForm.setArguments(args);
        return fragmentForm;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        if (title.equalsIgnoreCase("consult")) {
            view = inflater.inflate(R.layout.fragment_form, container, false);
            edtDate1 = (EditText)view.findViewById(R.id.edt_arrival);
            edtDate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker(edtDate1);
                }
            });
            edtDate2 = (EditText)view.findViewById(R.id.edt_departure);
            edtDate2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker(edtDate2);
                }
            });

        }
        else {
            view = inflater.inflate(R.layout.fragment_form2,container,false);
        }
        return view;
    }

    private void datePicker(final EditText edtDate1) {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                edtDate1.setText(selectedday +"/"+(selectedmonth+1)+"/"+selectedyear);
            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }
}
