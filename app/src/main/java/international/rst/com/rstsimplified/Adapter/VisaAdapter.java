package international.rst.com.rstsimplified.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import international.rst.com.rstsimplified.Activities.FormActivity;
import international.rst.com.rstsimplified.R;

/**
 * Created by ashish on 17/1/17.
 */

public class VisaAdapter extends RecyclerView.Adapter<VisaAdapter.MyViewHolder> {
    public String[] mDataSet;
    public int[] mimageSet, mImageSet2;
    private Context context;
    public VisaAdapter(Context context, String[] mDataSet, int[] mimageSet, int[] mImageSet2){
        this.mDataSet = mDataSet;
        this.context = context;
        this.mimageSet = mimageSet;
        this.mImageSet2 = mImageSet2;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgv, imgv2;
        TextView tv;
        Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn = (Button)itemView.findViewById(R.id.apply);
            imgv2 = (ImageView)itemView.findViewById(R.id.flag_image);
            imgv = (ImageView)itemView.findViewById(R.id.visa_image);
            tv = (TextView)itemView.findViewById(R.id.tv_visa);
            btn.setOnClickListener(this);
            //findViewById();

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.apply:
                    Intent intent = new Intent(context, FormActivity.class);
                    context.startActivity(intent);
                    break;
            }

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
        holder.imgv2.setImageResource(mImageSet2[position]);
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.length;
    }
}
