package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class States {
    @SerializedName("state")
    @Expose
    private List<State> state = null;

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }
}
