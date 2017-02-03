package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCountry {
    @SerializedName("allcountry")
    @Expose
    private List<CompleteCountry> allcountry = null;

    public List<CompleteCountry> getAllcountry() {
        return allcountry;
    }

    public void setCountry(List<CompleteCountry> allcountry) {
        this.allcountry = allcountry;
    }
}
