package international.rst.com.rstsimplified.Fragments;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Model.OccupationResponseUsa;
import international.rst.com.rstsimplified.Model.Profession;
import international.rst.com.rstsimplified.Model.ProfessionRes;
import international.rst.com.rstsimplified.Model.ProfessionResponse;
import international.rst.com.rstsimplified.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentUSAForm extends android.support.v4.app.Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String title;
    View view;
    ViewPager mViewPager;
    int atTab;
    String[] gender, martialStatus, documentType, stolen, contactPersonUs, relationPerson, indentedLength, payingTrip;
    Button buttonForm1, buttonForm2, buttonForm3, buttonForm4, buttonForm5, buttonForm6, buttonForm7, buttonForm8, buttonForm9, buttonForm10, buttonForm11, buttonForm12, buttonForm13, buttonForm14, buttonForm15;
    EditText arrivingDate, departureDate, currentAddress, currentCity, phoneCode, mobileNumberCurrent, countryCurrent, email, surName, givenName, nationalIdentificationNumber, usSecurityNumber, taxPayerId, maritalStatus, placeOfBirth, dateOfBirth, homeAddress, city, pinCode, state, country, countryCode, primaryPhoneNumber, secondaryPhoneNumber, emailAddress, passportNumber, passportBookNumber, issueCountry, issueCity, issueDate, purposeUS, noMonths, addressStayUS, personPayingTrip, nameContactUs, addressContactUs, codeContactUs, numberContactUs, fatherName, fatherDateBirth, motherName, motherDateBirth, employerName, addressEmployer, cityEmployment, countryEmployment, codeEmployment, contactNumberEmployment, languageSpeaking, consulateCity, interviewPriority1, interviewPriority2, interviewPriority3, biometricPriority1, biometricPriority2, biometricPriority3, deliveryAddress, deliveryState, deliveryCity, deliveryPinCode, nameFirst, nameLast, placeOfBirthForm2, stateCurrent, postalCodeCurrent, passportNumberForm2, issuedCity, issuedCountry, issuedDate, expiryDate, expiryDateForm2, currentNationality;
    Spinner birthCountrySpnr, birthStateSpnr, nationalitySpnr, travelDocType, stolenPassport, contactUs, relationContactUs, stateContactUs, primaryOccupation, interViewState, interviewConsulate, spnrGender, spnrMarital, stayLenght, personPaying;
    RadioGroup rgOtherName, rgTelecode, rgGender, rgOtherNationality, rgPermanentResident, rgMailAddress, rgotherPersonTravelling, rgTravelledUS, rgIssuedUsVisa, rgRefusedUsVisa, rgFatherUs, rgMotherUs, rgPriviousEmployed, rgTravelledCountries, rgContributedOrg, rgSpecializedSkill, rgServedMilitary, rgParamilitary, rgCommunicableDisease, rgMentalDisorder, rgdrugAbuser, rgArrested, rgViolated, rgMoneyLaundering, rgHumanTrafficing, rgHumanTrafficingAided, rgRelativeHumanTrafficing, rgIllegal, rgTerrorist, rgSupportTerrorist, rgTerroristMember, rgGenocide, rgTorture, rgKilling, rgChildSoldiers, rgReligiousFreedom, rgAbortion, rgTransplantation, rgFraudVisa, rgCustody, rgUsChild, rgViolatedLaw, rgAvoidingTaxation, rgProstitution;
    RadioButton rbOtherName1, rbOtherName2, rbTelecode1, rbTelecode2, rbGender1, rbGender2, rbotherNationality1, rbOtherNationality2, rbPermanentResident1, rbPermanentResident2, rbMailAddress1, rbMailAddrerss2, rbPersonTravelling1, rbPersonTravelling2, rbTravelledUs1, rbTravelledUs2, rbIssued1, rbIssued2, rbRefused1, rbRefused2, rbFatherUs1, rbFatherUs2, rbMotherUs1, rbMotherUs2, rbPriviousEmployed1, rbPreviousEmployed2, rbTravelledCountry1, rbTravelledCountry2, rbContributed1, rbContributed2, rbSpecializedSkill1, rbSpecializesSkill2, rbServedMilitary1, rbServedMilitary2, rbParamilitary1, rbParamilitary2, rbCommunicable1, rbCommunicable2, rbMental1, rbMental2, rbDrugAddict1, rbDrugAddict2, rbArrested1, rbArrested2, rbViolatedLaw1, rbViolatedLaw2, rbMoney1, rbMoney2, rbHumanTraffic1, rbHumanTraffic2, rbAidedHuman1, rbAidedHuman2, rbRelativeHuman1, rbRelativeHuman2, rbIllegalActivity1, rbIllegalActivity2, rbTerroristActivity1, rbTerroristActivity2, rbSupportTerrorist1, rbSupportTerrorist2, rbTerrorist1, rbTerrorist2, rbGenocide1, rbGenocide2, rbTorture1, rbTorture2, rbKilling1, rbKilling2, rbChildSoldier1, rbChildSoldier2, rbReligiousFreedom1, rbReligiousFreedom2, rbAbortion1, rbAbortion2, rbTransplant1, rbTransplant2, rbFraudVisa1, rbFraudVisa2, rbCustody1, rbCustody2, rbVoted1, rbVoted2, rbAvoidingTaxation1, rbAvoidingTaxation2, rbProstitution1, rbProstitution2;
    ImageView checked1, checked2, checked3, checked4,  attach1, attach2, attach3, attach4;
    private List<ProfessionRes> professionList = new ArrayList<>();
    private List<String> occupation = new ArrayList<>();
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
        }
        else if(title.equalsIgnoreCase("form6")){
            view = inflater.inflate(R.layout.usa_form6,container,false);
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
            initializeForm14View();
        }
        else if(title.equalsIgnoreCase("form15")){
            view = inflater.inflate(R.layout.usa_form15,container,false);
            initializeForm15View();
        }
        return view;
    }

    private void initializeForm1View() {
        nameFirst = (EditText)view.findViewById(R.id.name_first_usa);
        nameLast = (EditText)view.findViewById(R.id.name_last_usa);
        email = (EditText)view.findViewById(R.id.email_usa);
        mobileNumberCurrent = (EditText)view.findViewById(R.id.phone_applicant_usa);
        arrivingDate = (EditText)view.findViewById(R.id.edt_arrival_usa);
        departureDate = (EditText)view.findViewById(R.id.edt_departure_usa);
        buttonForm1 = (Button)view.findViewById(R.id.button_form1);
        buttonForm1.setOnClickListener(this);
    }
    private void initializeForm2View() {
        spnrGender = (Spinner)view.findViewById(R.id.spnr_gender);
        spnrMarital = (Spinner)view.findViewById(R.id.marital_status_form2);
        stolenPassport = (Spinner)view.findViewById(R.id.stolen_passport);
        currentNationality = (EditText)view.findViewById(R.id.current_nationality);
        currentAddress = (EditText) view.findViewById(R.id.current_address);
        currentCity = (EditText)view.findViewById(R.id.current_city_form2);
        placeOfBirthForm2 = (EditText)view.findViewById(R.id.place_of_birth_form2);
        stateCurrent = (EditText)view.findViewById(R.id.current_state_form2);
        postalCodeCurrent = (EditText)view.findViewById(R.id.postal_code_current);
        countryCurrent = (EditText)view.findViewById(R.id.current_country);
        passportNumberForm2 = (EditText)view.findViewById(R.id.passport_number_form2);
        issueCity = (EditText)view.findViewById(R.id.city_issued_form2);
        issuedCountry = (EditText)view.findViewById(R.id.country_issued_form2);
        issueDate = (EditText)view.findViewById(R.id.issue_date_form2);
        expiryDateForm2 = (EditText)view.findViewById(R.id.expiry_date_form2);
        buttonForm2 = (Button)view.findViewById(R.id.button_form2);
        buttonForm2.setOnClickListener(this);
    }
    private void initializeForm3View() {
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


    }
    private void initializeForm4View() {
        currentAddress = (EditText)view.findViewById(R.id.current_address);
        currentCity = (EditText)view.findViewById(R.id.current_city);
        pinCode = (EditText)view.findViewById(R.id.current_city_postal);
        state = (EditText)view.findViewById(R.id.current_state);
        countryCurrent = (EditText)view.findViewById(R.id.current_country);
        rgMailAddress = (RadioGroup)view.findViewById(R.id.rg_mailing_address);
        rbMailAddress1 = (RadioButton)view.findViewById(R.id.rb_mailing_address1);
        rbMailAddrerss2 = (RadioButton)view.findViewById(R.id.rb_mailing_address2);
        countryCode = (EditText)view.findViewById(R.id.country_code);
        primaryPhoneNumber = (EditText)view.findViewById(R.id.primary_phone_number);
        secondaryPhoneNumber = (EditText)view.findViewById(R.id.secondary_phone_number);
        emailAddress = (EditText)view.findViewById(R.id.email_address);
        buttonForm4 = (Button)view.findViewById(R.id.button_form4);
        buttonForm4.setOnClickListener(this);
    }
    private void initializeForm5View() {
        travelDocType = (Spinner)view.findViewById(R.id.spnr_document_type);
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
        stayLenght = (Spinner)view.findViewById(R.id.spnr_indented_length);
        personPaying = (Spinner)view.findViewById(R.id.spnr_person_paying);
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
        buttonForm8.setOnClickListener(this);
    }
    private void initializeForm9View() {
        relationContactUs = (Spinner)view.findViewById(R.id.spnr_relation_contact_us);
        contactUs = (Spinner)view.findViewById(R.id.spnr_contact_person_us);
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
    }
    private void initializeForm11View() {
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
        intializeSpinners();
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void populateGenderSpinner(){
        gender = new String[]{"Select One", "Male", "Female"};
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, gender);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrGender.setAdapter(dataAdapterPort);
        spnrGender.setOnItemSelectedListener(this);

    }
    private void populateMaritalStatusSpinner(){
        martialStatus = new String[]{"Select One", "Single", "Married", "Common Law Marriage", "Civil Union/Domestic Partnership", "Widowed","Divorce", "Legally Separated", "Other"};
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, martialStatus);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrMarital.setAdapter(dataAdapterPort);
        spnrMarital.setOnItemSelectedListener(this);
    }
    private void populateDocumentTypeSpinner(){
        documentType = new String[]{"Select One","Passport", "Travel Document"};
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, documentType);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelDocType.setAdapter(dataAdapterPort);
        travelDocType.setOnItemSelectedListener(this);
    }
    private void populateStolenPassportSpinner(){
        stolen = new String[]{"Select One","Yes","No"};
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,stolen);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stolenPassport.setAdapter(dataAdapterPort);
        stolenPassport.setOnItemSelectedListener(this);
    }
    private void populateContactPersonUsSpinner(){
        contactPersonUs = new String[]{"Select One","Organisation","Person"};
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, contactPersonUs);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contactUs.setAdapter(dataAdapterPort);
        contactUs.setOnItemSelectedListener(this);
    }
    private void populateRelationPersonSpinner(){
        relationPerson = new String[]{"Select One", "Relative", "Spouse", "Friend", "Biusness Associate", "Employer", "School Official", "other"};
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, relationPerson);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationContactUs.setAdapter(dataAdapterPort);
        relationContactUs.setOnItemSelectedListener(this);
    }
    private void populateIndentedLength(){
        indentedLength = new String[]{"Select One","Year(s)","Month(s)","Week(s)","Day(s)","Less than 24 hours"};
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, indentedLength);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stayLenght.setAdapter(dataAdapterPort);
        stayLenght.setOnItemSelectedListener(this);
    }
    private void populatePersonPayingTrip(){
        payingTrip = new String[]{"Select One","Self","Other Person/Organisation"};
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, payingTrip);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personPaying.setAdapter(dataAdapterPort);
        personPaying.setOnItemSelectedListener(this);
    }
    private void stateUsSpinner(){

    }
    private void moveToNextForm() {
        atTab = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(atTab + 1);
    }
    private void loadDataApi(){
        loadOccupation();
    }
    private void intializeSpinners(){
        populateContactPersonUsSpinner();
        populateDocumentTypeSpinner();
        populateGenderSpinner();
        populateIndentedLength();
        populateMaritalStatusSpinner();
        populatePersonPayingTrip();
        populateRelationPersonSpinner();
        populateStolenPassportSpinner();
    }
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

                //Log.v("Occupation Size", String.valueOf(occupation.size()));
            }
            @Override
            public void onFailure(retrofit2.Call<Profession> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }


}
