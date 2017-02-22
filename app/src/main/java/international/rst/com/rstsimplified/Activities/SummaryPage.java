package international.rst.com.rstsimplified.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
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
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import international.rst.com.rstsimplified.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SummaryPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Bundle bundle;
    Button button;
    SharedPreferences sharedPreferences;
    String serviceType, livingId, nationalityId, processingTime, deviceType, deviceOS, serviceFeeCs, nameFirst, nameLast, birthDate, birthPlace, emailEdt, nameFather, nameMother, dateIssue, dateExpiry,passportNumber, fullNameVisa, arrivalDate, departureDate,gender, fullName, profession, professionId, selectedCountry,selectedIssueCountry, religionApplicant, maritalStatus;
    int visaTypeId, age;
    String currentCity, hotelAddress, contactperson, contactNumber, currentAddress, selectedEmirate,placeIssue, mobileNumber, countryId, selectedFile1, selectedFile2, selectedFile3, selectedFile4, selectedFile5, selectedFile6, selectedPort;
    TextView tvVisaId, visaName, visaFee, finalServiceFee, totalVisaFee, tvName, tvBirthDate, tvPassportNumber, tvGender, tvArrivalDate,tvDepartureDate ;
    Float govtFee, serviceFee, mngFee, totalFee;
    String fileName1, fileName2, fileName3, fileName4, fileName5, fileName6, fileType1, fileType2, fileType3, fileType4, fileType5, fileType6, sponsorName, sponsorAddress, selectedGcc;
    private static final String BASE_URL_CONSULT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data_tab_2";
    private OkHttpClient client = new OkHttpClient();
    private static final String BASE_URL_APLLICANT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data";
    private static final String BASE_URL_UPLOAD_DOCS = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=upload";
    String resp;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    Context context;

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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void sendConsultData() {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", String.valueOf(visaTypeId))
                .addFormDataPart("start_date", arrivalDate)
                .addFormDataPart("end_date", departureDate)
                .addFormDataPart("pnrNo", "")
                .addFormDataPart("address1", currentAddress)
                .addFormDataPart("address2", "")
                .addFormDataPart("city", currentCity)
                .addFormDataPart("country", selectedCountry)
                .addFormDataPart("country_code", countryId)
                .addFormDataPart("mobile", mobileNumber)
                .addFormDataPart("emirates_uae", selectedEmirate)
                .addFormDataPart("emergency_contact_name", contactperson)
                .addFormDataPart("emergency_contact_number", contactNumber)
                .addFormDataPart("hotel_address", hotelAddress)
                .addFormDataPart("contact_uae", contactNumber)
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
                .addFormDataPart("email_id", emailEdt)
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
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvVisaId.setText(resp);
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

                        sendApplicandData(resp);
                        sendVerificationEmail(resp, emailEdt);


                        //sharedPreferences.edit().putString("Android ID",androidID).apply();
                    }else {

                    }
                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
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
                .addFormDataPart("sponserCompanyContact", "")
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
                    System.out.println(response2);
                    uploadDocuments(response2);
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

    private void sendVerificationEmail(String resp, String emailEdt) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email","em")
                .build();
        String emailUrl = ("http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=email_send&visa_id="+resp+"+&email_id="+emailEdt);
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
//                    Log.v(TAG_REGISTER, resp);
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


    private void uploadDocuments(String response2) {
        File file = new File(selectedFile1);
        File file1 = new File(selectedFile2);
        File file2 = new File(selectedFile3);
        File file3 = new File(selectedFile4);
        File file4 = new File(selectedFile5);
        File file5 = new File(selectedFile6);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("uploaded_file",fileName1,
                        RequestBody.create(MediaType.parse(fileType1), file))
                .addFormDataPart("uploaded_file1",fileName2,
                        RequestBody.create(MediaType.parse(fileType2), file1))
                .addFormDataPart("uploaded_file2",fileName3,
                        RequestBody.create(MediaType.parse(fileType3), file2))
                .addFormDataPart("uploaded_file3",fileName4,
                        RequestBody.create(MediaType.parse(fileType4), file3))
                .addFormDataPart("uploaded_file4",fileName5,
                        RequestBody.create(MediaType.parse(fileType5), file4))
                .addFormDataPart("uploaded_file5",fileName6,
                        RequestBody.create(MediaType.parse(fileType6), file5))
                .addFormDataPart("id", response2)
                .build();
        Request request = new Request.Builder().url(BASE_URL_UPLOAD_DOCS).post(requestBody).build();
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
        if(sharedPreferences != null){
            sendConsultData();
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        getSharedPreferencesData();

    }
}
