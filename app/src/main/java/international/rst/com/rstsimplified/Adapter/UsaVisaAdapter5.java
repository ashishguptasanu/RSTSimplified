package international.rst.com.rstsimplified.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Model.VisaType_;
import international.rst.com.rstsimplified.R;

/**
 * Created by Ashish on 30-03-2017.
 */

public class UsaVisaAdapter5 extends RecyclerView.Adapter<UsaVisaAdapter1.MyViewHolder> {
    private List<VisaType_> visaTypes1 = new ArrayList<>();
    Context context;

    public UsaVisaAdapter5(Context context, List<VisaType_> visaTypes1){
        this.context = context;
        this.visaTypes1 = visaTypes1;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView visaName, visaType, visaValidity, processingTime, visaFee, serviceFee, totalFee;
        public MyViewHolder(View itemView) {
            super(itemView);
            visaName = (TextView)itemView.findViewById(R.id.visa_name_usa);
            visaType = (TextView)itemView.findViewById(R.id.visa_type1_usa);
            visaValidity = (TextView)itemView.findViewById(R.id.visa_validity1_usa);
            processingTime = (TextView)itemView.findViewById(R.id.processing_time1_usa);
            visaFee = (TextView)itemView.findViewById(R.id.visa_fee1_usa);
            serviceFee = (TextView)itemView.findViewById(R.id.service_fee1_usa1);
            totalFee = (TextView)itemView.findViewById(R.id.total_fee1_usa);
        }
    }
    @Override
    public UsaVisaAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UsaVisaAdapter1.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
