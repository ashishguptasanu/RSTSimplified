package international.rst.com.rstsimplified.Model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ashish on 15-03-2017.
 */

public interface ConsulateInterface {
    @GET("/api/getdatairn.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=consulate")
    Call<Consulate> getConsulate();
}
