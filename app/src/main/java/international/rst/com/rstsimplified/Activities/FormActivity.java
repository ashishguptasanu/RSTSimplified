package international.rst.com.rstsimplified.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import international.rst.com.rstsimplified.Custom.CustomViewPager;
import international.rst.com.rstsimplified.Custom.OnSwipeTouchListener;
import international.rst.com.rstsimplified.Fragments.FragmentForm;
import international.rst.com.rstsimplified.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FormActivity extends AppCompatActivity {

    EditText edtDate1,edtDate2, edtIssue, edtExpiry;
    AlertDialog.Builder dialogBuilder;
    Context context;
    Button btnSavePref;
    String livingID, nationalityID, serviceFeeCs,serviceType;
    public int visaTypeId, currencyId;
    float govtFee, mngFee, serviceFee;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    CustomViewPager mFormPager;
    AlertDialog b;
    PagerTabStrip mTabStrip;
    PagerAdapter mFormPagerAdapter;
    String[] tabDataSet;

    int atTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Bundle bundle =  getIntent().getExtras();
        if ( bundle!= null && bundle.containsKey("living_id")){
            livingID = bundle.getString("living_id");
            nationalityID = bundle.getString("nationality_id");
            serviceType = bundle.getString("service_type");
            serviceFeeCs = bundle.getString("service_fee_cs");
            mngFee = bundle.getFloat("mng_fee");
            visaTypeId = bundle.getInt("visa_type_id");
            currencyId = bundle.getInt("currency_id");
            serviceFee = bundle.getFloat("service_fee");
            govtFee  = bundle.getFloat("govt_fee");
        }
        System.out.println("Living ID:" + livingID);
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString("service_type", serviceType);
        args.putString("nationality_id", nationalityID);
        args.putString("living_id", livingID);
        args.putString("service_fee_cs", serviceFeeCs);
        args.putFloat("govt_fee",govtFee);
        args.putFloat("service_fee",serviceFee);
        args.putFloat("mng_fee",mngFee);
        args.putInt("visa_type_id", visaTypeId);
        args.putInt("currency_id", currencyId);
        fragment.setArguments(args);
        /*sharedPreferences = FormActivity.this.getPreferences(Context.MODE_PRIVATE);
        edtDate1 = (EditText)findViewById(R.id.edt_arrival);
        btnSavePref = (Button)findViewById(R.id.btn_save);
        btnSavePref.setOnClickListener(this);
        edtDate1.setOnClickListener(this);
        edtDate2 = (EditText)findViewById(R.id.edt_departure);
        edtDate2.setOnClickListener(this);
        edtIssue = (EditText)findViewById(R.id.edt_issue_date);
        edtIssue.setOnClickListener(this);
        edtExpiry = (EditText)findViewById(R.id.edt_expiry_date);
        edtExpiry.setOnClickListener(this);

        */

        mTabStrip = (PagerTabStrip)findViewById(R.id.tab_strip);
        mTabStrip.setTabIndicatorColorResource(R.color.colorAccent);
        tabDataSet = new String[]{"Consult Form","Applicant Form","Upload Documents","Payment"};

        mFormPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mFormPager = (CustomViewPager) findViewById(R.id.formViewPager);
        mFormPager.setAdapter(mFormPagerAdapter);

        mFormPager.setOnTouchListener(new OnSwipeTouchListener(FormActivity.this) {

            public void onSwipeRight() {
                atTab = mFormPager.getCurrentItem();
                if(atTab == 1 || atTab == 2 || atTab == 3){
                    mFormPager.setCurrentItem(atTab - 1);
                }

            }
            public void onSwipeLeft() {

            }

        });



    }
    /*
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.edt_arrival:
                datePicker(edtDate1);
                break;
            case R.id.edt_departure:
                datePicker(edtDate2);
                break;
            case R.id.edt_issue_date:
                datePicker(edtIssue);
                break;
            case R.id.edt_expiry_date:
                datePicker(edtExpiry);
                break;
            case R.id.btn_save:
                Toast.makeText(getApplicationContext(),"Your Submission has been saved",Toast.LENGTH_SHORT).show();
                sharedPreferences = FormActivity.this.getPreferences(Context.MODE_PRIVATE);
                date1 = edtDate1.getText().toString();
                date2 = edtDate2.getText().toString();
                sharedPreferences.edit().putString("arrival", date1).apply();
                sharedPreferences.edit().putString("departure",date2).apply();




        }
    }

    private void datePicker(final EditText edtDate1) {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(FormActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                edtDate1.setText(selectedday +"/"+(selectedmonth+1)+"/"+selectedyear);
            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    /*private void openDialogConsultForm() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.applicantform, null);
        dialogBuilder.setTitle("Consult Form");
        dialogBuilder.setOnDismissListener(null);
        dialogBuilder.setIcon(R.mipmap.visa_icon);

        dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                imgView1.setImageResource(R.mipmap.checked_green);
                progressBar.setProgress(20);
                tv_pro.setText("1 of 5");
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                imgView1.setImageResource(R.mipmap.checked_gray);
                progressBar.setProgress(0);
                tv_pro.setText("0 of 5");
            }
        });
        dialogBuilder.setView(dialogView);
        b = dialogBuilder.create();
        b.show();
    }

    private void openDialogVisaType() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.visa_type_dialog, null);

        edtDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(FormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                         //Your code   to get date and time//
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


        dialogBuilder.setTitle("Select Visa Type");
        dialogBuilder.setOnDismissListener(null);
        dialogBuilder.setIcon(R.mipmap.visa_icon);

        dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                imgView1.setImageResource(R.mipmap.checked_green);
                progressBar.setProgress(20);
                tv_pro.setText("1 of 5");
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                imgView1.setImageResource(R.mipmap.checked_gray);
                progressBar.setProgress(0);
                tv_pro.setText("0 of 5");
            }
        });
        dialogBuilder.setView(dialogView);
        b = dialogBuilder.create();
        b.getWindow().setTitleColor(R.drawable.oman);
        b.show();
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        /*
        sharedPreferences = FormActivity.this.getPreferences(Context.MODE_PRIVATE);
        edtDate1.setText(sharedPreferences.getString("arrival", ""));
        edtDate2.setText(sharedPreferences.getString("departure",""));
        */


    }
    public static class PlaceholderFragment extends android.support.v4.app.Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newFormInstance(int sectionNumber, String name ) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString("name", name);

            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.consult_form, container, false);
            return rootView;
        }
    }
    public class PagerAdapter extends FragmentPagerAdapter {
        int[] selectors = {R.drawable.selector_tab_one, R.drawable.selector_tab_two, R.drawable.selector_tab_three,R.drawable.selector_tab_four};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FragmentForm.newFormInstance("consult");
                case 1:
                    return FragmentForm.newFormInstance("applicant");
                case 2:
                    return FragmentForm.newFormInstance("docs");
                case 3:
                    return FragmentForm.newFormInstance("payment");
                default:
                    return  PlaceholderFragment.newFormInstance(1, "");
            }
        }

        /*public void setCustomIconsFortabs(){
            for (int i=0; i< getCount(); i++){
                tabLayout.getTabAt(i).setIcon(selectors[i]);
            }
        }*/
        @Override
        public CharSequence getPageTitle(int position) {
            return tabDataSet[position];
        }


        @Override
        public int getCount() {
            return 4;
        }
    }

}
