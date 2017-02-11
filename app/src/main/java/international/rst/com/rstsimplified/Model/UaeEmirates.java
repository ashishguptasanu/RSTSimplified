package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 10-02-2017.
 */

public class UaeEmirates {
    @SerializedName("emirate")
    @Expose
    private List<Emirate> emirates = null;

    public List<Emirate> getEmirate() {
        return emirates;
    }

    public void setEmirates(List<Emirate> emirate) {
        this.emirates = emirates;
    }

}
