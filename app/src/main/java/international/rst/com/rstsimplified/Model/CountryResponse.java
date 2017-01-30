package international.rst.com.rstsimplified.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by Ashish on 30-01-2017.
 */

public interface CountryResponse {
        @GET("/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=livingIn&gofor=country")
        Call<Country> getCountry();
    }

