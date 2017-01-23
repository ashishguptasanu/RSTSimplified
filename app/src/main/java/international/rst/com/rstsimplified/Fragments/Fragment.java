package international.rst.com.rstsimplified.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import international.rst.com.rstsimplified.Activities.FormActivity;
import international.rst.com.rstsimplified.Adapter.VisaAdapter;
import international.rst.com.rstsimplified.R;

/**
 * Created by ashish on 13/1/17.
 */

public class Fragment extends android.support.v4.app.Fragment {
    TextView tv1;
    String title;
    View view;
    LinearLayoutManager linearLayoutManager1, linearLayoutManager2, linearLayoutManager3;
    RecyclerView recyclerView;
    VisaAdapter visaAdapter;
    String[] mDataset1;
    int[] mImageSet, mImageSet2;

    public Fragment() {
    }
    public static Fragment newInstance(String title){
        Fragment fragment1 = new Fragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment1.setArguments(args);
        return fragment1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        tv1 = (TextView)view.findViewById(R.id.text1);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        ImageView img = (ImageView)view.findViewById(R.id.img_background);
        title = getArguments().getString("title");
        if(title.equalsIgnoreCase("visa")){
            tv1.setText("Title: visa Services");
            img.setImageResource(R.mipmap.visa_background);
            mDataset1 = new String[]{"Singapore visa", "Oman visa"};
            mImageSet = new int[]{R.drawable.card_background, R.drawable.oman };
            mImageSet2 = new int[] {R.drawable.singapore_flag, R.drawable.oman_flag};
            visaAdapter = new VisaAdapter(getContext(),mDataset1, mImageSet,mImageSet2);
            recyclerView.setAdapter(visaAdapter);
            linearLayoutManager1 = new LinearLayoutManager(getActivity());
            recyclerView.setHasFixedSize(true);
            tv1.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            recyclerView.setLayoutManager(linearLayoutManager1);


        }
        else if(title.equalsIgnoreCase("hotel")){
            tv1.setText("Title: Hotel Services");
            img.setImageResource(R.mipmap.hotel_background);
        }
        else if(title.equalsIgnoreCase("meet")){
            tv1.setText("Title: Meet & Greet");
            img.setImageResource(R.mipmap.meet_gray);
        }
        else {
            tv1.setText("Title: Airport Services");
            img.setImageResource(R.mipmap.airport_background);
        }



        return view;
    }

}
