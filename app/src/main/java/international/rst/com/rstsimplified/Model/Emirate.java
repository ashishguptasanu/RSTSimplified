package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Emirate {


    @SerializedName("emirates")
    @Expose
    private List<Emirate_> emirates = null;

    public List<Emirate_> getEmirates() {
        return emirates;
    }

    public void setEmirates(List<Emirate_> emirates) {
        this.emirates = emirates;
    }

    }

