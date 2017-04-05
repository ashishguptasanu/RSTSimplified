package international.rst.com.rstsimplified.Fragments;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Model.AllCountryResponse;
import international.rst.com.rstsimplified.Model.AllCountryUsa;
import international.rst.com.rstsimplified.Model.Consulate;
import international.rst.com.rstsimplified.Model.ConsulateInterface;
import international.rst.com.rstsimplified.Model.ConsulateResponse;
import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.Model.OccupationResponseUsa;
import international.rst.com.rstsimplified.Model.Profession;
import international.rst.com.rstsimplified.Model.ProfessionRes;
import international.rst.com.rstsimplified.Model.PurposeRes;
import international.rst.com.rstsimplified.Model.PurposeResponse;
import international.rst.com.rstsimplified.Model.PurposeUs;
import international.rst.com.rstsimplified.Model.State;
import international.rst.com.rstsimplified.Model.StateResponse;
import international.rst.com.rstsimplified.Model.States;
import international.rst.com.rstsimplified.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class FragmentUSAForm extends android.support.v4.app.Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    String title, resp, securityQuestion1, securityQuestion2, securityQuestion3, securityQuestion4, securityQuestion5, securityQuestion6, securityQuestion7, securityQuestion8, securityQuestion9, securityQuestion10, securityQuestion11, securityQuestion12, securityQuestion13, securityQuestion14, securityQuestion15, securityQuestion16, securityQuestion17, securityQuestion18, securityQuestion19, securityQuestion20, securityQuestion21, securityQuestion22, securityQuestion23, securityQuestion24, securityQuestion25, selectedGenderSpnr, selectedMaritalSpnr, selectedLostPassportSpnr, selectedPlaceBirthSpnr, selectedBirthStateSpnr, selectedNationalitySpnr, selectedMailingCountrySpnr, selectedDocumentTypeSpnr, selectedPurposeSpnr, selectedLengthSpnr, selectedStateusSpnr, selectedPayeeSpnr, selectedRelationSpnr, selectedPersonTypeSpnr, selectedrelationUsSpnr, selectedStateStaySpnr, selectedPreviousEmployed, selectedTravelOtherCountry, selectedCharitable, selectedSpecialSkill, selectedServedMilitary, selectedParamilitary, selectedFile1, selectedFile2, selectedFile3, selectedFile4, name1, name2, name3, name4, filePath1, filePath2, filePath3, filePath4, fileType1, fileType2, fileType3, fileType4, selectedFatherUs, selectedMotherUs, selectedeverBeenUs, selectedUsLicense, selectedIssuedVisa, selectedApplyingSameVisa, selectedApplyingSameCountry, selectedTenPrinted, selectedVisaLost, selectedVisaCancelled, selectedRefusedVisa, selectedFatherStatus, selectedMotherStatus, selectedOccupationCountry, selectedEmploymentCode;
    View view;
    ViewPager mViewPager;
    int atTab, selectedPurposeSpinner;
    File file;
    TextView attachFileName1, attachFileName2, attachFileName3, attchFileName4;
    SharedPreferences sharedPreferences;
    TextInputLayout otherPassportLayout, groupNameLayout, layoutTravelledOther, layoutContributed, layoutSpecialSkills, layoutParamilitary, layoutYearStolen, layoutExplainStolen, layoutRevoked, layoutRefused, layoutPreviousVisaIssueDate;
    LinearLayout mailingLayout, otherNameLayout, telecodeLayout, otherNationalityLayout, permanentResidentLayout, personTravellingLayout, layoutOtherPerson, stolenPassportLayout, fatherUsLayout, motherUsLayout, layoutMilitary, layoutHoldLicense, layoutPreviousDates;
    String[] gender, martialStatus, documentType, stolen, contactPersonUs, relationPerson, indentedLength, payingTrip, status;
    Button buttonForm1, buttonForm2, buttonForm3, buttonForm4, buttonForm5, buttonForm6, buttonForm7, buttonForm8, buttonForm9, buttonForm10, buttonForm11, buttonForm12, buttonForm13, buttonForm14, buttonForm15, uploadButton1, uploadButton2, uploadButton3, uploadButton4;
    EditText arrivingDate, departureDate, currentAddress, currentCity, phoneCode, mobileNumberCurrent, countryCurrent, email, surName, givenName, nationalIdentificationNumber, usSecurityNumber, taxPayerId, maritalStatus, placeOfBirth, dateOfBirth, homeAddress, city, pinCode, state, country, countryCode, primaryPhoneNumber, secondaryPhoneNumber, emailAddress, passportNumber, passportBookNumber, issueCountry, issueCity, issueDate, noMonths, addressStayUS, personPayingTrip, nameContactUs, addressContactUs, codeContactUs, numberContactUs, fatherName, fatherDateBirth, motherName, motherDateBirth, employerName, addressEmployer, cityEmployment, codeEmployment, contactNumberEmployment, languageSpeaking, consulateCity, interviewPriority1, interviewPriority2, interviewPriority3, biometricPriority1, biometricPriority2, biometricPriority3, deliveryAddress, deliveryState, deliveryCity, deliveryPinCode, nameFirst, nameLast, placeOfBirthForm2, stateCurrent, postalCodeCurrent, passportNumberForm2, issuedCity, issuedCountry, issuedDate, expiryDate, expiryDateForm2, currentNationality, otherSurName, otherGivenName, telecodeSurname, telecodeGivenName, otherNationality, otherPassportNumber, otherMailingAddress, otherCityMailing, otherStateMailing, otherPostalMailing, phoneCodeUsa, cityStayUs, stolenPassportForm6, preArrivalUs, preDepartureUs,issueCountryForm2, previousVisaIssueDate, issueCountry2, specialSkills, travelledOtherCountries, contributedOrganisation, explainParamilitary, branchService, rank, speciality_military, fromDateService, toDateService, yearStolen, explainStolen, revokedExplain, refusedExplain;
    Spinner birthCountrySpnr, birthStateSpnr, nationalitySpnr, travelDocType, stolenPassport, contactUs, relationContactUs, stateContactUs, primaryOccupation, interViewState, interviewConsulate, spnrGender, spnrMarital, stayLenght, personPaying, residentOtherCountry, purposeUS, stateStayUs, fatherStatus, motherStatus, countryEmployment, spnrMilitaryCountry, spnrMailingCountry;
    RadioGroup rgOtherName, rgTelecode, rgGender, rgOtherNationality, rgPermanentResident, rgMailAddress, rgotherPersonTravelling, rgTravelledUS, rgIssuedUsVisa, rgRefusedUsVisa, rgFatherUs, rgMotherUs, rgPriviousEmployed, rgTravelledCountries, rgContributedOrg, rgSpecializedSkill, rgServedMilitary, rgParamilitary, rgCommunicableDisease, rgMentalDisorder, rgdrugAbuser, rgArrested, rgViolated, rgMoneyLaundering, rgHumanTrafficing, rgHumanTrafficingAided, rgRelativeHumanTrafficing, rgIllegal, rgTerrorist, rgSupportTerrorist, rgTerroristMember, rgGenocide, rgTorture, rgKilling, rgChildSoldiers, rgReligiousFreedom, rgAbortion, rgTransplantation, rgFraudVisa, rgCustody, rgUsChild, rgViolatedLaw, rgAvoidingTaxation, rgProstitution, rgOtherPassport, rgOtherGroup, rgIssueUsVisa, rgHoldLicence, rgStolenVisa;
    RadioButton rbOtherName1, rbOtherName2, rbTelecode1, rbTelecode2, rbGender1, rbGender2, rbotherNationality1, rbOtherNationality2, rbPermanentResident1, rbPermanentResident2, rbMailAddress1, rbMailAddress2, rbPersonTravelling1, rbPersonTravelling2, rbTravelledUs1, rbTravelledUs2, rbIssued1, rbIssued2, rbRefused1, rbRefused2, rbFatherUs1, rbFatherUs2, rbMotherUs1, rbMotherUs2, rbPriviousEmployed1, rbPreviousEmployed2, rbTravelledCountry1, rbTravelledCountry2, rbContributed1, rbContributed2, rbSpecializedSkill1, rbSpecializesSkill2, rbServedMilitary1, rbServedMilitary2, rbParamilitary1, rbParamilitary2, rbCommunicable1, rbCommunicable2, rbMental1, rbMental2, rbDrugAddict1, rbDrugAddict2, rbArrested1, rbArrested2, rbViolatedLaw1, rbViolatedLaw2, rbMoney1, rbMoney2, rbHumanTraffic1, rbHumanTraffic2, rbAidedHuman1, rbAidedHuman2, rbRelativeHuman1, rbRelativeHuman2, rbIllegalActivity1, rbIllegalActivity2, rbTerroristActivity1, rbTerroristActivity2, rbSupportTerrorist1, rbSupportTerrorist2, rbTerrorist1, rbTerrorist2, rbGenocide1, rbGenocide2, rbTorture1, rbTorture2, rbKilling1, rbKilling2, rbChildSoldier1, rbChildSoldier2, rbReligiousFreedom1, rbReligiousFreedom2, rbAbortion1, rbAbortion2, rbTransplant1, rbTransplant2, rbFraudVisa1, rbFraudVisa2, rbCustody1, rbCustody2, rbVoted1, rbVoted2, rbAvoidingTaxation1, rbAvoidingTaxation2, rbProstitution1, rbProstitution2, rbOtherPassport1, rbOtherPassport2, rbGroupName1, rbGroupName2, rbIssueUsVisa1, rbIssueUsVisa2, rbHoldUsLicense1,rbHoldLicense2, rbStolenVisa1, rbStolenVisa2  ;
    ImageView checked1, checked2, checked3, checked4,  attach1, attach2, attach3, attach4;
    private OkHttpClient client = new OkHttpClient();
    private List<ProfessionRes> professionList = new ArrayList<>();
    private List<String> occupation = new ArrayList<>();
    private List<String> allCountriesData = new ArrayList<>();
    private List<CountryRes> allcountry = new ArrayList<>();
    private List<ConsulateResponse> consulate = new ArrayList<>();
    private List<PurposeRes> purpose = new ArrayList<>();
    private List<String> purposeData = new ArrayList<>();
    private List<String> consulateData = new ArrayList<>();
    private List<State> states = new ArrayList<>();
    private List<String>stateData = new ArrayList<>();
    private List<State> statesByCountry = new ArrayList<>();
    private List<String>stateDataByCountry = new ArrayList<>();
    private static final String BASE_URL_FORM3 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=personalinfo3";
    private static final String BASE_URL_FORM4 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=addressinfo";
    private static final String BASE_URL_FORM5 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=familyinfo";
    private static final String BASE_URL_FORM6 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=passportinfo";
    private static final String BASE_URL_FORM7 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=travelinfo";
    private static final String BASE_URL_FORM8 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=travelcompan";
    private static final String BASE_URL_FORM9 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=pretravel";
    private static final String BASE_URL_FORM10 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=pretravelupdate";
    private static final String BASE_URL_FORM11 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=pointofcontactinfo";
    private static final String BASE_URL_FORM12 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=additionalworkinfo";
    private static final String BASE_URL_FORM14 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=interview";
    private static final String BASE_URL_FORM13 = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=security";
    private static final String BASE_URL_UPLOAD_DOCS = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=";
    private static final String STATES_USA = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=state&LivingInId=231";
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
        phoneCodeUsa = (EditText)view.findViewById(R.id.phone_code_usa);
        phoneCodeUsa.setText(sharedPreferences.getString("phone_code_usa",""));
        arrivingDate = (EditText)view.findViewById(R.id.edt_arrival_usa);
        departureDate = (EditText)view.findViewById(R.id.edt_departure_usa);
        buttonForm1 = (Button)view.findViewById(R.id.button_form1);
        nameFirst.setText(sharedPreferences.getString("first_name_usa",""));
        nameLast.setText(sharedPreferences.getString("last_name_usa",""));
        email.setText(sharedPreferences.getString("email_usa",""));
        mobileNumberCurrent.setText(sharedPreferences.getString("phone_usa",""));
        arrivingDate.setText(sharedPreferences.getString("date_arrival_usa", ""));
        departureDate.setText(sharedPreferences.getString("date_departure_usa",""));
        buttonForm1.setOnClickListener(this);
        disableInput(nameFirst);
        disableInput(nameLast);
        disableInput(email);
        disableInput(mobileNumberCurrent);
        disableInput(phoneCodeUsa);
        disableInput(arrivingDate);
        disableInput(departureDate);
    }
    private void initializeForm2View() {
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getActivity());
        currentNationality = (EditText)view.findViewById(R.id.current_nationality);
        currentNationality.setText(sharedPreferences.getString("nationality_usa",""));
        disableInput(currentNationality);
        currentAddress = (EditText) view.findViewById(R.id.current_address);
        currentAddress.setText(sharedPreferences.getString("address_usa",""));
        disableInput(currentAddress);
        currentCity = (EditText)view.findViewById(R.id.current_city_form2);
        currentCity.setText(sharedPreferences.getString("city_usa",""));
        disableInput(currentCity);
        placeOfBirthForm2 = (EditText)view.findViewById(R.id.place_of_birth_form2);
        placeOfBirthForm2.setText(sharedPreferences.getString("place_birth_usa",""));
        disableInput(placeOfBirthForm2);
        stateCurrent = (EditText)view.findViewById(R.id.current_state_form2);
        stateCurrent.setText(sharedPreferences.getString("state_usa",""));
        disableInput(stateCurrent);
        postalCodeCurrent = (EditText)view.findViewById(R.id.postal_code_current);
        postalCodeCurrent.setText(sharedPreferences.getString("postal_code_usa",""));
        disableInput(postalCodeCurrent);
        countryCurrent = (EditText)view.findViewById(R.id.current_country);
        countryCurrent.setText(sharedPreferences.getString("country_usa",""));
        disableInput(countryCurrent);
        passportNumberForm2 = (EditText)view.findViewById(R.id.passport_number_form2);
        passportNumberForm2.setText(sharedPreferences.getString("passport_num_usa",""));
        disableInput(passportNumberForm2);
        issueCity = (EditText)view.findViewById(R.id.city_issued_form2);
        issueCity.setText(sharedPreferences.getString("city_issued_usa",""));
        disableInput(issueCity);
        issueCountryForm2 = (EditText)view.findViewById(R.id.country_issue_form2);
        issueCountryForm2.setText(sharedPreferences.getString("issue_country_name",""));
        disableInput(issueCountryForm2);
