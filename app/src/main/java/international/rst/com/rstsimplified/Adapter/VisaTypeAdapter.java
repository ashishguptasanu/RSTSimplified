package international.rst.com.rstsimplified.Adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import international.rst.com.rstsimplified.Model.VisaType_;
import international.rst.com.rstsimplified.R;

/**
 * Created by Ashish on 02-02-2017.
 */

public class VisaTypeAdapter extends RecyclerView.Adapter<VisaTypeAdapter.VisaTypeHolder> {


    Context context;
    private List<VisaType_> visaTypes = new ArrayList<>();

    public VisaTypeAdapter(Context context, List<VisaType_> visaTypes){
        this.context = context;
        this.visaTypes = visaTypes;

    }
    public class VisaTypeHolder extends RecyclerView.ViewHolder{
        TextView visaName,visaType,visaValidity;

        public VisaTypeHolder(View itemView) {
            super(itemView);
            visaName = (TextView)itemView.findViewById(R.id.visa_name);
            visaType = (TextView)itemView.findViewById(R.id.visa_type);
            visaValidity = (TextView)itemView.findViewById(R.id.visa_validity);
            //findViewById();

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
        holder.visaValidity.setText(visaTypes.get(position).getStayValidity());

    }

    @Override
    public int getItemCount() {
        return visaTypes.size();
    }


}
