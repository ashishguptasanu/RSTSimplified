package international.rst.com.rstsimplified.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Model.VisaType_;
import international.rst.com.rstsimplified.R;


public class UsaVisaAdapter1 extends RecyclerView.Adapter<UsaVisaAdapter1.MyViewHolder> {
    private List<VisaType_> visaTypes1 = new ArrayList<>();
    Context context;
    private TextView visaName, visaType, visaValidity, processingTime, visaFee, serviceFee, totalFee;
    private Button button_submit;

    public UsaVisaAdapter1(Context context, List<VisaType_> visaTypes1){
        this.context = context;
        this.visaTypes1 = visaTypes1;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            visaName = (TextView)itemView.findViewById(R.id.visa_name_usa);
            visaType = (TextView)itemView.findViewById(R.id.visa_type1_usa);
            visaValidity = (TextView)itemView.findViewById(R.id.visa_validity1_usa);
            processingTime = (TextView)itemView.findViewById(R.id.processing_time1_usa);
            visaFee = (TextView)itemView.findViewById(R.id.visa_fee1_usa);
            serviceFee = (TextView)itemView.findViewById(R.id.service_fee1_usa1);
            totalFee = (TextView)itemView.findViewById(R.id.total_fee1_usa);
            button_submit = (Button)itemView.findViewById(R.id.button_submit_usa);
        }
    }
    @Override
    public UsaVisaAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usa_visa_type_card,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UsaVisaAdapter1.MyViewHolder holder, int position) {
        visaName.setText(visaTypes1.get(position).getName());
        visaType.setText(visaTypes1.get(position).getName());
        visaValidity.setText(visaTypes1.get(position).getVisaValidity());
        processingTime.setText(visaTypes1.get(position).getProcessingTime());
        visaFee.setText(String.valueOf(visaTypes1.get(position).getGovtFee()));
        serviceFee.setText(String.valueOf(visaTypes1.get(position).getServiceFee()));

    }

    @Override
    public int getItemCount() {
        return visaTypes1.size();
    }
}
