package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish on 06-02-2017.
 */

public class ProfessionRes {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ProfessionNo")
    @Expose
    private int professionNo;
    @SerializedName("Profession")
    @Expose
    private String profession;

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


    public int getProfessionNo() {
        return professionNo;
    }

    public void setProfessionNo(int professionNo) {
        this.professionNo = professionNo;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
