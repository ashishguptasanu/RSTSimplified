package international.rst.com.rstsimplified.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.Model.CountryResponse;
import international.rst.com.rstsimplified.Model.NationalityResponse;
import international.rst.com.rstsimplified.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisaServicesOptions extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , AdapterView.OnItemSelectedListener{
    private List<CountryRes> nationality = new ArrayList<>();
    private List<CountryRes> livingin = new ArrayList<>();
    private List<String> livingInData = new ArrayList<>();
    private List<String> nationalityData = new ArrayList<>();
    public int selectedLivingIn=0, selectedNationality=0, selectedLivingID,selectedNationalityID;

    Spinner spnrLivingIn, spnrNationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_services_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spnrLivingIn = (Spinner)findViewById(R.id.spinner_living_in);
        spnrLivingIn.setOnItemSelectedListener(this);
        spnrNationality  = (Spinner)findViewById(R.id.spinner_nationality);
        spnrNationality.setOnItemSelectedListener(this);
        loadLivingIn();
        loadNationality();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_visa_services);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedLivingIn != 0 && selectedNationality != 0){
                    Intent intent = new Intent(getApplicationContext(),VisaTypeSelection.class);
                    intent.putExtra("livingid", selectedLivingID);
                    intent.putExtra("nationid",selectedNationalityID);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Select country to countinue..", Toast.LENGTH_SHORT).show();

                }


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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){
            case R.id.spinner_living_in:
                selectedLivingIn = spnrLivingIn.getSelectedItemPosition();
                selectedLivingID = livingin.get(selectedLivingIn).getId();

                break;
            case R.id.spinner_nationality:
                selectedNationality = spnrNationality.getSelectedItemPosition();
                selectedNationalityID =nationality.get(selectedNationality).getId();
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
        getMenuInflater().inflate(R.menu.visa_services_options, menu);
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

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.visa_services) {

        } else if (id == R.id.airport_services) {

        } else if (id == R.id.hotel_services) {

        } else if (id == R.id.car_parking) {

        } else if (id == R.id.sight_seeing) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void loadNationality(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NationalityResponse request = retrofit.create(NationalityResponse.class);
        Call<Country> call = request.getCountry();
        //
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {


                Country jsonResponse = response.body();
                nationality = jsonResponse.getCountry();
                for(int i=0;i<nationality.size();i++){
                    nationalityData.add(nationality.get(i).getName());
                }
                populateNationalitySpinner();

            }
            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }



    private void loadLivingIn(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CountryResponse request = retrofit.create(CountryResponse.class);
        Call<Country> call = request.getCountry();
        //
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {


                Country jsonResponse = response.body();
                livingin = jsonResponse.getCountry();

                for(int i=0;i<livingin.size();i++){
                    livingInData.add(livingin.get(i).getName());
                }
                populateLivingInSpinner();


            }
            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }

    private void populateLivingInSpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, livingInData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrLivingIn.setAdapter(dataAdapter);
    }
    private void populateNationalitySpinner() {
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nationalityData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrNationality.setAdapter(dataAdapter2);
    }

}
