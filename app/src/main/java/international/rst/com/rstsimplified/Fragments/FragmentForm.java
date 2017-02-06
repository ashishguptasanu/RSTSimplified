package international.rst.com.rstsimplified.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.checkout.CheckoutKit;
import com.checkout.exceptions.CardException;
import com.checkout.exceptions.CheckoutException;
import com.checkout.httpconnector.Response;
import com.checkout.models.Card;
import com.checkout.models.CardTokenResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import international.rst.com.rstsimplified.Model.AllCountryResponse;
import international.rst.com.rstsimplified.Model.CompleteCountry;
import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


public class FragmentForm extends android.support.v4.app.Fragment {
    private static final int FILE_SELECT_CODE =  0;
    String title;
    View view;
    EditText edtDate1, edtDate2, expiryMonth, expiryYear, cardName, cardNumber, cardCvv;
    EditText nameFirst, nameLast, birthDate, birthPlace, profession, emailEdt, nameFather, nameMother, dateIssue, dateExpiry;
    private  static String publicKey = "pk_test_73e56b01-8726-4176-9159-db71454ff4af";
    String response;
    private List<CountryRes> allcountry = new ArrayList<>();
    private OkHttpClient client = new OkHttpClient();
    private static final String BASE_URL_APLLICANT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data";
    private static final String BASE_URL_CONSULT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data_tab_2";


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
                        sendCunsultData();
                    }
                    else{
                        Toast.makeText(getContext(),"OOps! Enter all value..",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        } else if (title.equalsIgnoreCase("applicant")) {
            view = inflater.inflate(R.layout.appicant_form, container, false);
            nameFirst = (EditText)view.findViewById(R.id.name_first);
            nameLast = (EditText)view.findViewById(R.id.name_last);
            birthDate = (EditText)view.findViewById(R.id.edt_dob);
            birthPlace = (EditText)view.findViewById(R.id.edt_place_birth);
            profession = (EditText)view.findViewById(R.id.edt_profession);
            emailEdt = (EditText)view.findViewById(R.id.edittext_email);
            nameFather = (EditText)view.findViewById(R.id.name_father);
            nameMother = (EditText)view.findViewById(R.id.name_mother);
            dateIssue = (EditText)view.findViewById(R.id.edt_issue_date);
            dateExpiry = (EditText)view.findViewById(R.id.edt_valid_till);
            Button button2 = (Button)view.findViewById(R.id.button_applicant);
            loadAllCountries();
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
                    int atTab = mFormPager.getCurrentItem();
                    mFormPager.setCurrentItem(atTab + 1);
                    sendFormData(nameFirst,nameLast,birthDate,birthPlace,profession,emailEdt,nameFather,nameMother,dateIssue,dateExpiry);

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
            ImageView imgV = (ImageView)view.findViewById(R.id.attach_file);
            imgV.setOnClickListener(new View.OnClickListener() {
                public static final int FLAG_GRANT_URI_WRITE_PERMISSION = 1 ;

                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    intent.setFlags(FLAG_GRANT_URI_WRITE_PERMISSION);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);

                    try {
                        startActivityForResult(
                                Intent.createChooser(intent, "Select a File to Upload"),
                                FILE_SELECT_CODE);
                    } catch (android.content.ActivityNotFoundException ex) {
                        // Potentially direct the user to the Market with a Dialog
                        Toast.makeText(getContext(), "Please install a File Manager.",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        return view;
    }

    private void sendCunsultData() {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", "Testing")
                .addFormDataPart("start_date", "Testing")
                .addFormDataPart("end_date", "Testing")
                .addFormDataPart("pnrNo", "Testing")
                .addFormDataPart("address1", "Testing")
                .addFormDataPart("address2", "Testing")
                .addFormDataPart("city", "Testing")
                .addFormDataPart("country", "Testing")
                .addFormDataPart("country_code", "Testing")
                .addFormDataPart("mobile", "Testing")
                .addFormDataPart("emirates_uae", "Testing")
                .addFormDataPart("emergency_contact_name", "Testing")
                .addFormDataPart("emergency_contact_number", "Testing")
                .addFormDataPart("hotel_address", "Testing")
                .addFormDataPart("contact_uae", "Testing")
                .addFormDataPart("created_date", "Testing")
                .addFormDataPart("order_id", "Testing")
                .addFormDataPart("service_type", "Testing")
                .addFormDataPart("nationality_id", "Testing")
                .addFormDataPart("living_in_id", "Testing")
                .addFormDataPart("currency_id", "Testing")
                .addFormDataPart("govt_fee", "Testing")
                .addFormDataPart("service_fee", "Testing")
                .addFormDataPart("processing_time", "Testing")
                .addFormDataPart("visa_type_id", "Testing")
                .addFormDataPart("email_id", "Testing")
                .addFormDataPart("email_varified", "Testing")
                .addFormDataPart("comments_added", "Testing")
                .addFormDataPart("insertedTimeIst", "Testing")
                .addFormDataPart("agentid", "Testing")
                .addFormDataPart("service_fee_cs", "Testing")
                .addFormDataPart("termConditions", "Testing")
                .addFormDataPart("mng_fee", "Testing")
                .addFormDataPart("application_type", "Testing")
                .addFormDataPart("agentType", "Testing")
                .addFormDataPart("device_type", "Testing")
                .addFormDataPart("device_os", "Testing")
                .build();
        Request request = new Request.Builder().url(BASE_URL_CONSULT_FORM).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    String resp = response.body().string();
//                    Log.v(TAG_REGISTER, resp);
                    System.out.println(resp);
                    if (response.isSuccessful()) {
                        //sharedPreferences.edit().putString("Device ID", deviceID).apply();
                        //sharedPreferences.edit().putString("Android ID",androidID).apply();
                    } else {

                    }
                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }

    private void loadAllCountries() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AllCountryResponse request = retrofit.create(AllCountryResponse.class);
        retrofit2.Call<Country> call = request.getCountry();
        //
        call.enqueue(new retrofit2.Callback<Country>() {
            @Override
            public void onResponse(retrofit2.Call<Country> call, retrofit2.Response<Country> response) {


                Country jsonResponse = response.body();
                allcountry = jsonResponse.getCountry();
                System.out.println(allcountry.size());
                //populateNationalitySpinner();


            }
            @Override
            public void onFailure(retrofit2.Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    // Get the path
                    String path = null;
                    //path = getPath(getContext(), uri);
                    path = uri.toString();
                    Log.d(TAG, "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;
            File myFile = new File(uri.getPath());
            String path = myFile.getAbsolutePath();
            System.out.println(path);

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {

            return uri.getScheme();

        }

        return null;
    }

    private void sendFormData(EditText nameFirst, EditText nameLast, EditText birthDate, EditText birthPlace, EditText profession, EditText emailEdt, EditText nameFather, EditText nameMother, EditText dateIssue, EditText dateExpiry) {

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("visa_id", nameFirst.getText().toString())
                    .addFormDataPart("first_name", nameFirst.getText().toString())
                    .addFormDataPart("last_name", nameLast.getText().toString())
                    .addFormDataPart("gender", nameLast.getText().toString())
                    .addFormDataPart("date_of_birth", birthDate.getText().toString())
                    .addFormDataPart("birth_place", nameFather.getText().toString())
                    .addFormDataPart("birth_country", birthPlace.getText().toString())
                    .addFormDataPart("religion", birthDate.getText().toString())
                    .addFormDataPart("email", emailEdt.getText().toString())
                    .addFormDataPart("fathers_name", nameFather.getText().toString())
                    .addFormDataPart("mothers_name", nameMother.getText().toString())
                    .addFormDataPart("marital_status", birthDate.getText().toString())
                    .addFormDataPart("passport_number", birthPlace.getText().toString())
                    .addFormDataPart("place_of_issue", emailEdt.getText().toString())
                    .addFormDataPart("country_of_issue", nameFather.getText().toString())
                    .addFormDataPart("passport_issue_date", dateIssue.getText().toString())
                    .addFormDataPart("passport_expiry_date", dateExpiry.getText().toString())
                    .addFormDataPart("profession_id", nameMother.getText().toString())
                    .addFormDataPart("insertedTimeIST", dateIssue.getText().toString())
                    .addFormDataPart("visa_status", dateExpiry.getText().toString())
                    .addFormDataPart("service_type", nameFirst.getText().toString())
                    .addFormDataPart("visaRenewalDate", nameLast.getText().toString())
                    .addFormDataPart("sponserName", birthDate.getText().toString())
                    .addFormDataPart("arrivingFrom", birthPlace.getText().toString())
                    .addFormDataPart("otherProfession", emailEdt.getText().toString())
                    .addFormDataPart("port_arrival", nameFather.getText().toString())
                    .addFormDataPart("age", nameMother.getText().toString())
                    .addFormDataPart("person_type", dateIssue.getText().toString())
                    .addFormDataPart("sponserType", dateExpiry.getText().toString())
                    .addFormDataPart("sponserNationality", birthPlace.getText().toString())
                    .addFormDataPart("sponserAdd", emailEdt.getText().toString())
                    .addFormDataPart("sponserRelation", nameFather.getText().toString())
                    .addFormDataPart("sponserCompanyContact", nameMother.getText().toString())
                    .build();
            Request request = new Request.Builder().url(BASE_URL_APLLICANT_FORM).post(requestBody).build();
            okhttp3.Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("Registration Error" + e.getMessage());
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {

                    try {
                        String resp = response.body().string();
//                    Log.v(TAG_REGISTER, resp);
                        System.out.println(resp);
                        if (response.isSuccessful()) {
                            //sharedPreferences.edit().putString("Device ID", deviceID).apply();
                            //sharedPreferences.edit().putString("Android ID",androidID).apply();
                        } else {

                        }
                    } catch (IOException e) {
                        // Log.e(TAG_REGISTER, "Exception caught: ", e);
                        System.out.println("Exception caught" + e.getMessage());
                    }
                }

            });



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
