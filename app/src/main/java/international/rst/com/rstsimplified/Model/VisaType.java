
package international.rst.com.rstsimplified.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VisaType {

    @SerializedName("visaType")
    @Expose
    private VisaType_ visaType;

    public List<VisaType_> getVisaType() {
        return (List<VisaType_>) visaType;
    }

    public void setVisaType(VisaType_ visaType) {
        this.visaType = visaType;
    }

}
