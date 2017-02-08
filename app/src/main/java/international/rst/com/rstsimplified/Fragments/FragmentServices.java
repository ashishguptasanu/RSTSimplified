package international.rst.com.rstsimplified.Fragments;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Activities.VisaTypeSelection;
import international.rst.com.rstsimplified.Adapter.VisaAdapter;
import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.Model.CountryResponse;
import international.rst.com.rstsimplified.Model.NationalityResponse;
import international.rst.com.rstsimplified.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentServices extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    TextView tv1;
    String title;
    View view;
    LinearLayoutManager linearLayoutManager1;
    Button buttonSubmission;
    LinearLayout linearLayoutVisa;
    VisaAdapter visaAdapter;
    String[] mDataset1;
    int[] mImageSet, mImageSet2;
    String[] arrayService;
    Spinner spinnerVisa,spinnerLivingIn,spinnerNationality;
    private List<CountryRes> nationality = new ArrayList<>();
    private List<CountryRes> livingin = new ArrayList<>();
    private List<String> livingInData = new ArrayList<>();
    private List<String> nationalityData = new ArrayList<>();
    public String selectedVisa;
    int selectedLivingIn, selectedNationality;
    ArrayAdapter<String> dataAdapter,dataAdapter2;


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
        loadJson();
        tv1 = (TextView)view.findViewById(R.id.text1);
        arrayService = new String[]{"UAE Visa","USA Visa","Singapore Visa","Oman Visa","Iran Visa"};
        linearLayoutVisa = (LinearLayout)view.findViewById(R.id.linear_layout_services);
        spinnerVisa = (Spinner)view.findViewById(R.id.spnr_visa);
        spinnerLivingIn = (Spinner)view.findViewById(R.id.spnr_living_in);
        spinnerNationality = (Spinner)view.findViewById(R.id.spnr_nationality);
        spinnerVisa.setOnItemSelectedListener(this);
        spinnerLivingIn.setOnItemSelectedListener(this);
        spinnerNationality.setOnItemSelectedListener(this);
        buttonSubmission = (Button)view.findViewById(R.id.services_submission);
        buttonSubmission.setOnClickListener(this);
        loadSpinners();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Getting Data from Server", Snackbar.LENGTH_LONG)
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
            linearLayoutVisa.setVisibility(View.GONE);

        }
        else if(title.equalsIgnoreCase("meet")){
            tv1.setText("Title: Meet & Greet");
            img.setImageResource(R.mipmap.meet_gray);
            linearLayoutVisa.setVisibility(View.GONE);
        }
        else {
            tv1.setText("Title: Airport Services");
            img.setImageResource(R.mipmap.airport_background);
            linearLayoutVisa.setVisibility(View.GONE);
        }
        return view;
    }

    private void loadJson() {
        loadNationality();
        loadLivingIn();
    }

    private void loadSpinners() {
        intializeVisaSpinner();
        intializeLivingInSpinner();
        intializeNationalitySpinner();
    }

    private void intializeVisaSpinner() {
        spinnerVisa = (Spinner)view.findViewById(R.id.spnr_visa);
        ArrayAdapter<String> genderDataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, arrayService);
        genderDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVisa.setAdapter(genderDataAdapter);
    }
    private void loadNationality(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NationalityResponse request = retrofit.create(NationalityResponse.class);
        Call<Country> call = request.getCountry();
        //
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {


                Country jsonResponse = response.body();
                nationality = jsonResponse.getCountry();
                for(int i=0;i<nationality.size();i++){
                    nationalityData.add(nationality.get(i).getName());
                }
                intializeNationalitySpinner();

            }
            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadLivingIn(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CountryResponse request = retrofit.create(CountryResponse.class);
        Call<Country> call = request.getCountry();
        //
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {


                Country jsonResponse = response.body();
                livingin = jsonResponse.getCountry();

                for(int i=0;i<livingin.size();i++){
                    livingInData.add(livingin.get(i).getName());
                }
                intializeLivingInSpinner();


            }
            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void intializeLivingInSpinner() {
        dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, livingInData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLivingIn.setAdapter(dataAdapter);
    }
    private void intializeNationalitySpinner() {
        dataAdapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, nationalityData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNationality.setAdapter(dataAdapter2);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spnr_visa:
                selectedVisa = arrayService[i];
                break;
            case R.id.spnr_living_in:
                int selectedLiving = spinnerLivingIn.getSelectedItemPosition();
                selectedLivingIn = livingin.get(selectedLiving).getId();
                break;
            case R.id.spnr_nationality:
                int livingIn = spinnerNationality.getSelectedItemPosition();
                selectedNationality = nationality.get(livingIn).getId();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.services_submission:
                if(selectedLivingIn != 0 && selectedNationality != 0){
                    Intent intent = new Intent(getContext(),VisaTypeSelection.class);
                    intent.putExtra("visa", selectedVisa);
                    intent.putExtra("livingid", selectedLivingIn);
                    intent.putExtra("nationid",selectedNationality);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(),"Select country to countinue..", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
}
