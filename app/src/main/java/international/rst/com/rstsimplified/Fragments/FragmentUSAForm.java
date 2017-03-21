package international.rst.com.rstsimplified.Fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import international.rst.com.rstsimplified.R;
public class FragmentUSAForm extends android.support.v4.app.Fragment {
    String title;
    View view;
    Button buttonForm3, buttonForm4, buttonForm5, buttonForm6, buttonForm7, buttonForm8, buttonForm9, buttonForm10, buttonForm11, buttonForm12, buttonForm13, buttonForm14, buttonForm15;
    EditText arrivingDate, departureDate, currentAddress, currentCity, phoneCode, mobileNumberCurrent, countryCurrent, email, surName, givenName, nationalIdentificationNumber, usSecurityNumber, taxPayerId, maritalStatus, placeOfBirth, dateOfBirth, homeAddress, city, pinCode, state, country, countryCode, primaryPhoneNumber, secondaryPhoneNumber, emailAddress, passportNumber, passportBookNumber, issueCountry, issueCity, issueDate, expiryDate, purposeUS, stayLenght, noMonths, addressStayUS, personPayingTrip, nameContactUs, addressContactUs, codeContactUs, numberContactUs, fatherName, fatherDateBirth, motherName, motherDateBirth, employerName, addressEmployer, cityEmployment, countryEmployment, codeEmployment, contactNumberEmployment, languageSpeaking, consulateCity, interviewPriority1, interviewPriority2, interviewPriority3, biometricPriority1, biometricPriority2, biometricPriority3, deliveryAddress, deliveryState, deliveryCity, deliveryPinCode;
    Spinner birthCountrySpnr, birthStateSpnr, nationalitySpnr, travelDocType, stolenPassport, contactUs, relationContactUs, stateContactUs, primaryOccupation, interViewState, interviewConsulate;
    RadioGroup rgOtherName, rgTelecode, rgGender, rgOtherNationality, rgPermanentResident, rgMailAddress, rgotherPersonTravelling, rgTravelledUS, rgIssuedUsVisa, rgRefusedUsVisa, rgFatherUs, rgMotherUs, rgPriviousEmployed, rgTravelledCountries, rgContributedOrg, rgSpecializedSkill, rgServedMilitary, rgParamilitary, rgCommunicableDisease, rgMentalDisorder, rgdrugAbuser, rgArrested, rgViolated, rgMoneyLaundering, rgHumanTrafficing, rgHumanTrafficingAided, rgRelativeHumanTrafficing, rgIllegal, rgTerrorist, rgSupportTerrorist, rgTerroristMember, rgGenocide, rgTorture, rgKilling, rgChildSoldiers, rgReligiousFreedom, rgAbortion, rgTransplantation, rgFraudVisa, rgUsChild, rgViolatedLaw, rgAvoidingTaxation, rgProstitution;
    RadioButton rbOtherName1, rbOtherName2, rbTelecode1, rbTelecode2, rbGender1, rbGender2, rbotherNationality1, rbOtherNationality2, rbPermanentResident1, rbPermanentResident2, rbMailAddress1, rbMailAddrerss2, rbPersonTravelling1, rbPersonTravelling2, rbTravelledUs1, rbTravelledUs2, rbIssued1, rbIssued2, rbRefused1, rbRefused2, rbFatherUs1, rbFatherUs2, rbMotherUs1, rbMotherUs2, rbPriviousEmployed1, rbPreviousEmployed2, rbTravelledCountry1, rbTravelledCountry2, rbContributed1, rbContributed, rbSpecializedSkill1, rbSpecializesSkill2, rbServedMilitary1, rbServedMilitary2, rbParamilitary1, rbParamilitary2;
    public static FragmentUSAForm newFormInstance( String title) {
        FragmentUSAForm fragmentUsaForm = new FragmentUSAForm();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentUsaForm.setArguments(args);
        return fragmentUsaForm;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        if(title.equalsIgnoreCase("form1")){
            view = inflater.inflate(R.layout.consult_form,container, false);
            initializeForm1View();
        }
        else if(title.equalsIgnoreCase("form2")){
            view = inflater.inflate(R.layout.contact_form,container,false);
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
    }
    private void initializeForm2View() {
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
        birthCountrySpnr = (Spinner) view.findViewById(R.id.spnr_birth_country);
        birthStateSpnr = (Spinner)view.findViewById(R.id.spnr_birth_state);
        nationalitySpnr = (Spinner)view.findViewById(R.id.country_origin);
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
        stolenPassport = (Spinner) view.findViewById(R.id.spnr_lost_passport);
        buttonForm5 = (Button)view.findViewById(R.id.button_form5);
    }
    private void initializeForm6View() {
        purposeUS = (EditText)view.findViewById(R.id.purpose_us);
        stayLenght = (EditText)view.findViewById(R.id.length_stay);
        noMonths = (EditText)view.findViewById(R.id.no_of_months);
        addressStayUS = (EditText)view.findViewById(R.id.address_us_stay);
        personPayingTrip = (EditText)view.findViewById(R.id.person_paying);
        buttonForm6 = (Button)view.findViewById(R.id.button_form6);
    }
    private void initializeForm7View() {
        rgotherPersonTravelling = (RadioGroup)view.findViewById(R.id.rg_travelling_with);
        rbPersonTravelling1 = (RadioButton)view.findViewById(R.id.rb_person_travelling1);
        rbPersonTravelling2 = (RadioButton)view.findViewById(R.id.rb_person_travelling2);
        buttonForm7 = (Button)view.findViewById(R.id.button_form7);
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
    }
    private void initializeForm9View() {
        contactUs = (Spinner)view.findViewById(R.id.spnr_contact_person_us);
        nameContactUs = (EditText)view.findViewById(R.id.full_name_contact_us);
        relationContactUs = (Spinner)view.findViewById(R.id.spnr_relation_contact_us);
        addressContactUs = (EditText) view.findViewById(R.id.address_contact_us);
        stateContactUs = (Spinner)view.findViewById(R.id.spnr_state_contact_us);
        codeContactUs = (EditText)view.findViewById(R.id.phone_code_contact_us);
        numberContactUs = (EditText)view.findViewById(R.id.contact_number_us);
        buttonForm9 = (Button)view.findViewById(R.id.button_form9);
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
        buttonForm10 = (Button)view.findViewById(R.id.button_form10);
    }
    private void initializeForm11View() {
        primaryOccupation = (Spinner)view.findViewById(R.id.spnr_primary_occupation);
        employerName = (EditText)view.findViewById(R.id.employer_name);
        addressEmployer = (EditText)view.findViewById(R.id.employer_address);
        cityEmployment = (EditText)view.findViewById(R.id.employer_city);
        countryEmployment = (EditText)view.findViewById(R.id.employer_country);
        codeEmployment = (EditText)view.findViewById(R.id.employer_code);
        contactNumberEmployment = (EditText)view.findViewById(R.id.employer_number);
        buttonForm11 = (Button)view.findViewById(R.id.button_form11);
    }
    private void initializeForm12View() {
    }
    private void initializeForm13View() {
    }
    private void initializeForm14View() {
    }
    private void initializeForm15View() {
    }
}
