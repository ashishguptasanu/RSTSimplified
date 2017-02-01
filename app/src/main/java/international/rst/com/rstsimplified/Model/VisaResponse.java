package international.rst.com.rstsimplified.Model;

import retrofit2.Call;
import retrofit2.http.GET;


public interface VisaResponse {
    @GET("/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=visaTypes&nationality=36&livingIn=118")
    Call<VisaType> getVisaType();
}
