package international.rst.com.rstsimplified.Adapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Activities.FormActivity;
import international.rst.com.rstsimplified.Activities.VisaTypeSelection;
import international.rst.com.rstsimplified.Model.VisaType_;
import international.rst.com.rstsimplified.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class VisaTypeAdapter extends RecyclerView.Adapter<VisaTypeAdapter.VisaTypeHolder> {


    private Context context;
    private Button submitButton;
    private List<VisaType_> visaTypes = new ArrayList<>();
    private LinearLayout linearLayout1,linearLayout2;
    String serviceType, processingTime;
    String livinginID;
    String nationalityID, resp;
    int CurrencyID;
    int visaTypeID;
    float serviceFee, mngFee;
    float govtFee;
    String serviceFeeCS, deviceName, deviceOS;
    public SharedPreferences sharedPreferences;
    private static final String BASE_URL_CONSULT_FORM = "http://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=mobile_data_tab_2";
    private OkHttpClient client = new OkHttpClient();

    public VisaTypeAdapter(Context context, List<VisaType_> visaTypes){
        this.context = context;
        this.visaTypes = visaTypes;

    }
    public class VisaTypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView visaName,visaType,visaValidity,stayValidity,tvprocessingTime,visaFee, tvServiceFee,totalFee,visaDetails;

        public VisaTypeHolder(View itemView) {
            super(itemView);
            linearLayout1 = (LinearLayout)itemView.findViewById(R.id.linear_layout_visa_combo);
            linearLayout2  = (LinearLayout)itemView.findViewById(R.id.linear_layout_regular_visa);
            submitButton = (Button)itemView.findViewById(R.id.button_submit);
            submitButton.setOnClickListener(this);
            visaName = (TextView)itemView.findViewById(R.id.visa_name);
            visaType = (TextView)itemView.findViewById(R.id.visa_type);
            visaValidity = (TextView)itemView.findViewById(R.id.visa_validity);
            stayValidity = (TextView)itemView.findViewById(R.id.visa_stay_validity);
            visaFee = (TextView)itemView.findViewById(R.id.visa_fee);
            tvServiceFee = (TextView)itemView.findViewById(R.id.service_fee);
            totalFee = (TextView)itemView.findViewById(R.id.total_fee);
            visaDetails = (TextView)itemView.findViewById(R.id.visa_details);
            tvprocessingTime = (TextView)itemView.findViewById(R.id.processing_time);


        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            serviceType = visaTypes.get(position).getEntryType();
            livinginID = visaTypes.get(position).getLivingInId();
            nationalityID = visaTypes.get(position).getNationalityId();
            govtFee = visaTypes.get(position).getGovtFee();
            serviceFee = visaTypes.get(position).getServiceFee();
            processingTime = visaTypes.get(position).getProcessingTime();
            visaTypeID = visaTypes.get(position).getVisaTypeId();
            serviceFeeCS = visaTypes.get(position).getServiceFeeCs();
            mngFee = visaTypes.get(position).getMngFee();

            Intent intent = new Intent(context, FormActivity.class);
            intent.putExtra("service_type",serviceType);
            intent.putExtra("living_id",livinginID);
            intent.putExtra("nationality_id",nationalityID);
            intent.putExtra("govt_fee",govtFee);
            intent.putExtra("service_fee",serviceFee);
            intent.putExtra("processing_time",processingTime);
            intent.putExtra("visa_type_id",visaTypeID);
            intent.putExtra("service_fee_cs",serviceFeeCS);
            intent.putExtra("mng_fee", mngFee);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            deviceName =android.os.Build.MODEL;
            deviceOS = Build.VERSION.RELEASE;




        }
    }
    @Override
    public VisaTypeAdapter.VisaTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.visatypecard,parent,false);
        VisaTypeHolder vh = new VisaTypeHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VisaTypeAdapter.VisaTypeHolder holder, int position) {
        holder.visaName.setText(visaTypes.get(position).getName());
        holder.visaType.setText(visaTypes.get(position).getEntryType());
        holder.visaValidity.setText(visaTypes.get(position).getVisaValidity());
        holder.stayValidity.setText(visaTypes.get(position).getStayValidity());
        holder.tvprocessingTime.setText(visaTypes.get(position).getProcessingTime());
        float service = visaTypes.get(position).getServiceFee();
        linearLayout2.setVisibility(View.GONE);

        float visa = visaTypes.get(position).getGovtFee();
        //System.out.println(service + visa);
        holder.tvServiceFee.setText(String.valueOf(service) + "$");
        holder.visaFee.setText(String.valueOf(visa)+ "$");
        float totalfee  = (service + visa);
        int currencyID = visaTypes.get(position).getCurrencyId();
        switch (currencyID){
            case 1:
                Log.d("Currency ID:", String.valueOf(currencyID));
                break;
            case 2:
                break;
            case 3:
                break;
        }
        holder.totalFee.setText(String.valueOf(totalfee) + "$");
        holder.visaDetails.setText(visaTypes.get(position).getDetail());

    }

    @Override
    public int getItemCount() {
        return visaTypes.size();
    }
    private void sendConsultData() {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("visa_id", String.valueOf(visaTypeID))
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
                .addFormDataPart("nationality_id",nationalityID)
                .addFormDataPart("living_in_id", livinginID)
                .addFormDataPart("currency_id", "")
                .addFormDataPart("govt_fee", String.valueOf(govtFee))
                .addFormDataPart("service_fee", String.valueOf(serviceFee))
                .addFormDataPart("processing_time", "")
                .addFormDataPart("visa_type_id", String.valueOf(visaTypeID))
                .addFormDataPart("email_id", "Testing")
                .addFormDataPart("email_varified", "Testing")
                .addFormDataPart("comments_added", "Testing")
                .addFormDataPart("insertedTimeIst", "Testing")
                .addFormDataPart("agentid", "Testing")
                .addFormDataPart("service_fee_cs", String.valueOf(serviceFeeCS))
                .addFormDataPart("termConditions", "Testing")
                .addFormDataPart("mng_fee", String.valueOf(mngFee))
                .addFormDataPart("application_type", "Testing")
                .addFormDataPart("agentType", "Testing")
                .addFormDataPart("device_type", deviceName)
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
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        sharedPreferences.edit().clear().apply();
                        sharedPreferences.edit().putString("visa_id", resp).apply();

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


}
