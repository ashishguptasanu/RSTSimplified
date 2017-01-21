package international.rst.com.rstsimplified.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
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
import java.util.Timer;
import java.util.TimerTask;

import international.rst.com.rstsimplified.Adapter.ViewPagerAdapter;
import international.rst.com.rstsimplified.R;
import me.relex.circleindicator.CircleIndicator;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static ViewPager imageviewPager;
    Bundle b;
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

    private void init() {
            for (Integer IMAGE : IMAGES) ImagesArray.add(IMAGE);

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
                Intent intent1=new Intent(this,ServicesActivity.class);
                b=new Bundle();
                b.putInt("tab",0);
                intent1.putExtras(b);
                startActivity(intent1);
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
                break;
        }

    }
}
