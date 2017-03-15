package international.rst.com.rstsimplified.Model;

import android.content.ContentResolver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ashish on 15-03-2017.
 */

public class Consulate {
    @SerializedName("consulate")
    @Expose
    private List<ConsulateResponse> consulate = null;

    public List<ConsulateResponse> getConsulate() {
        return consulate;
    }

    public void setConsulate(List<ConsulateResponse> consulate) {
        this.consulate = consulate;
    }
}
