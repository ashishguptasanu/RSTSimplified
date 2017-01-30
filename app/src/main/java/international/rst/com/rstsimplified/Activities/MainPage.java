package international.rst.com.rstsimplified.Activities;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.telephony.TelephonyManager;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import international.rst.com.rstsimplified.Adapter.ViewPagerAdapter;
import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.Model.CountryResponse;
import international.rst.com.rstsimplified.R;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.provider.Settings.Secure;
import android.widget.Toast;


import org.apache.http.NameValuePair;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import static android.R.attr.name;


public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final String REGISTER_URL ="http://uaevisasonline.com/api/new-device.php" ;
    private static ViewPager imageviewPager;
    public String androidID,  deviceID;
    Bundle b;
    String data;
    private List<CountryRes> countries = new ArrayList<>();
    TelephonyManager mngr;
    final int REQUEST_READ_PHONE_STATE = 0;
    CardView cardView1, cardView2,cardView3,cardView4;
    Menu menu;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.card_background,R.drawable.oman,R.drawable.paris,R.drawable.usa};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageviewPager = (ViewPager)findViewById(R.id.viewpager1);
        init();
        loadJSON();
        cardView1 = (CardView)findViewById(R.id.card1);
        cardView2 = (CardView)findViewById(R.id.card2);
        cardView3 = (CardView)findViewById(R.id.card3);
        cardView4 = (CardView)findViewById(R.id.card4);
        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        androidID = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);
        mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_READ_PHONE_STATE);
        } else {
            deviceID = mngr.getDeviceId();


        }

        if(isOnline() == true){
            Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
        }






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    String deviceID = mngr.getDeviceId();

                }
                break;

            default:
                break;
        }
    }
    private boolean isOnline()
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private void init() {
        Collections.addAll(ImagesArray, IMAGES);

        imageviewPager = (ViewPager) findViewById(R.id.viewpager1);


        imageviewPager.setAdapter(new ViewPagerAdapter(MainPage.this,ImagesArray));


        CircleIndicator indicator = (CircleIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(imageviewPager);

        final float density = getResources().getDisplayMetrics().density;



        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                imageviewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator

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
        getMenuInflater().inflate(R.menu.home, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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

        if (id == R.id.visa_services) {
            Intent intent1=new Intent(this,ActivityServices.class);
            b=new Bundle();
            b.putInt("tab",0);
            intent1.putExtras(b);
            startActivity(intent1);
        } else if (id == R.id.airport_services) {
            Intent intent2=new Intent(this,ActivityServices.class);
            b=new Bundle();
            b.putInt("tab",1);
            intent2.putExtras(b);
            startActivity(intent2);
        } else if (id == R.id.hotel_services) {
            Intent intent3=new Intent(this,ActivityServices.class);
            b=new Bundle();
            b.putInt("tab",2);
            intent3.putExtras(b);
            startActivity(intent3);
        } else if (id == R.id.meet_greet) {
            Intent intent4=new Intent(this,ActivityServices.class);
            b=new Bundle();
            b.putInt("tab",3);
            intent4.putExtras(b);
            startActivity(intent4);

        } else if (id == R.id.sight_seeing) {

        } else if (id == R.id.car_parking) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card1:
                Intent intent1=new Intent(this,ActivityServices.class);
                b=new Bundle();
                b.putInt("tab",0);
                intent1.putExtras(b);
                startActivity(intent1);
                new PostData();
                break;
            case R.id.card2:
                Intent intent2=new Intent(this,ActivityServices.class);
                b=new Bundle();
                b.putInt("tab",1);
                intent2.putExtras(b);
                startActivity(intent2);
                break;
            case R.id.card3:
                Intent intent3=new Intent(this,ActivityServices.class);
                b=new Bundle();
                b.putInt("tab",2);
                intent3.putExtras(b);
                startActivity(intent3);
                break;
            case R.id.card4:
                Intent intent4=new Intent(this,ActivityServices.class);
                b=new Bundle();
                b.putInt("tab",3);
                intent4.putExtras(b);
                startActivity(intent4);
                break;
        }

    }

    private void sendData() {


    }
    class PostData extends AsyncTask<Void, Void, Void> {

        private Exception exception;


        @Override
        protected Void doInBackground(Void... voids) {
            String  msg = androidID;

            HttpClient Client = new DefaultHttpClient();

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("id", msg));
            nameValuePairs.add(new BasicNameValuePair("etc", deviceID));

            try {

                String SetServerString = "";

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=new_registeration");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();

                httpclient.execute(httppost, responseHandler);
                System.out.println("Success");


            }  catch(Exception ex) {
                System.out.println(ex);
            }
            return null;
        }
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CountryResponse request = retrofit.create(CountryResponse.class);
        Call<Country> call = request.getCountry();
        //
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {


                Country jsonResponse = response.body();
                countries = jsonResponse.getCountry();
                System.out.println(countries.size());
                for(int i = 0; i<countries.size();i++){
                    Log.i("Name", countries.get(i).getName());
                }

            }
            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }


}
