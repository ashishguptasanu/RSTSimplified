package international.rst.com.rstsimplified.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Ashish on 27-02-2017.
 */

public interface StateResponse {
    @GET
    Call<States> getState(@Url String url);
}
