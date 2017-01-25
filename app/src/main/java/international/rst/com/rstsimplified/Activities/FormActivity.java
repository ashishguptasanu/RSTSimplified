package international.rst.com.rstsimplified.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import international.rst.com.rstsimplified.R;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtDate1,edtDate2, edtIssue, edtExpiry;
    AlertDialog.Builder dialogBuilder;
    Context context;
    Button btnSavePref;
    String date1;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    AlertDialog b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        sharedPreferences = FormActivity.this.getPreferences(Context.MODE_PRIVATE);
        edtDate1 = (EditText)findViewById(R.id.edt_arrival);
        btnSavePref = (Button)findViewById(R.id.btn_save);
        btnSavePref.setOnClickListener(this);
        edtDate1.setOnClickListener(this);
        edtDate2 = (EditText)findViewById(R.id.edt_departure);
        edtDate2.setOnClickListener(this);
        edtIssue = (EditText)findViewById(R.id.edt_issue);
        edtIssue.setOnClickListener(this);
        edtExpiry = (EditText)findViewById(R.id.edt_expire);
        edtExpiry.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.edt_arrival:
                datePicker(edtDate1);
                break;
            case R.id.edt_departure:
                datePicker(edtDate2);
                break;
            case R.id.edt_issue:
                datePicker(edtIssue);
                break;
            case R.id.edt_expire:
                datePicker(edtExpiry);
                break;
            case R.id.btn_save:
                Toast.makeText(getApplicationContext(),"Your Submission has been saved",Toast.LENGTH_SHORT).show();
                sharedPreferences = FormActivity.this.getPreferences(Context.MODE_PRIVATE);
                date1 = edtDate1.getText().toString();
                sharedPreferences.edit().putString("arrival", date1).commit();




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

        /*edtDate1.setOnClickListener(new View.OnClickListener() {
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
        sharedPreferences = FormActivity.this.getPreferences(Context.MODE_PRIVATE);
        edtDate1.setText(sharedPreferences.getString("arrival", ""));


    }
}
