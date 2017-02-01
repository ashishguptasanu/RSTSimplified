package international.rst.com.rstsimplified.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface VisaResponse {
    @GET
    Call<VisaType> getVisaType(@Url String url);
}
