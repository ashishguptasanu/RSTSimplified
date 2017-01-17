package international.rst.com.rstsimplified.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import international.rst.com.rstsimplified.R;

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
        public ImageView imgv;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgv = (ImageView)itemView.findViewById(R.id.img_background);
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
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.length;
    }
}
