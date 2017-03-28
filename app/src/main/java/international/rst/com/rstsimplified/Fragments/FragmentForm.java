package international.rst.com.rstsimplified.Fragments;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import international.rst.com.rstsimplified.Activities.SummaryPage;
import international.rst.com.rstsimplified.Model.AllCountryResponse;
import international.rst.com.rstsimplified.Model.ArrivalPort;
import international.rst.com.rstsimplified.Model.Consulate;
import international.rst.com.rstsimplified.Model.ConsulateInterface;
import international.rst.com.rstsimplified.Model.ConsulateResponse;
import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.Model.Emirate;
import international.rst.com.rstsimplified.Model.EmirateResponse;
import international.rst.com.rstsimplified.Model.Port;
import international.rst.com.rstsimplified.Model.Portofarrival;
import international.rst.com.rstsimplified.Model.Profession;
import international.rst.com.rstsimplified.Model.ProfessionRes;
import international.rst.com.rstsimplified.Model.ProfessionResponse;
import international.rst.com.rstsimplified.Model.Emirate_;
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

import java.util.Objects;


public class FragmentForm extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    View view;
    String livingInId, nationalityId, selectedGCC, selectedPort, serviceType, livingId, serviceFeeCs, processingTime, deviceType, deviceOS, fullNameVisa, title, selectedProfession,selectedProfessionID, selectedIssueCountry, selectedGender, selectedReligion,selectedCountry, selectedEmirate, selectedPhoneCode, selectedMaritalStatus, fileName, selectedFile1, selectedFile2,selectedFile3,selectedFile4,selectedFile5,selectedFile6, resp, arrivalDate, departureDate, fullNameApplicant, birthDateApplicant, passportNumberApplicant, savedArrivalDate, savedDepartureDate, selectedDuration, selectedPurpose, nationalityID, livingIn, codePhone;
    String[] gender, religion, gccList, addressType, purpose, duration, visaType, marital;
    ImageView attachFile1, attachFile2, attachFile3, attachFile4, attachFile5, attachFile6, checked1, checked2, checked3, checked4, checked5, checked6;
    EditText edtDate1, edtDate2, expiryMonth, expiryYear, cardName, cardNumber, cardCvv, phoneCode, livingInEdt, phoneCodeEdt, latestDate, edtSponsorName, edtSponsorAddress, edtSponsorCotact, edtEmailContact, edtOtherName, edtOtherAddress, edtOtherStart, edtOtherEnd, edtBorderEntry, edtNoPerson, edtDurationStay, noVisitIran, purposeVisitIran, lastVisitIran, visaNoIran, visitDateIran, otherCountryVisited,nameFirst, nameLast, birthDate, birthPlace, emailEdt, nameFather, nameMother, dateIssue, dateExpiry,passportNumber, edtAddress, edtLivingCity, edtHotelAddress, edtEmergencyContactName, edtEmergencyContactNumber, edtIssuePlace, edtMobile, addressSingapore, phoneSingapore, nameSingapore, nricSingapore, highestQualification, occupation, jobTitle, companyName, acticvityField, companyTelephone, previousNationality, firstNameUsa, lastNameUsa, emailUsa, phoneUsa, arrivalDateUsa, departureDateUsa, nationalityUsa, addressApplicantUsa, cityApplicantUsa, placeBirthUsa, stateUsa, postalUsa, countryUsa, passportNumberUsa, cityIssuedUsa, countryIssuedUsa, issueDateUsa, expiryDateUsa, phoneCodeUsa;
    TextInputLayout inputLayoutQualification, inputRace, inputOccupation, inputArrival, inputDeparture, previousNation;
    CardView cardDocs5, cardDocs6;
    Spinner spnrAllCountries, spnrIssueCountry,spnrGender,spnrReligion, spnrEmirates, spnrGCC, spnrPort, spnrAddressTypeSingapore, spnrDuration, spnrPurpose, spnrVisaFor, spnrApplyingFrom, spnrMaritalUsa;
    RadioButton radioButton1, radioButton2, singaporeRb1, singaporeRb2, singaporeRb3, singaporeRb4, singaporeRb5,singaporeRb6,singaporeRb7,singaporeRb8,singaporeRb9,singaporeRb10, radioButtonIran1, radioButtonIran2;
    RadioGroup radioGrpSingapore1, radioGrpSingapore2, radioGrpSingapore3, radioGrpSingapore4, radioGrpSingapore5, radioGroupIran;
    LinearLayout sponsorLayout, gccLayout, addressUaeLayout, addressSingaporeLayout,passportLayoutSingapore, otherCountrySingaporeLayout, layoutDuration, layoutPurpose, layoutIranConsult, layoutCompanyDetails, visitedIranLayout, visitedIranInfoLayout, layoutContactOther, layoutContactUsa, layoutApplicantOther, layoutApplicantUsa;
    Button button2, button3;
    SharedPreferences sharedPreferences;
    TextView  name1, name2, name3, name4, name5, name6;
    AutoCompleteTextView profession;
    ArrayAdapter<String> adapter;
    int visaTypeId, selectedVisaId, selectedAddressType, selectedConsulateId, selectedCountryID, selectedIssueCountryID, age, selectedEmirateId;
    float govtFee, mngFee, serviceFee;
    private OkHttpClient client = new OkHttpClient();
    private List<String> allCountriesData = new ArrayList<>();
    private List<CountryRes> allcountry = new ArrayList<>();
    private List<ProfessionRes> professionList = new ArrayList<>();
    private List<Emirate_> emirates = new ArrayList<>();
    private List<ConsulateResponse> consulate = new ArrayList<>();
    private List<Portofarrival> ports = new ArrayList<>();
    private List<String> arrivalPortData = new ArrayList<>();
    private List<String> emiratesData = new ArrayList<>();
    private List<String> professionData = new ArrayList<>();
    private List<String> consulateData = new ArrayList<>();
    private List<Integer> professionNumber = new ArrayList<>();
    private static final String BASE_URL_CONSULT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data_tab_2";
    private static final String UAE_EMAIL_URL = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D";
    private static final String SINGAPORE_EMAIL_URL = "http://singaporevisa-online.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D";
    private static final String IRAN_EMAIL_URL = "http://iranvisas.in/api/getdatairn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D";
    private static final String BASE_URL_CONSULT_FORM_SINGAPORE = "http://singaporevisa-online.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_CONSULT_FORM_OMAN = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String PUBLIC_KEY_PAYMENT = "pk_test_73e56b01-8726-4176-9159-db71454ff4af";
    private static final String BASE_URL_CONSULT_FORM_IRAN = "http://iranvisas.in/api/getdatairn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_USA_FORM1 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=personalinfo";
    private static final String USA_EMAIL_URL = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=email_send";
    private static final String BASE_URL_USA_FORM2 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=personalinfo2";
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
        selectedVisaId = sharedPreferences.getInt("visa_id",0);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        Bundle bundle  = getArguments();
        nationalityID = bundle.getString("nationality_id");
        if (title.equalsIgnoreCase("consult")) {
            view = inflater.inflate(R.layout.consult_form, container, false);
            intializeConsultViews();
            Button button1 = (Button)view.findViewById(R.id.button_consult);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edtDate1.getText().toString().length() != 0 && edtDate2.getText().toString().length() != 0 ){
                        ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
                        int atTab = mFormPager.getCurrentItem();
                        mFormPager.setCurrentItem(atTab + 1);
                        saveConsultData();
                    }
                    else {
                        Toast.makeText(getContext(),"Enter all fields", Toast.LENGTH_SHORT).show();
                    }}
            });
        } else if (title.equalsIgnoreCase("applicant")) {
            view = inflater.inflate(R.layout.appicant_form, container, false);
            intializeApplicantViews();
            disableInput(latestDate);
            ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
            int atTab = mFormPager.getCurrentItem();
            if(atTab == 2){
                emailEdt.setText(sharedPreferences.getString("email_contact",""));
            }
            latestDate.setOnClickListener(this);
            if(sharedPreferences!=null) {
                livingInId = sharedPreferences.getString("living_id", "");
                nationalityID = sharedPreferences.getString("nationality_id", "");
                if(Objects.equals(livingInId, "17") || Objects.equals(livingInId, "114") || Objects.equals(livingInId, "187") || Objects.equals(livingInId, "174") || Objects.equals(livingInId, "161")){
                    sponsorLayout.setVisibility(View.VISIBLE);
                    gccLayout.setVisibility(View.VISIBLE);
                }
                else {
                    sponsorLayout.setVisibility(View.GONE);
                    gccLayout.setVisibility(View.GONE);
                }
            }
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
                    selectedProfession = adapter.getItem(i);
                }
            });
            birthDate.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    final Calendar mcurrentDate=Calendar.getInstance();
                    final int mYear = mcurrentDate.get(Calendar.YEAR);
                    final int mMonth=mcurrentDate.get(Calendar.MONTH);
                    final int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            birthDate.setText(selectedyear +"-"+(selectedmonth+1)+"-"+selectedday);
                            age = mYear - selectedyear;
                        }
                    },mYear, mMonth, mDay);
                    mDatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDatePicker.setTitle("Select date");
                    mDatePicker.show();
                }
            });
            dateIssue.setOnClickListener(this);
            dateExpiry.setOnClickListener(this);
            loadAllCountries();
            loadArrivalPort();
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendUsaFormData2();
                    if(nameFirst.getText().toString().length() != 0 && nameLast.getText().toString().length() != 0 && birthDate.getText().toString().length() != 0 && passportNumber.getText().toString().length() != 0 && emailEdt.getText().toString().length() != 0 && birthPlace.getText().toString().length() != 0 && nameFather.getText().toString().length() != 0 && nameMother.getText().toString().length() != 0 && dateIssue.getText().toString().length() != 0 && dateExpiry.getText().toString().length() != 0){
                        if(isValidEmail(emailEdt.getText().toString())) {
                            if(radioButton1.isChecked() || radioButton2.isChecked()){
                                if (radioButton1.isChecked()){
                                    selectedMaritalStatus = "Single";
                                    saveSharedPreferences();
                                }
                                else{
                                    selectedMaritalStatus = "Married";
                                    saveSharedPreferences();
                                }
                                ViewPager mFormPager = (ViewPager) getActivity().findViewById(R.id.formViewPager);
                                int atTab = mFormPager.getCurrentItem();
                                mFormPager.setCurrentItem(atTab + 1);
                            }
                            else {
                                showToast("Select marital status");
                            }
                        }
                        else {
                            showToast("Email is not valid");
                        }
                    }
                    else {
                        showToast("Enter all Fields");
                    }
                    if(selectedVisaId == 1){
                        Intent intent = new Intent(getContext(), SummaryPage.class);
                        startActivity(intent);
                    }
                }
            });
            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, professionData);
            profession.setThreshold(1);
            profession.setAdapter(adapter);

        }  else if (title.equalsIgnoreCase("contact")){
            view = inflater.inflate(R.layout.contact_form,container,false);
            intializeContactView();
            if(sharedPreferences!=null){
                codePhone = sharedPreferences.getString("code","");
                phoneCodeEdt.setText(codePhone);
                livingIn = sharedPreferences.getString("living_in_country","");
                String hotelAddress = sharedPreferences.getString("hotel_address","");
                String address = sharedPreferences.getString("current_address","");
                String emergencyName = sharedPreferences.getString("emergency_name","");
                String emergencyNumber = sharedPreferences.getString("emergency_number","");
                livingInEdt.setText(livingIn);
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
                    sendUsaFormData1();
                    if(edtAddress.getText().toString().length() != 0 && edtHotelAddress.getText().toString().length() != 0 && edtEmergencyContactName.getText().toString().length() != 0 && edtEmergencyContactNumber.getText().toString().length() != 0 && edtLivingCity.getText().toString().length() != 0){
                        if(isValidEmail(edtEmailContact.getText().toString())){
                            if(isValidMobile(edtEmergencyContactNumber.getText().toString()) && isValidMobile(edtMobile.getText().toString())) {
                                saveContactSharedPref();
                                ViewPager mFormPager = (ViewPager) getActivity().findViewById(R.id.formViewPager);
                                int atTab = mFormPager.getCurrentItem();
                                mFormPager.setCurrentItem(atTab + 1);
                                getSharedPreferences();
                                if(sharedPreferences != null){
                                    if(sharedPreferences.getInt("visa_id",0) == 0){
                                        sendConsultData();
                                    }
                                    else if(sharedPreferences.getInt("visa_id",0) == 2){
                                        sendSingaporeConsultData();
                                    }
                                    else if(sharedPreferences.getInt("visa_id",0) == 3){
                                        sendOmanConsultData();
                                    }
                                    else if(sharedPreferences.getInt("visa_id",0) == 4){
                                        sendIranConsultData();
                                    }
                                }
                            }
                            else {
                                showToast("Mobile Number is not valid");
                            }
                        }
                        else {
                            showToast("Email is not valid");
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
            removeUploadPrefData();
            intializeDocsView();
        }
        return view;
    }
    private void intializeConsultViews() {
        loadConsulateData();
        intializeVisaForSpinner();
        layoutIranConsult = (LinearLayout)view.findViewById(R.id.iran_consult_layout);
        inputArrival = (TextInputLayout)view.findViewById(R.id.input_layout_arrival);
        inputDeparture = (TextInputLayout)view.findViewById(R.id.input_layout_departure);
        selectedVisaId = sharedPreferences.getInt("visa_id",0);
        edtDate1 = (EditText) view.findViewById(R.id.edt_arrival);
        edtDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(edtDate1);
            }
        });
        disableInput(edtDate1);
        edtNoPerson  = (EditText)view.findViewById(R.id.person_accompanying);
        edtBorderEntry = (EditText)view.findViewById(R.id.border_entry);
        edtDurationStay = (EditText)view.findViewById(R.id.duration_stay);
        edtDate2 = (EditText) view.findViewById(R.id.edt_departure);
        edtDate2.setHint(R.string.uae_visa_departure_hint);
        edtDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(edtDate2);
            }
        });
        disableInput(edtDate2);
        if(selectedVisaId == 0){
            layoutIranConsult.setVisibility(View.GONE);
            inputArrival.setHint("Arrival Date in UAE");
            inputDeparture.setHint("Departure Date from UAE");
        }
        else if(selectedVisaId == 1){
            layoutIranConsult.setVisibility(View.GONE);
            inputArrival.setHint("Arrival Date in USA");
            inputDeparture.setHint("Departure Date from USA");
        }
        else if(selectedVisaId == 2){
            layoutIranConsult.setVisibility(View.GONE);
            inputArrival.setHint("Arrival Date in Singapore");
            inputDeparture.setHint("Departure Date from Singapore");
        }
        else if(selectedVisaId == 3){
            layoutIranConsult.setVisibility(View.GONE);
            inputArrival.setHint("Arrival Date in Oman");
            inputDeparture.setHint("Departure Date from Oman");
        }
        else if(selectedVisaId == 4){

            inputArrival.setHint("Arrival Date in Iran");
            inputDeparture.setHint("Departure Date from Iran");
        }

        if(sharedPreferences!=null){
            String arrivalDateTemp  = sharedPreferences.getString("arrival_date","");
            String departureDateTemp = sharedPreferences.getString("departure_date","");
            edtDate1.setText(arrivalDateTemp);
            edtDate2.setText(departureDateTemp);

        }
    }
    private void intializeContactView() {
        populateAddressTypeSpinner();
        layoutContactUsa = (LinearLayout)view.findViewById(R.id.contact_layout_usa);
        layoutContactOther = (LinearLayout)view.findViewById(R.id.contact_layout_other);
        addressSingapore = (EditText)view.findViewById(R.id.singapore_address);
        phoneSingapore = (EditText)view.findViewById(R.id.singapore_phone);
        nameSingapore = (EditText)view.findViewById(R.id.singapore_name);
        nricSingapore = (EditText)view.findViewById(R.id.singapore_nric);
        addressUaeLayout = (LinearLayout)view.findViewById(R.id.address_uae_layout);
        addressSingaporeLayout = (LinearLayout)view.findViewById(R.id.address_singapore_layout);
        if(selectedVisaId == 0){
            addressUaeLayout.setVisibility(View.VISIBLE);
            addressSingaporeLayout.setVisibility(View.GONE);
        }
        else if(selectedVisaId == 1){
            layoutContactUsa.setVisibility(View.VISIBLE);
            layoutContactOther.setVisibility(View.GONE);
        }
        else if(selectedVisaId == 2){
            addressSingaporeLayout.setVisibility(View.VISIBLE);
            addressUaeLayout.setVisibility(View.GONE);
        }
        else {
            addressUaeLayout.setVisibility(View.GONE);
            addressSingaporeLayout.setVisibility(View.GONE);
        }
        edtAddress = (EditText)view.findViewById(R.id.edt_current_address);
        edtHotelAddress = (EditText)view.findViewById(R.id.edt_hotel_address);
        edtEmergencyContactName = (EditText)view.findViewById(R.id.edt_contact_person);
        edtEmergencyContactNumber = (EditText)view.findViewById(R.id.edt_contact_number);
        edtLivingCity = (EditText)view.findViewById(R.id.living_city);
        livingInEdt = (EditText)view.findViewById(R.id.edt_country);
        phoneCodeEdt = (EditText)view.findViewById(R.id.phone_code);
        edtMobile = (EditText)view.findViewById(R.id.edittext_mobile);
        edtEmailContact = (EditText)view.findViewById(R.id.edt_email_contact);
        edtEmailContact.addTextChangedListener(watcher);
        firstNameUsa = (EditText)view.findViewById(R.id.name_first_usa);
        lastNameUsa = (EditText)view.findViewById(R.id.name_last_usa);
        emailUsa = (EditText)view.findViewById(R.id.email_usa);
        phoneCodeUsa = (EditText)view.findViewById(R.id.phone_code_usa);
        phoneCodeUsa.setText("+" + sharedPreferences.getString("code",""));
        phoneUsa = (EditText)view.findViewById(R.id.phone_applicant_usa);
        arrivalDateUsa = (EditText)view.findViewById(R.id.edt_arrival_usa);
        departureDateUsa = (EditText)view.findViewById(R.id.edt_departure_usa);
        loadEmirates();
    }
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            sharedPreferences.edit().putString("email_contact", editable.toString()).apply();


        }
    };
    private void intializeApplicantViews() {
        layoutApplicantOther = (LinearLayout)view.findViewById(R.id.applicant_layout_other);
        layoutApplicantUsa = (LinearLayout)view.findViewById(R.id.applicant_layout_usa);
        nameFirst = (EditText)view.findViewById(R.id.name_first);
        nameLast = (EditText)view.findViewById(R.id.name_last);
        birthDate = (EditText)view.findViewById(R.id.edt_dob );
        inputOccupation = (TextInputLayout)view.findViewById(R.id.edt_occupation);
        inputRace = (TextInputLayout)view.findViewById(R.id.edt_race);
        inputLayoutQualification = (TextInputLayout)view.findViewById(R.id.input_layout_highest_qualification);
        highestQualification = (EditText)view.findViewById(R.id.edt_qualification);
        passportLayoutSingapore = (LinearLayout)view.findViewById(R.id.singapore_passport_layout);
        otherCountrySingaporeLayout = (LinearLayout)view.findViewById(R.id.other_country_singapore_layout);
        layoutDuration = (LinearLayout)view.findViewById(R.id.layout_duration_singapore);
        layoutPurpose = (LinearLayout)view.findViewById(R.id.layout_purpose_singapore);
        radioGrpSingapore1 = (RadioGroup)view.findViewById(R.id.radio_group_singapore1);
        radioGrpSingapore2 = (RadioGroup)view.findViewById(R.id.radio_group_singapore2);
        radioGrpSingapore3 = (RadioGroup)view.findViewById(R.id.radio_group_singapore3);
        radioGrpSingapore4 = (RadioGroup)view.findViewById(R.id.radio_group_singapore4);
        radioGrpSingapore5 = (RadioGroup)view.findViewById(R.id.radio_group_singapore5);
        singaporeRb1 = (RadioButton)view.findViewById(R.id.rb_other_country1);
        singaporeRb2 = (RadioButton)view.findViewById(R.id.rb_other_country2);
        singaporeRb3 = (RadioButton)view.findViewById(R.id.rb_refused_country1);
        singaporeRb4 = (RadioButton)view.findViewById(R.id.rb_refused_country2);
        singaporeRb5 = (RadioButton)view.findViewById(R.id.rb_convicted1);
        singaporeRb6 = (RadioButton)view.findViewById(R.id.rb_convicted2);
        singaporeRb7 = (RadioButton)view.findViewById(R.id.rb_prohibited1);
        singaporeRb8 = (RadioButton)view.findViewById(R.id.rb_prohibited2);
        singaporeRb9 = (RadioButton)view.findViewById(R.id.rb_diff_passport1);
        singaporeRb10 = (RadioButton)view.findViewById(R.id.rb_diff_passport2);
        edtOtherName = (EditText)view.findViewById(R.id.other_country_name);
        edtOtherAddress = (EditText)view.findViewById(R.id.other_country_address);
        edtOtherStart = (EditText)view.findViewById(R.id.other_country_start);
        edtOtherEnd = (EditText)view.findViewById(R.id.other_country_end);
        layoutCompanyDetails = (LinearLayout)view.findViewById(R.id.company_detail_layout);
        occupation = (EditText)view.findViewById(R.id.occupation);
        jobTitle = (EditText)view.findViewById(R.id.job_title);
        companyName = (EditText)view.findViewById(R.id.name_company);
        acticvityField = (EditText)view.findViewById(R.id.field_activity);
        companyTelephone = (EditText)view.findViewById(R.id.telephone_company);
        visitedIranLayout = (LinearLayout)view.findViewById(R.id.iran_visited_layout);
        visitedIranInfoLayout = (LinearLayout)view.findViewById(R.id.visited_iran_info_layout);
        visitedIranInfoLayout.setVisibility(View.GONE);
        radioGroupIran = (RadioGroup)view.findViewById(R.id.radio_group_iran);
        radioButtonIran1 = (RadioButton)view.findViewById(R.id.rb_iran1);
        radioButtonIran2 = (RadioButton)view.findViewById(R.id.rb_iran2);
        noVisitIran = (EditText)view.findViewById(R.id.no_visit_iran);
        purposeVisitIran = (EditText)view.findViewById(R.id.purpose_iran);
        lastVisitIran = (EditText)view.findViewById(R.id.last_visit_iran);
        visaNoIran = (EditText)view.findViewById(R.id.visa_number);
        visitDateIran = (EditText)view.findViewById(R.id.visit_date);
        otherCountryVisited = (EditText)view.findViewById(R.id.other_country_iran);
        radioGroupIran.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioButtonIran1.isChecked()){
                    visitedIranInfoLayout.setVisibility(View.VISIBLE);
                    sharedPreferences.edit().putString("visited_iran","Yes").apply();
                }else{
                    visitedIranInfoLayout.setVisibility(View.GONE);
                    sharedPreferences.edit().putString("visited_iran","No").apply();
                }
            }
        });

        radioGrpSingapore1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(singaporeRb1.isChecked()){otherCountrySingaporeLayout.setVisibility(View.VISIBLE);
                    sharedPreferences.edit().putString("reside_other_country","yes").apply();}
                else if(singaporeRb2.isChecked()){otherCountrySingaporeLayout.setVisibility(View.GONE);
                    sharedPreferences.edit().putString("reside_other_country","no").apply();}

            }
        });
        radioGrpSingapore2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(singaporeRb3.isChecked()){
                    sharedPreferences.edit().putString("refused_country","yes").apply();
                }
                else {
                    sharedPreferences.edit().putString("refused_country","no").apply();
                }
            }
        });
        radioGrpSingapore3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(singaporeRb5.isChecked()){
                    sharedPreferences.edit().putString("court_law", "yes").apply();
                }
                else {
                    sharedPreferences.edit().putString("court_law","no").apply();
                }
            }
        });
        radioGrpSingapore4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(singaporeRb7.isChecked()){
                    sharedPreferences.edit().putString("prohibited_country","yes").apply();
                }
                else{
                    sharedPreferences.edit().putString("prohibited_country","no").apply();
                }

            }
        });
        radioGrpSingapore5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(singaporeRb9.isChecked()){
                    sharedPreferences.edit().putString("different_passport","yes").apply();
                }
                else {
                    sharedPreferences.edit().putString("different_passport","no").apply();
                }

            }
        });
        disableInput(birthDate);
        birthPlace = (EditText)view.findViewById(R.id.edt_place_birth);
        emailEdt = (EditText)view.findViewById(R.id.edittext_email);
        disableInput(emailEdt);
        nameFather = (EditText)view.findViewById(R.id.name_father);
        nameMother = (EditText)view.findViewById(R.id.name_mother);
        dateIssue = (EditText)view.findViewById(R.id.edt_issue_date);
        disableInput(dateIssue);
        dateExpiry = (EditText)view.findViewById(R.id.edt_valid_till);
        disableInput(dateExpiry);
        passportNumber = (EditText)view.findViewById(R.id.edt_passport_number);
        edtIssuePlace = (EditText)view.findViewById(R.id.edt_issue_place);
        button2 = (Button)view.findViewById(R.id.button_applicant);
        profession = (AutoCompleteTextView)view.findViewById(R.id.auto_profession);
        phoneCode = (EditText)view.findViewById(R.id.phone_code);
        radioButton1 = (RadioButton)view.findViewById(R.id.rb_single);
        radioButton2 = (RadioButton)view.findViewById(R.id.rb_married);
        sponsorLayout  = (LinearLayout)view.findViewById(R.id.layout_sponsor);
        gccLayout = (LinearLayout)view.findViewById(R.id.layout_gcc);
        latestDate = (EditText)view.findViewById(R.id.late_issue_date);
        edtSponsorName = (EditText)view.findViewById(R.id.sponsor_name);
        edtSponsorAddress = (EditText)view.findViewById(R.id.sponsor_address);
        edtSponsorCotact = (EditText)view.findViewById(R.id.sponsor_contact);
        previousNation = (TextInputLayout)view.findViewById(R.id.edt_previous_nation);
        previousNationality = (EditText)view.findViewById(R.id.previous_nationality);
        nationalityUsa = (EditText)view.findViewById(R.id.current_nationality);
        addressApplicantUsa = (EditText)view.findViewById(R.id.current_address);
        cityApplicantUsa = (EditText)view.findViewById(R.id.current_city_form2);
        placeBirthUsa = (EditText)view.findViewById(R.id.place_of_birth_form2);
        stateUsa = (EditText)view.findViewById(R.id.current_state_form2);
        postalUsa = (EditText)view.findViewById(R.id.postal_code_current);
        countryUsa = (EditText)view.findViewById(R.id.current_country);
        passportNumberUsa = (EditText)view.findViewById(R.id.passport_number_form2);
        cityIssuedUsa = (EditText)view.findViewById(R.id.city_issued_form2);
        countryIssuedUsa = (EditText)view.findViewById(R.id.country_issued_form2);
        issueDateUsa = (EditText)view.findViewById(R.id.issue_date_form2);
        expiryDateUsa = (EditText)view.findViewById(R.id.expiry_date_form2);
        if(selectedVisaId == 2){
            visitedIranLayout.setVisibility(View.GONE);
            passportLayoutSingapore.setVisibility(View.VISIBLE);
            profession.setVisibility(View.GONE);
            previousNation.setVisibility(View.GONE);
            inputLayoutQualification.setVisibility(View.VISIBLE);
            layoutPurpose.setVisibility(View.VISIBLE);
            layoutDuration.setVisibility(View.VISIBLE);
            layoutCompanyDetails.setVisibility(View.GONE);


        }
        else if(selectedVisaId == 1){
            layoutApplicantUsa.setVisibility(View.VISIBLE);
            layoutApplicantOther.setVisibility(View.GONE);
        }
        else if(selectedVisaId == 4){
            visitedIranLayout.setVisibility(View.VISIBLE);
            previousNation.setVisibility(View.VISIBLE);
            layoutCompanyDetails.setVisibility(View.VISIBLE);
            passportLayoutSingapore.setVisibility(View.GONE);
            profession.setVisibility(View.GONE);
            inputLayoutQualification.setVisibility(View.GONE);
            layoutDuration.setVisibility(View.GONE);
            layoutPurpose.setVisibility(View.GONE);
            inputOccupation.setVisibility(View.GONE);
            inputRace.setVisibility(View.GONE);

        }
        else {
            visitedIranLayout.setVisibility(View.GONE);
            passportLayoutSingapore.setVisibility(View.GONE);
            layoutCompanyDetails.setVisibility(View.GONE);
            profession.setVisibility(View.VISIBLE);
            previousNation.setVisibility(View.GONE);
            inputLayoutQualification.setVisibility(View.GONE);
            layoutDuration.setVisibility(View.GONE);
            layoutPurpose.setVisibility(View.GONE);
            inputOccupation.setVisibility(View.GONE);
            inputRace.setVisibility(View.GONE);
        }
    }
    private void intializeDocsView() {
        button3 = (Button)view.findViewById(R.id.button_docs);
        button3.setText("Skip & Submit");
        name1 = (TextView)view.findViewById(R.id.doc1);
        name2 = (TextView)view.findViewById(R.id.doc2);
        name3 = (TextView)view.findViewById(R.id.doc3);
        name4 = (TextView)view.findViewById(R.id.doc4);
        name5 = (TextView)view.findViewById(R.id.doc5);
        name6 = (TextView)view.findViewById(R.id.doc6);
        cardDocs5 = (CardView)view.findViewById(R.id.card_doc5);
        cardDocs6 = (CardView)view.findViewById(R.id.card_doc6);
        if(selectedVisaId == 4){
            name1.setText("Coloured Passport Scanned Copy");
            name2.setText("Colour Photo");
            name3.setText("Miscellaneous Documents");
            name4.setText("Miscellaneous Documents");
            cardDocs5.setVisibility(View.GONE);
            cardDocs6.setVisibility(View.GONE);
        }
        else if(selectedVisaId == 2){
            name1.setText("Coloured Passport Scanned Copy");
            name2.setText("Colour Photo");
            name3.setText("Miscellaneous Documents");
            name4.setText("Miscellaneous Documents");
            cardDocs5.setVisibility(View.GONE);
            cardDocs6.setVisibility(View.GONE);
        }
        checked1 = (ImageView)view.findViewById(R.id.attach_file_checked1);
        checked2 = (ImageView)view.findViewById(R.id.attach_file_checked2);
        checked3 = (ImageView)view.findViewById(R.id.attach_file_checked3);
        checked4 = (ImageView)view.findViewById(R.id.attach_file_checked4);
        checked5 = (ImageView)view.findViewById(R.id.attach_file_checked5);
        checked6 = (ImageView)view.findViewById(R.id.attach_file_checked6);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    /*ViewPager mFormPager = (ViewPager)getActivity().findViewById(R.id.formViewPager);
                    int atTab = mFormPager.getCurrentItem();
                    mFormPager.setCurrentItem(atTab + 1);*/
                Intent intent  = new Intent(getContext(), SummaryPage.class);
                startActivity(intent);
            }
        });
        attachFile1 = (ImageView)view.findViewById(R.id.attach_file1);
        attachFile2 = (ImageView)view.findViewById(R.id.attach_file2);
        attachFile3 = (ImageView)view.findViewById(R.id.attach_file3);
        attachFile4 = (ImageView)view.findViewById(R.id.attach_file4);
        attachFile5 = (ImageView)view.findViewById(R.id.attach_file5);
        attachFile6 = (ImageView)view.findViewById(R.id.attach_file6);
        attachFile1.setOnClickListener(this);
        attachFile2.setOnClickListener(this);
        attachFile3.setOnClickListener(this);
        attachFile4.setOnClickListener(this);
        attachFile5.setOnClickListener(this);
        attachFile6.setOnClickListener(this);
    }
    private void removeUploadPrefData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.edit().putString("file_type1","").apply();
        sharedPreferences.edit().putString("file_name1","").apply();
        sharedPreferences.edit().putString("file_path1","").apply();
        sharedPreferences.edit().putString("file_type2","").apply();
        sharedPreferences.edit().putString("file_name2","").apply();
        sharedPreferences.edit().putString("file_path2","").apply();
        sharedPreferences.edit().putString("file_type3","").apply();
        sharedPreferences.edit().putString("file_name3","").apply();
        sharedPreferences.edit().putString("file_path3","").apply();
        sharedPreferences.edit().putString("file_type4","").apply();
        sharedPreferences.edit().putString("file_name4","").apply();
        sharedPreferences.edit().putString("file_path4","").apply();
        sharedPreferences.edit().putString("file_type5","").apply();
        sharedPreferences.edit().putString("file_name5","").apply();
        sharedPreferences.edit().putString("file_path5","").apply();
        sharedPreferences.edit().putString("file_type6","").apply();
        sharedPreferences.edit().putString("file_name6","").apply();
        sharedPreferences.edit().putString("file_path6","").apply();
    }
    private void saveConsultData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        arrivalDate = edtDate1.getText().toString();
        departureDate = edtDate2.getText().toString();
        sharedPreferences.edit().putString("arrival_date", arrivalDate).apply();
        sharedPreferences.edit().putString("departure_date", departureDate).apply();
        sharedPreferences.edit().putString("no_person", edtNoPerson.getText().toString()).apply();
        sharedPreferences.edit().putString("border_entry",edtBorderEntry.getText().toString()).apply();
        sharedPreferences.edit().putString("duration_stay",edtDurationStay.getText().toString()).apply();
        sharedPreferences.edit().putInt("consulate_id",selectedConsulateId).apply();
    }
    private void saveContactSharedPref() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.edit().putString("current_address", edtAddress.getText().toString()).apply();
        sharedPreferences.edit().putString("hotel_address", edtHotelAddress.getText().toString()).apply();
        sharedPreferences.edit().putString("emergency_name", edtEmergencyContactName.getText().toString()).apply();
        sharedPreferences.edit().putString("emergency_number", edtEmergencyContactNumber.getText().toString()).apply();
        sharedPreferences.edit().putString("living_city", edtLivingCity.getText().toString()).apply();
        //sharedPreferences.edit().putString("email_contact", edtEmailContact.getText().toString()).apply();
        sharedPreferences.edit().putString("selected_emirate", selectedEmirate).apply();
        sharedPreferences.edit().putInt("selected_emirate_id", selectedEmirateId).apply();
        sharedPreferences.edit().putString("mobile", (codePhone + edtMobile.getText().toString())).apply();
    }
    private void getSharedPreferences(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        serviceType = sharedPreferences.getString("service_type", "");
        livingId = sharedPreferences.getString("living_id", "");
        nationalityId = sharedPreferences.getString("nationality_id", "");
        serviceFeeCs = sharedPreferences.getString("service_fee_cs", "");
        processingTime = sharedPreferences.getString("processing_time", "");
        deviceType = sharedPreferences.getString("device_name", "");
        deviceOS = sharedPreferences.getString("device_os", "");
        visaTypeId = sharedPreferences.getInt("visa_type_id", 0);
        govtFee = sharedPreferences.getFloat("govt_fee", 0);
        serviceFee = sharedPreferences.getFloat("service_fee", 0);
        mngFee = sharedPreferences.getFloat("mng_fee", 0);
        fullNameVisa = sharedPreferences.getString("visa_name","");
        savedArrivalDate = sharedPreferences.getString("arrival_date","");
        savedDepartureDate = sharedPreferences.getString("departure_date","");
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
        sharedPreferences.edit().putString("marital_status",selectedMaritalStatus).apply();
        sharedPreferences.edit().putString("sponsor_name",edtSponsorName.getText().toString()).apply();
        sharedPreferences.edit().putString("sponsor_address",edtSponsorAddress.getText().toString()).apply();
        sharedPreferences.edit().putString("sponsor_type",selectedGCC).apply();
        sharedPreferences.edit().putString("port",selectedPort).apply();
        sharedPreferences.edit().putString("sponsor_contact",edtSponsorCotact.getText().toString()).apply();
        sharedPreferences.edit().putString("other_country_name", edtOtherName.getText().toString()).apply();
        sharedPreferences.edit().putString("other_country_address", edtOtherAddress.getText().toString()).apply();
        sharedPreferences.edit().putString("start_year_other_country",edtOtherStart.getText().toString()).apply();
        sharedPreferences.edit().putString("end_year_other_country", edtOtherEnd.getText().toString()).apply();
        sharedPreferences.edit().putString("highest_qualification",highestQualification.getText().toString()).apply();
        sharedPreferences.edit().putString("purpose_singapore", selectedPurpose).apply();
        sharedPreferences.edit().putString("duration_singapore",selectedDuration).apply();
        sharedPreferences.edit().putString("job_title",jobTitle.getText().toString()).apply();
        sharedPreferences.edit().putString("company_name",companyName.getText().toString()).apply();
        sharedPreferences.edit().putString("field_activity",acticvityField.getText().toString()).apply();
        sharedPreferences.edit().putString("telephone_company",companyTelephone.getText().toString()).apply();
        sharedPreferences.edit().putString("previous_nationality",previousNationality.getText().toString()).apply();
        sharedPreferences.edit().putString("no_of_visit",previousNationality.getText().toString()).apply();
        sharedPreferences.edit().putString("purpose_of_visit",previousNationality.getText().toString()).apply();
        sharedPreferences.edit().putString("last_visit_iran",previousNationality.getText().toString()).apply();
        sharedPreferences.edit().putString("visa_no_iran",previousNationality.getText().toString()).apply();
        sharedPreferences.edit().putString("visit_date_iran",previousNationality.getText().toString()).apply();
        sharedPreferences.edit().putString("other_country_visited",otherCountryVisited.getText().toString()).apply();
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
        call.enqueue(new retrofit2.Callback<Country>() {
            @Override
            public void onResponse(retrofit2.Call<Country> call, retrofit2.Response<Country> response) {


                Country jsonResponse = response.body();
                allcountry = jsonResponse.getCountry();
                for(int i=0;i<allcountry.size();i++){
                    allCountriesData.add(allcountry.get(i).getName());
                }

                intializeSpinners();


            }
            @Override
            public void onFailure(retrofit2.Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadArrivalPort() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Port request = retrofit.create(Port.class);
        retrofit2.Call<ArrivalPort> call = request.getPortofarrival();
        call.enqueue(new retrofit2.Callback<ArrivalPort>() {
            @Override
            public void onResponse(retrofit2.Call<ArrivalPort> call, retrofit2.Response<ArrivalPort> response) {


                ArrivalPort jsonResponse = response.body();
                ports = jsonResponse.getPortofarrival();
                for(int i=0;i<ports.size();i++){
                    arrivalPortData.add(ports.get(i).getPortArrival());
                }

                intializeSpinners();


            }
            @Override
            public void onFailure(retrofit2.Call<ArrivalPort> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }

    private void intializeSpinners() {
        populateAllCountrySpinner();
        populateIssueCountrySpinner();
        populateGenderSpinner();
        populateReligionSpinner();

        if(selectedVisaId == 2){
            populateDurationSpinner();
            populatePurposeSpinner();
        }
        else if(selectedVisaId == 0){
            populateGCCSpinner();
            populatePortArrivalSpinner();
        }
    }
    private void showFileChooser(int one) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra("path",Environment.getExternalStorageDirectory().getPath());
        intent.setType("*/*");
        intent.putExtra("count", one);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    one);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        Uri uri;
        String filePath;
        String type;
        ContentResolver cR;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        switch (requestCode){
            case 1:
                uri = data.getData();
                filePath = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                type = cR.getType(uri);
                sharedPreferences.edit().putString("file_type1", type).apply();
                selectedFile1=filePath.substring(filePath.lastIndexOf("/")+1);
                sharedPreferences.edit().putString("file_name1", selectedFile1).apply();
                sharedPreferences.edit().putString("file_path1", filePath).apply();
                name1.setText(selectedFile1);
                checked1.setImageResource(R.drawable.checked);

                button3.setText("Submit");

                break;
            case 2:
                uri = data.getData();
                filePath = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                type = cR.getType(uri);
                sharedPreferences.edit().putString("file_type2", type).apply();
                selectedFile2=filePath.substring(filePath.lastIndexOf("/")+1);
                sharedPreferences.edit().putString("file_name2", selectedFile2).apply();
                sharedPreferences.edit().putString("file_path2", filePath).apply();
                name2.setText(selectedFile2);
                checked2.setImageResource(R.drawable.checked);
                button3.setText("Submit");
                break;
            case 3:
                uri = data.getData();
                filePath = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                type = cR.getType(uri);
                sharedPreferences.edit().putString("file_type3", type).apply();
                selectedFile3=filePath.substring(filePath.lastIndexOf("/")+1);
                sharedPreferences.edit().putString("file_name3", selectedFile3).apply();
                sharedPreferences.edit().putString("file_path3", filePath).apply();
                name3.setText(selectedFile3);
                checked3.setImageResource(R.drawable.checked);
                button3.setText("Submit");
                break;
            case 4:
                uri = data.getData();
                filePath = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                type = cR.getType(uri);
                sharedPreferences.edit().putString("file_type4", type).apply();
                selectedFile4=filePath.substring(filePath.lastIndexOf("/")+1);
                sharedPreferences.edit().putString("file_name4", selectedFile4).apply();
                sharedPreferences.edit().putString("file_path4", filePath).apply();

                name4.setText(selectedFile4);
                checked4.setImageResource(R.drawable.checked);

                button3.setText("Submit");
                break;
            case 5:
                uri = data.getData();
                filePath = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                type = cR.getType(uri);
                sharedPreferences.edit().putString("file_type5", type).apply();
                selectedFile5=filePath.substring(filePath.lastIndexOf("/")+1);
                sharedPreferences.edit().putString("file_name5", selectedFile5).apply();
                sharedPreferences.edit().putString("file_path5", filePath).apply();
                name5.setText(selectedFile5);
                checked5.setImageResource(R.drawable.checked);

                button3.setText("Submit");
                break;
            case 6:
                uri = data.getData();
                filePath = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                type = cR.getType(uri);
                sharedPreferences.edit().putString("file_type6", type).apply();
                selectedFile6=filePath.substring(filePath.lastIndexOf("/")+1);
                sharedPreferences.edit().putString("file_name6", selectedFile6).apply();
                sharedPreferences.edit().putString("file_path6", filePath).apply();
                name6.setText(selectedFile6);
                checked6.setImageResource(R.drawable.checked);

                button3.setText("Submit");
                break;
        }

    }
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
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
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {

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
                emailEdt.setText(sharedPreferences.getString("email_contact", ""));
                /*if(selectedCountryID == 0){
                    phoneCode.setText("");
                }
                else {
                    phoneCode.setText(selectedPhoneCode);
                }*/
                break;
            case R.id.spnr_country_issue:
                selectedIssueCountryID = allcountry.get(i).getId();
                selectedIssueCountry = allcountry.get(i).getName();
                emailEdt.setText(sharedPreferences.getString("email_contact", ""));
                break;
            case R.id.spnr_gender:
                selectedGender = gender[i];
                emailEdt.setText(sharedPreferences.getString("email_contact", ""));
                break;
            case R.id.spnr_religion:
                selectedReligion = religion[i];
                emailEdt.setText(sharedPreferences.getString("email_contact", ""));
                break;
            case R.id.emirates:
                selectedEmirate = emiratesData.get(i);
                selectedEmirateId = i;
                break;
            case R.id.spnr_sponsor:
                selectedGCC = gccList[i];
                break;
            case R.id.spnr_port:
                selectedPort = arrivalPortData.get(i);
                break;
            case R.id.spinner_address_type_singapore:
                selectedAddressType = i;
                if(selectedAddressType == 2){
                    addressSingapore.setHint(R.string.address_relative);
                    phoneSingapore.setHint(R.string.phone_relative);
                    nameSingapore.setHint(R.string.name_relative);
                    nricSingapore.setVisibility(View.VISIBLE);
                    nricSingapore.setHint(R.string.nric_relative);
                }
                else if(selectedAddressType == 3){
                    addressSingapore.setHint(R.string.address_friend);
                    phoneSingapore.setHint(R.string.phone_friend);
                    nameSingapore.setHint(R.string.name_friend);
                    nricSingapore.setVisibility(View.VISIBLE);
                    nricSingapore.setHint(R.string.nric_friend);
                }
                else if(selectedAddressType == 4){
                    addressSingapore.setHint(R.string.address_kin);
                    phoneSingapore.setHint(R.string.phone_kin);
                    nameSingapore.setHint(R.string.name_kin);
                    nricSingapore.setVisibility(View.VISIBLE);
                    nricSingapore.setHint(R.string.nric_kin);
                }
                else {
                    addressSingapore.setHint("Address");
                    phoneSingapore.setHint("Phone");
                    nameSingapore.setHint("Name of your Hotel");
                    nricSingapore.setVisibility(View.GONE);
                }
                break;
            case R.id.spnr_duration:
                selectedDuration = duration[i];
                break;
            case R.id.spnr_purpose:
                selectedPurpose = purpose[i];
                break;
            case R.id.spnr_apply_visa:
                selectedConsulateId = i;
                break;
            case R.id.spnr_visa_type:
                if(i== 1){
                    sharedPreferences.edit().putString("visa_type_iran", "Tourist").apply();
                }
                else if(i==2){
                    sharedPreferences.edit().putString("visa_type_iran", "Business").apply();
                }


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        emailEdt.setText(sharedPreferences.getString("email_contact", ""));

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
            case R.id.attach_file1:
                showFileChooser(1);
                break;
            case R.id.attach_file2:
                showFileChooser(2);
                break;
            case R.id.attach_file3:
                showFileChooser(3);
                break;
            case R.id.attach_file4:
                showFileChooser(4);
                break;
            case R.id.attach_file5:
                showFileChooser(5);
                break;
            case R.id.attach_file6:
                showFileChooser(6);
                break;
            case R.id.late_issue_date:
                datePicker(latestDate);
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
                CheckoutKit ck = CheckoutKit.getInstance(PUBLIC_KEY_PAYMENT);


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
            showToast("success");

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            showToast("Payment Unsuccessful");
        }
    }
    private void populateAllCountrySpinner() {
        spnrAllCountries = (Spinner)view.findViewById(R.id.spnr_country);
        spnrAllCountries.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapterCountries = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, allCountriesData);
        dataAdapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrAllCountries.setAdapter(dataAdapterCountries);
        spnrAllCountries.setOnItemSelectedListener(this);
    }
    private void populatePortArrivalSpinner() {
        spnrPort = (Spinner)view.findViewById(R.id.spnr_port);
        spnrPort.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, arrivalPortData);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrPort.setAdapter(dataAdapterPort);
        spnrPort.setOnItemSelectedListener(this);
    }
    private void populateIssueCountrySpinner() {
        spnrIssueCountry = (Spinner)view.findViewById(R.id.spnr_country_issue);
        spnrIssueCountry.setOnItemSelectedListener(this);
        ArrayAdapter<String> issueDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, allCountriesData);
        issueDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrIssueCountry.setAdapter(issueDataAdapter);
    }
    private void populateGenderSpinner() {
        spnrGender = (Spinner)view.findViewById(R.id.spnr_gender);
        spnrGender.setOnItemSelectedListener(this);
        gender = new String[]{"Select One","Male","Female","Other"};
        ArrayAdapter<String> genderDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, gender);
        genderDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrGender.setAdapter(genderDataAdapter);
    }
    private void populateGenderUsaSpinner() {
        spnrGender = (Spinner)view.findViewById(R.id.spnr_usa_gender_applicant);
        spnrGender.setOnItemSelectedListener(this);
        gender = new String[]{"Select One","Male","Female","Other"};
        ArrayAdapter<String> genderDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, gender);
        genderDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrGender.setAdapter(genderDataAdapter);
    }
    private void populateMaritalUsaSpinner() {
        spnrMaritalUsa = (Spinner)view.findViewById(R.id.marital_status_form2_applicant);
        spnrMaritalUsa.setOnItemSelectedListener(this);
        marital = new String[]{"Select One","Male","Female","Other"};
        ArrayAdapter<String> maritalAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, marital);
        maritalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrMaritalUsa.setAdapter(maritalAdapter);
    }
    private void populateGCCSpinner() {
        spnrGCC = (Spinner)view.findViewById(R.id.spnr_sponsor);
        spnrGCC.setOnItemSelectedListener(this);
        gccList = new String[]{"Select One", "Estabishment","GCC Citizen", "GCC Resident"};
        ArrayAdapter<String> gccDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, gccList);
        gccDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrGCC.setAdapter(gccDataAdapter);
    }
    private void populateAddressTypeSpinner() {
        spnrAddressTypeSingapore = (Spinner)view.findViewById(R.id.spinner_address_type_singapore);
        spnrAddressTypeSingapore.setOnItemSelectedListener(this);
        addressType = new String[]{"Select One", "Hotel","Relative's Place", "Friends Place","Next of Kin's Place"};
        ArrayAdapter<String> addressTypeSingaporeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, addressType);
        addressTypeSingaporeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrAddressTypeSingapore.setAdapter(addressTypeSingaporeAdapter);
    }
    private void populateReligionSpinner() {
        spnrReligion = (Spinner)view.findViewById(R.id.spnr_religion);
        spnrReligion.setOnItemSelectedListener(this);
        religion = new String[]{"Select One","Bahai","Buddhism","Christian","Hindu","Islam","Jainism","Judaism","Parsi","Sikh","Zoroastrian","Other"};
        ArrayAdapter<String> religionDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, religion);
        religionDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrReligion.setAdapter(religionDataAdapter);
    }
    private void populateDurationSpinner() {
        spnrDuration = (Spinner)view.findViewById(R.id.spnr_duration);
        spnrDuration.setOnItemSelectedListener(this);
        duration = new String[]{"Select One","Upto 30 days", "More than 30 days"};
        ArrayAdapter<String> durationDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, duration);
        durationDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrDuration.setAdapter(durationDataAdapter);
    }
    private void populatePurposeSpinner() {
        spnrPurpose = (Spinner)view.findViewById(R.id.spnr_purpose);
        spnrPurpose.setOnItemSelectedListener(this);
        purpose = new String[]{"Select One","Holiday","Business Meeting","Family Visit", "Relative Visit", "Friend Visit", "Medical Treatment", "Transit"};
        ArrayAdapter<String> religionDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, purpose);
        religionDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrPurpose.setAdapter(religionDataAdapter);
    }
    private void populateEmiratesSpinner() {
        spnrEmirates = (Spinner)view.findViewById(R.id.emirates);
        spnrEmirates.setOnItemSelectedListener(this);
        ArrayAdapter<String> emirateDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, emiratesData);
        emirateDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrEmirates.setAdapter(emirateDataAdapter);
        if(sharedPreferences != null){
            int emirate = sharedPreferences.getInt("selected_emirate_id", 0);
            spnrEmirates.setSelection(emirate);
        }
    }
    private void populateConsulateSpinner() {
        spnrApplyingFrom = (Spinner)view.findViewById(R.id.spnr_apply_visa);
        spnrApplyingFrom.setOnItemSelectedListener(this);
        ArrayAdapter<String> cousulateAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, consulateData);
        cousulateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrApplyingFrom.setAdapter(cousulateAdapter);
    }
    private void intializeVisaForSpinner() {
        spnrVisaFor = (Spinner)view.findViewById(R.id.spnr_visa_type);
        spnrVisaFor.setOnItemSelectedListener(this);
        visaType = new String[]{"Select One","Tourist","Business"};
        ArrayAdapter<String> visaArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, visaType);
        visaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrVisaFor.setAdapter(visaArrayAdapter);
    }
    private void sendConsultData() {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", "")
                .addFormDataPart("start_date", savedArrivalDate)
                .addFormDataPart("end_date", savedDepartureDate)
                .addFormDataPart("pnrNo", "")
                .addFormDataPart("address1", edtAddress.getText().toString())
                .addFormDataPart("address2", "")
                .addFormDataPart("city", edtLivingCity.getText().toString())
                .addFormDataPart("country", livingIn)
                .addFormDataPart("country_code", codePhone)
                .addFormDataPart("mobile", edtMobile.getText().toString())
                .addFormDataPart("emirates_uae", selectedEmirate)
                .addFormDataPart("emergency_contact_name", edtEmergencyContactName.getText().toString())
                .addFormDataPart("emergency_contact_number", edtEmergencyContactNumber.getText().toString())
                .addFormDataPart("hotel_address", edtHotelAddress.getText().toString())
                .addFormDataPart("contact_uae", "")
                .addFormDataPart("created_date", "")
                .addFormDataPart("order_id", "")
                .addFormDataPart("service_type", serviceType)
                .addFormDataPart("nationality_id",nationalityId)
                .addFormDataPart("living_in_id", livingId)
                .addFormDataPart("currency_id", "")
                .addFormDataPart("govt_fee", String.valueOf(govtFee))
                .addFormDataPart("service_fee", String.valueOf(serviceFee))
                .addFormDataPart("processing_time",processingTime)
                .addFormDataPart("visa_type_id", String.valueOf(visaTypeId))
                .addFormDataPart("email_id", edtEmailContact.getText().toString())
                .addFormDataPart("email_varified", "")
                .addFormDataPart("comments_added", "")
                .addFormDataPart("insertedTimeIst", "")
                .addFormDataPart("agentid", "")
                .addFormDataPart("service_fee_cs","")
                .addFormDataPart("termConditions", "")
                .addFormDataPart("mng_fee", String.valueOf(mngFee))
                .addFormDataPart("application_type", fullNameVisa)
                .addFormDataPart("agentType", "")
                .addFormDataPart("device_type", "app")
                .addFormDataPart("device_os", ("Android:"+deviceOS))
                .build();
        Request request = new Request.Builder().url(BASE_URL_CONSULT_FORM).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {


            public static final String MODE_PRIVATE = "";

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    resp = response.body().string();
                    Log.v("Response", resp);
                    Log.v("Arrival",savedArrivalDate);
                    sendVerificationEmail(resp, edtEmailContact.getText().toString(), UAE_EMAIL_URL);
                    sharedPreferences.edit().putString("response",resp).apply();
                    if (response.isSuccessful()) {
                    }else {
                        //
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void sendOmanConsultData(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", "")
                .addFormDataPart("start_date", savedArrivalDate)
                .addFormDataPart("end_date", savedDepartureDate)
                .addFormDataPart("pnrNo", "")
                .addFormDataPart("address1", edtAddress.getText().toString())
                .addFormDataPart("address2", "")
                .addFormDataPart("city", edtLivingCity.getText().toString())
                .addFormDataPart("country", livingIn)
                .addFormDataPart("country_code", livingId)
                .addFormDataPart("mobile", edtMobile.getText().toString())
                .addFormDataPart("emergency_contact_name", edtEmergencyContactName.getText().toString())
                .addFormDataPart("emergency_contact_number", edtEmergencyContactNumber.getText().toString())
                .addFormDataPart("hotel_address", edtHotelAddress.getText().toString())
                .addFormDataPart("contact_uae", "")
                .addFormDataPart("created_date", "")
                .addFormDataPart("order_id", "")
                .addFormDataPart("service_type", serviceType)
                .addFormDataPart("nationality_id",nationalityId)
                .addFormDataPart("living_in_id", livingId)
                .addFormDataPart("currency_id", "")
                .addFormDataPart("govt_fee", String.valueOf(govtFee))
                .addFormDataPart("service_fee", String.valueOf(serviceFee))
                .addFormDataPart("processing_time",processingTime)
                .addFormDataPart("visa_type_id", String.valueOf(visaTypeId))
                .addFormDataPart("email_id", edtEmailContact.getText().toString())
                .addFormDataPart("email_varified", "")
                .addFormDataPart("comments_added", "")
                .addFormDataPart("insertedTimeIst", "")
                .addFormDataPart("agentid", "")
                .addFormDataPart("service_fee_cs","")
                .addFormDataPart("termConditions", "")
                .addFormDataPart("mng_fee", String.valueOf(mngFee))
                .addFormDataPart("application_type", fullNameVisa)
                .addFormDataPart("agentType", "")
                .addFormDataPart("device_type", "app")
                .addFormDataPart("device_os", ("Android:"+deviceOS))
                .build();
        Request request = new Request.Builder().url(BASE_URL_CONSULT_FORM_OMAN).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {


            public static final String MODE_PRIVATE = "";

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    resp = response.body().string();
                    Log.v("Response", resp);
                    sendVerificationEmail(resp, edtEmailContact.getText().toString(), UAE_EMAIL_URL);
                    sharedPreferences.edit().putString("response",resp).apply();
                    if (response.isSuccessful()) {
                    }else {
                        //
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }

    private void sendSingaporeConsultData(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", "")
                .addFormDataPart("start_date", savedArrivalDate)
                .addFormDataPart("end_date", savedDepartureDate)
                .addFormDataPart("pnrNo", "")
                .addFormDataPart("address1", edtAddress.getText().toString())
                .addFormDataPart("address2", "")
                .addFormDataPart("city", edtLivingCity.getText().toString())
                .addFormDataPart("country", livingIn)
                .addFormDataPart("country_code", livingId)
                .addFormDataPart("mobile", edtMobile.getText().toString())
                .addFormDataPart("emirates_uae", "")
                .addFormDataPart("emergency_contact_name", nameSingapore.getText().toString())
                .addFormDataPart("emergency_contact_number", phoneSingapore.getText().toString())
                .addFormDataPart("hotel_address", addressSingapore.getText().toString())
                .addFormDataPart("contact_uae", "")
                .addFormDataPart("nric_fin_no",nricSingapore.getText().toString())
                .addFormDataPart("created_date", "")
                .addFormDataPart("order_id", "")
                .addFormDataPart("service_type", serviceType)
                .addFormDataPart("nationality_id",nationalityId)
                .addFormDataPart("living_in_id", livingId)
                .addFormDataPart("currency_id", "")
                .addFormDataPart("govt_fee", String.valueOf(govtFee))
                .addFormDataPart("service_fee", String.valueOf(serviceFee))
                .addFormDataPart("processing_time",processingTime)
                .addFormDataPart("visa_type_id", String.valueOf(visaTypeId))
                .addFormDataPart("email_id", edtEmailContact.getText().toString())
                .addFormDataPart("email_varified", "")
                .addFormDataPart("comments_added", "")
                .addFormDataPart("insertedTimeIst", "")
                .addFormDataPart("agentid", String.valueOf(0))
                .addFormDataPart("service_fee_cs","")
                .addFormDataPart("termConditions", "")
                .addFormDataPart("mng_fee", String.valueOf(mngFee))
                .addFormDataPart("application_type", fullNameVisa)
                .addFormDataPart("agentType", "")
                .addFormDataPart("device_type", "app")
                .addFormDataPart("device_os", ("Android:"+deviceOS))
                .build();
        Request request = new Request.Builder().url(BASE_URL_CONSULT_FORM_SINGAPORE).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    resp = response.body().string();
                    Log.v("Response", resp);
                    sendVerificationEmail(resp, edtEmailContact.getText().toString(), SINGAPORE_EMAIL_URL);
                    sharedPreferences.edit().putString("response",resp).apply();
                    if (response.isSuccessful()) {
                    }else {

                    }
                } catch (IOException e) {
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void sendIranConsultData(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", "")
                .addFormDataPart("start_date", savedArrivalDate)
                .addFormDataPart("end_date", savedDepartureDate)
                .addFormDataPart("pnrNo", "")
                .addFormDataPart("address1", edtAddress.getText().toString())
                .addFormDataPart("address2", "")
                .addFormDataPart("city", edtLivingCity.getText().toString())
                .addFormDataPart("country", livingIn)
                .addFormDataPart("country_code", livingId)
                .addFormDataPart("mobile", edtMobile.getText().toString())
                .addFormDataPart("emergency_contact_name", edtEmergencyContactName.getText().toString())
                .addFormDataPart("emergency_contact_number", edtEmergencyContactNumber.getText().toString())
                .addFormDataPart("hotel_address", edtHotelAddress.getText().toString())
                .addFormDataPart("contact_uae", "")
                .addFormDataPart("created_date", "")
                .addFormDataPart("order_id", "")
                .addFormDataPart("service_type", serviceType)
                .addFormDataPart("nationality_id",nationalityId)
                .addFormDataPart("living_in_id", livingId)
                .addFormDataPart("currency_id", "")
                .addFormDataPart("govt_fee", String.valueOf(govtFee))
                .addFormDataPart("service_fee", String.valueOf(serviceFee))
                .addFormDataPart("processing_time",processingTime)
                .addFormDataPart("visa_type_id", String.valueOf(visaTypeId))
                .addFormDataPart("email_id", edtEmailContact.getText().toString())
                .addFormDataPart("email_varified", "")
                .addFormDataPart("comments_added", "")
                .addFormDataPart("insertedTimeIst", "")
                .addFormDataPart("agentid", "")
                .addFormDataPart("service_fee_cs","")
                .addFormDataPart("termConditions", "")
                .addFormDataPart("mng_fee", String.valueOf(mngFee))
                .addFormDataPart("application_type", fullNameVisa)
                .addFormDataPart("agentType", "")
                .addFormDataPart("no_of_person",sharedPreferences.getString("no_person",""))
                .addFormDataPart("entry_border",sharedPreferences.getString("border_entry",""))
                .addFormDataPart("stay_duration",sharedPreferences.getString("duration_stay",""))
                .addFormDataPart("consulate_id", String.valueOf(sharedPreferences.getInt("consulate_id",0)))
                .addFormDataPart("device_type", "app")
                .addFormDataPart("device_os", ("Android:"+deviceOS))
                .build();
        Request request = new Request.Builder().url(BASE_URL_CONSULT_FORM_IRAN).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {


            public static final String MODE_PRIVATE = "";

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    resp = response.body().string();
                    Log.v("Response", resp);
                    sendVerificationEmail(resp, edtEmailContact.getText().toString(), IRAN_EMAIL_URL);
                    sharedPreferences.edit().putString("response",resp).apply();
                    if (response.isSuccessful()) {
                    }else {
                        //
                    }
                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void sendUsaFormData1(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", "")
                .addFormDataPart("vad_id", "")
                .addFormDataPart("first_name", firstNameUsa.getText().toString())
                .addFormDataPart("last_name", lastNameUsa.getText().toString())
                .addFormDataPart("phone_code", "")
                .addFormDataPart("mobile_num", phoneUsa.getText().toString())
                .addFormDataPart("email", emailUsa.getText().toString())
                .addFormDataPart("email_varified","N")
                .addFormDataPart("service_type","")
                .addFormDataPart("consulate_status", "")
                .addFormDataPart("visa_status","")
                .addFormDataPart("created_date","")
                .addFormDataPart("dateOfArrival", arrivalDateUsa.getText().toString())
                .addFormDataPart("dateOfDept", departureDateUsa.getText().toString())
                .build();
        Request request = new Request.Builder().url(BASE_URL_USA_FORM1).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        Log.v("Data", emailUsa.getText().toString() + phoneUsa.getText().toString());
        call.enqueue(new okhttp3.Callback() {


            public static final String MODE_PRIVATE = "";

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                try {
                    resp = response.body().string();
                    Log.v("Response", resp);
                    sharedPreferences.edit().putString("first_name_usa", firstNameUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("last_name_usa", lastNameUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("email_usa", emailUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("phone_usa", phoneUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("date_arrival_usa", arrivalDateUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("date_departure_usa", departureDateUsa.getText().toString()).apply();
                    sendVerificationEmail(resp, emailUsa.getText().toString(), USA_EMAIL_URL);
                    sharedPreferences.edit().putString("response_usa",resp).apply();
                    if (response.isSuccessful()) {
                    }else {

                    }
                } catch (IOException e) {
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void sendUsaFormData2(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("last_id",sharedPreferences.getString("response_usa",""))
                .addFormDataPart("gender", "Male")
                .addFormDataPart("MaritalStatus", "Single")
                .addFormDataPart("Address1", addressApplicantUsa.getText().toString())
                .addFormDataPart("Address2", "")
                .addFormDataPart("City", cityApplicantUsa.getText().toString())
                .addFormDataPart("placeBirth", placeBirthUsa.getText().toString())
                .addFormDataPart("zipcode", postalUsa.getText().toString())
                .addFormDataPart("passportnumber", passportNumberUsa.getText().toString())
                .addFormDataPart("placeOfissue", cityIssuedUsa.getText().toString())
                .addFormDataPart("issuecountry", countryIssuedUsa.getText().toString())
                .addFormDataPart("issuedate", issueDateUsa.getText().toString())
                .addFormDataPart("expirydate", expiryDateUsa.getText().toString())
                .build();
        Request request = new Request.Builder().url(BASE_URL_USA_FORM2).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {


            public static final String MODE_PRIVATE = "";

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                try {
                    resp = response.body().string();
                    Log.v("Response", resp);
                    sharedPreferences.edit().putString("nationality_usa",nationalityUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("address_usa",addressApplicantUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("city_usa",cityApplicantUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("place_birth_usa",placeBirthUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("state_usa",stateUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("postal_code_usa",postalUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("country_usa",countryUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("passport_num_usa",passportNumberUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("city_issued_usa",cityIssuedUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("country_issued_usa",countryIssuedUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("issue_date_usa",issueDateUsa.getText().toString()).apply();
                    sharedPreferences.edit().putString("expiry_date_usa",expiryDateUsa.getText().toString()).apply();
                    //sendVerificationEmail(resp, edtEmailContact.getText().toString(), UAE_EMAIL_URL);
                    //sharedPreferences.edit().putString("response",resp).apply();
                    if (response.isSuccessful()) {
                    }else {
                        //
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void sendVerificationEmail(String resp, String emailEdt, String uaeEmailUrl) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email","em")
                .build();
        String emailUrl = (uaeEmailUrl +"&gofor=email_send&visa_id="+resp+"+&email_id="+emailEdt);
        Request request = new Request.Builder().url(emailUrl).post(requestBody).build();
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
                    if (response.isSuccessful()) {

                    } else {
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void loadConsulateData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://iranvisas.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ConsulateInterface request = retrofit.create(ConsulateInterface.class);
        retrofit2.Call<Consulate> call = request.getConsulate();

        call.enqueue(new retrofit2.Callback<Consulate>() {
            @Override
            public void onResponse(retrofit2.Call<Consulate> call, retrofit2.Response<Consulate> response) {
                Consulate jsonResponse = response.body();
                consulate = jsonResponse.getConsulate();
                for(int i=0;i<consulate.size();i++){
                    consulateData.add(consulate.get(i).getConsulateName());
                }
                populateConsulateSpinner();
            }
            @Override
            public void onFailure(retrofit2.Call<Consulate> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
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
    private void disableInput(EditText editText) {
        editText.setFocusable(false);
    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    private void showToast(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }
    @TargetApi(Build.VERSION_CODES.N)
    private void datePicker(final EditText edtDate1) {
        Calendar mcurrentDate=Calendar.getInstance();
        final int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                edtDate1.setText(selectedyear +"-"+(selectedmonth+1)+"-"+selectedday);
            }
        },mYear, mMonth, mDay);
        mDatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }




}
