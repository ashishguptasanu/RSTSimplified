package international.rst.com.rstsimplified.Model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ashish on 04-04-2017.
 */

public interface AllCountryUsa {
//http://www.usa-visahub.in/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=livingIn&gofor=country
@GET("/api/getdata.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&requireData=livingIn&gofor=country")
Call<Country> getCountry();
}
