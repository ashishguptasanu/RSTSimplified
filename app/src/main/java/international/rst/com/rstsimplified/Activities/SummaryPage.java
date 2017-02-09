package international.rst.com.rstsimplified.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import java.io.IOException;

import international.rst.com.rstsimplified.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SummaryPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Bundle bundle;
    Button button;
    SharedPreferences sharedPreferences;
    String serviceType, livingId, nationalityId, processingTime, deviceType, deviceOS, serviceFeeCs, nameLast, birthDate, birthPlace, emailEdt, nameFather, nameMother, dateIssue, dateExpiry,passportNumber, fullNameVisa, arrivalDate, departureDate,gender, fullName;
    int visaTypeId;
    TextView tvVisaId, visaName, visaFee, finalServiceFee, totalVisaFee, tvName, tvBirthDate, tvPassportNumber, tvGender, tvArrivalDate,tvDepartureDate ;
    Float govtFee, serviceFee, mngFee, totalFee;
    private static final String BASE_URL_CONSULT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data_tab_2";
    private OkHttpClient client = new OkHttpClient();
    private static final String BASE_URL_APLLICANT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data";
    String resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    private void getSharedPreferencesData() {
        bundle =  getIntent().getExtras();
        if ( bundle!= null && bundle.containsKey("key")){
            arrivalDate = bundle.getString("arrival_date");
            departureDate = bundle.getString("departure_date");
            fullName = bundle.getString("full_name");
            birthDate =bundle.getString("birth_date");
            passportNumber = bundle.getString("passport_number");
            gender = bundle.getString("gender");
        }
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
        if(sharedPreferences != null){
            sendConsultData();
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
                .addFormDataPart("order_id", "")
                .addFormDataPart("service_type", serviceType)
                .addFormDataPart("nationality_id",nationalityId)
                .addFormDataPart("living_in_id", livingId)
                .addFormDataPart("currency_id", "")
                .addFormDataPart("govt_fee", String.valueOf(govtFee))
                .addFormDataPart("service_fee", String.valueOf(serviceFee))
                .addFormDataPart("processing_time", "")
                .addFormDataPart("visa_type_id", String.valueOf(visaTypeId))
                .addFormDataPart("email_id", "Testing")
                .addFormDataPart("email_varified", "Testing")
                .addFormDataPart("comments_added", "Testing")
                .addFormDataPart("insertedTimeIst", "Testing")
                .addFormDataPart("agentid", "Testing")
                .addFormDataPart("service_fee_cs", serviceFeeCs)
                .addFormDataPart("termConditions", "Testing")
                .addFormDataPart("mng_fee", String.valueOf(mngFee))
                .addFormDataPart("application_type", "Testing")
                .addFormDataPart("agentType", "Testing")
                .addFormDataPart("device_type", deviceType)
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

                        //sendApplicandData(resp);


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
                .addFormDataPart("first_name", fullName)
                .addFormDataPart("last_name", nameLast)
                .addFormDataPart("gender", "")
                .addFormDataPart("date_of_birth", birthDate)
                .addFormDataPart("birth_place", birthPlace)
                .addFormDataPart("birth_country","")
                .addFormDataPart("religion", "")
                .addFormDataPart("email", emailEdt)
                .addFormDataPart("fathers_name", nameFather)
                .addFormDataPart("mothers_name", nameMother)
                .addFormDataPart("marital_status", "")
                .addFormDataPart("passport_number", passportNumber)
                .addFormDataPart("place_of_issue", "")
                .addFormDataPart("country_of_issue", "")
                .addFormDataPart("passport_issue_date", "")
                .addFormDataPart("passport_expiry_date", "")
                .addFormDataPart("profession_id", "")
                .addFormDataPart("insertedTimeIST", "")
                .addFormDataPart("visa_status", "")
                .addFormDataPart("service_type", "")
                .addFormDataPart("visaRenewalDate", "")
                .addFormDataPart("sponserName", "")
                .addFormDataPart("arrivingFrom", "")
                .addFormDataPart("otherProfession", "")
                .addFormDataPart("port_arrival", "")
                .addFormDataPart("age", "")
                .addFormDataPart("person_type", "")
                .addFormDataPart("sponserType", "")
                .addFormDataPart("sponserNationality", "")
                .addFormDataPart("sponserAdd", "")
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

    @Override
    protected void onStart() {
        super.onStart();
        getSharedPreferencesData();
    }
}
