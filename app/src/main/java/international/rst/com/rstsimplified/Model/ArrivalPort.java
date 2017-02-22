package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 22-02-2017.
 */

public class ArrivalPort {
    @SerializedName("portofarrival")
    @Expose
    private List<Portofarrival> portofarrival = null;

    public List<Portofarrival> getPortofarrival() {
        return portofarrival;
    }

    public void setPortofarrival(List<Portofarrival> portofarrival) {
        this.portofarrival = portofarrival;
    }
}
