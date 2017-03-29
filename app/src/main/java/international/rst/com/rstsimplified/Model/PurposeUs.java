package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class PurposeUs {

    @SerializedName("purpose")
    @Expose
    private List<PurposeRes> purpose = null;

    public List<PurposeRes> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<PurposeRes> purpose) {
        this.purpose = purpose;
    }

}
