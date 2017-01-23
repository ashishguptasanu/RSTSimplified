package international.rst.com.rstsimplified.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import international.rst.com.rstsimplified.R;

import static java.security.AccessController.getContext;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgView1, imgView2, imgView3, imgView4,imgView5;
    TextView tv1, tv2, tv3, tv4, tv5, tv_pro;
    ProgressBar progressBar;
    AlertDialog.Builder dialogBuilder;
    AlertDialog b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        tv1 = (TextView)findViewById(R.id.tv_visa_type);
        tv1.setOnClickListener(this);
        tv2 = (TextView)findViewById(R.id.tv_consult_form);
        tv2.setOnClickListener(this);
        tv3 = (TextView)findViewById(R.id.tv_applicant_form);
        tv3.setOnClickListener(this);
        tv4 = (TextView)findViewById(R.id.tv_upload_docs);
        tv4.setOnClickListener(this);
        tv5 = (TextView)findViewById(R.id.tv_payment);
        tv5.setOnClickListener(this);
        tv_pro = (TextView)findViewById(R.id.tv_progress);
        tv_pro.setText("0 of 5");
        imgView1 = (ImageView)findViewById(R.id.imgv_visa_type);
        imgView2 = (ImageView)findViewById(R.id.imgv_consult);
        imgView3 = (ImageView)findViewById(R.id.imgv_applicant);
        imgView4 = (ImageView)findViewById(R.id.imgv_docs);
        imgView5 = (ImageView)findViewById(R.id.imgv_payment);
        progressBar = (ProgressBar)findViewById(R.id.progress_form);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_visa_type:

                openDialogVisaType();
                break;
            case R.id.tv_consult_form:
                imgView2.setImageResource(R.mipmap.checked_green);
                progressBar.setProgress(40);
                tv_pro.setText("2 of 5");
                break;
            case R.id.tv_applicant_form:
                imgView3.setImageResource(R.mipmap.checked_green);
                progressBar.setProgress(60);
                tv_pro.setText("3 of 5");
                break;
            case R.id.tv_upload_docs:
                imgView4.setImageResource(R.mipmap.checked_green);
                progressBar.setProgress(80);
                tv_pro.setText("4 of 5");
                break;
            case R.id.tv_payment:
                imgView5.setImageResource(R.mipmap.checked_green);
                progressBar.setProgress(100);
                tv_pro.setText("5 of 5");
                break;
        }
    }

    private void openDialogVisaType() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.visa_type_dialog, null);
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
    }
}
