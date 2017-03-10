
package international.rst.com.rstsimplified.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisaType_ {
    @SerializedName("TravelPlanId")
    @Expose
    private String travelPlanId;
    @SerializedName("destination_id")
    @Expose
    private String destinationId;
    @SerializedName("MissionId")
    @Expose
    private String missionId;
    @SerializedName("visadurationid")
    @Expose
    private String visadurationid;
    @SerializedName("NoOfEntries")
    @Expose
    private String noOfEntries;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("visaadultfee")
    @Expose
    private String visaadultfee;
    @SerializedName("visaminorfee")
    @Expose
    private String visaminorfee;
    @SerializedName("visinfantfee")
    @Expose
    private String visinfantfee;
    @SerializedName("visafeeex")
    @Expose
    private String visafeeex;
    @SerializedName("servicefeeex")
    @Expose
    private String servicefeeex;
    @SerializedName("servicefeeex1")
    @Expose
    private String servicefeeex1;
    @SerializedName("servicefeeexvanila")
    @Expose
    private String servicefeeexvanila;
    @SerializedName("ospfee")
    @Expose
    private String ospfee;
    @SerializedName("visaprocesstimeex")
    @Expose
    private String visaprocesstimeex;
    @SerializedName("visaprocesstimeex1")
    @Expose
    private String visaprocesstimeex1;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("minoragecutoff")
    @Expose
    private String minoragecutoff;
    @SerializedName("infantagecutoff")
    @Expose
    private String infantagecutoff;
    @SerializedName("visaissuedtype")
    @Expose
    private String visaissuedtype;
    @SerializedName("deleted")
    @Expose
    private String deleted;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("visa_type_id")
    @Expose
    private int visaTypeId;
    @SerializedName("nationality_id")
    @Expose
    private String nationalityId;
    @SerializedName("living_in_id")
    @Expose
    private String livingInId;
    @SerializedName("currency_id")
    @Expose
    private int currencyId;
    @SerializedName("govt_fee")
    @Expose
    private float govtFee;
    @SerializedName("service_fee")
    @Expose
    private float serviceFee;
    @SerializedName("service_fee_e")
    @Expose
    private String serviceFeeE;
    @SerializedName("processing_time")
    @Expose
    private String processingTime;
    @SerializedName("processing_time_e")
    @Expose
    private String processingTimeE;
    @SerializedName("isActive")
    @Expose
    private String isActive;
    @SerializedName("service_fee_cs")
    @Expose
    private String serviceFeeCs;
    @SerializedName("mng_fee")
    @Expose
    private float mngFee;
    @SerializedName("mng_fee_combo")
    @Expose
    private float mngFeeCombo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("entryType")
    @Expose
    private String entryType;
    @SerializedName("visaValidity")
    @Expose
    private String visaValidity;
    @SerializedName("stayValidity")
    @Expose
    private String stayValidity;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("vv_comments")
    @Expose
    private String vvComments;
    @SerializedName("sv_comments")
    @Expose
    private String svComments;
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("hotel_min_fee")
    @Expose
    private float hotelMinFee;
    public float getHotelMinFee(){
        return hotelMinFee;
    }
    public void setHotelMinFee(float hotelMinFee){this.hotelMinFee = hotelMinFee;}
    public String getTravelPlanId() {
        return travelPlanId;
    }

    public void setTravelPlanId(String travelPlanId) {
        this.travelPlanId = travelPlanId;
    }
    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }
    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }
    public String getVisadurationid() {
        return visadurationid;
    }

    public void setVisadurationid(String visadurationid) {
        this.visadurationid = visadurationid;
    }

    public String getNoOfEntries() {
        return noOfEntries;
    }

    public void setNoOfEntries(String noOfEntries) {
        this.noOfEntries = noOfEntries;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVisaadultfee() {
        return visaadultfee;
    }

    public void setVisaadultfee(String visaadultfee) {
        this.visaadultfee = visaadultfee;
    }

    public String getVisaminorfee() {
        return visaminorfee;
    }

    public void setVisaminorfee(String visaminorfee) {
        this.visaminorfee = visaminorfee;
    }

    public String getVisinfantfee() {
        return visinfantfee;
    }

    public void setVisinfantfee(String visinfantfee) {
        this.visinfantfee = visinfantfee;
    }

    public String getVisafeeex() {
        return visafeeex;
    }

    public void setVisafeeex(String visafeeex) {
        this.visafeeex = visafeeex;
    }
    public String getServicefeeex() {
        return servicefeeex;
    }

    public void setServicefeeex(String servicefeeex) {
        this.servicefeeex = servicefeeex;
    }

    public String getServicefeeex1() {
        return servicefeeex1;
    }

    public void setServicefeeex1(String servicefeeex1) {
        this.servicefeeex1 = servicefeeex1;
    }

    public String getServicefeeexvanila() {
        return servicefeeexvanila;
    }

    public void setServicefeeexvanila(String servicefeeexvanila) {
        this.servicefeeexvanila = servicefeeexvanila;
    }

    public String getOspfee() {
        return ospfee;
    }

    public void setOspfee(String ospfee) {
        this.ospfee = ospfee;
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMinoragecutoff() {
        return minoragecutoff;
    }

    public void setMinoragecutoff(String minoragecutoff) {
        this.minoragecutoff = minoragecutoff;
    }

    public String getInfantagecutoff() {
        return infantagecutoff;
    }

    public void setInfantagecutoff(String infantagecutoff) {
        this.infantagecutoff = infantagecutoff;
    }

    public String getVisaissuedtype() {
        return visaissuedtype;
    }

    public void setVisaissuedtype(String visaissuedtype) {
        this.visaissuedtype = visaissuedtype;
    }

    public String getDeleted() {
        return deleted;
    }

    public String getVisaprocesstimeex() {
        return visaprocesstimeex;
    }

    public void setVisaprocesstimeex(String visaprocesstimeex) {
        this.visaprocesstimeex = visaprocesstimeex;
    }

    public String getVisaprocesstimeex1() {
        return visaprocesstimeex1;
    }

    public void setVisaprocesstimeex1(String visaprocesstimeex1) {
        this.visaprocesstimeex1 = visaprocesstimeex1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVisaTypeId() {
        return visaTypeId;
    }

    public void setVisaTypeId(int visaTypeId) {
        this.visaTypeId = visaTypeId;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getLivingInId() {
        return livingInId;
    }

    public void setLivingInId(String livingInId) {
        this.livingInId = livingInId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public float getGovtFee() {
        return govtFee;
    }

    public void setGovtFee(float govtFee) {
        this.govtFee = govtFee;
    }

    public float getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getServiceFeeE() {
        return serviceFeeE;
    }

    public void setServiceFeeE(String serviceFeeE) {
        this.serviceFeeE = serviceFeeE;
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }

    public String getProcessingTimeE() {
        return processingTimeE;
    }

    public void setProcessingTimeE(String processingTimeE) {
        this.processingTimeE = processingTimeE;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getServiceFeeCs() {
        return serviceFeeCs;
    }

    public void setServiceFeeCs(String serviceFeeCs) {
        this.serviceFeeCs = serviceFeeCs;
    }

    public float getMngFee() {
        return mngFee;
    }

    public void setMngFee(float mngFee) {
        this.mngFee = mngFee;
    }

    public float getMngFeeCombo() {
        return mngFeeCombo;
    }

    public void setMngFeeCombo(float mngFeeCombo) {
        this.mngFeeCombo = mngFeeCombo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getVisaValidity() {
        return visaValidity;
    }

    public void setVisaValidity(String visaValidity) {
        this.visaValidity = visaValidity;
    }

    public String getStayValidity() {
        return stayValidity;
    }

    public void setStayValidity(String stayValidity) {
        this.stayValidity = stayValidity;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getVvComments() {
        return vvComments;
    }

    public void setVvComments(String vvComments) {
        this.vvComments = vvComments;
    }

    public String getSvComments() {
        return svComments;
    }

    public void setSvComments(String svComments) {
        this.svComments = svComments;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}

