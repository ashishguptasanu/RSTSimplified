package international.rst.com.rstsimplified.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Model.VisaType_;

/**
 * Created by Ashish on 30-03-2017.
 */

public class UsaVisaAdapter1 extends RecyclerView.Adapter<UsaVisaAdapter1.MyViewHolder> {
    private List<VisaType_> visaTypes1 = new ArrayList<>();
    Context context;

    public UsaVisaAdapter1(Context context, List<VisaType_> visaTypes1){
        this.context = context;
        this.visaTypes1 = visaTypes1;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
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
