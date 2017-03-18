package international.rst.com.rstsimplified.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import international.rst.com.rstsimplified.Activities.VisaTypeSelection;
import international.rst.com.rstsimplified.Model.Country;
import international.rst.com.rstsimplified.Model.CountryRes;
import international.rst.com.rstsimplified.Model.CountryResponse;
import international.rst.com.rstsimplified.Model.NationalityResponse;
import international.rst.com.rstsimplified.Model.State;
import international.rst.com.rstsimplified.Model.StateResponse;
import international.rst.com.rstsimplified.Model.States;
import international.rst.com.rstsimplified.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentServices extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    TextView tv1;
    String title, urlNationality, urlLivingIn, urlVisaType, urlStates;
    View view;
    LinearLayoutManager linearLayoutManager1;
    Button buttonSubmission;
    LinearLayout linearLayoutVisa, linearLayoutStates, linearLayoutIran;
    String[] mDataset1;
    int[] mImageSet, mImageSet2;
    String[] arrayService, travellingFor;
    int selectedVisaId;
    ProgressBar mProgressBar, nationalityProgress, livingProgress;
    Spinner spinnerVisa,spinnerLivingIn,spinnerNationality, spinnerStates, spinnerTravellingFor;
    private List<CountryRes> nationality = new ArrayList<>();
    private List<CountryRes> livingin = new ArrayList<>();
    private List<State> states = new ArrayList<>();
    private List<String> livingInData = new ArrayList<>();
    private List<String> stateData = new ArrayList<>();
    private List<String> nationalityData = new ArrayList<>();
    public String selectedVisa, selectedLivingInCountry;
    int selectedLivingIn, selectedNationality, selectedLiving;
    ImageView img1;
    SharedPreferences sharedPreferences;
    ArrayAdapter<String> dataAdapter,dataAdapter2, travellingAdapter;


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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //loadJson();
        tv1 = (TextView)view.findViewById(R.id.text1);
        arrayService = new String[]{"UAE Visa","USA Visa","Singapore Visa","Oman Visa","Iran Visa"};
        linearLayoutVisa = (LinearLayout)view.findViewById(R.id.linear_layout_services);
        linearLayoutStates = (LinearLayout)view.findViewById(R.id.layout_state_usa);
        linearLayoutIran = (LinearLayout)view.findViewById(R.id.layout_travelling_for);
        linearLayoutStates.setVisibility(View.GONE);
        spinnerVisa = (Spinner)view.findViewById(R.id.spnr_visa);
        spinnerLivingIn = (Spinner)view.findViewById(R.id.spnr_living_in);
        spinnerNationality = (Spinner)view.findViewById(R.id.spnr_nationality);
        spinnerLivingIn.setVisibility(View.GONE);
        spinnerNationality.setVisibility(View.GONE);
        spinnerVisa.setOnItemSelectedListener(this);
        spinnerLivingIn.setOnItemSelectedListener(this);
        spinnerNationality.setOnItemSelectedListener(this);
        spinnerStates = (Spinner)view.findViewById(R.id.spnr_state);
        spinnerStates.setOnItemSelectedListener(this);
        buttonSubmission = (Button)view.findViewById(R.id.services_submission);
        buttonSubmission.setOnClickListener(this);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progress);
        nationalityProgress = (ProgressBar)view.findViewById(R.id.nationality_progress);
        livingProgress = (ProgressBar)view.findViewById(R.id.living_in_progress);
        nationalityProgress.setVisibility(View.VISIBLE);
        livingProgress.setVisibility(View.VISIBLE);
        loadSpinners();

        //RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        ImageView img = (ImageView)view.findViewById(R.id.img_background);
        img1 = (ImageView)view.findViewById(R.id.img_visa_background);

        title = getArguments().getString("title");
        if(title.equalsIgnoreCase("visa")){
            tv1.setText("Title: visa Services");
            tv1.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            //recyclerView.setLayoutManager(linearLayoutManager1);


        }
        else if(title.equalsIgnoreCase("hotel")){
            tv1.setText("Title: Hotel Services");
            img1.setVisibility(View.GONE);
            img.setImageResource(R.mipmap.hotel_background);
            linearLayoutVisa.setVisibility(View.GONE);

        }
        else if(title.equalsIgnoreCase("meet")){
            tv1.setText("Title: Meet & Greet");
            img1.setVisibility(View.GONE);
            img.setImageResource(R.mipmap.meet_gray);
            linearLayoutVisa.setVisibility(View.GONE);
        }
        else {
            tv1.setText("Title: Airport Services");
            img1.setVisibility(View.GONE);
            img.setImageResource(R.mipmap.airport_background);
            linearLayoutVisa.setVisibility(View.GONE);
        }
        return view;
    }

    private void loadJson() {
        loadNationality(urlNationality);
        loadLivingIn(urlLivingIn);
    }


    private void loadNationality(String urlNationality){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NationalityResponse request = retrofit.create(NationalityResponse.class);
        Call<Country> call = request.getCountry(urlNationality);
        //
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {


                Country jsonResponse = response.body();
                nationality = jsonResponse.getCountry();
                nationalityData.clear();
                for(int i=0;i<nationality.size();i++){
                    nationalityData.add(nationality.get(i).getName());
                }
                intializeNationalitySpinner();
                spinnerNationality.setVisibility(View.VISIBLE);
                nationalityProgress.setVisibility(View.GONE);

            }
            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadLivingIn(String urlLivingIn){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uaevisasonline.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CountryResponse request = retrofit.create(CountryResponse.class);
        Call<Country> call = request.getCountry(urlLivingIn);
        //
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {


                Country jsonResponse = response.body();
                livingin = jsonResponse.getCountry();
                livingInData.clear();
                for(int i=0;i<livingin.size();i++){
                    livingInData.add(livingin.get(i).getName());
                }
                spinnerLivingIn.setVisibility(View.VISIBLE);
                livingProgress.setVisibility(View.GONE);
                intializeLivingInSpinner();


            }
            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
    private void loadStates(String urlStates){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.usa-visahub.in")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        StateResponse request = retrofit.create(StateResponse.class);
        Call<States> call = request.getState(urlStates);
        //
        call.enqueue(new Callback<States>() {
            @Override
            public void onResponse(Call<States> call, Response<States> response) {


                States jsonResponse = response.body();
                states = jsonResponse.getState();
                stateData.clear();
                for(int i=0;i<states.size();i++){
                    stateData.add(states.get(i).getStateName());
                }
                mProgressBar.setVisibility(View.GONE);
                linearLayoutStates.setVisibility(View.VISIBLE);
                populateStateSpinner();
            }
            @Override
            public void onFailure(Call<States> call, Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spnr_visa:
                selectedVisa = arrayService[i];
                selectedVisaId = i;
                spinnerLivingIn.setVisibility(View.GONE);
                spinnerNationality.setVisibility(View.GONE);
                livingProgress.setVisibility(View.VISIBLE);
                nationalityProgress.setVisibility(View.VISIBLE);
                if(i==0){
                    linearLayoutStates.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                    linearLayoutIran.setVisibility(View.GONE);
                    urlNationality = "https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=nationality&gofor=country";
                    urlLivingIn = "https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=livingIn&gofor=country";
                    loadSpinnerData(urlNationality, urlLivingIn);
                }
                else if(i==1){
                    linearLayoutStates.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                    linearLayoutIran.setVisibility(View.GONE);
                    urlNationality = "http://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=nationality&gofor=country";
                    urlLivingIn = "http://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=livingIn&gofor=country";
                    loadSpinnerData(urlNationality, urlLivingIn);
                }
                else if(i==2){
                    linearLayoutStates.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                    linearLayoutStates.setVisibility(View.GONE);
                    linearLayoutIran.setVisibility(View.GONE);
                    urlNationality = "http://singaporevisa-online.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=country&requireData=nationality";
                    urlLivingIn = "http://singaporevisa-online.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=country&requireData=livingIn";
                    loadSpinnerData(urlNationality,urlLivingIn);
                }
                else if(i==3){
                    linearLayoutStates.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                    linearLayoutStates.setVisibility(View.GONE);
                    linearLayoutIran.setVisibility(View.GONE);
                    urlNationality ="http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=country&requireData=nationality";
                    urlLivingIn = "http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=country&requireData=livingIn";
                    loadSpinnerData(urlNationality,urlLivingIn);
                }
                else if(i==4){
                    linearLayoutStates.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                    linearLayoutStates.setVisibility(View.GONE);
                    linearLayoutIran.setVisibility(View.VISIBLE);
                    populateTravellingForSpinner();
                    urlNationality = "http://iranvisas.in/api/getdatairn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=country&requireData=nationality";
                    urlLivingIn = "http://iranvisas.in/api/getdatairn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=country&requireData=livingIn";
                    loadSpinnerData(urlNationality, urlLivingIn);
                }
                break;
            case R.id.spnr_living_in:
                selectedLiving = spinnerLivingIn.getSelectedItemPosition();
                selectedLivingIn = livingin.get(selectedLiving).getId();
                selectedLivingInCountry = livingInData.get(i);
                System.out.println(selectedLivingInCountry);
                String phoneCode = livingin.get(i).getPhoneCode();
                final String code= ("+" + phoneCode);
                sharedPreferences.edit().putString("code", code).apply();
                if(selectedVisaId == 1 && selectedLiving != 0){
                    mProgressBar.setVisibility(View.VISIBLE);
                    linearLayoutStates.setVisibility(View.GONE);
                    urlStates = "https://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=state&LivingInId=" + selectedLivingIn;
                    System.out.println(urlStates);
                    loadStates(urlStates);
                }
                else{
                    //
                }


               break;
            case R.id.spnr_nationality:
                int livingIn = spinnerNationality.getSelectedItemPosition();
                selectedNationality = nationality.get(livingIn).getId();
                break;
            case R.id.spnr_state:

        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.services_submission:
                if(selectedVisaId == 0) {
                    if (selectedLivingIn != 0 && selectedNationality != 0) {
                        Intent intent = new Intent(getContext(), VisaTypeSelection.class);
                        intent.putExtra("livingid", selectedLivingIn);
                        intent.putExtra("nationid", selectedNationality);
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        sharedPreferences.edit().putString("living_in_country", selectedLivingInCountry).apply();
                        sharedPreferences.edit().putInt("living_in_id", selectedLiving).apply();
                        sharedPreferences.edit().putInt("visa_id", 0).apply();
                            urlVisaType = ("https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaTypes&nationality=" + selectedNationality + "&livingIn=" + selectedLivingIn);
                            intent.putExtra("visa_type_url", urlVisaType);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Select country to countinue..", Toast.LENGTH_SHORT).show();

                    }
                }
                else if(selectedVisaId == 2){
                    if (selectedLivingIn != 0 && selectedNationality != 0) {
                        Intent intent = new Intent(getContext(), VisaTypeSelection.class);
                        intent.putExtra("livingid", selectedLivingIn);
                        intent.putExtra("nationid", selectedNationality);
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        sharedPreferences.edit().putString("living_in_country", selectedLivingInCountry).apply();
                        sharedPreferences.edit().putInt("living_in_id", selectedLiving).apply();
                        sharedPreferences.edit().putInt("visa_id", 2).apply();
                            urlVisaType = ("http://singaporevisa-online.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaTypes&nationality_id=" + selectedNationality + "&living_in_id=" + selectedLivingIn);
                            intent.putExtra("visa_type_url", urlVisaType);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Select country to countinue..", Toast.LENGTH_SHORT).show();

                    }
                }
                else if(selectedVisaId == 3){
                    if (selectedLivingIn != 0 && selectedNationality != 0) {
                        Intent intent = new Intent(getContext(), VisaTypeSelection.class);
                        intent.putExtra("livingid", selectedLivingIn);
                        intent.putExtra("nationid", selectedNationality);
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        sharedPreferences.edit().putString("living_in_country", selectedLivingInCountry).apply();
                        sharedPreferences.edit().putInt("living_in_id", selectedLiving).apply();
                        sharedPreferences.edit().putInt("visa_id", 3).apply();
                            urlVisaType = ("http://omanvisas.in/api/getdataomn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaType&nationality_id=" + selectedNationality + "&living_in_id=" + selectedLivingIn);
                            intent.putExtra("visa_type_url", urlVisaType);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Select country to countinue..", Toast.LENGTH_SHORT).show();

                    }
                }
                else if(selectedVisaId == 4){
                    if (selectedLivingIn != 0 && selectedNationality != 0) {
                        Intent intent = new Intent(getContext(), VisaTypeSelection.class);
                        intent.putExtra("livingid", selectedLivingIn);
                        intent.putExtra("nationid", selectedNationality);
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        sharedPreferences.edit().putString("living_in_country", selectedLivingInCountry).apply();
                        sharedPreferences.edit().putInt("living_in_id", selectedLiving).apply();
                        sharedPreferences.edit().putInt("visa_id", 4).apply();
                        urlVisaType = ("http://iranvisas.in/api/getdatairn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaType&nationality_id=" + selectedNationality + "&living_in_id=" + selectedLivingIn);
                        intent.putExtra("visa_type_url", urlVisaType);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Select country to countinue..", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }
    }
    private void loadSpinners() {
        intializeVisaSpinner();
        //intializeLivingInSpinner();
        //intializeNationalitySpinner();
    }

    private void intializeVisaSpinner() {
        spinnerVisa = (Spinner)view.findViewById(R.id.spnr_visa);
        ArrayAdapter<String> genderDataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, arrayService);
        genderDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVisa.setAdapter(genderDataAdapter);
    }
    private void populateStateSpinner() {
        spinnerStates = (Spinner)view.findViewById(R.id.spnr_state);
        dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, stateData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStates.setAdapter(dataAdapter);
    }
    private void populateTravellingForSpinner() {
        spinnerTravellingFor = (Spinner)view.findViewById(R.id.spnr_travelling_for);
        travellingFor = new String[]{"Select One", "Tourism", "Buisness"};
        travellingAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, travellingFor);
        travellingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTravellingFor.setAdapter(travellingAdapter);
    }
    private void intializeLivingInSpinner() {
        spinnerLivingIn = (Spinner)view.findViewById(R.id.spnr_living_in);
        dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, livingInData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(getActivity() != null){
            spinnerLivingIn.setAdapter(dataAdapter);
        }
    }
    private void intializeNationalitySpinner() {
        spinnerNationality = (Spinner)view.findViewById(R.id.spnr_nationality);
        dataAdapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nationalityData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(getActivity() != null){
            spinnerNationality.setAdapter(dataAdapter2);
        }

    }
    private void loadSpinnerData(String urlNationality, String urlLivingIn){
        loadLivingIn(urlLivingIn);
        loadNationality(urlNationality);
    }
}
