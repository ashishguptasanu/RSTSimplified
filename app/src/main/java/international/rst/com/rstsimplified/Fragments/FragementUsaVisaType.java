package international.rst.com.rstsimplified.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        visaUrl = sharedPreferences.getString("url_usa_visa","");
        loadVisaType(visaUrl);
        System.out.println(visaUrl);
        Bundle bundle  = getArguments();
        if(title.equalsIgnoreCase("consult")){
            view = inflater.inflate(R.layout.fragment_usa_visa1, container, false);
        }
        return view;
    }
    private void loadVisaType(String url) {
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
                        if (visaTypes.get(i).getVisaTypeId() == 1){
                            visaTypes1.add(visaTypes.get(i));
                        }
                        else if(visaTypes.get(i).getVisaTypeId() == 2){
                            visaTypes2.add(visaTypes2.get(i));
                        }
                        else if(visaTypes.get(i).getVisaTypeId() == 3){
                            visaTypes3.add(visaTypes2.get(i));
                        }
                        else if(visaTypes.get(i).getVisaTypeId() == 4){
                            visaTypes4.add(visaTypes2.get(i));
                        }
                        else if(visaTypes.get(i).getVisaTypeId() == 8){
                            visaTypes5.add(visaTypes2.get(i));
                        }
                    }
                    //adapter = new VisaTypeAdapter(getApplicationContext(),visaTypes);
                    //recyclerView.setAdapter(adapter);
                }

            }
            @Override
            public void onFailure(Call<VisaType> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
}
