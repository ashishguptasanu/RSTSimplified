package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 15-03-2017.
 */

public class ConsulateResponse {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("consulateName")
    @Expose
    private String consulateName;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String getConsulateName() {
        return consulateName;
    }

    public void setConsulateName(String consulateName) {
        this.consulateName = consulateName;
    }

}
