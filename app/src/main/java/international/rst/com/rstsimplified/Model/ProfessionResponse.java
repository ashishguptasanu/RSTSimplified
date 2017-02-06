package international.rst.com.rstsimplified.Model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ashish on 06-02-2017.
 */

public interface ProfessionResponse {
    @GET("/api/getData1.php?secure_id=nAN9qJlcBAR%2Fzs0R%2BZHJmII0W7GFPuRzY%2BfyrT65Fyw%3D&gofor=profession")
    Call<Profession> getProfession();
}
