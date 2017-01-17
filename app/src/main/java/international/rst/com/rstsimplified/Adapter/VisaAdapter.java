package international.rst.com.rstsimplified.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ashish on 17/1/17.
 */

public class VisaAdapter extends RecyclerView.Adapter<VisaAdapter.MyViewHolder> {
    public String[] mDataSet;
    private Context context;
    public VisaAdapter(Context context, String[] mDataSet){
        this.mDataSet = mDataSet;
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            //findViewById()

        }
    }
    @Override
    public VisaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(cardView,parent,false);
        RecyclerView.ViewHolder vh = new RecyclerView.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VisaAdapter.MyViewHolder holder, int position) {
        //mTV.setText("HBHJBHJ");
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.length;
    }
}
