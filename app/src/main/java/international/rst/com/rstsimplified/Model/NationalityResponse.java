package international.rst.com.rstsimplified.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Ashish on 31-01-2017.
 */

public interface NationalityResponse {
    //@GET("/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=nationality&gofor=country")
    @GET
    Call<Country> getCountry(@Url String url);
}
