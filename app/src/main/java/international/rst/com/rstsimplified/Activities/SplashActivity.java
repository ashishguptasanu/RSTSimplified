package international.rst.com.rstsimplified.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import international.rst.com.rstsimplified.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SplashActivity extends AppCompatActivity {


    private static final long SPLASH_DISPLAY_LENGTH = 3000;
    public String androidID,  deviceID;
    private static final String BASE_URL = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=new_registration";
    //private static final String BASE_URL = "https://www.google.com/url?q=https%3A%2F%2Fuaevisasonline.com%3A2083%2Fcpsess9380849380%2F3rdparty%2FphpMyAdmin%2Fsql.php%3Fserver%3D1%26db%3Dchandrac_db%26table%3Duv_visa_applicants%26pos%3D0%26token%3Dcaaf8db0a55e4aa7a631ad3a84358922%23PMAURL-0%3Asql.php%3Fdb%3Dchandrac_db%26table%3Duv_visa_applicants%26server%3D1%26target%3D%26token%3Dcaaf8db0a55e4aa7a631ad3a84358922&sa=D&sntz=1&usg=AFQjCNGeGoS92KxwhJRs0kyfgebC8xUPYw";
    private OkHttpClient client = new OkHttpClient();
    SharedPreferences sharedPreferences;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View yourView = findViewById(R.id.your_view);
        sharedPreferences = SplashActivity.this.getPreferences(Context.MODE_PRIVATE);
        //sendData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainPage.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGTH);






    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = SplashActivity.this.getPreferences(Context.MODE_PRIVATE);


    }

    private void sendData() {
        getDeviceID();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("Android ID", androidID)
                .addFormDataPart("Device ID", deviceID)
                .build();
        Request request = new Request.Builder().url(BASE_URL).post(requestBody).build();
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
    private void getDeviceID() {
        androidID = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

    }




}
