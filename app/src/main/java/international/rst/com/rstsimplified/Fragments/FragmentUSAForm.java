package international.rst.com.rstsimplified.Fragments;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Model.AllCountryResponse;
import international.rst.com.rstsimplified.Model.Consulate;
import international.rst.com.rstsimplified.Model.ConsulateInterface;
import international.rst.com.rstsimplified.Model.ConsulateResponse;
import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.Model.OccupationResponseUsa;
import international.rst.com.rstsimplified.Model.Profession;
import international.rst.com.rstsimplified.Model.ProfessionRes;
import international.rst.com.rstsimplified.R;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentUSAForm extends android.support.v4.app.Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    String title, resp;
    View view;
    ViewPager mViewPager;
    int atTab;
    SharedPreferences sharedPreferences;
    String[] gender, martialStatus, documentType, stolen, contactPersonUs, relationPerson, indentedLength, payingTrip;
    Button buttonForm1, buttonForm2, buttonForm3, buttonForm4, buttonForm5, buttonForm6, buttonForm7, buttonForm8, buttonForm9, buttonForm10, buttonForm11, buttonForm12, buttonForm13, buttonForm14, buttonForm15;
    EditText arrivingDate, departureDate, currentAddress, currentCity, phoneCode, mobileNumberCurrent, countryCurrent, email, surName, givenName, nationalIdentificationNumber, usSecurityNumber, taxPayerId, maritalStatus, placeOfBirth, dateOfBirth, homeAddress, city, pinCode, state, country, countryCode, primaryPhoneNumber, secondaryPhoneNumber, emailAddress, passportNumber, passportBookNumber, issueCountry, issueCity, issueDate, purposeUS, noMonths, addressStayUS, personPayingTrip, nameContactUs, addressContactUs, codeContactUs, numberContactUs, fatherName, fatherDateBirth, motherName, motherDateBirth, employerName, addressEmployer, cityEmployment, countryEmployment, codeEmployment, contactNumberEmployment, languageSpeaking, consulateCity, interviewPriority1, interviewPriority2, interviewPriority3, biometricPriority1, biometricPriority2, biometricPriority3, deliveryAddress, deliveryState, deliveryCity, deliveryPinCode, nameFirst, nameLast, placeOfBirthForm2, stateCurrent, postalCodeCurrent, passportNumberForm2, issuedCity, issuedCountry, issuedDate, expiryDate, expiryDateForm2, currentNationality;
    Spinner birthCountrySpnr, birthStateSpnr, nationalitySpnr, travelDocType, stolenPassport, contactUs, relationContactUs, stateContactUs, primaryOccupation, interViewState, interviewConsulate, spnrGender, spnrMarital, stayLenght, personPaying;
    RadioGroup rgOtherName, rgTelecode, rgGender, rgOtherNationality, rgPermanentResident, rgMailAddress, rgotherPersonTravelling, rgTravelledUS, rgIssuedUsVisa, rgRefusedUsVisa, rgFatherUs, rgMotherUs, rgPriviousEmployed, rgTravelledCountries, rgContributedOrg, rgSpecializedSkill, rgServedMilitary, rgParamilitary, rgCommunicableDisease, rgMentalDisorder, rgdrugAbuser, rgArrested, rgViolated, rgMoneyLaundering, rgHumanTrafficing, rgHumanTrafficingAided, rgRelativeHumanTrafficing, rgIllegal, rgTerrorist, rgSupportTerrorist, rgTerroristMember, rgGenocide, rgTorture, rgKilling, rgChildSoldiers, rgReligiousFreedom, rgAbortion, rgTransplantation, rgFraudVisa, rgCustody, rgUsChild, rgViolatedLaw, rgAvoidingTaxation, rgProstitution;
    RadioButton rbOtherName1, rbOtherName2, rbTelecode1, rbTelecode2, rbGender1, rbGender2, rbotherNationality1, rbOtherNationality2, rbPermanentResident1, rbPermanentResident2, rbMailAddress1, rbMailAddress2, rbPersonTravelling1, rbPersonTravelling2, rbTravelledUs1, rbTravelledUs2, rbIssued1, rbIssued2, rbRefused1, rbRefused2, rbFatherUs1, rbFatherUs2, rbMotherUs1, rbMotherUs2, rbPriviousEmployed1, rbPreviousEmployed2, rbTravelledCountry1, rbTravelledCountry2, rbContributed1, rbContributed2, rbSpecializedSkill1, rbSpecializesSkill2, rbServedMilitary1, rbServedMilitary2, rbParamilitary1, rbParamilitary2, rbCommunicable1, rbCommunicable2, rbMental1, rbMental2, rbDrugAddict1, rbDrugAddict2, rbArrested1, rbArrested2, rbViolatedLaw1, rbViolatedLaw2, rbMoney1, rbMoney2, rbHumanTraffic1, rbHumanTraffic2, rbAidedHuman1, rbAidedHuman2, rbRelativeHuman1, rbRelativeHuman2, rbIllegalActivity1, rbIllegalActivity2, rbTerroristActivity1, rbTerroristActivity2, rbSupportTerrorist1, rbSupportTerrorist2, rbTerrorist1, rbTerrorist2, rbGenocide1, rbGenocide2, rbTorture1, rbTorture2, rbKilling1, rbKilling2, rbChildSoldier1, rbChildSoldier2, rbReligiousFreedom1, rbReligiousFreedom2, rbAbortion1, rbAbortion2, rbTransplant1, rbTransplant2, rbFraudVisa1, rbFraudVisa2, rbCustody1, rbCustody2, rbVoted1, rbVoted2, rbAvoidingTaxation1, rbAvoidingTaxation2, rbProstitution1, rbProstitution2;
    ImageView checked1, checked2, checked3, checked4,  attach1, attach2, attach3, attach4;
    private OkHttpClient client = new OkHttpClient();
    private List<ProfessionRes> professionList = new ArrayList<>();
    private List<String> occupation = new ArrayList<>();
    private List<String> allCountriesData = new ArrayList<>();
    private List<CountryRes> allcountry = new ArrayList<>();
    private List<ConsulateResponse> consulate = new ArrayList<>();
    private List<String> consulateData = new ArrayList<>();
    private static final String BASE_URL_FORM3 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM4 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM5 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM6 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM7 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM8 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM9 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM10 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM11 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM12 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM13 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    private static final String BASE_URL_FORM14 = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data1";
    public static FragmentUSAForm newFormInstance( String title) {
        FragmentUSAForm fragmentUsaForm = new FragmentUSAForm();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentUsaForm.setArguments(args);
        return fragmentUsaForm;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getActivity());
        getSharedPreferences();

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        //loadDataApi();
        mViewPager = (ViewPager)getActivity().findViewById(R.id.usa_form_view_pager);
        if(title.equalsIgnoreCase("form1")){
            view = inflater.inflate(R.layout.usa_form1,container, false);
            initializeForm1View();
        }
        else if(title.equalsIgnoreCase("form2")){
            view = inflater.inflate(R.layout.usa_form2,container,false);
            initializeForm2View();
        }
        else if(title.equalsIgnoreCase("form3")){
            view = inflater.inflate(R.layout.usa_form3,container,false);
            initializeForm3View();
        }
        else if(title.equalsIgnoreCase("form4")){
            view = inflater.inflate(R.layout.usa_form4,container,false);
            initializeForm4View();
        }
        else if(title.equalsIgnoreCase("form5")){
            view = inflater.inflate(R.layout.usa_form5,container,false);
            initializeForm5View();
            populateDocumentTypeSpinner();
        }
        else if(title.equalsIgnoreCase("form6")){
            view = inflater.inflate(R.layout.usa_form6,container,false);
            populatePersonPayingTrip();
            populateIndentedLength();
            initializeForm6View();
        }
        else if(title.equalsIgnoreCase("form7")){
            view = inflater.inflate(R.layout.usa_form7,container,false);
            initializeForm7View();
        }
        else if(title.equalsIgnoreCase("form8")){
            view = inflater.inflate(R.layout.usa_form8,container,false);
            initializeForm8View();
        }
        else if(title.equalsIgnoreCase("form9")){
            view = inflater.inflate(R.layout.usa_form9,container,false);
            populateRelationPersonSpinner();
            populateContactPersonUsSpinner();
            initializeForm9View();
        }
        else if(title.equalsIgnoreCase("form10")){
            view = inflater.inflate(R.layout.usa_form10,container,false);
            initializeForm10View();
        }
        else if(title.equalsIgnoreCase("form11")){
            view = inflater.inflate(R.layout.usa_form11,container,false);
            initializeForm11View();
        }
        else if(title.equalsIgnoreCase("form12")){
            view = inflater.inflate(R.layout.usa_form12,container,false);
            initializeForm12View();
        }
        else if(title.equalsIgnoreCase("form13")){
            view = inflater.inflate(R.layout.usa_form13,container,false);
            initializeForm13View();
        }
        else if(title.equalsIgnoreCase("form14")){
            view = inflater.inflate(R.layout.usa_form14,container,false);
            interviewConsulate = (Spinner)view.findViewById(R.id.spnr_consulate_interview);
            loadConsulate();
            initializeForm14View();
        }
        else if(title.equalsIgnoreCase("form15")){
            view = inflater.inflate(R.layout.usa_form15,container,false);
            initializeForm15View();
        }
        return view;
    }

    private void initializeForm1View() {
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getActivity());
        nameFirst = (EditText)view.findViewById(R.id.name_first_usa);
        nameLast = (EditText)view.findViewById(R.id.name_last_usa);
        email = (EditText)view.findViewById(R.id.email_usa);
        mobileNumberCurrent = (EditText)view.findViewById(R.id.phone_applicant_usa);
        arrivingDate = (EditText)view.findViewById(R.id.edt_arrival_usa);
        departureDate = (EditText)view.findViewById(R.id.edt_departure_usa);
        buttonForm1 = (Button)view.findViewById(R.id.button_form1);
        nameFirst.setText(sharedPreferences.getString("first_name_usa",""));
        nameLast.setText(sharedPreferences.getString("last_name_usa",""));
        email.setText(sharedPreferences.getString("email_usa",""));
        mobileNumberCurrent.setText(sharedPreferences.getString("phone_usa",""));
        arrivingDate.setText(sharedPreferences.getString("date_arrival_usa", ""));
        departureDate.setText(sharedPreferences.getString("date-departure_usa",""));
        buttonForm1.setOnClickListener(this);
    }
    private void initializeForm2View() {
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getActivity());
        currentNationality = (EditText)view.findViewById(R.id.current_nationality);
        currentNationality.setText(sharedPreferences.getString("nationality_usa",""));
        currentAddress = (EditText) view.findViewById(R.id.current_address);
        currentAddress.setText(sharedPreferences.getString("address_usa",""));
        currentCity = (EditText)view.findViewById(R.id.current_city_form2);
        currentCity.setText(sharedPreferences.getString("city_usa",""));
        placeOfBirthForm2 = (EditText)view.findViewById(R.id.place_of_birth_form2);
        placeOfBirthForm2.setText(sharedPreferences.getString("place_birth_usa",""));
        stateCurrent = (EditText)view.findViewById(R.id.current_state_form2);
        stateCurrent.setText(sharedPreferences.getString("state_usa",""));
        postalCodeCurrent = (EditText)view.findViewById(R.id.postal_code_current);
        postalCodeCurrent.setText(sharedPreferences.getString("postal_code_usa",""));
        countryCurrent = (EditText)view.findViewById(R.id.current_country);
        countryCurrent.setText(sharedPreferences.getString("country_usa",""));
        passportNumberForm2 = (EditText)view.findViewById(R.id.passport_number_form2);
        passportNumberForm2.setText(sharedPreferences.getString("passport_num_usa",""));
        issueCity = (EditText)view.findViewById(R.id.city_issued_form2);
        issueCity.setText(sharedPreferences.getString("city_issued_usa",""));
        issuedCountry = (EditText)view.findViewById(R.id.country_issue_form2);
