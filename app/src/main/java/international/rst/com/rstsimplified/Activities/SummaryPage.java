package international.rst.com.rstsimplified.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import international.rst.com.rstsimplified.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SummaryPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Bundle b;
    Button button;
    SharedPreferences sharedPreferences;
    String serviceType, livingId, nationalityId, processingTime, deviceType, deviceOS, serviceFeeCs, nameFirst, nameLast, birthDate, birthPlace, emailEdt, nameFather, nameMother, dateIssue, dateExpiry,passportNumber, fullNameVisa, arrivalDate, departureDate,gender, fullName, profession, professionId, selectedCountry,selectedIssueCountry, religionApplicant, maritalStatus;
    int visaTypeId, age, selectedVisaId;
    String currentCity, hotelAddress, contactperson, contactNumber, currentAddress, selectedEmirate,placeIssue, mobileNumber, countryId, selectedFile1, selectedFile2, selectedFile3, selectedFile4, selectedFile5, selectedFile6, selectedPort, responseVisa, refusedCountry, courtLaw, prohibitedCountry, differentPassport, otherCountryName, otherCountryAddress, startOtherCountry, endOtherCountry, resideOther, highestQualification, purposeSingapore, durationSingapore;
    TextView tvVisaId, visaName, visaFee, finalServiceFee, totalVisaFee, tvName, tvBirthDate, tvPassportNumber, tvGender, tvArrivalDate,tvDepartureDate ;
    Float govtFee, serviceFee, mngFee, totalFee;
    String fileName1, fileName2, fileName3, fileName4, fileName5, fileName6, fileType1, fileType2, fileType3, fileType4, fileType5, fileType6, sponsorName, sponsorAddress, selectedGcc, sponsorContact;
    private static final String SINGAPORE_APPLICANT_FORM = "http://singaporevisa-online.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data2";
    private static final String IRAN_APPLICANT_FORM = "http://iranvisas.in/api/getdatairn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=data2";
    private OkHttpClient client = new OkHttpClient();
    private static final String BASE_URL_APLLICANT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data";
    private static final String BASE_URL_UPLOAD_DOCS_UAE = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=";
    private static final String BASE_URL_UPLOAD_DOCS_SINGAPORE = "http://singaporevisa-online.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=";
    private static final String BASE_URL_UPLOAD_DOCS_IRAN = "http://iranvisas.in/api/getdatairn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=";

    String resp;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    Context context;
    File file, file1, file2, file3, file4, file5;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        tvVisaId = (TextView)findViewById(R.id.visa_id);
        visaName = (TextView)findViewById(R.id.visa_name_summary);
        visaFee = (TextView)findViewById(R.id.tv_visa_fee);
        finalServiceFee = (TextView)findViewById(R.id.tv_service_fee);
        totalVisaFee = (TextView)findViewById(R.id.total_fee_summary);
        tvName = (TextView)findViewById(R.id.tv_applicant_name);
        tvArrivalDate = (TextView)findViewById(R.id.tv_arrival_date);
        tvDepartureDate = (TextView)findViewById(R.id.tv_departure_date);
        tvBirthDate = (TextView)findViewById(R.id.tv_applicant_dob);
        tvPassportNumber = (TextView)findViewById(R.id.tv_passport_number);
        tvGender = (TextView)findViewById(R.id.tv_applicant_gender);
        button = (Button)findViewById(R.id.submit_payment);
        getSharedPreferencesData();
        if(sharedPreferences != null){
            System.out.println(selectedVisaId);
            if(selectedVisaId == 0){
                sendApplicandData(responseVisa);
            }
            else if(selectedVisaId == 2){
                singaporeApplicantData(responseVisa);
            }
            else if(selectedVisaId == 4){
                sendIranApplicantData(responseVisa);
            }

        }
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheck2 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED && permissionCheck2 != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE);
        } else {
            //uploadDocuments();



        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(getApplicationContext(), PaymentGateway.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //uploadDocuments();

                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.summary_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       /// Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

        } else if (id == R.id.visa_services) {
            Intent intent1=new Intent(this,ActivityServices.class);
            b=new Bundle();
            b.putInt("tab",0);
            intent1.putExtras(b);
            startActivity(intent1);
        } else if (id == R.id.airport_services) {
            Intent intent1=new Intent(this,ActivityServices.class);
            b=new Bundle();
            b.putInt("tab",1);
            intent1.putExtras(b);
            startActivity(intent1);
        } else if (id == R.id.hotel_services) {
            Intent intent1=new Intent(this,ActivityServices.class);
            b=new Bundle();
            b.putInt("tab",2);
            intent1.putExtras(b);
            startActivity(intent1);
        } else if (id == R.id.meet_greet) {
            Intent intent1=new Intent(this,ActivityServices.class);
            b=new Bundle();
            b.putInt("tab",3);
            intent1.putExtras(b);
            startActivity(intent1);
        } else if (id == R.id.sight_seeing) {

        }
        else if (id == R.id.car_parking) {

        }
        else if (id == R.id.launge_booking) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void sendApplicandData(String resp) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visaId", resp)
                .addFormDataPart("first_name", nameFirst)
                .addFormDataPart("last_name", nameLast)
                .addFormDataPart("gender", gender)
                .addFormDataPart("date_of_birth", birthDate)
                .addFormDataPart("birth_place", birthPlace)
                .addFormDataPart("birth_country",selectedCountry)
                .addFormDataPart("religion", religionApplicant)
                .addFormDataPart("email", emailEdt)
                .addFormDataPart("fathers_name", nameFather)
                .addFormDataPart("mothers_name", nameMother)
                .addFormDataPart("marital_status", maritalStatus)
                .addFormDataPart("passport_number", passportNumber)
                .addFormDataPart("place_of_issue", placeIssue)
                .addFormDataPart("country_of_issue", selectedIssueCountry)
                .addFormDataPart("passport_issue_date", dateIssue)
                .addFormDataPart("passport_expiry_date", dateExpiry)
                .addFormDataPart("profession_id", professionId)
                .addFormDataPart("insertedTimeIST", "")
                .addFormDataPart("visa_status", "")
                .addFormDataPart("service_type", fullNameVisa)
                .addFormDataPart("visaRenewalDate", "")
                .addFormDataPart("sponserName", sponsorName)
                .addFormDataPart("arrivingFrom", selectedPort)
                .addFormDataPart("otherProfession", profession)
                .addFormDataPart("port_arrival", "")
                .addFormDataPart("age", String.valueOf(age))
                .addFormDataPart("person_type", "")
                .addFormDataPart("sponserType", selectedGcc)
                .addFormDataPart("sponserNationality", "")
                .addFormDataPart("sponserAdd", sponsorAddress)
                .addFormDataPart("sponserRelation", "")
                .addFormDataPart("sponserCompanyContact", sponsorContact)
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
                    String response2 = response.body().string();
//                    Log.v(TAG_REGISTER, resp);
                    checkDocuments(BASE_URL_UPLOAD_DOCS_UAE);


                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvVisaId.setText(responseVisa);
                                visaName.setText(fullNameVisa);
                                visaFee.setText(String.valueOf(govtFee) + "$");
                                finalServiceFee.setText(String.valueOf(serviceFee) + "$");
                                totalFee = (govtFee + serviceFee);
                                totalVisaFee.setText(String.valueOf(totalFee) + "$");
                                tvBirthDate.setText(birthDate);
                                tvPassportNumber.setText(passportNumber);
                                tvDepartureDate.setText(departureDate);
                                tvArrivalDate.setText(arrivalDate);
                                tvName.setText(fullName);
                                tvGender.setText(gender);
                            }
                        });
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



    private void singaporeApplicantData(String resp){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", resp)
                .addFormDataPart("first_name", nameFirst)
                .addFormDataPart("last_name", nameLast)
                .addFormDataPart("gender", gender)
                .addFormDataPart("date_of_birth", birthDate)
                .addFormDataPart("birth_place", birthPlace)
                .addFormDataPart("birth_country",selectedCountry)
                .addFormDataPart("religion", religionApplicant)
                .addFormDataPart("email", emailEdt)
                .addFormDataPart("fathers_name", nameFather)
                .addFormDataPart("mothers_name", nameMother)
                .addFormDataPart("marital_status", maritalStatus)
                .addFormDataPart("spouse_nationality","")
                .addFormDataPart("passport_number", passportNumber)
                .addFormDataPart("place_of_issue", placeIssue)
                .addFormDataPart("country_of_issue", selectedIssueCountry)
                .addFormDataPart("passport_issue_date", dateIssue)
                .addFormDataPart("passport_expiry_date", dateExpiry)
                .addFormDataPart("created_date", professionId)
                .addFormDataPart("profession_id", professionId)
                .addFormDataPart("hightest_qualification", highestQualification)
                .addFormDataPart("visa_upload", "")
                .addFormDataPart("visa_number", "")
                .addFormDataPart("visa_upload_time", "")
                .addFormDataPart("visa_denied_reason", "")
                .addFormDataPart("visa_status", "")
                .addFormDataPart("admin_upload", "")
                .addFormDataPart("insertedTimeIST", "")
                .addFormDataPart("agent_id", "")
                .addFormDataPart("processingTime", "")
                .addFormDataPart("hold_comment", "")
                .addFormDataPart("flag", "")
                .addFormDataPart("delBy", "")
                .addFormDataPart("delDate", "")
                .addFormDataPart("delRemark", "")
                .addFormDataPart("service_type", "")
                .addFormDataPart("duration_stay", "")
                .addFormDataPart("visit_purpose", "")
                .addFormDataPart("reside_other_country", resideOther)
                .addFormDataPart("other_country_name", otherCountryName)
                .addFormDataPart("other_country_address", otherCountryAddress)
                .addFormDataPart("start_year", startOtherCountry)
                .addFormDataPart("end_year", endOtherCountry)
                .addFormDataPart("refused_country", refusedCountry)
                .addFormDataPart("court_low_country", courtLaw)
                .addFormDataPart("prohibited_entry", prohibitedCountry)
                .build();
        Request request = new Request.Builder().url(SINGAPORE_APPLICANT_FORM).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    String response2 = response.body().string();
                    Log.v("response", response2);
                    checkDocuments(BASE_URL_UPLOAD_DOCS_SINGAPORE);
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvVisaId.setText(responseVisa);
                                visaName.setText(fullNameVisa);
                                visaFee.setText(String.valueOf(govtFee) + "$");
                                finalServiceFee.setText(String.valueOf(serviceFee) + "$");
                                totalFee = (govtFee + serviceFee);
                                totalVisaFee.setText(String.valueOf(totalFee) + "$");
                                tvBirthDate.setText(birthDate);
                                tvPassportNumber.setText(passportNumber);
                                tvDepartureDate.setText(departureDate);
                                tvArrivalDate.setText(arrivalDate);
                                tvName.setText(fullName);
                                tvGender.setText(gender);
                            }
                        });

                    } else {
                            //
                    }
                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }
    private void sendIranApplicantData(String resp){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", resp)
                .addFormDataPart("first_name", nameFirst)
                .addFormDataPart("last_name", nameLast)
                .addFormDataPart("gender", gender)
                .addFormDataPart("date_of_birth", birthDate)
                .addFormDataPart("birth_place", birthPlace)
                .addFormDataPart("birth_country",selectedCountry)
                .addFormDataPart("religion", religionApplicant)
                .addFormDataPart("email", emailEdt)
                .addFormDataPart("fathers_name", nameFather)
                .addFormDataPart("mothers_name", nameMother)
                .addFormDataPart("marital_status", maritalStatus)
                .addFormDataPart("spouse_nationality","")
                .addFormDataPart("passport_type","")
                .addFormDataPart("passport_number", passportNumber)
                .addFormDataPart("place_of_issue", placeIssue)
                .addFormDataPart("country_of_issue", selectedIssueCountry)
                .addFormDataPart("passport_issue_date", dateIssue)
                .addFormDataPart("passport_expiry_date", dateExpiry)
                .addFormDataPart("designation",sharedPreferences.getString("job_title",""))
                .addFormDataPart("company_name",sharedPreferences.getString("company_name",""))
                .addFormDataPart("field_activity",sharedPreferences.getString("field_activity",""))
                .addFormDataPart("company_tel",sharedPreferences.getString("telephone_company",""))
                .addFormDataPart("previous_nationality",sharedPreferences.getString("previous_nationality",""))
                .addFormDataPart("created_data", "")
                .addFormDataPart("profession_id", "")
                .addFormDataPart("hightest_qualification", highestQualification)
                .addFormDataPart("visa_upload", "")
                .addFormDataPart("visa_number", "")
                .addFormDataPart("visa_upload_time", "")
                .addFormDataPart("visa_denied_reason", "")
                .addFormDataPart("visa_status", "")
                .addFormDataPart("admin_upload", "")
                .addFormDataPart("insertedTimeIST", "")
                .addFormDataPart("agent_id", "")
                .addFormDataPart("processingTime", "")
                .addFormDataPart("hold_comment", "")
                .addFormDataPart("flag", "")
                .addFormDataPart("delBy", "")
                .addFormDataPart("delDate", "")
                .addFormDataPart("delRemark", "")
                .addFormDataPart("service_type", "")
                .addFormDataPart("duration_stay", "")
                .addFormDataPart("visit_purpose", sharedPreferences.getString("purpose_of_visit",""))
                .addFormDataPart("visit_before_iran", sharedPreferences.getString("visited_iran",""))
                .addFormDataPart("no_of_visit_iran", sharedPreferences.getString("no_of_visit",""))
                .addFormDataPart("last_visit", sharedPreferences.getString("last_visit_iran",""))
                .addFormDataPart("visa_no", sharedPreferences.getString("visa_no_iran",""))
                .addFormDataPart("visit_date", sharedPreferences.getString("visit_date_iran",""))
                .addFormDataPart("country_visited", sharedPreferences.getString("other_country_visited",""))
                .build();
        Request request = new Request.Builder().url(IRAN_APPLICANT_FORM).post(requestBody).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                try {
                    String response2 = response.body().string();
                    Log.v("response", response2);
                    checkDocuments(BASE_URL_UPLOAD_DOCS_IRAN);
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvVisaId.setText(responseVisa);
                                visaName.setText(fullNameVisa);
                                visaFee.setText(String.valueOf(govtFee) + "$");
                                finalServiceFee.setText(String.valueOf(serviceFee) + "$");
                                totalFee = (govtFee + serviceFee);
                                totalVisaFee.setText(String.valueOf(totalFee) + "$");
                                tvBirthDate.setText(birthDate);
                                tvPassportNumber.setText(passportNumber);
                                tvDepartureDate.setText(departureDate);
                                tvArrivalDate.setText(arrivalDate);
                                tvName.setText(fullName);
                                tvGender.setText(gender);
                            }
                        });
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





    private void uploadDocuments(String response2, String selectedFile1,  String fileType1, String fileName1, String uploadedFile,String s) {
        file = new File(selectedFile1);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(uploadedFile, fileName1,
                        RequestBody.create(MediaType.parse(fileType1), file))
                .addFormDataPart("visa_id", response2)
                .build();
        Request request = new Request.Builder().url(s).post(requestBody).build();
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
                    Log.v("Docs", resp);
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
    private void getSharedPreferencesData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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
        fullName = sharedPreferences.getString("full_name","");
        birthDate = sharedPreferences.getString("birth_date","");
        passportNumber = sharedPreferences.getString("passport_number","");
        gender = sharedPreferences.getString("gender","");
        arrivalDate = sharedPreferences.getString("arrival_date","");
        departureDate = sharedPreferences.getString("departure_date","");
        nameFirst = sharedPreferences.getString("first_name","");
        nameLast = sharedPreferences.getString("last_name","");
        birthPlace = sharedPreferences.getString("last_name","");
        emailEdt = sharedPreferences.getString("email","");
        nameFather = sharedPreferences.getString("father_name","");
        nameMother = sharedPreferences.getString("mother_name","");
        dateIssue = sharedPreferences.getString("date_issue","");
        dateExpiry = sharedPreferences.getString("date_expiry","");
        profession= sharedPreferences.getString("profession","");
        professionId = sharedPreferences.getString("profession_id","");
        selectedCountry= sharedPreferences.getString("selected_country","");
        selectedIssueCountry = sharedPreferences.getString("selected_issue_country","");
        currentCity = sharedPreferences.getString("living_city","");
        currentAddress = sharedPreferences.getString("current_address","");
        hotelAddress = sharedPreferences.getString("hotel_address","");
        contactperson = sharedPreferences.getString("emergency_name","");
        contactNumber = sharedPreferences.getString("emergency_number","");
        religionApplicant = sharedPreferences.getString("selected_religion","");
        selectedEmirate = sharedPreferences.getString("selected_emirate", "");
        placeIssue = sharedPreferences.getString("place_issue", "");
        mobileNumber = sharedPreferences.getString("mobile","");
        countryId = sharedPreferences.getString("country_id","");
        age = sharedPreferences.getInt("age",0);
        maritalStatus = sharedPreferences.getString("marital_status","");
        selectedFile1 = sharedPreferences.getString("file_path1","");
        selectedFile2 = sharedPreferences.getString("file_path2","");
        selectedFile3 = sharedPreferences.getString("file_path3","");
        selectedFile4 = sharedPreferences.getString("file_path4","");
        selectedFile5 = sharedPreferences.getString("file_path5","");
        selectedFile6 = sharedPreferences.getString("file_path6","");
        fileName1 = sharedPreferences.getString("file_name1","");
        fileName2 = sharedPreferences.getString("file_name2","");
        fileName3 = sharedPreferences.getString("file_name3","");
        fileName4 = sharedPreferences.getString("file_name4","");
        fileName5 = sharedPreferences.getString("file_name5","");
        fileName6 = sharedPreferences.getString("file_name6","");
        fileType1 = sharedPreferences.getString("file_type1","");
        fileType2 = sharedPreferences.getString("file_type2","");
        fileType3 = sharedPreferences.getString("file_type3","");
        fileType4 = sharedPreferences.getString("file_type4","");
        fileType5 = sharedPreferences.getString("file_type5","");
        fileType6 = sharedPreferences.getString("file_type6","");
        sponsorName = sharedPreferences.getString("sponsor_name", "");
        sponsorAddress  = sharedPreferences.getString("sponsor_address","");
        selectedGcc = sharedPreferences.getString("sponsor_type", "");
        selectedPort = sharedPreferences.getString("port","");
        sponsorContact = sharedPreferences.getString("sponsor_contact","");
        responseVisa = sharedPreferences.getString("response", "");
        selectedVisaId = sharedPreferences.getInt("visa_id", 0);
        refusedCountry = sharedPreferences.getString("refused_country","");
        courtLaw = sharedPreferences.getString("court_law","");
        prohibitedCountry = sharedPreferences.getString("prohibited_country","");
        differentPassport = sharedPreferences.getString("different_passport","");
        otherCountryName  = sharedPreferences.getString("other_country_name", "");
        otherCountryAddress = sharedPreferences.getString("other_country_address","");
        startOtherCountry = sharedPreferences.getString("start_year_other_country","");
        endOtherCountry = sharedPreferences.getString("end_year_other_country","");
        resideOther = sharedPreferences.getString("reside_other_country","");
        highestQualification = sharedPreferences.getString("highest_qualification","");
        purposeSingapore = sharedPreferences.getString("purpose_singapore","");
        durationSingapore = sharedPreferences.getString("duration_singapore","");

        if(sharedPreferences != null){
            //sendConsultData();
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        getSharedPreferencesData();

    }
    private void checkDocuments(String baseUrlUploadDocs) {
        if(selectedFile1.length() != 0){
            uploadDocuments(responseVisa,selectedFile1,fileType1,fileName1, "uploaded_file", baseUrlUploadDocs +"uploadphoto");
        }
        if(selectedFile2.length() != 0){
            uploadDocuments(responseVisa,selectedFile2,fileType2,fileName2, "uploaded_file1", baseUrlUploadDocs +"uploadpassport");
        }
        if(selectedFile3.length() != 0){
            uploadDocuments(responseVisa,selectedFile3,fileType3,fileName3, "uploaded_file2", baseUrlUploadDocs +"uploadadd");
        }
        if(selectedFile4.length() != 0){
            uploadDocuments(responseVisa,selectedFile4,fileType4,fileName4, "uploaded_file3", baseUrlUploadDocs +"uploadadd1");
        }
        if(selectedFile5.length() != 0){
            uploadDocuments(responseVisa,selectedFile5,fileType5,fileName5, "uploaded_file4", baseUrlUploadDocs +"uploadadd2");
        }
        if(selectedFile6.length() != 0){
            uploadDocuments(responseVisa,selectedFile6,fileType6,fileName6, "uploaded_file5", baseUrlUploadDocs +"uploadadd3");
        }
    }
}
