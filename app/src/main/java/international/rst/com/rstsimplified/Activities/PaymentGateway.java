package international.rst.com.rstsimplified.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.EditText;

import com.checkout.CheckoutKit;
import com.checkout.exceptions.CardException;
import com.checkout.exceptions.CheckoutException;
import com.checkout.httpconnector.Response;
import com.checkout.models.Card;
import com.checkout.models.CardTokenResponse;
import com.checkout.models.CustDetails;

import java.io.IOException;

import international.rst.com.rstsimplified.R;

import static android.R.attr.publicKey;

public class PaymentGateway extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private  static String publicKey = "pk_test_73e56b01-8726-4176-9159-db71454ff4af";
    int ammount = 100;
    Bundle b;
    SharedPreferences sharedPreferences;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mButton = (Button)findViewById(R.id.button_payment);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UsaFormActivity.class);
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
        getMenuInflater().inflate(R.menu.payment_gateway, menu);
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
    class ConnectionTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            /* All the fields that need to be filled with the card information */
            final EditText name = (EditText) findViewById(R.id.card_name);
            final EditText number = (EditText) findViewById(R.id.card_number);
            final EditText expMonth = (EditText) findViewById(R.id.card_month);
            final EditText expYear = (EditText) findViewById(R.id.card_year);
            final EditText cvv = (EditText) findViewById(R.id.card_cvv);

            try {
                /* Create the card object */
                Card card = new Card(number.getText().toString(), name.getText().toString(), expMonth.getText().toString(), expYear.getText().toString(), cvv.getText().toString());
                /* Create the CheckoutKit instance */
                CheckoutKit ck = CheckoutKit.getInstance(publicKey);
                final Response<CardTokenResponse> resp = ck.createCardToken(card);
                if (resp.hasError) {
                    /* Handle errors */
                } else {
                    /* The card token */
                    String cardToken = resp.model.getCardToken();
                }
            } catch (final CardException | CheckoutException e1) {
                /* Handle validation errors */
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