//        issueCountry.setText(sharedPreferences.getString("country_issued_usa",""));
        issueDate = (EditText)view.findViewById(R.id.issue_date_form2);
        issueDate.setText(sharedPreferences.getString("issue_date_usa",""));
        disableInput(issueDate);
        expiryDateForm2 = (EditText)view.findViewById(R.id.expiry_date_form2);
        expiryDateForm2.setText(sharedPreferences.getString("expiry_date_usa",""));
        disableInput(expiryDateForm2);
        buttonForm2 = (Button)view.findViewById(R.id.button_form2);
        buttonForm2.setOnClickListener(this);
        populateGenderSpinner();
        populateMaritalStatusSpinner();
        populateStolenPassportSpinner();
    }
    private void initializeForm3View() {
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getActivity());
        birthCountrySpnr = (Spinner)view.findViewById(R.id.spnr_birth_country);
        nationalitySpnr = (Spinner)view.findViewById(R.id.country_origin);
        birthStateSpnr = (Spinner)view.findViewById(R.id.spnr_birth_state);
        birthCountrySpnr.setOnItemSelectedListener(this);
        nationalitySpnr.setOnItemSelectedListener(this);
        loadAllCountries();
        surName = (EditText)view.findViewById(R.id.surname);
        givenName = (EditText)view.findViewById(R.id.given_name);
        rgOtherName = (RadioGroup)view.findViewById(R.id.rg_other_name);
        rbOtherName1 = (RadioButton)view.findViewById(R.id.rb_other_name1);
        rbOtherName2 = (RadioButton)view.findViewById(R.id.rb_other_name2);
        rgTelecode = (RadioGroup)view.findViewById(R.id.rg_telecode);
        rbTelecode1 = (RadioButton)view.findViewById(R.id.rb_telecode1);
        rbTelecode2 = (RadioButton)view.findViewById(R.id.rb_telecode2);
        otherSurName = (EditText)view.findViewById(R.id.other_name_surname);
        otherGivenName = (EditText)view.findViewById(R.id.other_name_given_name);
        telecodeSurname = (EditText)view.findViewById(R.id.telecode_surname);
        telecodeGivenName = (EditText)view.findViewById(R.id.telecode_given_name);
        otherNationality = (EditText)view.findViewById(R.id.other_country_nationality);
        otherPassportNumber = (EditText)view.findViewById(R.id.other_passport_number);
        residentOtherCountry = (Spinner)view.findViewById(R.id.spnr_resident_other_country);
        residentOtherCountry.setOnItemSelectedListener(this);
        rgGender = (RadioGroup)view.findViewById(R.id.rg_gender);
        rbGender1 = (RadioButton)view.findViewById(R.id.rb_gender1);
        rbGender2 = (RadioButton)view.findViewById(R.id.rb_gender2);
        rgGender.setEnabled(false);
        int gender = sharedPreferences.getInt("gender_usa_num",0);
        if(gender == 1){
            rbGender1.setChecked(true);}
        else if(gender == 2){
            rbGender2.setChecked(false);}
        maritalStatus  = (EditText)view.findViewById(R.id.marital_status);
        disableInput(maritalStatus);
        maritalStatus.setText(sharedPreferences.getString("marital_usa",""));
        dateOfBirth = (EditText)view.findViewById(R.id.date_of_birth);
        disableInput(dateOfBirth);
        dateOfBirth.setOnClickListener(this);
        placeOfBirth = (EditText)view.findViewById(R.id.place_of_birth);
        disableInput(placeOfBirth);
        placeOfBirth.setText(sharedPreferences.getString("place_birth_usa",""));
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
        otherNameLayout = (LinearLayout)view.findViewById(R.id.other_name_layout);
        telecodeLayout = (LinearLayout)view.findViewById(R.id.telecode_layout);
        otherNationalityLayout = (LinearLayout)view.findViewById(R.id.other_country_layout);
        otherPassportLayout = (TextInputLayout)view.findViewById(R.id.input_other_passport);
        permanentResidentLayout = (LinearLayout)view.findViewById(R.id.resident_other_layout);
        rgOtherPassport = (RadioGroup)view.findViewById(R.id.rg_other_passport);
        rbOtherPassport1  = (RadioButton)view.findViewById(R.id.rb_other_passport1);
        rbOtherPassport2 = (RadioButton)view.findViewById(R.id.rb_other_passport2);
        rgOtherPassport.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbOtherPassport1.isChecked()){
                    otherPassportLayout.setVisibility(View.VISIBLE);
                }
                else if(rbOtherPassport2.isChecked()){
                    otherPassportLayout.setVisibility(View.GONE);
                }
            }
        });
        rgOtherName.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbOtherName1.isChecked()){
                    otherNameLayout.setVisibility(View.VISIBLE);
                }
                else if(rbOtherName2.isChecked()){
                    otherNameLayout.setVisibility(View.GONE);
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
                    telecodeLayout.setVisibility(View.VISIBLE);
                }
                else if(rbTelecode2.isChecked()){
                    telecodeLayout.setVisibility(View.GONE);
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
                    otherNationalityLayout.setVisibility(View.VISIBLE);
                }
                else if(rbOtherNationality2.isChecked()){
                    otherNationalityLayout.setVisibility(View.GONE);
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
                    permanentResidentLayout.setVisibility(View.VISIBLE);
                }
                else if(rbPermanentResident2.isChecked()){
                    permanentResidentLayout.setVisibility(View.GONE);
                }
                else {
                    showToast("Check all field");
                }
            }
        });
    }
    private void initializeForm4View() {
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getActivity());
        currentAddress = (EditText)view.findViewById(R.id.current_address);
        currentAddress.setText(sharedPreferences.getString("address_usa",""));
        disableInput(currentAddress);
        currentCity = (EditText)view.findViewById(R.id.current_city);
        currentCity.setText(sharedPreferences.getString("city_usa",""));
        disableInput(currentCity);
        pinCode = (EditText)view.findViewById(R.id.current_city_postal);
        pinCode.setText(sharedPreferences.getString("postal_code_usa",""));
        disableInput(pinCode);
        state = (EditText)view.findViewById(R.id.current_state);
        state.setText(sharedPreferences.getString("state_usa",""));
        disableInput(state);
        countryCurrent = (EditText)view.findViewById(R.id.current_country);
        countryCurrent.setText(sharedPreferences.getString("country_usa",""));
        disableInput(countryCurrent);
        rgMailAddress = (RadioGroup)view.findViewById(R.id.rg_mailing_address);
        rbMailAddress1 = (RadioButton)view.findViewById(R.id.rb_mailing_address1);
        rbMailAddress2 = (RadioButton)view.findViewById(R.id.rb_mailing_address2);
        countryCode = (EditText)view.findViewById(R.id.country_code);
        countryCode.setText(sharedPreferences.getString("phone_code_usa",""));
        disableInput(countryCode);
        primaryPhoneNumber = (EditText)view.findViewById(R.id.primary_phone_number);
        primaryPhoneNumber.setText(sharedPreferences.getString("phone_usa",""));
        disableInput(primaryPhoneNumber);
        secondaryPhoneNumber = (EditText)view.findViewById(R.id.secondary_phone_number);
        emailAddress = (EditText)view.findViewById(R.id.email_address);
        emailAddress.setText(sharedPreferences.getString("email_usa",""));
        disableInput(emailAddress);
        buttonForm4 = (Button)view.findViewById(R.id.button_form4);
        buttonForm4.setOnClickListener(this);
        mailingLayout = (LinearLayout)view.findViewById(R.id.layout_mailing_address);
        otherMailingAddress = (EditText)view.findViewById(R.id.mailing_address);
        otherCityMailing = (EditText)view.findViewById(R.id.mailing_city);
        otherStateMailing = (EditText)view.findViewById(R.id.mailing_state);
        otherPostalMailing = (EditText)view.findViewById(R.id.mailing_postal_code);
        spnrMailingCountry = (Spinner)view.findViewById(R.id.mailing_country_spnr);
        loadAllCountriesOccupation(spnrMailingCountry);
        rgMailAddress.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbMailAddress1.isChecked()){
                    mailingLayout.setVisibility(View.VISIBLE);
                }
                else if(rbMailAddress2.isChecked()){
                    mailingLayout.setVisibility(View.GONE);
                }
                else {
                    showToast("Check all field");
                }
            }
        });
    }
    private void initializeForm5View() {
        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getActivity());
        passportNumber = (EditText)view.findViewById(R.id.travel_document_number);
        passportBookNumber = (EditText)view.findViewById(R.id.passport_book_number);
        issueCountry = (EditText)view.findViewById(R.id.issue_country_name);
        issueCity = (EditText)view.findViewById(R.id.issued_city);
        issueCountry2 = (EditText)view.findViewById(R.id.issued_country);
        issueDate = (EditText)view.findViewById(R.id.issued_date);
        expiryDate = (EditText)view.findViewById(R.id.expiry_date);
        disableInput(issueDate);
        disableInput(expiryDate);
        disableInput(issueCity);
        disableInput(issueCountry);
        disableInput(passportNumber);
        disableInput(issueCountry2);
        passportNumber.setText(sharedPreferences.getString("passport_num_usa",""));
        issueDate.setText(sharedPreferences.getString("issue_date_usa",""));
        expiryDate.setText(sharedPreferences.getString("expiry_date_usa",""));
        issueCity.setText(sharedPreferences.getString("city_issued_usa",""));
        issueCountry.setText(sharedPreferences.getString("issue_usa_country",""));
        issueCountry2.setText(sharedPreferences.getString("issue_usa_country",""));
        buttonForm5 = (Button)view.findViewById(R.id.button_form5);
        stolenPassportForm6 = (EditText)view.findViewById(R.id.stolen_passport_form6);
        disableInput(stolenPassportForm6);
        stolenPassportForm6.setText(sharedPreferences.getString("stolen_passport_usa",""));
        stolenPassportLayout = (LinearLayout)view.findViewById(R.id.stolen_passport_layout5);
        if(sharedPreferences.getInt("stolen_passport",0) == 1){
            stolenPassportLayout.setVisibility(View.VISIBLE);
        }
        else{
            stolenPassportLayout.setVisibility(View.GONE);
        }
        buttonForm5.setOnClickListener(this);
    }
    private void initializeForm6View() {
        loadTripPurpose();
        loadStates(STATES_USA);
        purposeUS = (Spinner) view.findViewById(R.id.spnr_purpose_trip);
        noMonths = (EditText)view.findViewById(R.id.no_of_months);
        addressStayUS = (EditText)view.findViewById(R.id.address_us_stay);
        cityStayUs = (EditText)view.findViewById(R.id.stay_us_city);
        stateStayUs  = (Spinner)view.findViewById(R.id.spnr_state_us);
        stateStayUs.setOnItemSelectedListener(this);
        buttonForm6 = (Button)view.findViewById(R.id.button_form6);
        buttonForm6.setOnClickListener(this);

    }
    private void initializeForm7View() {
        rgotherPersonTravelling = (RadioGroup)view.findViewById(R.id.rg_travelling_with);
        rbPersonTravelling1 = (RadioButton)view.findViewById(R.id.rb_person_travelling1);
        rbPersonTravelling2 = (RadioButton)view.findViewById(R.id.rb_person_travelling2);
        buttonForm7 = (Button)view.findViewById(R.id.button_form7);
        personTravellingLayout = (LinearLayout)view.findViewById(R.id.person_travelling_layout);
        layoutOtherPerson = (LinearLayout)view.findViewById(R.id.layout_other_person);
        groupNameLayout = (TextInputLayout)view.findViewById(R.id.group_name_layout);
        buttonForm7.setOnClickListener(this);
        rgOtherGroup = (RadioGroup)view.findViewById(R.id.rg_travelling_group);
        rbGroupName1 = (RadioButton)view.findViewById(R.id.rb_group_travelling1);
        rbGroupName2 = (RadioButton)view.findViewById(R.id.rb_group_travelling2);
        rgotherPersonTravelling.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbPersonTravelling1.isChecked()){
                    layoutOtherPerson.setVisibility(View.VISIBLE);
                }
                else if(rbPersonTravelling2.isChecked()){
                    layoutOtherPerson.setVisibility(View.GONE);
                    personTravellingLayout.setVisibility(View.GONE);
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgOtherGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbGroupName1.isChecked()){
                    personTravellingLayout.setVisibility(View.VISIBLE);
                    groupNameLayout.setVisibility(View.GONE);
                }
                else if(rbGroupName2.isChecked()){
                    personTravellingLayout.setVisibility(View.GONE);
                    groupNameLayout.setVisibility(View.VISIBLE);
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
        preArrivalUs = (EditText)view.findViewById(R.id.arrived_date);
        disableInput(preArrivalUs);
        preArrivalUs.setOnClickListener(this);
        preDepartureUs = (EditText)view.findViewById(R.id.departed_date);
        disableInput(preDepartureUs);
        preDepartureUs.setOnClickListener(this);
        previousVisaIssueDate = (EditText)view.findViewById(R.id.previous_visa_issue_date);
        disableInput(previousVisaIssueDate);
        previousVisaIssueDate.setOnClickListener(this);
        layoutYearStolen = (TextInputLayout)view.findViewById(R.id.layout_year_stolen);
        layoutExplainStolen = (TextInputLayout)view.findViewById(R.id.layout_explain_stolen);
        layoutRevoked = (TextInputLayout)view.findViewById(R.id.layout_revoked);
        layoutRefused = (TextInputLayout)view.findViewById(R.id.layout_refused);
        layoutPreviousVisaIssueDate = (TextInputLayout)view.findViewById(R.id.layout_previous_us_visa_date);
        yearStolen = (EditText)view.findViewById(R.id.year_stolen);
        explainStolen = (EditText)view.findViewById(R.id.explain_stolen);
        revokedExplain = (EditText)view.findViewById(R.id.revoked_explain);
        refusedExplain = (EditText)view.findViewById(R.id.refused_explain);
        layoutHoldLicense = (LinearLayout)view.findViewById(R.id.layout_hold_licence);
        rgIssuedUsVisa = (RadioGroup)view.findViewById(R.id.rg_issued_us_visa);
        rgHoldLicence = (RadioGroup)view.findViewById(R.id.rg_hold_license);
        rgStolenVisa = (RadioGroup)view.findViewById(R.id.rg_stolen_us_visa);
        rbStolenVisa1 = (RadioButton)view.findViewById(R.id.rb_stolen_us_visa1);
        rbStolenVisa2 = (RadioButton)view.findViewById(R.id.rb_stolen_us_visa2);
        rbHoldUsLicense1 = (RadioButton)view.findViewById(R.id.rb_license1);
        rbHoldLicense2 = (RadioButton)view.findViewById(R.id.rb_license2);
        layoutPreviousDates = (LinearLayout)view.findViewById(R.id.layout_previous_date);
        rgStolenVisa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbStolenVisa1.isChecked()){
                    layoutYearStolen.setVisibility(View.VISIBLE);
                    layoutExplainStolen.setVisibility(View.VISIBLE);
                }
                else if(rbStolenVisa2.isChecked()){
                    layoutYearStolen.setVisibility(View.GONE);
                    layoutExplainStolen.setVisibility(View.GONE);
                }
            }
        });

        rgTravelledUS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTravelledUs1.isChecked()){
                    selectedeverBeenUs = "Y";
                    layoutPreviousDates.setVisibility(View.VISIBLE);
                }
                else if(rbTravelledUs2.isChecked()){
                    selectedeverBeenUs = "N";
                    layoutPreviousDates.setVisibility(View.GONE);
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
                    selectedIssuedVisa = "Y";
                    layoutPreviousVisaIssueDate.setVisibility(View.VISIBLE);

                }
                else if(rbIssued2.isChecked()){
                    selectedIssuedVisa = "N";
                    layoutPreviousVisaIssueDate.setVisibility(View.GONE);
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
                    selectedRefusedVisa = "Y";
                    layoutRefused.setVisibility(View.VISIBLE);
                }
                else if(rbRefused2.isChecked()){
                    selectedRefusedVisa = "N";
                    layoutRefused.setVisibility(View.GONE);
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgHoldLicence.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbHoldUsLicense1.isChecked()){
                    layoutHoldLicense.setVisibility(View.VISIBLE);
                }
                else if(rbHoldLicense2.isChecked()){
                    layoutHoldLicense.setVisibility(View.GONE);
                }
            }
        });
        buttonForm8.setOnClickListener(this);
    }
    private void initializeForm9View() {

        nameContactUs = (EditText)view.findViewById(R.id.full_name_contact_us);
        addressContactUs = (EditText) view.findViewById(R.id.address_contact_us);
        stateContactUs = (Spinner)view.findViewById(R.id.spnr_state_contact_us);
        stateContactUs.setOnItemSelectedListener(this);
        codeContactUs = (EditText)view.findViewById(R.id.phone_code_contact_us);
        codeContactUs.setText("+1");
        disableInput(codeContactUs);
        numberContactUs = (EditText)view.findViewById(R.id.contact_number_us);
        buttonForm9 = (Button)view.findViewById(R.id.button_form9);
        buttonForm9.setOnClickListener(this);
    }
    private void initializeForm10View() {
        fatherName = (EditText)view.findViewById(R.id.full_name_father);
        fatherDateBirth = (EditText)view.findViewById(R.id.father_date_of_birth);
        disableInput(fatherDateBirth);
        fatherDateBirth.setOnClickListener(this);
        rgFatherUs = (RadioGroup)view.findViewById(R.id.rg_father_usa);
        rbFatherUs1 = (RadioButton)view.findViewById(R.id.rb_father_usa1);
        rbFatherUs2 = (RadioButton)view.findViewById(R.id.rb_father_usa2);
        motherName = (EditText)view.findViewById(R.id.full_name_mother);
        motherDateBirth = (EditText)view.findViewById(R.id.mother_date_of_birth);
        disableInput(motherDateBirth);
        motherDateBirth.setOnClickListener(this);
        rgMotherUs = (RadioGroup)view.findViewById(R.id.rg_mother_usa);
        rbMotherUs1 = (RadioButton)view.findViewById(R.id.rb_mother_usa1);
        rbMotherUs2 = (RadioButton)view.findViewById(R.id.rb_mother_usa2);
        buttonForm10 = (Button)view.findViewById(R.id.button_form10);
        buttonForm10.setOnClickListener(this);
        fatherUsLayout = (LinearLayout)view.findViewById(R.id.spnr_father_status_layout);
        motherUsLayout = (LinearLayout)view.findViewById(R.id.spnr_mother_status_layout);
        fatherStatus = (Spinner)view.findViewById(R.id.spnr_father_status);
        motherStatus = (Spinner)view.findViewById(R.id.spnr_status_mother);
        populateFamilyStatusSpinner(fatherStatus, R.id.spnr_father_status);
        populateFamilyStatusSpinner(motherStatus, R.id.spnr_status_mother);
        rgFatherUs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbFatherUs1.isChecked()){
                    selectedFatherUs = "Y";
                    fatherUsLayout.setVisibility(View.VISIBLE);
                }
                else if(rbFatherUs2.isChecked()){
                    selectedFatherUs = "N";
                    fatherUsLayout.setVisibility(View.GONE);
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
                    selectedMotherUs  = "Y";
                    motherUsLayout.setVisibility(View.VISIBLE);
                }
                else if(rbMotherUs2.isChecked()){
                    selectedMotherUs = "N";
                    motherUsLayout.setVisibility(View.GONE);
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
        countryEmployment = (Spinner)view.findViewById(R.id.spnr_country_occupation);
        countryEmployment.setOnItemSelectedListener(this);
        codeEmployment = (EditText)view.findViewById(R.id.employer_code);
        contactNumberEmployment = (EditText)view.findViewById(R.id.employer_number);
        loadAllCountriesOccupation(countryEmployment);
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
        layoutSpecialSkills = (TextInputLayout)view.findViewById(R.id.layout_special_skill);
        layoutTravelledOther = (TextInputLayout)view.findViewById(R.id.layout_travelled_other);
        layoutContributed = (TextInputLayout)view.findViewById(R.id.layout_contributed);
        specialSkills = (EditText)view.findViewById(R.id.special_skill);
        travelledOtherCountries = (EditText)view.findViewById(R.id.travelled_other_countries);
        contributedOrganisation = (EditText)view.findViewById(R.id.contributed_organisation);
        layoutMilitary = (LinearLayout)view.findViewById(R.id.layout_military);
        spnrMilitaryCountry = (Spinner)view.findViewById(R.id.spnr_military_country);
        branchService = (EditText)view.findViewById(R.id.branch_service);
        rank = (EditText)view.findViewById(R.id.rank);
        speciality_military = (EditText)view.findViewById(R.id.speciality_military);
        fromDateService = (EditText)view.findViewById(R.id.from_date_service);
        toDateService = (EditText)view.findViewById(R.id.to_date_service);
        layoutParamilitary = (TextInputLayout)view.findViewById(R.id.layout_paramilitary);
        explainParamilitary = (EditText)view.findViewById(R.id.explain_paramilitary);
        rgPriviousEmployed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbPriviousEmployed1.isChecked()){
                    selectedPreviousEmployed = "Y";
                }
                else if(rbPreviousEmployed2.isChecked()){
                    selectedPreviousEmployed = "N";
                }
            }
        });
        rgTravelledCountries.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTravelledCountry1.isChecked()){
                    selectedTravelOtherCountry = "Y";
                    layoutTravelledOther.setVisibility(View.VISIBLE);
                }
                else if(rbTravelledCountry2.isChecked()){
                    selectedTravelOtherCountry = "N";
                    travelledOtherCountries.setVisibility(View.GONE);
                }
            }
        });
        rgContributedOrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbContributed1.isChecked()){
                    selectedCharitable = "Y";
                    layoutContributed.setVisibility(View.VISIBLE);
                }
                else if(rbContributed2.isChecked()){
                    selectedCharitable = "N";
                    layoutContributed.setVisibility(View.GONE);
                }
            }
        });
        rgSpecializedSkill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbSpecializedSkill1.isChecked()){
                    selectedSpecialSkill = "Y";
                    layoutSpecialSkills.setVisibility(View.VISIBLE);
                }
                else if(rbSpecializesSkill2.isChecked()){
                    selectedSpecialSkill = "N";
                    layoutSpecialSkills.setVisibility(View.GONE);
                }
            }
        });
        rgServedMilitary.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbServedMilitary1.isChecked()){
                    selectedServedMilitary = "Y";
                    layoutMilitary.setVisibility(View.VISIBLE);
                }
                else if(rbServedMilitary2.isChecked()){
                    selectedServedMilitary = "N";
                    layoutMilitary.setVisibility(View.GONE);
                }
            }
        });
        rgParamilitary.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbParamilitary1.isChecked()){
                    selectedParamilitary = "Y";
                    layoutParamilitary.setVisibility(View.VISIBLE);
                }
                else if(rbParamilitary2.isChecked()){
                    selectedParamilitary = "N";
                    layoutParamilitary.setVisibility(View.GONE);
                }
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
        rgViolated = (RadioGroup)view.findViewById(R.id.rg_violated);
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
                    securityQuestion1 = "Y";
                }
                else if(rbCommunicable2.isChecked()){
                    securityQuestion1 = "N";
                }
            }
        });
        rgMentalDisorder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbMental1.isChecked()){
                    securityQuestion2 = "Y";
                }
                else if(rbMental2.isChecked()){
                    securityQuestion2 = "N";
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
                    securityQuestion3 = "Y";
                }
                else if(rbDrugAddict2.isChecked()){
                    securityQuestion3 = "N";
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
                    securityQuestion4 = "Y";
                }
                else if(rbArrested2.isChecked()){
                    securityQuestion4 = "N";
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
                    securityQuestion5 = "Y";
                }
                else if(rbViolatedLaw2.isChecked()){
                    securityQuestion5 = "N";
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
                    securityQuestion6 = "Y";
                }
                else if(rbMoney2.isChecked()){
                    securityQuestion6 = "N";
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
                    securityQuestion7 = "Y";
                }
                else if(rbHumanTraffic2.isChecked()){
                    securityQuestion7 = "N";
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
                    securityQuestion8 = "Y";
                }
                else if(rbAidedHuman2.isChecked()){
                    securityQuestion8 = "N";
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
                    securityQuestion9 = "Y";
                }
                else if(rbRelativeHuman2.isChecked()){
                    securityQuestion9 = "N";
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
                    securityQuestion10 = "Y";
                }
                else if(rbIllegalActivity2.isChecked()){
                    securityQuestion10 = "N";
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
                    securityQuestion11 = "Y";
                }
                else if(rbTerroristActivity1.isChecked()){
                    securityQuestion11 = "N";
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgTerroristMember.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbTerrorist1.isChecked()){
                    securityQuestion12 = "Y";
                }
                else if(rbTerrorist2.isChecked()){
                    securityQuestion12 = "N";
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgSupportTerrorist.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbSupportTerrorist1.isChecked()){
                    securityQuestion13 = "Y";
                }
                else if(rbSupportTerrorist2.isChecked()){
                    securityQuestion13 = "N";
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
                    securityQuestion14 = "Y";
                }
                else if(rbGenocide2.isChecked()){
                    securityQuestion14 = "N";
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
                    securityQuestion15 = "Y";
                }
                else if(rbTorture2.isChecked()){
                    securityQuestion15 = "N";
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
                    securityQuestion16 = "Y";
                }
                else if(rbKilling2.isChecked()){
                    securityQuestion16 = "N";
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
                    securityQuestion17 = "Y";
                }
                else if(rbChildSoldier2.isChecked()){
                    securityQuestion17 = "N";
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
                    securityQuestion18= "Y";
                }
                else if(rbReligiousFreedom2.isChecked()){
                    securityQuestion18 = "N";
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
                    securityQuestion19 = "Y";
                }
                else if(rbAbortion2.isChecked()){
                    securityQuestion19 = "N";
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
                    securityQuestion20 = "Y";
                }
                else if(rbTransplant2.isChecked()){
                    securityQuestion20 = "N";
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
                    securityQuestion21 = "Y";
                }
                else if(rbFraudVisa2.isChecked()){
                    securityQuestion21 = "N";
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
                    securityQuestion22 = "Y";
                }
                else if(rbCustody2.isChecked()){
                    securityQuestion22 = "N";
                }
                else {
                    showToast("Check all field");
                }
            }
        });
        rgViolated.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbVoted1.isChecked()){
                    securityQuestion23 = "Y";
                }
                else if(rbVoted2.isChecked()){
                    securityQuestion23 = "N";
                }
            }
        });
        rgAvoidingTaxation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(rbAvoidingTaxation1.isChecked()){
                    securityQuestion24 = "Y";
                }
                else if(rbAvoidingTaxation2.isChecked()){
                    securityQuestion24 = "N";
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
                    securityQuestion25 = "Y";
                }
                else if(rbProstitution2.isChecked()){
                    securityQuestion25 = "N";
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
        disableInput(interviewPriority1);
        disableInput(interviewPriority2);
        disableInput(interviewPriority3);
        disableInput(biometricPriority1);
        disableInput(biometricPriority2);
        disableInput(biometricPriority3);
        interviewPriority1.setOnClickListener(this);
        interviewPriority2.setOnClickListener(this);
        interviewPriority3.setOnClickListener(this);
        biometricPriority1.setOnClickListener(this);
        biometricPriority2.setOnClickListener(this);
        biometricPriority3.setOnClickListener(this);
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
        attach1.setOnClickListener(this);
        attach2 = (ImageView)view.findViewById(R.id.attach_usa_file2);
        attach2.setOnClickListener(this);
        attach3 = (ImageView)view.findViewById(R.id.attach_usa_file3);
        attach3.setOnClickListener(this);
        attach4 = (ImageView)view.findViewById(R.id.attach_usa_file4);
        attach4.setOnClickListener(this);
        attachFileName1 = (TextView)view.findViewById(R.id.doc1);
        attachFileName2 = (TextView)view.findViewById(R.id.doc2);
        attachFileName3 = (TextView)view.findViewById(R.id.doc3);
        attchFileName4 = (TextView)view.findViewById(R.id.doc4);

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
                sendForm3Data();
                moveToNextForm();
                break;
            case R.id.button_form4:
                sendForm4Data();
                moveToNextForm();
                break;
            case R.id.button_form5:
                sendForm5Data();
                moveToNextForm();
                break;
            case R.id.button_form6:
                sendForm6Data();
                moveToNextForm();
                break;
            case R.id.button_form7:
                sendForm7Data();
                moveToNextForm();
                break;
            case R.id.button_form8:
                sendForm8Data();
                moveToNextForm();
                break;
            case R.id.button_form9:
                sendForm9Data();
                moveToNextForm();
                break;
            case R.id.button_form10:
                sendForm10Data();
                moveToNextForm();
                break;
            case R.id.button_form11:
                sendForm11Data();
                moveToNextForm();
                break;
            case R.id.button_form12:
                sendForm12Data();
                moveToNextForm();
                break;
            case R.id.button_form13:
                moveToNextForm();
                sendForm13Data();
                break;
            case R.id.button_form14:
                sendForm14Data();
                moveToNextForm();
                break;
            case R.id.button_form15:
                checkDocuments(BASE_URL_UPLOAD_DOCS);
                break;
            case R.id.attach_usa_file1:
                showFileChooser(1);
                break;
            case R.id.attach_usa_file2:
                showFileChooser(2);
                break;
            case R.id.attach_usa_file3:
                showFileChooser(3);
                break;
            case R.id.attach_usa_file4:
                showFileChooser(4);
                break;
            case R.id.father_date_of_birth:
                datePicker(fatherDateBirth);
                break;
            case R.id.mother_date_of_birth:
                datePicker(motherDateBirth);
                break;
            case R.id.date_of_birth:
                datePicker(dateOfBirth);
                break;
            case R.id.interview_priority1:
                datePicker(interviewPriority1);
                break;
            case R.id.interview_priority2:
                datePicker(interviewPriority2);
                break;
            case R.id.interview_priority3:
                datePicker(interviewPriority3);
                break;
            case R.id.biometric_priority1:
                datePicker(biometricPriority1);
                break;
            case R.id.biometric_priority2:
                datePicker(biometricPriority2);
                break;
            case R.id.biometric_priority3:
                datePicker(biometricPriority3);
                break;
            case R.id.arrived_date:
                datePicker(preArrivalUs);
                break;
            case R.id.departed_date:
                datePicker(preDepartureUs);
                break;
            case R.id.previous_visa_issue_date:
                datePicker(previousVisaIssueDate);
                break;
        }


    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spnr_birth_country:
                int countryId = allcountry.get(i).getId();
                System.out.println("Country Id:"+countryId + "& Country is:" + allcountry.get(i).getName());
                String urlStates = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=state&LivingInId=" + countryId;
                loadStateByCountry(urlStates);
                System.out.println(urlStates);
                break;
            case R.id.country_origin:
                break;
            case R.id.spnr_resident_other_country:
                break;
            case R.id.spnr_birth_state:
                break;
            case R.id.mailing_country_spnr:
                break;
            case R.id.spnr_document_type:
                break;
            case R.id.spnr_country_stolen_passport:
                break;
            case R.id.spnr_person_paying:
                break;
            case R.id.spnr_relation_payee_trip:
                break;
            case R.id.spnr_state_contact_us:
                break;
            case R.id.spnr_father_status:
                selectedFatherStatus = status[i];
                break;
            case R.id.spnr_status_mother:
                selectedMotherStatus = status[i];
                break;
            case R.id.spnr_primary_occupation:
                break;
            case R.id.spnr_consulate_interview:
                break;
            case R.id.spnr_state_interview:
                break;
            case R.id.spnr_indented_length:
                int indentedLength = i;
                if(indentedLength == 1){
                    noMonths.setHint("No of years");}
                else if(indentedLength == 2){
                    noMonths.setHint(R.string.no_of_months_us);}
                else if(indentedLength == 3){
                    noMonths.setHint(R.string.no_of_weeks_us);}
                else if(indentedLength == 4){
                    noMonths.setHint(R.string.no_of_days_us);}
                else if(indentedLength == 5){
                    noMonths.setHint("No of hours");
                }
                else if(indentedLength == 0){
                    noMonths.setHint("Stay Length");
                }
                break;
            case R.id.spnr_country_occupation:
                selectedOccupationCountry = allCountriesData.get(i);
                if(i != 0){codeEmployment.setText("+" + allcountry.get(i).getPhoneCode());}
                selectedEmploymentCode = allcountry.get(i).getPhoneCode();
                break;




        }
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
        spnrGender.setSelection(sharedPreferences.getInt("gender_usa_num", 0));
        spnrGender.setOnItemSelectedListener(this);
    }
    private void populateMaritalStatusSpinner(){
        spnrMarital = (Spinner)view.findViewById(R.id.marital_status_form2);
        martialStatus = new String[]{"Select One", "Single", "Married", "Common Law Marriage", "Civil Union/Domestic Partnership", "Widowed","Divorce", "Legally Separated", "Other"};
        ArrayAdapter<String> maritalAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, martialStatus);
        maritalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrMarital.setAdapter(maritalAdapter);
        spnrMarital.setSelection(sharedPreferences.getInt("marital_usa_num",0));
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
    private void populateFamilyStatusSpinner(Spinner memberSpinner, int id){
        memberSpinner = (Spinner)view.findViewById(id);
        status = new String[]{"Select One","U.S. Citizen", "US Legal Permanent Resident", "Nonimigrant"};
        ArrayAdapter<String> documentTypeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, status);
        documentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberSpinner.setAdapter(documentTypeAdapter);
        memberSpinner.setOnItemSelectedListener(this);
    }
    private void populateStolenPassportSpinner(){
        stolenPassport = (Spinner)view.findViewById(R.id.stolen_passport);
        stolen = new String[]{"Select One","Yes","No"};
        ArrayAdapter<String> stolenPassportAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,stolen);
        stolenPassportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stolenPassport.setAdapter(stolenPassportAdapter);
        stolenPassport.setOnItemSelectedListener(this);
        stolenPassport.setSelection(sharedPreferences.getInt("stolen_passport", 0));
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
    private void loadStates(String urlStates){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.usa-visahub.in")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        StateResponse request = retrofit.create(StateResponse.class);
        Call<States> call = request.getState(urlStates);

        call.enqueue(new Callback<States>() {
            @Override
            public void onResponse(Call<States> call, Response<States> response) {
                States jsonResponse = response.body();
                states = jsonResponse.getState();
                for(int i=0;i<states.size();i++){
                    stateData.add(states.get(i).getStateName());
                }
                if(stateData != null){
                    ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, stateData);
                    stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    stateStayUs.setAdapter(stateAdapter);
                }
            }
            @Override
            public void onFailure(Call<States> call, Throwable t) {
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
    private void loadTripPurpose(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://usa-visahub.in")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PurposeResponse request = retrofit.create(PurposeResponse.class);
        retrofit2.Call<PurposeUs> call = request.getPurpose();

        call.enqueue(new Callback<PurposeUs>() {
            @Override
            public void onResponse(Call<PurposeUs> call, Response<PurposeUs> response) {
                PurposeUs jsonResponse = response.body();
                purpose = jsonResponse.getPurpose();
                for(int i=0;i<purpose.size();i++){
                    purposeData.add( purpose.get(i).getPurpose());
                }
                if(purposeData != null){
                    ArrayAdapter<String> occupationAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, purposeData);
                    occupationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    purposeUS.setAdapter(occupationAdapter);
                    purposeUS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedPurposeSpinner = i;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

            }
            @Override
            public void onFailure(retrofit2.Call<PurposeUs> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadStateByCountry(String urlStates) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.usa-visahub.in")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        StateResponse request = retrofit.create(StateResponse.class);
        Call<States> call = request.getState(urlStates);
        //
        call.enqueue(new Callback<States>() {
            @Override
            public void onResponse(Call<States> call, Response<States> response) {


                States jsonResponse = response.body();
                statesByCountry = jsonResponse.getState();
                stateDataByCountry.clear();
                for(int i=0;i<statesByCountry.size();i++){
                    stateDataByCountry.add(statesByCountry.get(i).getStateName());
                }
                ArrayAdapter<String> dataAdapterCountries = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, stateDataByCountry);
                dataAdapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                birthStateSpnr.setAdapter(dataAdapterCountries);
            }
            @Override
            public void onFailure(Call<States> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadAllCountries() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.usa-visahub.in")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AllCountryUsa request = retrofit.create(AllCountryUsa.class);
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
    private void loadAllCountriesOccupation(final Spinner countryEmployment) {
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
                countryEmployment.setAdapter(dataAdapterCountries);


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
                .addFormDataPart("per_visa_id", sharedPreferences.getString("vad_id",""))
                .addFormDataPart("per_vad_id", sharedPreferences.getString("response",""))
                .addFormDataPart("per_other_name", surName.getText().toString())
                .addFormDataPart("per_other_sur", otherSurName.getText().toString())
                .addFormDataPart("per_other_given", otherGivenName.getText().toString())
                .addFormDataPart("telecode_name", "")
                .addFormDataPart("telecode_sur", telecodeSurname.getText().toString())
                .addFormDataPart("telecode_given", telecodeGivenName.getText().toString())
                .addFormDataPart("date_of_birth", dateOfBirth.getText().toString())
                .addFormDataPart("birth_state","")
                .addFormDataPart("birth_country", "")
                .addFormDataPart("per_other_nationality","")
                .addFormDataPart("per_other_nation_name", otherNationality.getText().toString())
                .addFormDataPart("per_other_pass", "")
                .addFormDataPart("per_other_pass_no", otherPassportNumber.getText().toString())
                .addFormDataPart("per_other_permanent", "")
                .addFormDataPart("per_other_permanent_name","")
                .addFormDataPart("per_NIN", nationalIdentificationNumber.getText().toString())
                .addFormDataPart("per_SSN", usSecurityNumber.getText().toString())
                .addFormDataPart("per_TIN",taxPayerId.getText().toString())
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
                .addFormDataPart("per_mail_line1", otherMailingAddress.getText().toString())
                .addFormDataPart("per_mail_line2", "")
                .addFormDataPart("per_mail_city", otherCityMailing.getText().toString())
                .addFormDataPart("per_mail_state", otherStateMailing.getText().toString())
                .addFormDataPart("per_mail_zip", otherPostalMailing.getText().toString())
                .addFormDataPart("per_mail_country", "")
                .addFormDataPart("per_sec_phone", secondaryPhoneNumber.getText().toString())
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
                .addFormDataPart("traveldocumentnum", passportBookNumber.getText().toString())
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
                .addFormDataPart("travel_vad_id", sharedPreferences.getString("response_usa",""))
                .addFormDataPart("travel_purpose", "")
                .addFormDataPart("travel_length", "")
                .addFormDataPart("travel_length_val", noMonths.getText().toString())
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
               // .addFormDataPart("pre_inus",selectedeverBeenUs)
                .addFormDataPart("pre_inus_arrive", preArrivalUs.getText().toString())
                .addFormDataPart("pre_inus_depart", preDepartureUs.getText().toString())
                .addFormDataPart("pre_inus_licence", "")
                .addFormDataPart("pre_inus_licence_state", "")
                //.addFormDataPart("pre_usvisa", selectedApplyingSameVisa)
                .addFormDataPart("pre_usvisa_date", previousVisaIssueDate.getText().toString())
                .addFormDataPart("pre_usvisa_sametype", "")
                .addFormDataPart("pre_usvisa_samecountry", "")
                .addFormDataPart("pre_usvisa_ten", "")
                .addFormDataPart("pre_usvisa_lost", "")
                .addFormDataPart("pre_usvisa_lost_year", "")
                .addFormDataPart("pre_usvisa_lost_explain", "")
                //.addFormDataPart("pre_usvisa_revoke", selectedRefusedVisa)
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
                .addFormDataPart("contact_name", nameContactUs.getText().toString())
                .addFormDataPart("contact_relation", "")
                .addFormDataPart("contact_add1", addressContactUs.getText().toString())
                .addFormDataPart("contact_add2", "")
                .addFormDataPart("contact_city", "")
                .addFormDataPart("contact_state", "")
                .addFormDataPart("contact_phone", "+1" + numberContactUs.getText().toString())
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
                .addFormDataPart("fam_father", fatherName.getText().toString())
                .addFormDataPart("fam_father_dob", fatherDateBirth.getText().toString())
//                .addFormDataPart("fam_father_inus", selectedFatherUs)
//                .addFormDataPart("fam_father_status", selectedFatherStatus)
                .addFormDataPart("fam_mother", motherName.getText().toString())
                .addFormDataPart("fam_mother_dob", motherDateBirth.getText().toString())
 //               .addFormDataPart("fam_mother_inus", selectedMotherUs)
 //               .addFormDataPart("fam_mother_status", selectedMotherStatus)
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
                .addFormDataPart("work_name", employerName.getText().toString())
                .addFormDataPart("work_add", addressEmployer.getText().toString())
                .addFormDataPart("work_city", cityEmployment.getText().toString())
                //.addFormDataPart("work_code", String.valueOf(selectedEmploymentCode))
                .addFormDataPart("country", selectedOccupationCountry)
                .addFormDataPart("work_phone", contactNumberEmployment.getText().toString())
                .addFormDataPart("work_country", selectedOccupationCountry)
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
//                .addFormDataPart("work_add_emp", selectedPreviousEmployed)
                .addFormDataPart("work_add_lang", languageSpeaking.getText().toString())
//                .addFormDataPart("work_add_countries", selectedTravelOtherCountry)
                .addFormDataPart("work_add_countries_list", "")
//                .addFormDataPart("work_add_charity", selectedCharitable)
                .addFormDataPart("work_add_charity_list", "")
//                .addFormDataPart("work_add_skill", selectedSpecialSkill)
                .addFormDataPart("work_add_skill_explain", "")
  //              .addFormDataPart("work_add_military", selectedServedMilitary)
                .addFormDataPart("work_add_military_country", "")
                .addFormDataPart("work_add_military_branch", "")
                .addFormDataPart("work_add_military_rank", "")
                .addFormDataPart("work_add_military_speciality", "")
                .addFormDataPart("work_add_military_from", "")
                .addFormDataPart("work_add_military_to", "")
                .addFormDataPart("work_add_paramilitary", selectedParamilitary)
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
                .addFormDataPart("ques_1", securityQuestion1)
                .addFormDataPart("ques_2", securityQuestion2)
                .addFormDataPart("ques_3", securityQuestion3)
                .addFormDataPart("ques_4", securityQuestion4)
                .addFormDataPart("ques_5", securityQuestion5)
                .addFormDataPart("ques_6", securityQuestion6)
                .addFormDataPart("ques_7", securityQuestion7)
                .addFormDataPart("ques_8", securityQuestion8)
                .addFormDataPart("ques_9", securityQuestion9)
                .addFormDataPart("ques_10", securityQuestion10)
                .addFormDataPart("ques_11", securityQuestion11)
                .addFormDataPart("ques_12", securityQuestion12)
                .addFormDataPart("ques_13", securityQuestion13)
                .addFormDataPart("ques_14", securityQuestion14)
                .addFormDataPart("ques_15", securityQuestion15)
                .addFormDataPart("ques_16", securityQuestion16)
                .addFormDataPart("ques_17", securityQuestion17)
                .addFormDataPart("ques_18", securityQuestion18)
                .addFormDataPart("ques_19", securityQuestion19)
                .addFormDataPart("ques_20", securityQuestion20)
                .addFormDataPart("ques_21", securityQuestion21)
                .addFormDataPart("ques_22", securityQuestion22)
                .addFormDataPart("ques_23", securityQuestion23)
                .addFormDataPart("ques_24", securityQuestion24)
                .addFormDataPart("ques_25", securityQuestion25)
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
                .addFormDataPart("visa_id",sharedPreferences.getString("response",""))
                .addFormDataPart("city",consulateCity.getText().toString())
                .addFormDataPart("consulate_id","")
                .addFormDataPart("prefer_date_first", interviewPriority1.getText().toString())
                .addFormDataPart("prefer_date_second",interviewPriority2.getText().toString())
                .addFormDataPart("prefer_date_third",interviewPriority3.getText().toString())
                .addFormDataPart("prefer_biomatric_first",biometricPriority1.getText().toString())
                .addFormDataPart("prefer_biomatric_second",biometricPriority2.getText().toString())
                .addFormDataPart("prefer_biomatric_third",biometricPriority3.getText().toString())
                .addFormDataPart("dr_delivery_address1",deliveryAddress.getText().toString())
                .addFormDataPart("dr_delivery_address2","")
                .addFormDataPart("dr_delivery_city",deliveryCity.getText().toString())
                .addFormDataPart("dr_delivery_state",deliveryState.getText().toString())
                .addFormDataPart("dr_delivery_country","")
                .addFormDataPart("dr_delivery_country_code","")
                .addFormDataPart("pk_name","")
                .addFormDataPart("pr_location","")
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
    private void showFileChooser(int one) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra("path", Environment.getExternalStorageDirectory().getPath());
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
        ContentResolver cR;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        switch (requestCode){
            case 1:
                uri = data.getData();
                filePath1 = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                 fileType1= cR.getType(uri);
                selectedFile1=filePath1.substring(filePath1.lastIndexOf("/")+1);
                attachFileName1.setText(selectedFile1);
                checked1.setImageResource(R.drawable.checked);
                buttonForm15.setText("Submit");
                break;
            case 2:
                uri = data.getData();
                filePath2 = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                fileType2 = cR.getType(uri);
                selectedFile2=filePath2.substring(filePath2.lastIndexOf("/")+1);
                attachFileName2.setText(selectedFile2);
                checked2.setImageResource(R.drawable.checked);
                buttonForm15.setText("Submit");
                break;
            case 3:
                uri = data.getData();
                filePath3 = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                fileType3 = cR.getType(uri);
                selectedFile3=filePath3.substring(filePath3.lastIndexOf("/")+1);
                attachFileName3.setText(selectedFile3);
                checked3.setImageResource(R.drawable.checked);
                buttonForm15.setText("Submit");
                break;
            case 4:
                uri = data.getData();
                filePath4 = getPath(getContext(),uri);
                cR = getContext().getContentResolver();
                fileType4 = cR.getType(uri);
                selectedFile4=filePath4.substring(filePath4.lastIndexOf("/")+1);
                attchFileName4.setText(selectedFile4);
                checked4.setImageResource(R.drawable.checked);
                buttonForm15.setText("Submit");
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
    private void uploadDocuments(String response2, String selectedFile1,  String fileType1, String fileName1, String uploadedFile,String s) {
        System.out.println(response2 + "+" + selectedFile1 + "+" + fileType1 + "+" + fileName1 + "+" + uploadedFile + "+" + s );
        file = new File(selectedFile1);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(uploadedFile, fileName1,
                        RequestBody.create(MediaType.parse(fileType1), file))
                .addFormDataPart("visa_id", response2)
                .addFormDataPart("visa_pid",sharedPreferences.getString("vad_id",""))

                .build();
        Request request = new Request.Builder().url(s).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                try {
                    String resp = response.body().string();
                    Log.v("Docs", resp);
                    System.out.println(resp);
                    if (response.isSuccessful()) {

                    } else {

                    }
                } catch (IOException e) {
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void checkDocuments(String baseUrlUploadDocs) {
        String visaId = sharedPreferences.getString("response","");
        if(filePath1.length() != 0){
            uploadDocuments(visaId,filePath1,fileType1,selectedFile1, "uploaded_file", baseUrlUploadDocs +"uploadphoto");
        }
        if(filePath2.length() != 0){
            uploadDocuments(visaId,filePath2,fileType2,selectedFile2, "uploaded_file1", baseUrlUploadDocs +"uploadpassport");
        }
        if(filePath3.length() != 0){
            uploadDocuments(visaId,filePath3,fileType3,selectedFile3, "uploaded_file2", baseUrlUploadDocs +"uploadadd");
        }
        if(filePath4.length() != 0){
            uploadDocuments(visaId,filePath4,fileType4,selectedFile4, "uploaded_file3", baseUrlUploadDocs +"uploadadd1");
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
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
    private void disableInput(EditText editText) {
        editText.setFocusable(false);
        editText.setTextColor(R.color.disabled_text);
    }


}
