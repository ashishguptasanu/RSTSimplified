package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 10-02-2017.
 */

public class Emirate_ {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("emirates")
    @Expose
    private String emirates;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String getEmirates() {
        return emirates;
    }

    public void setEmirates(String emirates) {
        this.emirates = emirates;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
