package international.rst.com.rstsimplified.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.checkout.CheckoutKit;
import com.checkout.exceptions.CardException;
import com.checkout.exceptions.CheckoutException;
import com.checkout.httpconnector.Response;
import com.checkout.models.Card;
import com.checkout.models.CardTokenResponse;

import java.io.IOException;

import international.rst.com.rstsimplified.Activities.PaymentGateway;
import international.rst.com.rstsimplified.R;


public class FragmentForm extends android.support.v4.app.Fragment {
    String title;
    View view;
    EditText edtDate1, edtDate2, expiryMonth, expiryYear, cardName, cardNumber, cardCvv;
    private  static String publicKey = "pk_test_73e56b01-8726-4176-9159-db71454ff4af";
    String response;


    public FragmentForm() {
    }

    public static FragmentForm newFormInstance(String title) {
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
            view = inflater.inflate(R.layout.consult_form, container, false);
            edtDate1 = (EditText) view.findViewById(R.id.edt_arrival);
            edtDate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker(edtDate1);
                }
            });
            edtDate2 = (EditText) view.findViewById(R.id.edt_departure);
            edtDate2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker(edtDate2);
                }
            });
            final EditText edtLivingIn = (EditText)view.findViewById(R.id.living_in);
            Button button1 = (Button)view.findViewById(R.id.button_consult);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
                    if(edtLivingIn.getText().toString().length() != 0){
                        int atTab = mFormPager.getCurrentItem();
                        mFormPager.setCurrentItem(atTab + 1);
                    }
                    else{
                        Toast.makeText(getContext(),"OOps! Enter all value..",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        } else if (title.equalsIgnoreCase("applicant")) {
            view = inflater.inflate(R.layout.appicant_form, container, false);
            Button button2 = (Button)view.findViewById(R.id.button_applicant);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
                    int atTab = mFormPager.getCurrentItem();
                    mFormPager.setCurrentItem(atTab + 1);

                }
            });
        }  else if (title.equalsIgnoreCase("payment")){
            view = inflater.inflate(R.layout.payment_form,container,false);
            expiryMonth  = (EditText)view.findViewById(R.id.card_month);
            expiryYear = (EditText)view.findViewById(R.id.card_year);
            cardName = (EditText)view.findViewById(R.id.card_name);
            cardNumber = (EditText)view.findViewById(R.id.card_number);
            cardCvv = (EditText)view.findViewById(R.id.card_cvv);
            Button btnSubmit = (Button)view.findViewById(R.id.button_payment);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(expiryMonth.getText().toString().length() != 0 && expiryYear.getText().toString().length() != 0 && cardName.getText().toString().length() != 0 && cardCvv.getText().toString().length() != 0 && cardNumber.getText().toString().length() != 0 ){
                        try {
                            new ConnectionTask().execute("");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(getContext(),"OOps! Enter all value..",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else if(title.equalsIgnoreCase("docs")){
            view = inflater.inflate(R.layout.docs_form, container, false);
            Button button3 = (Button)view.findViewById(R.id.button_docs);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
                    int atTab = mFormPager.getCurrentItem();
                    mFormPager.setCurrentItem(atTab + 1);
                }
            });
        }

        return view;
    }

    class ConnectionTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            /* All the fields that need to be filled with the card information */


            try {
                /* Create the card object */

                Card card = new Card(cardNumber.getText().toString(), cardName.getText().toString(), expiryMonth.getText().toString(), expiryYear.getText().toString(), cardCvv.getText().toString());
                /* Create the CheckoutKit instance */
                CheckoutKit ck = CheckoutKit.getInstance(publicKey);


                final Response<CardTokenResponse> resp = ck.createCardToken(card);
                if (resp.hasError) {
                    /* Handle errors */
                } else {
                    /* The card token */
                    String cardToken = resp.model.getCardToken();
                }
            } catch (final CardException | CheckoutException e1) {
                /* Handle validation errors */

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getContext(),"Payment Unsuccessful",Toast.LENGTH_SHORT);
        }
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
