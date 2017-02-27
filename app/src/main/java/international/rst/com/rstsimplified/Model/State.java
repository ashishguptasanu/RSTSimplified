package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 27-02-2017.
 */

public class State {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("StateName")
    @Expose
    private String stateName;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
