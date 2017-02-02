package international.rst.com.rstsimplified.Adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Activities.FormActivity;
import international.rst.com.rstsimplified.Model.VisaType_;
import international.rst.com.rstsimplified.R;

public class VisaTypeAdapter extends RecyclerView.Adapter<VisaTypeAdapter.VisaTypeHolder> {


    Context context;
    float service, visa;
    Button submitButton;
    private List<VisaType_> visaTypes = new ArrayList<>();

    public VisaTypeAdapter(Context context, List<VisaType_> visaTypes){
        this.context = context;
        this.visaTypes = visaTypes;

    }
    public class VisaTypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView visaName,visaType,visaValidity,stayValidity,processingTime,visaFee,serviceFee,totalFee,visaDetails;

        public VisaTypeHolder(View itemView) {
            super(itemView);
            submitButton = (Button)itemView.findViewById(R.id.button_submit);
            submitButton.setOnClickListener(this);
            visaName = (TextView)itemView.findViewById(R.id.visa_name);
            visaType = (TextView)itemView.findViewById(R.id.visa_type);
            visaValidity = (TextView)itemView.findViewById(R.id.visa_validity);
            stayValidity = (TextView)itemView.findViewById(R.id.visa_stay_validity);
            visaFee = (TextView)itemView.findViewById(R.id.visa_fee);
            serviceFee = (TextView)itemView.findViewById(R.id.service_fee);
            totalFee = (TextView)itemView.findViewById(R.id.total_fee);
            visaDetails = (TextView)itemView.findViewById(R.id.visa_details);
            processingTime = (TextView)itemView.findViewById(R.id.processing_time);
            //findViewById();

        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, FormActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


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
        holder.processingTime.setText(visaTypes.get(position).getProcessingTime());
        service = visaTypes.get(position).getServiceFee();

        visa = visaTypes.get(position).getGovtFee();
        //System.out.println(service + visa);
        holder.serviceFee.setText(String.valueOf(service) + "$");
        holder.visaFee.setText(String.valueOf(visa)+ "$");
        float totalfee  = (service + visa);
        holder.totalFee.setText(String.valueOf(totalfee) + "$");
        holder.visaDetails.setText(visaTypes.get(position).getDetail());

    }

    @Override
    public int getItemCount() {
        return visaTypes.size();
    }


}
