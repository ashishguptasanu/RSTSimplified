package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurposeRes {

    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("purpose")
    @Expose
    private String purpose;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

}