//        issueCountry.setText(sharedPreferences.getString("country_issued_usa",""));
        issueDate = (EditText)view.findViewById(R.id.issue_date_form2);
        issueDate.setText(sharedPreferences.getString("issue_date_usa",""));
        expiryDateForm2 = (EditText)view.findViewById(R.id.expiry_date_form2);
        expiryDateForm2.setText(sharedPreferences.getString("expiry_date_usa",""));
        buttonForm2 = (Button)view.findViewById(R.id.button_form2);
        buttonForm2.setOnClickListener(this);
        populateGenderSpinner();
        populateMaritalStatusSpinner();
        populateStolenPassportSpinner();
    }
    private void initializeForm3View() {
        birthCountrySpnr = (Spinner)view.findViewById(R.id.spnr_birth_country);
        nationalitySpnr = (Spinner)view.findViewById(R.id.country_origin);
        loadAllCountries();
        surName = (EditText)view.findViewById(R.id.surname);
        givenName = (EditText)view.findViewById(R.id.given_name);
        rgOtherName = (RadioGroup)view.findViewById(R.id.rg_other_name);
        rbOtherName1 = (RadioButton)view.findViewById(R.id.rb_other_name1);
        rbOtherName2 = (RadioButton)view.findViewById(R.id.rb_other_name2);
        rgTelecode = (RadioGroup)view.findViewById(R.id.rg_telecode);
        rbTelecode1 = (RadioButton)view.findViewById(R.id.rb_telecode1);
        rbTelecode2 = (RadioButton)view.findViewById(R.id.rb_telecode2);
        rgGender = (RadioGroup)view.findViewById(R.id.rg_gender);
        rbGender1 = (RadioButton)view.findViewById(R.id.rb_gender1);
        rbGender2 = (RadioButton)view.findViewById(R.id.rb_gender2);
        maritalStatus  = (EditText)view.findViewById(R.id.marital_status);
        dateOfBirth = (EditText)view.findViewById(R.id.date_of_birth);
        placeOfBirth = (EditText)view.findViewById(R.id.place_of_birth);
        rgOtherNationality = (RadioGroup)view.findViewById(R.id.rg_other_nationality);
        rbotherNationality1 = (RadioButton)view.findViewById(R.id.rb_other_nationality1);
        rbOtherNationality2 = (RadioButton)view.findViewById(R.id.rb_other_nationality2);
        rgPermanentResident = (RadioGroup)view.findViewById(R.id.rg_permanent_resident_country);
        rbPermanentResident1 = (RadioButton)view.findViewById(R.id.rb_permanent_country1);
        rbPermanentResident2 = (RadioButton)view.findViewById(R.id.rb_permanent_country2);
        nationalIdentificationNumber = (EditText)view.findViewById(R.id.identification_number);
        usSecurityNumber = (EditText)view.findViewById(R.id.security_number);
        taxPayerId = (EditText)view.findViewById(R.id.tax_payer_id);
        buttonForm3 = (Button)view.findViewById(R.id.button_form3);
        buttonForm3.setOnClickListener(this);
        rgOtherName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbOtherName1.isChecked()){
                }
                else if(rbOtherName2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgTelecode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTelecode1.isChecked()){
                }
                else if(rbTelecode2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbGender1.isChecked()){
                }
                else if(rbGender2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgOtherNationality.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbotherNationality1.isChecked()){
                }
                else if(rbOtherNationality2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgPermanentResident.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbPermanentResident1.isChecked()){
                }
                else if(rbPermanentResident2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
    }
    private void initializeForm4View() {
        currentAddress = (EditText)view.findViewById(R.id.current_address);
        currentCity = (EditText)view.findViewById(R.id.current_city);
        pinCode = (EditText)view.findViewById(R.id.current_city_postal);
        state = (EditText)view.findViewById(R.id.current_state);
        countryCurrent = (EditText)view.findViewById(R.id.current_country);
        rgMailAddress = (RadioGroup)view.findViewById(R.id.rg_mailing_address);
        rbMailAddress1 = (RadioButton)view.findViewById(R.id.rb_mailing_address1);
        rbMailAddress2 = (RadioButton)view.findViewById(R.id.rb_mailing_address2);
        countryCode = (EditText)view.findViewById(R.id.country_code);
        primaryPhoneNumber = (EditText)view.findViewById(R.id.primary_phone_number);
        secondaryPhoneNumber = (EditText)view.findViewById(R.id.secondary_phone_number);
        emailAddress = (EditText)view.findViewById(R.id.email_address);
        buttonForm4 = (Button)view.findViewById(R.id.button_form4);
        buttonForm4.setOnClickListener(this);
        rgMailAddress.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbMailAddress1.isChecked()){
                }
                else if(rbMailAddress2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
    }
    private void initializeForm5View() {
        passportNumber = (EditText)view.findViewById(R.id.travel_document_number);
        passportBookNumber = (EditText)view.findViewById(R.id.passport_book_number);
        issueCountry = (EditText)view.findViewById(R.id.issue_country_name);
        issueCity = (EditText)view.findViewById(R.id.issued_city);
        issueCountry = (EditText)view.findViewById(R.id.issued_country);
        issueDate = (EditText)view.findViewById(R.id.issued_date);
        expiryDate = (EditText)view.findViewById(R.id.expiry_date);
        buttonForm5 = (Button)view.findViewById(R.id.button_form5);
        buttonForm5.setOnClickListener(this);
    }
    private void initializeForm6View() {
        purposeUS = (EditText)view.findViewById(R.id.purpose_us);
        noMonths = (EditText)view.findViewById(R.id.no_of_months);
        addressStayUS = (EditText)view.findViewById(R.id.address_us_stay);
        buttonForm6 = (Button)view.findViewById(R.id.button_form6);
        buttonForm6.setOnClickListener(this);
    }
    private void initializeForm7View() {
        rgotherPersonTravelling = (RadioGroup)view.findViewById(R.id.rg_travelling_with);
        rbPersonTravelling1 = (RadioButton)view.findViewById(R.id.rb_person_travelling1);
        rbPersonTravelling2 = (RadioButton)view.findViewById(R.id.rb_person_travelling2);
        buttonForm7 = (Button)view.findViewById(R.id.button_form7);
        buttonForm7.setOnClickListener(this);
        rgotherPersonTravelling.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbPersonTravelling1.isChecked()){
                }
                else if(rbPersonTravelling2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
    }
    private void initializeForm8View() {
        rgTravelledUS = (RadioGroup)view.findViewById(R.id.rg_visited_us);
        rbTravelledUs1 = (RadioButton)view.findViewById(R.id.rb_visited_us1);
        rbTravelledUs2 = (RadioButton)view.findViewById(R.id.rb_visited_us2);
        rgIssuedUsVisa = (RadioGroup)view.findViewById(R.id.rg_issued_us_visa);
        rbIssued1 = (RadioButton)view.findViewById(R.id.rb_issued_us_visa1);
        rbIssued2 = (RadioButton)view.findViewById(R.id.rb_issued_us_visa2);
        rgRefusedUsVisa = (RadioGroup)view.findViewById(R.id.rg_refused_us_visa);
        rbRefused1 = (RadioButton)view.findViewById(R.id.rb_refused_us_visa1);
        rbRefused2 = (RadioButton)view.findViewById(R.id.rb_refused_us_visa2);
        buttonForm8 = (Button)view.findViewById(R.id.button_form8);
        rgTravelledUS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTravelledUs1.isChecked()){
                }
                else if(rbTravelledUs2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgIssuedUsVisa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbIssued1.isChecked()){
                }
                else if(rbIssued2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgRefusedUsVisa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbRefused1.isChecked()){
                }
                else if(rbRefused2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        buttonForm8.setOnClickListener(this);
    }
    private void initializeForm9View() {

        nameContactUs = (EditText)view.findViewById(R.id.full_name_contact_us);
        addressContactUs = (EditText) view.findViewById(R.id.address_contact_us);
        stateContactUs = (Spinner)view.findViewById(R.id.spnr_state_contact_us);
        codeContactUs = (EditText)view.findViewById(R.id.phone_code_contact_us);
        numberContactUs = (EditText)view.findViewById(R.id.contact_number_us);
        buttonForm9 = (Button)view.findViewById(R.id.button_form9);
        buttonForm9.setOnClickListener(this);
    }
    private void initializeForm10View() {
        fatherName = (EditText)view.findViewById(R.id.full_name_father);
        fatherDateBirth = (EditText)view.findViewById(R.id.father_date_of_birth);
        rgFatherUs = (RadioGroup)view.findViewById(R.id.rg_father_usa);
        rbFatherUs1 = (RadioButton)view.findViewById(R.id.rb_father_usa1);
        rbFatherUs2 = (RadioButton)view.findViewById(R.id.rb_father_usa2);
        motherName = (EditText)view.findViewById(R.id.full_name_mother);
        motherDateBirth = (EditText)view.findViewById(R.id.mother_date_of_birth);
        rgMotherUs = (RadioGroup)view.findViewById(R.id.rg_mother_usa);
        rbMotherUs1 = (RadioButton)view.findViewById(R.id.rb_mother_usa1);
        rbMotherUs2 = (RadioButton)view.findViewById(R.id.rb_mother_usa2);
        buttonForm10 = (Button)view.findViewById(R.id.button_form10);
        buttonForm10.setOnClickListener(this);
        rgFatherUs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbFatherUs1.isChecked()){
                }
                else if(rbFatherUs2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgMotherUs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbMotherUs1.isChecked()){
                }
                else if(rbMotherUs2.isChecked()){

                }
                else {
                    showToast("Check all field");
                }
            }
        });
    }
    private void initializeForm11View() {
        primaryOccupation = (Spinner)view.findViewById(R.id.spnr_primary_occupation);
        loadDataApi();
        employerName = (EditText)view.findViewById(R.id.employer_name);
        addressEmployer = (EditText)view.findViewById(R.id.employer_address);
        cityEmployment = (EditText)view.findViewById(R.id.employer_city);
        countryEmployment = (EditText)view.findViewById(R.id.employer_country);
        codeEmployment = (EditText)view.findViewById(R.id.employer_code);
        contactNumberEmployment = (EditText)view.findViewById(R.id.employer_number);
        buttonForm11 = (Button)view.findViewById(R.id.button_form11);
        buttonForm11.setOnClickListener(this);
    }
    private void initializeForm12View() {
        rgPriviousEmployed = (RadioGroup)view.findViewById(R.id.rg_previous_employed);
        rbPriviousEmployed1 = (RadioButton)view.findViewById(R.id.rb_previous_employed1);
        rbPreviousEmployed2 = (RadioButton)view.findViewById(R.id.rb_previous_employed2);
        languageSpeaking = (EditText)view.findViewById(R.id.speaking_languages);
        rgTravelledCountries = (RadioGroup)view.findViewById(R.id.rg_travelled_country);
        rbTravelledCountry1 = (RadioButton)view.findViewById(R.id.rb_travelled_country1);
        rbTravelledCountry2 = (RadioButton)view.findViewById(R.id.rb_travelled_country2);
        rgContributedOrg = (RadioGroup)view.findViewById(R.id.rg_social_work);
        rbContributed1 = (RadioButton)view.findViewById(R.id.rb_social_work1);
        rbContributed2 = (RadioButton)view.findViewById(R.id.rb_social_work2);
        rgSpecializedSkill = (RadioGroup)view.findViewById(R.id.rg_special_skills);
        rbSpecializedSkill1 = (RadioButton)view.findViewById(R.id.rb_special_skill1);
        rbSpecializesSkill2 = (RadioButton)view.findViewById(R.id.rb_special_skill2);
        rgServedMilitary = (RadioGroup)view.findViewById(R.id.rg_served_military);
        rbServedMilitary1 = (RadioButton)view.findViewById(R.id.rb_served_military1);
        rbServedMilitary2 = (RadioButton)view.findViewById(R.id.rb_served_military2);
        rgParamilitary = (RadioGroup)view.findViewById(R.id.rg_paramilitary);
        rbParamilitary1 = (RadioButton)view.findViewById(R.id.rb_paramilitary1);
        rbParamilitary2 = (RadioButton)view.findViewById(R.id.rb_paramilitary2);
        buttonForm12 = (Button)view.findViewById(R.id.button_form12);
        buttonForm12.setOnClickListener(this);
        rgPriviousEmployed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
        rgTravelledCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
        rgContributedOrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
        rgSpecializedSkill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
        rgServedMilitary.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
        rgParamilitary.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
    }
    private void initializeForm13View() {
        rgCommunicableDisease = (RadioGroup)view.findViewById(R.id.rg_communicable_disease);
        rbCommunicable1 = (RadioButton)view.findViewById(R.id.rb_communicable_disease1);
        rbCommunicable2 = (RadioButton)view.findViewById(R.id.rb_communicable_disease2);
        rgMentalDisorder = (RadioGroup)view.findViewById(R.id.rg_mental_disorder);
        rbMental1 = (RadioButton)view.findViewById(R.id.rb_mental_disorder1);
        rbMental2 = (RadioButton)view.findViewById(R.id.rb_mental_disorder2);
        rgdrugAbuser = (RadioGroup)view.findViewById(R.id.rg_drug_addict);
        rbDrugAddict1 = (RadioButton)view.findViewById(R.id.rb_drug_addict1);
        rbDrugAddict2 = (RadioButton)view.findViewById(R.id.rb_drug_addict2);
        rgArrested = (RadioGroup)view.findViewById(R.id.rg_arrested);
        rbArrested1 = (RadioButton)view.findViewById(R.id.rb_arrested1);
        rbArrested2 = (RadioButton)view.findViewById(R.id.rb_arrested2);
        rgViolatedLaw = (RadioGroup)view.findViewById(R.id.rg_violation_law);
        rbViolatedLaw1 = (RadioButton)view.findViewById(R.id.rb_violation_law1);
        rbViolatedLaw2 = (RadioButton)view.findViewById(R.id.rb_violation_law2);
        rgMoneyLaundering = (RadioGroup)view.findViewById(R.id.rg_money_laundering);
        rbMoney1  =(RadioButton)view.findViewById(R.id.rb_money_laundering1);
        rbMoney2 = (RadioButton)view.findViewById(R.id.rb_money_laundering2);
        rgHumanTrafficing = (RadioGroup)view.findViewById(R.id.rg_human_trafficking);
        rbHumanTraffic1 = (RadioButton)view.findViewById(R.id.rb_human_trafficking1);
        rbHumanTraffic2 = (RadioButton)view.findViewById(R.id.rb_human_trafficking2);
        rgHumanTrafficingAided = (RadioGroup)view.findViewById(R.id.rg_aided_human_trafficking);
        rbAidedHuman1 = (RadioButton)view.findViewById(R.id.rb_aided_human_trafficking1);
        rbAidedHuman2 = (RadioButton)view.findViewById(R.id.rb_aided_human_trafficking2);
        rgRelativeHumanTrafficing = (RadioGroup)view.findViewById(R.id.rg_human_trafficking_relative);
        rbRelativeHuman1 = (RadioButton)view.findViewById(R.id.rb_human_trafficking_relative1);
        rbRelativeHuman2 = (RadioButton)view.findViewById(R.id.rb_human_trafficking_relative2);
        rgIllegal = (RadioGroup)view.findViewById(R.id.rg_illegal_us);
        rbIllegalActivity1 = (RadioButton)view.findViewById(R.id.rb_illegal1);
        rbIllegalActivity2 = (RadioButton)view.findViewById(R.id.rb_illegal2);
        rgTerrorist = (RadioGroup)view.findViewById(R.id.rg_terrorist_activity);
        rbTerroristActivity1 = (RadioButton)view.findViewById(R.id.rb_terrorist_activity1);
        rbTerroristActivity2 = (RadioButton)view.findViewById(R.id.rb_terrorist_activity2);
        rgSupportTerrorist = (RadioGroup)view.findViewById(R.id.rg_support_terrorist);
        rbSupportTerrorist1 = (RadioButton)view.findViewById(R.id.rb_support_terrorist1);
        rbSupportTerrorist2 = (RadioButton)view.findViewById(R.id.rb_support_terrorist2);
        rgTerroristMember = (RadioGroup)view.findViewById(R.id.rg_terrorist1);
        rbTerrorist1 = (RadioButton)view.findViewById(R.id.rb_terrorist1);
        rbTerrorist2 = (RadioButton)view.findViewById(R.id.rb_terrorist2);
        rgGenocide = (RadioGroup)view.findViewById(R.id.rg_genocide);
        rbGenocide1 = (RadioButton)view.findViewById(R.id.rb_genocide1);
        rbGenocide2 = (RadioButton)view.findViewById(R.id.rb_genocide2);
        rgTorture = (RadioGroup)view.findViewById(R.id.rg_participated_torture);
        rbTorture1 = (RadioButton)view.findViewById(R.id.rb_torture1);
        rbTorture2 = (RadioButton)view.findViewById(R.id.rb_torture2);
        rgKilling = (RadioGroup)view.findViewById(R.id.rg_killing);

        rbKilling1 = (RadioButton)view.findViewById(R.id.rb_killing1);
        rbKilling2 = (RadioButton)view.findViewById(R.id.rb_killing2);
        rgChildSoldiers = (RadioGroup)view.findViewById(R.id.rg_child_soldiers);
        rbChildSoldier1 = (RadioButton)view.findViewById(R.id.rb_child_soldier1);
        rbChildSoldier2 = (RadioButton)view.findViewById(R.id.rb_child_soldier2);
        rgReligiousFreedom = (RadioGroup)view.findViewById(R.id.rg_religious_freedom);
        rbReligiousFreedom1 = (RadioButton)view.findViewById(R.id.rb_religious_freedom1);
        rbReligiousFreedom2 = (RadioButton)view.findViewById(R.id.rb_religious_freedom2);
        rgAbortion = (RadioGroup)view.findViewById(R.id.rg_abortion);
        rbAbortion1 = (RadioButton)view.findViewById(R.id.rb_abortion1);
        rbAbortion2 = (RadioButton)view.findViewById(R.id.rb_abortion2);
        rgTransplantation = (RadioGroup)view.findViewById(R.id.rg_transplant);
        rbTransplant1 = (RadioButton)view.findViewById(R.id.rb_transplant1);
        rbTransplant2 = (RadioButton)view.findViewById(R.id.rb_transplant2);
        rgFraudVisa = (RadioGroup)view.findViewById(R.id.rg_fraud_visa);
        rbFraudVisa1 = (RadioButton)view.findViewById(R.id.rb_fraud_visa1);
        rbFraudVisa2 = (RadioButton)view.findViewById(R.id.rb_fraud_visa2);
        rgCustody = (RadioGroup)view.findViewById(R.id.rg_custody_court);
        rbCustody1 = (RadioButton)view.findViewById(R.id.rb_custody_court1);
        rbCustody2 = (RadioButton)view.findViewById(R.id.rb_custody_court2);
        rgViolatedLaw = (RadioGroup)view.findViewById(R.id.rg_violated);
        rbVoted1  = (RadioButton)view.findViewById(R.id.rb_violated1);
        rbVoted2 = (RadioButton)view.findViewById(R.id.rb_violated2);
        rgAvoidingTaxation = (RadioGroup)view.findViewById(R.id.rg_avoiding_taxation);
        rbAvoidingTaxation1 = (RadioButton)view.findViewById(R.id.rb_avoiding_taxation1);
        rbAvoidingTaxation2 = (RadioButton)view.findViewById(R.id.rb_avoiding_taxation2);
        rgProstitution = (RadioGroup)view.findViewById(R.id.rg_prostitution);
        rbProstitution1 = (RadioButton)view.findViewById(R.id.rb_prostitution1);
        rbProstitution2 = (RadioButton)view.findViewById(R.id.rb_prostitution2);
        buttonForm13 = (Button)view.findViewById(R.id.button_form13);
        buttonForm13.setOnClickListener(this);
        rgCommunicableDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbCommunicable1.isChecked()){
                    showToast("One");
                }
                else if(rbCommunicable2.isChecked()){
                    showToast("TWO");
                }
            }
        });
        rgMentalDisorder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbMental1.isChecked()){
                }
                else if(rbMental2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgdrugAbuser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbDrugAddict1.isChecked()){
                }
                else if(rbDrugAddict2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgArrested.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbArrested1.isChecked()){
                }
                else if(rbArrested2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgViolatedLaw.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbViolatedLaw1.isChecked()){
                }
                else if(rbViolatedLaw2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgMoneyLaundering.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbMoney1.isChecked()){
                }
                else if(rbMoney2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgHumanTrafficing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbHumanTraffic1.isChecked()){
                }
                else if(rbHumanTraffic2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgHumanTrafficingAided.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbAidedHuman1.isChecked()){
                }
                else if(rbAidedHuman2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgRelativeHumanTrafficing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbRelativeHuman1.isChecked()){
                }
                else if(rbRelativeHuman2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgIllegal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbIllegalActivity1.isChecked()){
                }
                else if(rbIllegalActivity2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }

        });
        rgTerrorist.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTerroristActivity1.isChecked()){
                }
                else if(rbTerroristActivity1.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgTerroristMember.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbSupportTerrorist1.isChecked()){
                }
                else if(rbSupportTerrorist2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgSupportTerrorist.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTerrorist1.isChecked()){
                }
                else if(rbTerrorist2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgGenocide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbGenocide1.isChecked()){
                }
                else if(rbGenocide2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgTorture.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTorture1.isChecked()){
                }
                else if(rbTorture2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgKilling.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbKilling1.isChecked()){
                }
                else if(rbKilling2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgChildSoldiers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbChildSoldier1.isChecked()){
                }
                else if(rbChildSoldier2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgReligiousFreedom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbReligiousFreedom1.isChecked()){
                }
                else if(rbReligiousFreedom2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });
        rgAbortion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbAbortion1.isChecked()){
                }
                else if(rbAbortion2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgTransplantation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTransplant1.isChecked()){
                }
                else if(rbTransplant2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgFraudVisa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbFraudVisa1.isChecked()){
                }
                else if(rbFraudVisa2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgCustody.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbCustody1.isChecked()){
                }
                else if(rbCustody2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgAvoidingTaxation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbAvoidingTaxation1.isChecked()){
                }
                else if(rbAvoidingTaxation2.isChecked()){
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgProstitution.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbProstitution1.isChecked()){
                }
                else if(rbProstitution2.isChecked()){
                }
                else{
                    showToast("Check all field");
                }
            }
        });

    }
    private void initializeForm14View() {
        consulateCity = (EditText)view.findViewById(R.id.city_interview);
        interviewConsulate = (Spinner)view.findViewById(R.id.spnr_consulate_interview);
        interviewPriority1 = (EditText)view.findViewById(R.id.interview_priority1);
        interviewPriority2 = (EditText)view.findViewById(R.id.interview_priority2);
        interviewPriority3 = (EditText)view.findViewById(R.id.interview_priority3);
        biometricPriority1 = (EditText)view.findViewById(R.id.biometric_priority1);
        biometricPriority2 = (EditText)view.findViewById(R.id.biometric_priority2);
        biometricPriority3 = (EditText)view.findViewById(R.id.biometric_priority3);
        deliveryAddress = (EditText)view.findViewById(R.id.address_delivery);
        deliveryState = (EditText)view.findViewById(R.id.state_delivery);
        deliveryCity = (EditText)view.findViewById(R.id.city_delivery);
        deliveryPinCode = (EditText)view.findViewById(R.id.postal_delivery);
        buttonForm14 = (Button)view.findViewById(R.id.button_form14);
        buttonForm14.setOnClickListener(this);
    }
    private void initializeForm15View() {
        checked1 = (ImageView)view.findViewById(R.id.attach_usa_checked1);
        checked2 = (ImageView)view.findViewById(R.id.attach_usa_checked2);
        checked3 = (ImageView)view.findViewById(R.id.attach_usa_checked3);
        checked4 = (ImageView)view.findViewById(R.id.attach_usa_checked4);
        attach1 = (ImageView)view.findViewById(R.id.attach_usa_file1);
        attach2 = (ImageView)view.findViewById(R.id.attach_usa_file2);
        attach3 = (ImageView)view.findViewById(R.id.attach_usa_file3);
        attach4 = (ImageView)view.findViewById(R.id.attach_usa_file4);
        buttonForm15 = (Button)view.findViewById(R.id.button_form15);
        buttonForm15.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_form1:
                moveToNextForm();
                break;
            case R.id.button_form2:
                moveToNextForm();
                break;
            case R.id.button_form3:
                moveToNextForm();
                break;
            case R.id.button_form4:
                moveToNextForm();
                break;
            case R.id.button_form5:
                moveToNextForm();
                break;
            case R.id.button_form6:
                moveToNextForm();
                break;
            case R.id.button_form7:
                moveToNextForm();
                break;
            case R.id.button_form8:
                moveToNextForm();
                break;
            case R.id.button_form9:
                moveToNextForm();
                break;
            case R.id.button_form10:
                moveToNextForm();
                break;
            case R.id.button_form11:
                moveToNextForm();
                break;
            case R.id.button_form12:
                moveToNextForm();
                break;
            case R.id.button_form13:
                moveToNextForm();
                break;
            case R.id.button_form14:
                moveToNextForm();
                break;
            case R.id.button_form15:
                break;
        }


    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        /*switch (view.getId()){
            case R.id.spnr_usa_gender:
                break;
            case R.id.marital_status_form2:
                break;
            case R.id.stolen_passport:
                break;
            case R.id.spnr_birth_country:
                break;
            case R.id.country_origin:
                break;
            case R.id.spnr_document_type:
                break;
            case R.id.spnr_indented_length:
                break;
            case R.id.spnr_person_paying:
                break;
            case R.id.spnr_contact_person_us:
                break;
            case R.id.spnr_relation_contact_us:
                break;
            case R.id.spnr_primary_occupation:
                break;
            case R.id.spnr_consulate_interview:
                break;
        }*/
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    private void populateGenderSpinner(){
        spnrGender = (Spinner)view.findViewById(R.id.spnr_usa_gender);
        gender = new String[]{"Select One", "Male", "Female"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, gender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrGender.setAdapter(genderAdapter);
        spnrGender.setOnItemSelectedListener(this);
    }
    private void populateMaritalStatusSpinner(){
        spnrMarital = (Spinner)view.findViewById(R.id.marital_status_form2);
        martialStatus = new String[]{"Select One", "Single", "Married", "Common Law Marriage", "Civil Union/Domestic Partnership", "Widowed","Divorce", "Legally Separated", "Other"};
        ArrayAdapter<String> maritalAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, martialStatus);
        maritalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrMarital.setAdapter(maritalAdapter);
        spnrMarital.setOnItemSelectedListener(this);
    }
    private void populateDocumentTypeSpinner(){
        travelDocType = (Spinner)view.findViewById(R.id.spnr_document_type);
        documentType = new String[]{"Select One","Passport", "Travel Document"};
        ArrayAdapter<String> documentTypeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, documentType);
        documentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelDocType.setAdapter(documentTypeAdapter);
        travelDocType.setOnItemSelectedListener(this);
    }
    private void populateStolenPassportSpinner(){
        stolenPassport = (Spinner)view.findViewById(R.id.stolen_passport);
        stolen = new String[]{"Select One","Yes","No"};
        ArrayAdapter<String> stolenPassportAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,stolen);
        stolenPassportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stolenPassport.setAdapter(stolenPassportAdapter);
        stolenPassport.setOnItemSelectedListener(this);
    }
    private void populateContactPersonUsSpinner(){
        contactUs = (Spinner)view.findViewById(R.id.spnr_contact_person_us);
        contactPersonUs = new String[]{"Select One", "Organisation", "Person"};
        ArrayAdapter<String> personUsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, contactPersonUs);
        personUsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactUs.setAdapter(personUsAdapter);
        contactUs.setOnItemSelectedListener(this);
    }
    private void populateRelationPersonSpinner(){
        relationContactUs = (Spinner)view.findViewById(R.id.spnr_relation_contact_us);
        relationPerson = new String[]{"Select One", "Relative", "Spouse", "Friend", "Business Associate", "Employer", "School Official", "other"};
        ArrayAdapter<String> relationUsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, relationPerson);
        relationUsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationContactUs.setAdapter(relationUsAdapter);
        relationContactUs.setOnItemSelectedListener(this);
    }
    private void populateIndentedLength(){
        stayLenght = (Spinner)view.findViewById(R.id.spnr_indented_length);
        indentedLength = new String[]{"Select One","Year(s)","Month(s)","Week(s)","Day(s)","Less than 24 hours"};
        ArrayAdapter<String> stayLengthAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, indentedLength);
        stayLengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stayLenght.setAdapter(stayLengthAdapter);
        stayLenght.setOnItemSelectedListener(this);
    }
    private void populatePersonPayingTrip(){
        personPaying = (Spinner)view.findViewById(R.id.spnr_person_paying);
        payingTrip = new String[]{"Select One","Self","Other Person/Organisation"};
        ArrayAdapter<String> personPayingAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, payingTrip);
        personPayingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personPaying.setAdapter(personPayingAdapter);
        personPaying.setOnItemSelectedListener(this);
    }
    private void populateAllCountrySpinner(){

    }
    private void populateNationalitySpinner(){
        //nationalitySpnr = (Spinner)view.findViewById(R.id.);
        ArrayAdapter<String> dataAdapterCountries = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, allCountriesData);
        dataAdapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationalitySpnr.setAdapter(dataAdapterCountries);
        nationalitySpnr.setOnItemSelectedListener(this);
    }
    private void stateUsSpinner(){

    }
    private void populateOccupationSpinner(){

    }
    private void moveToNextForm() {
        atTab = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(atTab + 1);
    }
    private void loadDataApi(){
        loadOccupation();

    }
    /*private void intializeSpinners(){
        populateContactPersonUsSpinner();
        populateDocumentTypeSpinner();
        populateGenderSpinner();
        populateIndentedLength();
        populateMaritalStatusSpinner();
        populatePersonPayingTrip();
        populateRelationPersonSpinner();
        populateStolenPassportSpinner();
    }*/
    private void loadOccupation(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://usa-visahub.in")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        OccupationResponseUsa request = retrofit.create(OccupationResponseUsa.class);
        retrofit2.Call<Profession> call = request.getProfession();

        call.enqueue(new retrofit2.Callback<Profession>() {
            @Override
            public void onResponse(retrofit2.Call<Profession> call, retrofit2.Response<Profession> response) {
                Profession jsonResponse = response.body();
                professionList = jsonResponse.getProfession();
                for(int i=0;i<professionList.size();i++){
                    occupation.add(professionList.get(i).getProfession());
                }
                if(occupation != null){
                    ArrayAdapter<String> occupationAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, occupation);
                    occupationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    primaryOccupation.setAdapter(occupationAdapter);
                }

            }
            @Override
            public void onFailure(retrofit2.Call<Profession> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadConsulate(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://iranvisas.in")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ConsulateInterface request = retrofit.create(ConsulateInterface.class);
        retrofit2.Call<Consulate> call = request.getConsulate();

        call.enqueue(new Callback<Consulate>() {
            @Override
            public void onResponse(Call<Consulate> call, Response<Consulate> response) {
                Consulate jsonResponse = response.body();
                consulate = jsonResponse.getConsulate();
                for(int i=0;i<consulate.size();i++){
                    consulateData.add( consulate.get(i).getConsulateName());
                }
                if(consulateData != null){
                    ArrayAdapter<String> occupationAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, consulateData);
                    occupationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    interviewConsulate.setAdapter(occupationAdapter);
                }

            }
            @Override
            public void onFailure(retrofit2.Call<Consulate> call, Throwable t) {
                Log.v("Error",t.getMessage());
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
        call.enqueue(new retrofit2.Callback<Country>() {
            @Override
            public void onResponse(retrofit2.Call<Country> call, retrofit2.Response<Country> response) {


                Country jsonResponse = response.body();
                allcountry = jsonResponse.getCountry();
                for(int i=0;i<allcountry.size();i++){
                    allCountriesData.add(allcountry.get(i).getName());
                }

                //populateAllCountrySpinner();
                //populateNationalitySpinner();
                ArrayAdapter<String> dataAdapterCountries = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, allCountriesData);
                dataAdapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                birthCountrySpnr.setAdapter(dataAdapterCountries);
                nationalitySpnr.setAdapter(dataAdapterCountries);


            }
            @Override
            public void onFailure(retrofit2.Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }


    private void showToast(String mString){
        Toast.makeText(getContext(),mString,Toast.LENGTH_SHORT).show();
    }
    private void sendForm3Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("per_visa_id", "")
                .addFormDataPart("per_vad_id", "")
                .addFormDataPart("per_other_name", "")
                .addFormDataPart("per_other_sur", "")
                .addFormDataPart("per_other_given", "")
                .addFormDataPart("telecode_name", "")
                .addFormDataPart("telecode_sur", "")
                .addFormDataPart("telecode_given", "")
                .addFormDataPart("date_of_birth", "")
                .addFormDataPart("birth_state", "")
                .addFormDataPart("birth_country", "")
                .addFormDataPart("per_other_nationality", "")
                .addFormDataPart("per_other_nation_name", "")
                .addFormDataPart("per_other_pass", "")
                .addFormDataPart("per_other_pass_no", "")
                .addFormDataPart("per_other_permanent", "")
                .addFormDataPart("per_other_permanent_name", "")
                .addFormDataPart("per_NIN", "")
                .addFormDataPart("per_SSN", "")
                .addFormDataPart("per_TIN","")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM3).post(requestBody).build();
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
    private void sendForm4Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("per_mail", "")
                .addFormDataPart("per_mail_line1", "")
                .addFormDataPart("per_mail_line2", "")
                .addFormDataPart("per_mail_city", "")
                .addFormDataPart("per_mail_state", "")
                .addFormDataPart("per_mail_zip", "")
                .addFormDataPart("per_mail_country", "")
                .addFormDataPart("per_sec_phone", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM4).post(requestBody).build();
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
    private void sendForm5Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("traveldocument", "")
                .addFormDataPart("traveldocumentnum", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM5).post(requestBody).build();
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
    private void sendForm6Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("travel_visa_id", "")
                .addFormDataPart("travel_vad_id", "")
                .addFormDataPart("travel_purpose", "")
                .addFormDataPart("travel_length", "")
                .addFormDataPart("travel_length_val", "")
                .addFormDataPart("travel_line1", "")
                .addFormDataPart("travel_state", "")
                .addFormDataPart("travel_payee", "")
                .addFormDataPart("travel_surname", "")
                .addFormDataPart("travel_givenname", "")
                .addFormDataPart("travel_phone", "")
                .addFormDataPart("travel_relation", "")
                .addFormDataPart("travel_org_add1", "")
                .addFormDataPart("travel_org_add2", "")
                .addFormDataPart("travel_org_city", "")
                .addFormDataPart("travel_org_state", "")
                .addFormDataPart("travel_org_country", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM6).post(requestBody).build();
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
    private void sendForm7Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("travel_other_sur", "")
                .addFormDataPart("travel_other_given", "")
                .addFormDataPart("travel_other_relation", "")
                .addFormDataPart("travel_other_group", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM7).post(requestBody).build();
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
    private void sendForm8Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("pre_inus", "")
                .addFormDataPart("pre_inus_arrive", "")
                .addFormDataPart("pre_inus_depart", "")
                .addFormDataPart("pre_inus_licence", "")
                .addFormDataPart("pre_inus_licence_state", "")
                .addFormDataPart("pre_usvisa", "")
                .addFormDataPart("pre_usvisa_date", "")
                .addFormDataPart("pre_usvisa_sametype", "")
                .addFormDataPart("pre_usvisa_samecountry", "")
                .addFormDataPart("pre_usvisa_ten", "")
                .addFormDataPart("pre_usvisa_lost", "")
                .addFormDataPart("pre_usvisa_lost_year", "")
                .addFormDataPart("pre_usvisa_lost_explain", "")
                .addFormDataPart("pre_usvisa_revoke", "")
                .addFormDataPart("pre_usvisa_revoke_explain", "")
                .addFormDataPart("pre_withdraw_app", "")
                .addFormDataPart("pre_withdraw_app_explain", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM8).post(requestBody).build();
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

    private void sendForm9Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("contact_visa_id", "")
                .addFormDataPart("contact_vad_id", "")
                .addFormDataPart("contact_name", "")
                .addFormDataPart("contact_relation", "")
                .addFormDataPart("contact_add1", "")
                .addFormDataPart("contact_add2", "")
                .addFormDataPart("contact_city", "")
                .addFormDataPart("contact_state", "")
                .addFormDataPart("contact_phone", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM10).post(requestBody).build();
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
    private void sendForm10Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fam_father", "")
                .addFormDataPart("fam_father_dob", "")
                .addFormDataPart("fam_father_inus", "")
                .addFormDataPart("fam_father_status", "")
                .addFormDataPart("fam_mother", "")
                .addFormDataPart("fam_mother_dob", "")
                .addFormDataPart("fam_mother_inus", "")
                .addFormDataPart("fam_mother_status", "")
                .addFormDataPart("fam_mar_name", "")
                .addFormDataPart("fam_mar_dob", "")
                .addFormDataPart("fam_mar_birth", "")
                .addFormDataPart("fam_mar_nation", "")
                .addFormDataPart("fam_mar_date", "")
                .addFormDataPart("fam_mar_end", "")
                .addFormDataPart("fam_mar_how_end", "")
                .addFormDataPart("fam_mar_end_count", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM9).post(requestBody).build();
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
    private void sendForm11Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("work_visa_id", "")
                .addFormDataPart("work_vad_id", "")
                .addFormDataPart("work_occupation", "")
                .addFormDataPart("work_name", "")
                .addFormDataPart("work_add", "")
                .addFormDataPart("work_city", "")
                .addFormDataPart("work_code", "")
                .addFormDataPart("country", "")
                .addFormDataPart("work_phone", "")
                .addFormDataPart("work_country", "")
                .addFormDataPart("work_duties", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM11).post(requestBody).build();
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
    private void sendForm12Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("work_add_emp", "")
                .addFormDataPart("work_add_lang", "")
                .addFormDataPart("work_add_countries", "")
                .addFormDataPart("work_add_countries_list", "")
                .addFormDataPart("work_add_charity", "")
                .addFormDataPart("work_add_charity_list", "")
                .addFormDataPart("work_add_skill", "")
                .addFormDataPart("work_add_skill_explain", "")
                .addFormDataPart("work_add_military", "")
                .addFormDataPart("work_add_military_country", "")
                .addFormDataPart("work_add_military_branch", "")
                .addFormDataPart("work_add_military_rank", "")
                .addFormDataPart("work_add_military_speciality", "")
                .addFormDataPart("work_add_military_from", "")
                .addFormDataPart("work_add_military_to", "")
                .addFormDataPart("work_add_paramilitary", "")
                .addFormDataPart("work_add_paramilitary_explain", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM12).post(requestBody).build();
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
    private void sendForm13Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("ques_1", "")
                .addFormDataPart("ques_2", "")
                .addFormDataPart("ques_3", "")
                .addFormDataPart("ques_4", "")
                .addFormDataPart("ques_5", "")
                .addFormDataPart("ques_6", "")
                .addFormDataPart("ques_7", "")
                .addFormDataPart("ques_8", "")
                .addFormDataPart("ques_9", "")
                .addFormDataPart("ques_10", "")
                .addFormDataPart("ques_11", "")
                .addFormDataPart("ques_12", "")
                .addFormDataPart("ques_13", "")
                .addFormDataPart("ques_14", "")
                .addFormDataPart("ques_15", "")
                .addFormDataPart("ques_16", "")
                .addFormDataPart("ques_17", "")
                .addFormDataPart("ques_18","")
                .addFormDataPart("ques_19", "")
                .addFormDataPart("ques_20", "")
                .addFormDataPart("ques_21", "")
                .addFormDataPart("ques_22", "")
                .addFormDataPart("ques_23","")
                .addFormDataPart("ques_24", "")
                .addFormDataPart("ques_25", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM13).post(requestBody).build();
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
    private void sendForm14Data(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", "")
                .addFormDataPart("start_date", "")
                .addFormDataPart("end_date", "")
                .addFormDataPart("pnrNo", "")
                .addFormDataPart("address1", "")
                .addFormDataPart("address2", "")
                .addFormDataPart("city", "")
                .addFormDataPart("country", "")
                .addFormDataPart("country_code", "")
                .addFormDataPart("mobile", "")
                .addFormDataPart("emergency_contact_name", "")
                .addFormDataPart("emergency_contact_number", "")
                .addFormDataPart("hotel_address", "")
                .addFormDataPart("contact_uae", "")
                .addFormDataPart("created_date", "")
                .addFormDataPart("order_id", "")
                .addFormDataPart("service_type", "")
                .addFormDataPart("nationality_id","")
                .addFormDataPart("living_in_id", "")
                .addFormDataPart("currency_id", "")
                .addFormDataPart("govt_fee", "")
                .addFormDataPart("service_fee", "")
                .addFormDataPart("processing_time","")
                .addFormDataPart("visa_type_id", "")
                .addFormDataPart("email_id", "")
                .addFormDataPart("email_varified", "")
                .addFormDataPart("comments_added", "")
                .addFormDataPart("insertedTimeIst", "")
                .addFormDataPart("agentid", "")
                .addFormDataPart("service_fee_cs","")
                .addFormDataPart("termConditions", "")
                .addFormDataPart("mng_fee", "")
                .addFormDataPart("application_type", "")
                .addFormDataPart("agentType", "")
                .addFormDataPart("device_type", "app")
                .addFormDataPart("device_os", "")
                .build();
        Request request = new Request.Builder().url(BASE_URL_FORM14).post(requestBody).build();
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
    private void getSharedPreferences() {

    }
}
