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
    int currencyID;
    int visaTypeID;
    float serviceFee, mngFee;
    float govtFee;
    String serviceFeeCS, deviceName, deviceOS;
    public SharedPreferences sharedPreferences;
    String finalVisaName;


    public VisaTypeAdapter(Context context, List<VisaType_> visaTypes){
        this.context = context;
        this.visaTypes = visaTypes;

    }
    public class VisaTypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView visaName,visaType,visaValidity,stayValidity,tvprocessingTime,visaFee, tvServiceFee,totalFee,visaDetails;

        public VisaTypeHolder(View itemView) {
            super(itemView);
            //linearLayout1 = (LinearLayout)itemView.findViewById(R.id.linear_layout_visa_combo);
            //linearLayout2  = (LinearLayout)itemView.findViewById(R.id.linear_layout_regular_visa);
            //submitButton = (Button)itemView.findViewById(R.id.button_submit);
            //submitButton.setOnClickListener(this);
            //visaName = (TextView)itemView.findViewById(R.id.visa_name);
            visaType = (TextView)itemView.findViewById(R.id.visa_type);
            visaValidity = (TextView)itemView.findViewById(R.id.visa_validity);
            stayValidity = (TextView)itemView.findViewById(R.id.visa_stay_validity);
            visaFee = (TextView)itemView.findViewById(R.id.visa_fee);
            tvServiceFee = (TextView)itemView.findViewById(R.id.service_fee);
            totalFee = (TextView)itemView.findViewById(R.id.total_fee);
            //visaDetails = (TextView)itemView.findViewById(R.id.visa_details);
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
            currencyID = visaTypes.get(position).getCurrencyId();
            String visaName = visaTypes.get(position).getName();
            finalVisaName = (visaName + " " + serviceType);



            Intent intent = new Intent(context, FormActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            deviceName =android.os.Build.MODEL;
            deviceOS = Build.VERSION.RELEASE;
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            //sharedPreferences.edit().clear().apply();
            sharedPreferences.edit().putString("service_type", serviceType).apply();
            sharedPreferences.edit().putString("living_id", livinginID).apply();
            sharedPreferences.edit().putString("nationality_id", nationalityID).apply();
            sharedPreferences.edit().putFloat("govt_fee", govtFee).apply();
            sharedPreferences.edit().putFloat("service_fee", serviceFee).apply();
            sharedPreferences.edit().putString("processing_time", processingTime).apply();
            sharedPreferences.edit().putInt("visa_type_id", visaTypeID).apply();
            sharedPreferences.edit().putString("service_fee_cs", serviceFeeCS).apply();
            sharedPreferences.edit().putFloat("mng_fee", mngFee).apply();
            sharedPreferences.edit().putString("device_name", deviceName).apply();
            sharedPreferences.edit().putString("device_os", deviceOS).apply();
            sharedPreferences.edit().putString("visa_name", finalVisaName).apply();





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
        //holder.visaName.setText(visaTypes.get(position).getName());
        holder.visaType.setText(visaTypes.get(position).getEntryType());
        holder.visaValidity.setText(visaTypes.get(position).getVisaValidity());
        holder.stayValidity.setText(visaTypes.get(position).getStayValidity());
        holder.tvprocessingTime.setText(visaTypes.get(position).getProcessingTime());
        float service = visaTypes.get(position).getServiceFee();
        //linearLayout2.setVisibility(View.GONE);

        float visa = visaTypes.get(position).getGovtFee();
        //System.out.println(service + visa);
        holder.tvServiceFee.setText(String.valueOf(service) + "$");
        holder.visaFee.setText(String.valueOf(visa)+ "$");
        float totalfee  = (service + visa);
        int currencyID = visaTypes.get(position).getCurrencyId();
        switch (currencyID){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        holder.totalFee.setText(String.valueOf(totalfee) + "$");
        //holder.visaDetails.setText(visaTypes.get(position).getDetail());

    }

    @Override
    public int getItemCount() {
        return visaTypes.size();
    }



}
