package international.rst.com.rstsimplified.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import international.rst.com.rstsimplified.Custom.CustomPagerTabStrip;
import international.rst.com.rstsimplified.Custom.CustomViewPager;
import international.rst.com.rstsimplified.Fragments.FragmentUSAForm;
import international.rst.com.rstsimplified.R;

public class UsaFormActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Bundle b;
    CustomViewPager usaViewPager;
    CustomPagerTabStrip customPagerTabStrip;
    PagerAdapter mFormPagerAdapter;
    String[] usaTabDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usa_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        usaViewPager = (CustomViewPager)findViewById(R.id.usa_form_view_pager);
        customPagerTabStrip = (CustomPagerTabStrip)findViewById(R.id.usa_form_tab_strip);
        mFormPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        usaTabDataSet = new String[]{"Contact Details","Applicant Information", "Personal Information","Address & Phone Information","Travel Information","Travel Companion","Previous US Travel Information","US Point of Contact Information","Family Information","Present Work/Education/Training Information","Additional Work/Educational/Training Information","Security & Background","Interview Schedule","Document Upload"};
        usaViewPager.setAdapter(mFormPagerAdapter);
        setSupportActionBar(toolbar);
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
        getMenuInflater().inflate(R.menu.usa_form, menu);
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
        int id = item.getItemId();
        if (id == R.id.home) {
            Intent intent = new Intent(this,MainPage.class);
            startActivity(intent);
        }
        else if (id == R.id.visa_services) {
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
        }else if (id == R.id.sight_seeing) {}
        else if (id == R.id.car_parking) {}
        else if (id == R.id.launge_booking) {}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static class PlaceholderFragment extends android.support.v4.app.Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newFormInstance(int sectionNumber, String name ) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString("name", name);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.usa_form3, container, false);
            return rootView;
        }
    }
    public class PagerAdapter extends FragmentPagerAdapter {
        int[] selectors = {R.drawable.selector_tab_one, R.drawable.selector_tab_two, R.drawable.selector_tab_three,R.drawable.selector_tab_four};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FragmentUSAForm.newFormInstance("form1");
                case 1:
                    return FragmentUSAForm.newFormInstance("form2");
                case 2:
                    return FragmentUSAForm.newFormInstance("form3");
                case 3:
                    return FragmentUSAForm.newFormInstance("form4");
                case 4:
                    return FragmentUSAForm.newFormInstance("form5");
                case 5:
                    return FragmentUSAForm.newFormInstance("form6");
                case 6:
                    return FragmentUSAForm.newFormInstance("form7");
                case 7:
                    return FragmentUSAForm.newFormInstance("form8");
                case 8:
                    return FragmentUSAForm.newFormInstance("form9");
                case 9:
                    return FragmentUSAForm.newFormInstance("form10");
                case 10:
                    return FragmentUSAForm.newFormInstance("form11");
                case 11:
                    return FragmentUSAForm.newFormInstance("form12");
                case 12:
                    return FragmentUSAForm.newFormInstance("form13");
                case 13:
                    return FragmentUSAForm.newFormInstance("form14");
                default:
                    return  PlaceholderFragment.newFormInstance(1,"");
            }
        }



        @Override
        public CharSequence getPageTitle(int position) {
            return usaTabDataSet[position];
        }

        @Override
        public int getCount() {
            return 14;
        }
    }
}
