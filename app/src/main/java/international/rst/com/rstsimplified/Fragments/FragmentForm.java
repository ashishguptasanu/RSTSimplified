package international.rst.com.rstsimplified.Fragments;

import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.checkout.CheckoutKit;
import com.checkout.exceptions.CardException;
import com.checkout.exceptions.CheckoutException;
import com.checkout.httpconnector.Response;
import com.checkout.models.Card;
import com.checkout.models.CardTokenResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import international.rst.com.rstsimplified.Activities.SummaryPage;
import international.rst.com.rstsimplified.Custom.CustomScrollView;
import international.rst.com.rstsimplified.Custom.CustomViewPager;
import international.rst.com.rstsimplified.Model.AllCountryResponse;
import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.Model.Emirate;
import international.rst.com.rstsimplified.Model.EmirateResponse;
import international.rst.com.rstsimplified.Model.Profession;
import international.rst.com.rstsimplified.Model.ProfessionRes;
import international.rst.com.rstsimplified.Model.ProfessionResponse;
import international.rst.com.rstsimplified.Model.Emirate_;
import international.rst.com.rstsimplified.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import java.util.Date;


public class FragmentForm extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final int FILE_SELECT_CODE = 3;
    String title, selectedProfession,selectedProfessionID, selectedIssueCountry, selectedGender, selectedReligion,selectedCountry, selectedEmirate, selectedPhoneCode, selectedMaritalStatus;
    View view;
    EditText edtDate1, edtDate2, expiryMonth, expiryYear, cardName, cardNumber, cardCvv, phoneCode;
    EditText nameFirst, nameLast, birthDate, birthPlace, emailEdt, nameFather, nameMother, dateIssue, dateExpiry,passportNumber, edtAddress, edtLivingCity, edtHotelAddress, edtEmergencyContactName, edtEmergencyContactNumber, edtIssuePlace, edtMobile;
    private  static String publicKey = "pk_test_73e56b01-8726-4176-9159-db71454ff4af";
    String[] gender, religion;
    Spinner spnrAllCountries, spnrIssueCountry,spnrGender,spnrReligion, spnrEmirates;
    private List<String> allCountriesData = new ArrayList<>();
    private List<CountryRes> allcountry = new ArrayList<>();
    private List<ProfessionRes> professionList = new ArrayList<>();
    private List<Emirate_> emirates = new ArrayList<>();
    private List<String> emiratesData = new ArrayList<>();
    private List<String> professionData = new ArrayList<>();
    private List<Integer> professionNumber = new ArrayList<>();
    int serverResponseCode = 0;
    CustomScrollView scrollView;
    final static int FILE_CODE = 3;
    RadioButton radioButton1, radioButton2;

    SharedPreferences sharedPreferences;
    public String nationalityID;
    public static String filePath;
    TextView tvPassportCopy;
    private OkHttpClient client = new OkHttpClient();
    AutoCompleteTextView profession;
    ArrayAdapter<String> adapter;
    String arrivalDate, departureDate, fullNameApplicant, birthDateApplicant, passportNumberApplicant,genderApplicant;
    int  selectedCountryID, selectedIssueCountryID, age, selectedEmirateId;
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
        loadProfession();
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getActivity());




    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        Bundle bundle  = getArguments();
        nationalityID = bundle.getString("nationality_id");
        if (title.equalsIgnoreCase("consult")) {
            view = inflater.inflate(R.layout.consult_form, container, false);

            edtDate1 = (EditText) view.findViewById(R.id.edt_arrival);
            edtDate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker(edtDate1);
                }
            });
            disableInput(edtDate1);

            edtDate2 = (EditText) view.findViewById(R.id.edt_departure);
            edtDate2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker(edtDate2);
                }
            });
            disableInput(edtDate2);

            if(sharedPreferences!=null){
                String arrivalDateTemp  = sharedPreferences.getString("arrival_date","");
                String departureDateTemp = sharedPreferences.getString("departure_date","");
                edtDate1.setText(arrivalDateTemp);
                edtDate2.setText(departureDateTemp);

            }

            //final EditText edtLivingIn = (EditText)view.findViewById(R.id.living_in);
            Button button1 = (Button)view.findViewById(R.id.button_consult);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtDate1.getText().toString().length() != 0 && edtDate2.getText().toString().length() != 0 ){
                        ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
                        int atTab = mFormPager.getCurrentItem();
                        mFormPager.setCurrentItem(atTab + 1);
                        //sendConsultData();
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        arrivalDate = edtDate1.getText().toString();
                        departureDate = edtDate2.getText().toString();
                        sharedPreferences.edit().putString("arrival_date", arrivalDate).apply();
                        sharedPreferences.edit().putString("departure_date", departureDate).apply();
                    }
                    else {
                        Toast.makeText(getContext(),"Enter all fields", Toast.LENGTH_SHORT).show();
                    }



                }
            });

        } else if (title.equalsIgnoreCase("applicant")) {
            view = inflater.inflate(R.layout.appicant_form, container, false);
            scrollView = (CustomScrollView)view.findViewById(R.id.scroll_view);
            CustomViewPager parentPager = (CustomViewPager)getActivity().findViewById(R.id.formViewPager);
            scrollView.setParentPager(parentPager);
            nameFirst = (EditText)view.findViewById(R.id.name_first);
            nameLast = (EditText)view.findViewById(R.id.name_last);
            birthDate = (EditText)view.findViewById(R.id.edt_dob);
            disableInput(birthDate);
            birthPlace = (EditText)view.findViewById(R.id.edt_place_birth);
            emailEdt = (EditText)view.findViewById(R.id.edittext_email);
            nameFather = (EditText)view.findViewById(R.id.name_father);
            nameMother = (EditText)view.findViewById(R.id.name_mother);
            dateIssue = (EditText)view.findViewById(R.id.edt_issue_date);
            disableInput(dateIssue);
            dateExpiry = (EditText)view.findViewById(R.id.edt_valid_till);
            disableInput(dateExpiry);
            passportNumber = (EditText)view.findViewById(R.id.edt_passport_number);
            edtIssuePlace = (EditText)view.findViewById(R.id.edt_issue_place);
            Button button2 = (Button)view.findViewById(R.id.button_applicant);
            profession = (AutoCompleteTextView)view.findViewById(R.id.auto_profession);
            edtMobile = (EditText)view.findViewById(R.id.edittext_mobile);
            phoneCode = (EditText)view.findViewById(R.id.phone_code);
            radioButton1 = (RadioButton)view.findViewById(R.id.rb_single);
            radioButton2 = (RadioButton)view.findViewById(R.id.rb_married);


            /*if(sharedPreferences != null){
                //visaId = sharedPreferences.getString("visa_id", "");
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                        visaId = sharedPreferences.getString("visa_id", "");
                        System.out.println(visaId);
                    }
                };
            //}
           // else{
               // Toast.makeText(getContext(),"No Data Available", Toast.LENGTH_SHORT).show();
            //}*/

            profession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selection = (String) adapterView.getItemAtPosition(i);
                    int pos = -1;
                    for (int j = 0; j < professionData.size(); j++) {
                        if (professionData.get(j).equals(selection)) {
                            pos = j;
                            break;
                        }
                    }
                    int id = professionNumber.get(pos);
                    selectedProfessionID = String.valueOf(id);
                    System.out.println("Position " + selectedProfessionID);
                    selectedProfession = adapter.getItem(i);
                }
            });
            birthDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar mcurrentDate=Calendar.getInstance();
                    final int mYear = mcurrentDate.get(Calendar.YEAR);
                    final int mMonth=mcurrentDate.get(Calendar.MONTH);
                    final int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            birthDate.setText(selectedyear +"-"+(selectedmonth+1)+"-"+selectedday);
                            age = mYear - selectedyear;
                            System.out.println(age);
                        }
                    },mYear, mMonth, mDay);

                    mDatePicker.setTitle("Select date");
                    mDatePicker.show();
                }
            });
            dateIssue.setOnClickListener(this);
            dateExpiry.setOnClickListener(this);

            loadAllCountries();
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(nameFirst.getText().toString().length() != 0 && nameLast.getText().toString().length() != 0 && birthDate.getText().toString().length() != 0 && passportNumber.getText().toString().length() != 0 && profession.getText().toString().length() != 0 && emailEdt.getText().toString().length() != 0 && birthPlace.getText().toString().length() != 0 && nameFather.getText().toString().length() != 0 && nameMother.getText().toString().length() != 0 && dateIssue.getText().toString().length() != 0 && dateExpiry.getText().toString().length() != 0){
                        if(isValidEmail(emailEdt.getText().toString())) {
                            if(isValidMobile(edtMobile.getText().toString())){
                                if(radioButton1.isChecked() || radioButton2.isChecked()){
                                    if (radioButton1.isChecked()){
                                        selectedMaritalStatus = "Single";
                                    }
                                    else{
                                        selectedMaritalStatus = "Married";
                                    }
                                    saveSharedPreferences();
                                    ViewPager mFormPager = (ViewPager) getActivity().findViewById(R.id.formViewPager);
                                    int atTab = mFormPager.getCurrentItem();
                                    mFormPager.setCurrentItem(atTab + 1);

                                }
                                else {
                                    showToast("Select marital status");
                                }
                            }
                            else{
                                showToast("Mobile Numeber is not valid");
                            }
                        }
                        else {
                            showToast("Email is not valid");
                        }
                    }
                    else {
                        showToast("Enter all Fields");
                    }

                    //sendFormData(nameFirst,nameLast,birthDate,birthPlace,selectedProfession,selectedProfessionID,emailEdt,nameFather,nameMother,dateIssue,dateExpiry);
                }
            });

                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, professionData);
                profession.setThreshold(1);
                profession.setAdapter(adapter);

        }  else if (title.equalsIgnoreCase("contact")){
            view = inflater.inflate(R.layout.contact_form,container,false);
            edtAddress = (EditText)view.findViewById(R.id.edt_current_address);
            edtHotelAddress = (EditText)view.findViewById(R.id.edt_hotel_address);
            edtEmergencyContactName = (EditText)view.findViewById(R.id.edt_contact_person);
            edtEmergencyContactNumber = (EditText)view.findViewById(R.id.edt_contact_number);
            edtLivingCity = (EditText)view.findViewById(R.id.living_city);
            loadEmirates();
            if(sharedPreferences!=null){
                String hotelAddress = sharedPreferences.getString("hotel_address","");
                String address = sharedPreferences.getString("current_address","");
                String emergencyName = sharedPreferences.getString("emergency_name","");
                String emergencyNumber = sharedPreferences.getString("emergency_number","");
                edtEmergencyContactName.setText(emergencyName);
                edtEmergencyContactNumber.setText(emergencyNumber);
                edtAddress.setText(address);
                edtHotelAddress.setText(hotelAddress);
                String city = sharedPreferences.getString("living_city","");
                edtLivingCity.setText(city);

            }
            Button button = (Button)view.findViewById(R.id.button_contact);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtAddress.getText().toString().length() != 0 && edtHotelAddress.getText().toString().length() != 0 && edtEmergencyContactName.getText().toString().length() != 0 && edtEmergencyContactNumber.getText().toString().length() != 0 && edtLivingCity.getText().toString().length() != 0){
                        if(isValidMobile(edtEmergencyContactNumber.getText().toString())) {
                            ViewPager mFormPager = (ViewPager) getActivity().findViewById(R.id.formViewPager);
                            int atTab = mFormPager.getCurrentItem();
                            mFormPager.setCurrentItem(atTab + 1);
                            //sendConsultData();
                            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                            sharedPreferences.edit().putString("current_address", edtAddress.getText().toString()).apply();
                            sharedPreferences.edit().putString("hotel_address", edtHotelAddress.getText().toString()).apply();
                            sharedPreferences.edit().putString("emergency_name", edtEmergencyContactName.getText().toString()).apply();
                            sharedPreferences.edit().putString("emergency_number", edtEmergencyContactNumber.getText().toString()).apply();
                            sharedPreferences.edit().putString("living_city", edtLivingCity.getText().toString()).apply();
                            sharedPreferences.edit().putString("selected_emirate", selectedEmirate).apply();
                            sharedPreferences.edit().putInt("selected_emirate_id", selectedEmirateId).apply();
                        }
                        else {
                            showToast("Mobile Number is not valid");
                        }
                    }
                    else {
                        showToast("Enter all Fields");
                    }
                }
            });


        }
        else if(title.equalsIgnoreCase("docs")){
            view = inflater.inflate(R.layout.docs_form, container, false);
            Button button3 = (Button)view.findViewById(R.id.button_docs);
            tvPassportCopy = (TextView)view.findViewById(R.id.doc1);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
                    int atTab = mFormPager.getCurrentItem();
                    mFormPager.setCurrentItem(atTab + 1);*/
                    Intent intent  = new Intent(getContext(), SummaryPage.class);
                    intent.putExtra("arrival_date",arrivalDate);
                    intent.putExtra("departure_date",departureDate);
                    intent.putExtra("full_name", fullNameApplicant);
                    intent.putExtra("birth_date",birthDateApplicant);
                    intent.putExtra("passport_number", passportNumberApplicant);
                    intent.putExtra("gender", genderApplicant);
                    startActivity(intent);
                }
            });
            ImageView imgV = (ImageView)view.findViewById(R.id.attach_file);
            imgV.setOnClickListener(new View.OnClickListener() {
                public static final int FLAG_GRANT_URI_WRITE_PERMISSION = 1 ;

                @Override
                public void onClick(View view) {
                    showFileChooser();
                }
            });

        }


        return view;
    }

    private void showToast(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    private void saveSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        fullNameApplicant = (nameFirst.getText().toString() + " " + nameLast.getText().toString());
        sharedPreferences.edit().putString("full_name", fullNameApplicant).apply();
        sharedPreferences.edit().putString("first_name", nameFirst.getText().toString()).apply();
        sharedPreferences.edit().putString("last_name", nameLast.getText().toString()).apply();
        birthDateApplicant = birthDate.getText().toString();
        sharedPreferences.edit().putString("birth_date", birthDateApplicant).apply();
        passportNumberApplicant = passportNumber.getText().toString();
        sharedPreferences.edit().putString("passport_number", passportNumberApplicant).apply();
        sharedPreferences.edit().putString("gender", selectedGender).apply();
        sharedPreferences.edit().putString("mobile", edtMobile.getText().toString()).apply();
        sharedPreferences.edit().putString("profession", selectedProfession).apply();
        sharedPreferences.edit().putString("profession_id", selectedProfessionID).apply();
        sharedPreferences.edit().putString("email", emailEdt.getText().toString()).apply();
        sharedPreferences.edit().putString("birth_place", birthPlace.getText().toString()).apply();
        sharedPreferences.edit().putString("father_name", nameFather.getText().toString()).apply();
        sharedPreferences.edit().putString("mother_name", nameMother.getText().toString()).apply();
        sharedPreferences.edit().putString("date_issue", dateIssue.getText().toString()).apply();
        sharedPreferences.edit().putString("date_expiry", dateExpiry.getText().toString()).apply();
        sharedPreferences.edit().putString("selected_country", selectedCountry).apply();
        sharedPreferences.edit().putString("selected_issue_country", selectedIssueCountry).apply();
        sharedPreferences.edit().putString("selected_religion", selectedReligion).apply();
        sharedPreferences.edit().putString("place_issue", edtIssuePlace.getText().toString()).apply();
        sharedPreferences.edit().putString("country_id", String.valueOf(selectedPhoneCode)).apply();
        sharedPreferences.edit().putInt("age",age).apply();
        sharedPreferences.edit().putString("marital_status",selectedMaritalStatus);
    }


    private void disableInput(EditText editText) {
        editText.setFocusable(false);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //String path = data.getStringExtra("path");
        //System.out.println(path);
        if (resultCode != RESULT_OK) return;
        //String path     = "";
        if(requestCode == FILE_SELECT_CODE)
        {
            Uri uri = data.getData();

            String FilePath = getPath(getContext(),uri);
            System.out.println(FilePath);

        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
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
                for(int i=0;i<allcountry.size();i++){
                    allCountriesData.add(allcountry.get(i).getName());
                }
                int currency = allcountry.get(13).getCurrencyId();
                String code = allcountry.get(13).getPhoneCode();

                intializeSpinners();


            }
            @Override
            public void onFailure(retrofit2.Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }

    private void intializeSpinners() {
        populateAllCountrySpinner();
        populateIssueCountrySpinner();
        populateGenderSpinner();
        populateReligionSpinner();
    }
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra("path",Environment.getExternalStorageDirectory().getPath());
        intent.setType("*/*");
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
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            filePath = uri.getPath();

            return uri.getPath();
        }


        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spnr_country:
                selectedCountryID = allcountry.get(i).getId();
                selectedCountry = allcountry.get(i).getName();
                selectedPhoneCode = ("+" + allcountry.get(i).getPhoneCode());
                if(selectedCountryID == 0){
                    phoneCode.setText("");
                }
                else {
                    phoneCode.setText(selectedPhoneCode);
                }
                break;
            case R.id.spnr_country_issue:
                selectedIssueCountryID = allcountry.get(i).getId();
                selectedIssueCountry = allcountry.get(i).getName();
                System.out.println(selectedIssueCountry);
                break;
            case R.id.spnr_gender:
                selectedGender = gender[i];
                break;
            case R.id.spnr_religion:
                selectedReligion = religion[i];
                break;
            case R.id.emirates:
                selectedEmirate = emiratesData.get(i);
                selectedEmirateId = i;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void datePicker(final EditText edtDate1) {
        Calendar mcurrentDate=Calendar.getInstance();
        final int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                edtDate1.setText(selectedyear +"-"+(selectedmonth+1)+"-"+selectedday);
            }
        },mYear, mMonth, mDay);

        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }
    private void loadProfession() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ProfessionResponse request = retrofit.create(ProfessionResponse.class);
        retrofit2.Call<Profession> call = request.getProfession();
        //
        call.enqueue(new retrofit2.Callback<Profession>() {
            @Override
            public void onResponse(retrofit2.Call<Profession> call, retrofit2.Response<Profession> response) {
                Profession jsonResponse = response.body();
                professionList = jsonResponse.getProfession();
                for(int i=0;i<professionList.size();i++){
                    professionData.add(professionList.get(i).getProfession());
                }
                for(int i=0;i<professionList.size();i++){
                    professionNumber.add(professionList.get(i).getProfessionNo());
                }
            }
            @Override
            public void onFailure(retrofit2.Call<Profession> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadEmirates(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmirateResponse request = retrofit.create(EmirateResponse.class);
        retrofit2.Call<Emirate> call = request.getEmirates();
        //
        call.enqueue(new retrofit2.Callback<Emirate>() {
            @Override
            public void onResponse(retrofit2.Call<Emirate> call, retrofit2.Response<Emirate> response) {
                Emirate jsonResponse = response.body();
                emirates = jsonResponse.getEmirates();
                for(int i=0;i<emirates.size();i++){
                    emiratesData.add(emirates.get(i).getName());
                }
                populateEmiratesSpinner();

                /*for(int i=0;i<professionList.size();i++){
                    professionNumber.add(professionList.get(i).getProfessionNo());
                }*/
            }
            @Override
            public void onFailure(retrofit2.Call<Emirate> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edt_dob:
                datePicker(birthDate);
                break;
            case R.id.edt_issue_date:
                datePicker(dateIssue);
                break;
            case R.id.edt_valid_till:
                datePicker(dateExpiry);
                break;
        }

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
    private void populateAllCountrySpinner() {
        spnrAllCountries = (Spinner)view.findViewById(R.id.spnr_country);
        spnrAllCountries.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapterCountries = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, allCountriesData);
        dataAdapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrAllCountries.setAdapter(dataAdapterCountries);
        spnrAllCountries.setOnItemSelectedListener(this);
    }
    private void populateIssueCountrySpinner() {
        spnrIssueCountry = (Spinner)view.findViewById(R.id.spnr_country_issue);
        spnrIssueCountry.setOnItemSelectedListener(this);
        ArrayAdapter<String> issueDataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, allCountriesData);
        issueDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrIssueCountry.setAdapter(issueDataAdapter);
    }
    private void populateGenderSpinner() {
        spnrGender = (Spinner)view.findViewById(R.id.spnr_gender);
        spnrGender.setOnItemSelectedListener(this);
        gender = new String[]{"Select One","Male","Female","Other"};
        ArrayAdapter<String> genderDataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, gender);
        genderDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrGender.setAdapter(genderDataAdapter);
    }
    private void populateReligionSpinner() {
        spnrReligion = (Spinner)view.findViewById(R.id.spnr_religion);
        spnrReligion.setOnItemSelectedListener(this);
        religion = new String[]{"Select One","Bahai","Buddhism","Christian","Hindu","Islam","Jainism","Judaism","Parsi","Sikh","Zoroastrian","Other"};
        ArrayAdapter<String> religionDataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, religion);
        religionDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrReligion.setAdapter(religionDataAdapter);
    }
    private void populateEmiratesSpinner() {
        spnrEmirates = (Spinner)view.findViewById(R.id.emirates);
        spnrEmirates.setOnItemSelectedListener(this);
        ArrayAdapter<String> emirateDataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, emiratesData);
        emirateDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrEmirates.setAdapter(emirateDataAdapter);
        if(sharedPreferences != null){
            int emirate = sharedPreferences.getInt("selected_emirate_id", 0);
            spnrEmirates.setSelection(emirate);
        }
    }
    private class UploadFileAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String sourceFileUri = "/document/Download.pdf";

                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                File sourceFile = new File(sourceFileUri);

                if (sourceFile.isFile()) {

                    try {
                        String upLoadServerUri = "\n" +
                                "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=upload";

                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(upLoadServerUri);

                        // Open a HTTP connection to the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE",
                                "multipart/form-data");
                        conn.setRequestProperty("Content-Type",
                                "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("additional_document_upload", sourceFileUri);

                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"additional_document_upload\";filename=\""
                                + sourceFileUri + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                        // create a buffer of maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                        System.out.println("Got the File");

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);

                        }

                        // send multipart form data necesssary after file
                        // data...
                        System.out.println("reading successful");
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);

                        // Responses from the server (code and message)
                        int serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn
                                .getResponseMessage();
                        System.out.println(serverResponseCode);

                        if (serverResponseCode == 200) {

                            // messageText.setText(msg);
                            System.out.println("OK");

                            // recursiveDelete(mDirectory1);

                        }

                        // close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();

                    } catch (Exception e) {

                        // dialog.dismiss();
                        e.printStackTrace();

                    }
                    // dialog.dismiss();

                } // End else block


            } catch (Exception ex) {
                // dialog.dismiss();

                ex.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("Server Closed");
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }



}
