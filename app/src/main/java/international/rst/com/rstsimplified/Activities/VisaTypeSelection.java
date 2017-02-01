package international.rst.com.rstsimplified.Activities;

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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Model.VisaResponse;
import international.rst.com.rstsimplified.Model.VisaType;
import international.rst.com.rstsimplified.Model.VisaType_;
import international.rst.com.rstsimplified.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisaTypeSelection extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int livingID,nationalityID;
    TextView tv1, tv2,tv3;
    String url;
    private List<VisaType_> visaTypes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_type_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv1 = (TextView)findViewById(R.id.living_in_tv) ;
        tv2 = (TextView)findViewById(R.id.nationality_id_tv);
        tv3 = (TextView)findViewById(R.id.url_tv);
        //https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaTypes&nationality="+nationalityID+"&livingIn="+livingID
        Bundle bundle =  getIntent().getExtras();
        if ( bundle!= null && bundle.containsKey("livingid") && bundle.containsKey("nationid")){
            livingID = bundle.getInt("livingid");
            nationalityID = bundle.getInt("nationid");
        }
        System.out.println(livingID);
        System.out.println(nationalityID);
        url = "https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaTypes&nationality="+nationalityID+"&livingIn="+livingID;
        System.out.println("https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaTypes&nationality="+nationalityID+"&livingIn="+livingID);
        tv1.setText("Living In ID: " + livingID);
        tv2.setText("Nation ID: "+ nationalityID);
        tv3.setText(url);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        loadVisaType();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        getMenuInflater().inflate(R.menu.visa_type_selection, menu);
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
    private void loadVisaType() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        VisaResponse request = retrofit.create(VisaResponse.class);
        Call<VisaType> call = request.getVisaType(url);
        //
        call.enqueue(new Callback<VisaType>() {
            @Override
            public void onResponse(Call<VisaType> call, Response<VisaType> response) {


                VisaType jsonResponse = response.body();
                visaTypes = jsonResponse.getVisaType();
                System.out.println(visaTypes.size());
                for(int i = 0; i<visaTypes.size();i++){
                    Log.i("Name", String.valueOf(visaTypes.get(i).getName()));
                }

            }
            @Override
            public void onFailure(Call<VisaType> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
}