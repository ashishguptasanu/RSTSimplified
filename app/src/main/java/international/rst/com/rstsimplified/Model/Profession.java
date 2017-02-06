package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 06-02-2017.
 */

public class Profession {
    @SerializedName("profession")
    @Expose
    private List<ProfessionRes> profession = null;

    public List<ProfessionRes> getProfession() {
        return profession;
    }

    public void setProfession(List<ProfessionRes> profession) {
        this.profession = profession;
    }
}
