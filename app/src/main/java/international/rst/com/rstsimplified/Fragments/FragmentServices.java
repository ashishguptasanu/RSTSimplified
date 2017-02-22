package international.rst.com.rstsimplified.Fragments;

import android.content.Intent;
import android.os.Bundle;
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
import international.rst.com.rstsimplified.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentServices extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    TextView tv1;
    String title, urlNationality, urlLivingIn, urlVisaType;
    View view;
    LinearLayoutManager linearLayoutManager1;
    Button buttonSubmission;
    LinearLayout linearLayoutVisa;
    String[] mDataset1;
    int[] mImageSet, mImageSet2;
    String[] arrayService;
    int selectedVisaId;
    Spinner spinnerVisa,spinnerLivingIn,spinnerNationality;
    private List<CountryRes> nationality = new ArrayList<>();
    private List<CountryRes> livingin = new ArrayList<>();
    private List<String> livingInData = new ArrayList<>();
    private List<String> nationalityData = new ArrayList<>();
    public String selectedVisa;
    int selectedLivingIn, selectedNationality;
    ImageView img1;
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
    private void loadSpinnerData(String urlNationality, String urlLivingIn){
        loadLivingIn(urlLivingIn);
        loadNationality(urlNationality);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.spnr_visa:
                selectedVisa = arrayService[i];
                selectedVisaId = i;
                if(i==0){
                    urlNationality = "https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=nationality&gofor=country";
                    urlLivingIn = "https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=livingIn&gofor=country";
                    loadSpinnerData(urlNationality, urlLivingIn);

                }
                else if(i==1){
                    urlNationality = "http://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=nationality&gofor=country";
                    urlLivingIn = "http://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=livingIn&gofor=country";
                    loadSpinnerData(urlNationality, urlLivingIn);

                }
                else if(i==2){
                    urlNationality = "";
                    urlLivingIn = "";
                }
                else if(i==3){
                    urlNationality ="";
                    urlLivingIn = "";
                }
                else if(i==4){
                    urlNationality = "";
                    urlLivingIn = "";
                }
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
                    intent.putExtra("livingid", selectedLivingIn);
                    intent.putExtra("nationid",selectedNationality);



                    if(selectedVisaId==0){
                        urlVisaType = ("https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaTypes&nationality="+selectedNationality+"&livingIn="+selectedLivingIn);
                        intent.putExtra("visa_type_url", urlVisaType);
                    }
                    else {
                        urlVisaType = ("https://www.uaevisasonline.com/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaTypes&nationality="+selectedNationality+"&livingIn="+selectedLivingIn);
                        intent.putExtra("visa_type_url", urlVisaType);
                    }
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(),"Select country to countinue..", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
}
