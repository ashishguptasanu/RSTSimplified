package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 22-02-2017.
 */

public class Portofarrival {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("port_arrival")
    @Expose
    private String portArrival;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String get1() {
        return _1;
    }

    public void set1(String _1) {
        this._1 = _1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortArrival() {
        return portArrival;
    }

    public void setPortArrival(String portArrival) {
        this.portArrival = portArrival;
    }

}
