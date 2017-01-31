package international.rst.com.rstsimplified.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Adapter.VisaAdapter;
import international.rst.com.rstsimplified.Model.VisaResponse;
import international.rst.com.rstsimplified.Model.VisaType;
import international.rst.com.rstsimplified.Model.VisaType_;
import international.rst.com.rstsimplified.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentServices extends android.support.v4.app.Fragment {
    TextView tv1;
    String title;
    View view;
    LinearLayoutManager linearLayoutManager1;
    VisaAdapter visaAdapter;
    String[] mDataset1;
    int[] mImageSet, mImageSet2;
    private List<VisaType_> visaTypes = new ArrayList<>();

    public FragmentServices() {
    }
    public static FragmentServices newInstance(String title){
        FragmentServices fragment1 = new FragmentServices();
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
        view = inflater.inflate(R.layout.fragment_service, container, false);
        tv1 = (TextView)view.findViewById(R.id.text1);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Getting Data from Server", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                loadVisaType();
            }

            private void loadVisaType() {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.uaevisasonline.com")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                VisaResponse request = retrofit.create(VisaResponse.class);
                Call<VisaType> call = request.getVisaType();
                //
                call.enqueue(new Callback<VisaType>() {
                    @Override
                    public void onResponse(Call<VisaType> call, Response<VisaType> response) {


                        VisaType jsonResponse = response.body();
                        visaTypes = jsonResponse.getVisaType();
                        System.out.println(visaTypes.size());
                        for(int i = 0; i<visaTypes.size();i++){
                            Log.i("Name", String.valueOf(visaTypes.get(i).get90DaysVisaJs()));
                        }

                    }
                    @Override
                    public void onFailure(Call<VisaType> call, Throwable t) {
                        Log.v("Error",t.getMessage());
                    }
                });
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
