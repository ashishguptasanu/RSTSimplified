package international.rst.com.rstsimplified.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import international.rst.com.rstsimplified.Adapter.ViewPagerAdapter;
import international.rst.com.rstsimplified.R;
import me.relex.circleindicator.CircleIndicator;
import android.provider.Settings.Secure;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static ViewPager imageviewPager;
    public String androidID,  deviceID;
    Bundle b;
    String data;
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
        Toast.makeText(getApplicationContext(),androidID,Toast.LENGTH_SHORT).show();
        mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_READ_PHONE_STATE);
        } else {
            deviceID = mngr.getDeviceId();


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

        if (id == R.id.visa_services) {
            Intent intent1=new Intent(this,ServicesActivity.class);
            b=new Bundle();
            b.putInt("tab",0);
            intent1.putExtras(b);
            startActivity(intent1);
        } else if (id == R.id.airport_services) {
            Intent intent2=new Intent(this,ServicesActivity.class);
            b=new Bundle();
            b.putInt("tab",1);
            intent2.putExtras(b);
            startActivity(intent2);
        } else if (id == R.id.hotel_services) {
            Intent intent3=new Intent(this,ServicesActivity.class);
            b=new Bundle();
            b.putInt("tab",2);
            intent3.putExtras(b);
            startActivity(intent3);
        } else if (id == R.id.meet_greet) {
            Intent intent4=new Intent(this,ServicesActivity.class);
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
                /*Intent intent1=new Intent(this,ServicesActivity.class);
                b=new Bundle();
                b.putInt("tab",0);
                intent1.putExtras(b);
                startActivity(intent1);*/
                new JSONAsyncTask(MainPage.class).execute();
                break;
            case R.id.card2:
                Intent intent2=new Intent(this,ServicesActivity.class);
                b=new Bundle();
                b.putInt("tab",1);
                intent2.putExtras(b);
                startActivity(intent2);
                break;
            case R.id.card3:
                Intent intent3=new Intent(this,ServicesActivity.class);
                b=new Bundle();
                b.putInt("tab",2);
                intent3.putExtras(b);
                startActivity(intent3);
                break;
            case R.id.card4:
                Intent intent4=new Intent(this,ServicesActivity.class);
                b=new Bundle();
                b.putInt("tab",3);
                intent4.putExtras(b);
                startActivity(intent4);
                break;
        }

    }


    final HttpURLConnection[] conn = {null};
    final BufferedReader reader = null;
    class JSONAsyncTask extends AsyncTask<Void, Void, Boolean> {


        public JSONAsyncTask(Class<MainPage> mainPageClass) {
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Toast.makeText(getApplicationContext(), "Server Response: OK", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... strings) {

            try {
                URL url = new URL("http://uaevisasonline.com/api/new-device.php");
                conn[0] = (HttpURLConnection) url.openConnection();
                conn[0].setDoOutput(true);
                Log.e("", "" + conn[0].getResponseMessage());
                conn[0].connect();
                JSONObject obj = new JSONObject();
                data = ("Device ID" + deviceID + "/" + "IMEI No." + androidID);
                obj.put("Marge", "Simpson");

                HttpClient client = new DefaultHttpClient();

                StringBuilder pat = new StringBuilder();
                HttpGet post = new HttpGet(url.toURI());
                post.setHeader("json", obj.toString());
                post.setHeader("Content-Type", "application/json");
                post.setHeader("accept-encoding", "gzip, deflate");
                post.setHeader("accept-language", "en-US,en;q=0.8");
                post.setHeader("FormData", obj.toString());
                HttpResponse lazy = client.execute(post);
                HttpEntity ent = lazy.getEntity();
                String lb = EntityUtils.toString(ent);
                pat.append(data);
                Log.i("Read from server", pat.toString());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (conn[0] != null)
                    conn[0].disconnect();

                try {
                    if (reader != null)
                        reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;

        }
    }









}
