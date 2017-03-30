package international.rst.com.rstsimplified.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Adapter.UsaVisaAdapter1;
import international.rst.com.rstsimplified.Adapter.VisaTypeAdapter;
import international.rst.com.rstsimplified.Model.VisaResponse;
import international.rst.com.rstsimplified.Model.VisaType;
import international.rst.com.rstsimplified.Model.VisaType_;
import international.rst.com.rstsimplified.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragementUsaVisaType extends android.support.v4.app.Fragment{
    View view;
    String title, visaUrl;
    SharedPreferences sharedPreferences;
    public RecyclerView mRecyclerView, mRecyclerView2, mRecyclerView3, mRecyclerView4, mRecyclerView5;
    UsaVisaAdapter1 mAdapter1, mAdapter2, mAdapter3, mAdapter4, mAdapter5;
    RecyclerView.LayoutManager layoutManager1, layoutManager2, layoutManager3, layoutManager4, layoutManager5;
    private List<VisaType_> visaTypes = new ArrayList<>();
    private List<VisaType_> visaTypes1 = new ArrayList<>();
    private List<VisaType_> visaTypes2 = new ArrayList<>();
    private List<VisaType_> visaTypes3 = new ArrayList<>();
    private List<VisaType_> visaTypes4 = new ArrayList<>();
    private List<VisaType_> visaTypes5 = new ArrayList<>();
    public FragementUsaVisaType() {
    }
    public static FragementUsaVisaType newFormInstance(String title) {
        FragementUsaVisaType fragmentUsaVisaType = new FragementUsaVisaType();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentUsaVisaType.setArguments(args);
        return fragmentUsaVisaType;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        visaUrl = sharedPreferences.getString("url_usa_visa","");

        System.out.println(visaUrl);
        Bundle bundle  = getArguments();

        if(title.equalsIgnoreCase("business")){
            view = inflater.inflate(R.layout.fragment_usa_visa1, container, false);
            mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_usa_visa);
            layoutManager1 = new LinearLayoutManager(getContext());
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(layoutManager1);
            loadVisaType1(visaUrl);

        }
        else if(title.equalsIgnoreCase("family")){
            view = inflater.inflate(R.layout.fragment_usa_visa2, container, false);
            mRecyclerView2 = (RecyclerView)view.findViewById(R.id.recycler_usa_visa2);
            layoutManager2 = new LinearLayoutManager(getContext());
            mRecyclerView2.setHasFixedSize(true);
            mRecyclerView2.setLayoutManager(layoutManager2);
            loadVisaType2(visaUrl);


        }
        else if(title.equalsIgnoreCase("tourist")){
            view = inflater.inflate(R.layout.fragment_usa_visa3, container, false);
            mRecyclerView3 = (RecyclerView)view.findViewById(R.id.recycler_usa_visa3);
            mAdapter3 = new UsaVisaAdapter1(getContext(), visaTypes3);
            mRecyclerView3.setHasFixedSize(true);
            layoutManager3 = new LinearLayoutManager(getContext());
            mRecyclerView3.setLayoutManager(layoutManager3);
            loadVisaType3(visaUrl);
        }
        else if(title.equalsIgnoreCase("work")){
            view = inflater.inflate(R.layout.fragment_usa_visa4, container, false);
            mRecyclerView4 = (RecyclerView)view.findViewById(R.id.recycler_usa_visa4);
            mAdapter4 = new UsaVisaAdapter1(getContext(), visaTypes4);
            mRecyclerView4.setHasFixedSize(true);
            layoutManager4 = new LinearLayoutManager(getContext());
            mRecyclerView4.setLayoutManager(layoutManager4);
            loadVisaType4(visaUrl);
        }
        else if(title.equalsIgnoreCase("treaty")){
            view = inflater.inflate(R.layout.fragment_usa_visa5, container, false);
            mRecyclerView5 = (RecyclerView)view.findViewById(R.id.recycler_usa_visa5);
            mAdapter5 = new UsaVisaAdapter1(getContext(), visaTypes5);
            mRecyclerView5.setHasFixedSize(true);
            layoutManager5 = new LinearLayoutManager(getContext());
            mRecyclerView5.setLayoutManager(layoutManager5);
            loadVisaType5(visaUrl);
        }

        return view;
    }



    private void loadVisaType1(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final VisaResponse request = retrofit.create(VisaResponse.class);
        Call<VisaType> call = request.getVisaType(url);
        //
        call.enqueue(new Callback<VisaType>() {
            @Override
            public void onResponse(Call<VisaType> call, Response<VisaType> response) {
                visaTypes1.clear();
                if(response != null){
                    VisaType jsonResponse = response.body();
                    visaTypes = jsonResponse.getVisaType();
                    for(int i=0; i<visaTypes.size();i++){
                        if (visaTypes.get(i).getVisaTypeId() == 1){
                            visaTypes1.add(visaTypes.get(i));
                        }
                    }


                    if(visaTypes1 != null){
                        mAdapter1 = new UsaVisaAdapter1(getContext(), visaTypes1);
                        mRecyclerView.setAdapter(mAdapter1);
                    }
                        //mRecyclerView2.setAdapter(mAdapter2);
                        //mRecyclerView3.setAdapter(mAdapter3);
                        //mRecyclerView4.setAdapter(mAdapter4);
                        //mRecyclerView5.setAdapter(mAdapter5);

                    //adapter = new VisaTypeAdapter(getApplicationContext(),visaTypes);
                    //recyclerView.setAdapter(adapter);
                }

                System.out.println(visaTypes.size()+ "  " + visaTypes1.size()+ "  "+ visaTypes2.size()+ "  "+ visaTypes3.size()+ "  "+ visaTypes4.size());

            }
            @Override
            public void onFailure(Call<VisaType> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadVisaType2(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final VisaResponse request = retrofit.create(VisaResponse.class);
        Call<VisaType> call = request.getVisaType(url);
        //
        call.enqueue(new Callback<VisaType>() {
            @Override
            public void onResponse(Call<VisaType> call, Response<VisaType> response) {

                if(response != null){
                    VisaType jsonResponse = response.body();
                    visaTypes = jsonResponse.getVisaType();
                    for(int i=0; i<visaTypes.size();i++){
                        if (visaTypes.get(i).getVisaTypeId() == 2){
                            visaTypes2.add(visaTypes.get(i));
                        }
                    }


                    if(visaTypes2 != null){
                        mAdapter2 = new UsaVisaAdapter1(getContext(), visaTypes2);
                        mRecyclerView2.setAdapter(mAdapter2);
                    }
                    //mRecyclerView2.setAdapter(mAdapter2);
                    //mRecyclerView3.setAdapter(mAdapter3);
                    //mRecyclerView4.setAdapter(mAdapter4);
                    //mRecyclerView5.setAdapter(mAdapter5);

                    //adapter = new VisaTypeAdapter(getApplicationContext(),visaTypes);
                    //recyclerView.setAdapter(adapter);
                }

                System.out.println(visaTypes.size()+ "  " + visaTypes1.size()+ "  "+ visaTypes2.size()+ "  "+ visaTypes3.size()+ "  "+ visaTypes4.size());

            }
            @Override
            public void onFailure(Call<VisaType> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadVisaType3(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final VisaResponse request = retrofit.create(VisaResponse.class);
        Call<VisaType> call = request.getVisaType(url);
        //
        call.enqueue(new Callback<VisaType>() {
            @Override
            public void onResponse(Call<VisaType> call, Response<VisaType> response) {
                visaTypes3.clear();
                if(response != null){
                    VisaType jsonResponse = response.body();
                    visaTypes = jsonResponse.getVisaType();
                    for(int i=0; i<visaTypes.size();i++){
                        if (visaTypes.get(i).getVisaTypeId() == 3){
                            visaTypes3.add(visaTypes.get(i));
                        }
                    }


                    if(visaTypes3 != null){
                        mAdapter3 = new UsaVisaAdapter1(getContext(), visaTypes3);
                        mRecyclerView3.setAdapter(mAdapter3);
                    }
                    //mRecyclerView2.setAdapter(mAdapter2);
                    //mRecyclerView3.setAdapter(mAdapter3);
                    //mRecyclerView4.setAdapter(mAdapter4);
                    //mRecyclerView5.setAdapter(mAdapter5);

                    //adapter = new VisaTypeAdapter(getApplicationContext(),visaTypes);
                    //recyclerView.setAdapter(adapter);
                }

                System.out.println(visaTypes.size()+ "  " + visaTypes1.size()+ "  "+ visaTypes2.size()+ "  "+ visaTypes3.size()+ "  "+ visaTypes4.size());

            }
            @Override
            public void onFailure(Call<VisaType> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadVisaType4(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final VisaResponse request = retrofit.create(VisaResponse.class);
        Call<VisaType> call = request.getVisaType(url);
        //
        call.enqueue(new Callback<VisaType>() {
            @Override
            public void onResponse(Call<VisaType> call, Response<VisaType> response) {
                visaTypes4.clear();
                if(response != null){
                    VisaType jsonResponse = response.body();
                    visaTypes = jsonResponse.getVisaType();
                    for(int i=0; i<visaTypes.size();i++){
                        if (visaTypes.get(i).getVisaTypeId() == 4){
                            visaTypes4.add(visaTypes.get(i));
                        }
                    }


                    if(visaTypes2 != null){
                        mAdapter4 = new UsaVisaAdapter1(getContext(), visaTypes4);
                        mRecyclerView4.setAdapter(mAdapter4);
                    }
                    //mRecyclerView2.setAdapter(mAdapter2);
                    //mRecyclerView3.setAdapter(mAdapter3);
                    //mRecyclerView4.setAdapter(mAdapter4);
                    //mRecyclerView5.setAdapter(mAdapter5);

                    //adapter = new VisaTypeAdapter(getApplicationContext(),visaTypes);
                    //recyclerView.setAdapter(adapter);
                }

                System.out.println(visaTypes.size()+ "  " + visaTypes1.size()+ "  "+ visaTypes2.size()+ "  "+ visaTypes3.size()+ "  "+ visaTypes4.size());

            }
            @Override
            public void onFailure(Call<VisaType> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadVisaType5(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final VisaResponse request = retrofit.create(VisaResponse.class);
        Call<VisaType> call = request.getVisaType(url);
        //
        call.enqueue(new Callback<VisaType>() {
            @Override
            public void onResponse(Call<VisaType> call, Response<VisaType> response) {
                visaTypes5.clear();
                if(response != null){
                    VisaType jsonResponse = response.body();
                    visaTypes = jsonResponse.getVisaType();
                    for(int i=0; i<visaTypes.size();i++){
                        if (visaTypes.get(i).getVisaTypeId() == 8){
                            visaTypes5.add(visaTypes.get(i));
                        }
                    }


                    if(visaTypes5 != null){
                        mAdapter5 = new UsaVisaAdapter1(getContext(), visaTypes5);
                        mRecyclerView5.setAdapter(mAdapter5);
                    }
                    //mRecyclerView2.setAdapter(mAdapter2);
                    //mRecyclerView3.setAdapter(mAdapter3);
                    //mRecyclerView4.setAdapter(mAdapter4);
                    //mRecyclerView5.setAdapter(mAdapter5);

                    //adapter = new VisaTypeAdapter(getApplicationContext(),visaTypes);
                    //recyclerView.setAdapter(adapter);
                }

                System.out.println(visaTypes.size()+ "  " + visaTypes1.size()+ "  "+ visaTypes2.size()+ "  "+ visaTypes3.size()+ "  "+ visaTypes4.size());

            }
            @Override
            public void onFailure(Call<VisaType> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
}
