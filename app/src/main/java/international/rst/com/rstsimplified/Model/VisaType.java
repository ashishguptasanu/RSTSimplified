
package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VisaType {
    @SerializedName("visaType")
    @Expose
    private List<VisaType_> visaType = null;

    public List<VisaType_> getVisaType() {
        return visaType;
    }

    public void setVisaType(List<VisaType_> visaType) {
        this.visaType = visaType;
    }

}
