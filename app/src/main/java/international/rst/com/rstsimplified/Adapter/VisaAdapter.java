package international.rst.com.rstsimplified.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import international.rst.com.rstsimplified.R;

/**
 * Created by ashish on 17/1/17.
 */

public class VisaAdapter extends RecyclerView.Adapter<VisaAdapter.MyViewHolder> {
    public String[] mDataSet;
    public int[] mimageSet;
    private Context context;
    public VisaAdapter(Context context, String[] mDataSet, int[] mimageSet){
        this.mDataSet = mDataSet;
        this.context = context;
        this.mimageSet = mimageSet;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgv;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgv = (ImageView)itemView.findViewById(R.id.visa_image);
            tv = (TextView)itemView.findViewById(R.id.tv_visa);
            //findViewById();

        }
    }
    @Override
    public VisaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.visa_card,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VisaAdapter.MyViewHolder holder, int position) {
        holder.imgv.setImageResource(mimageSet[position]);
        holder.tv.setText(mDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.length;
    }
}
