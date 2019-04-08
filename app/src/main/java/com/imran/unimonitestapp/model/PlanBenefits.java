package com.imran.unimonitestapp.model;

import com.google.gson.annotations.SerializedName;

public class PlanBenefits {

    @SerializedName("benefit_title")
    private String benefitTitle;

    @SerializedName("benefit_sub_title")
    private String benefitSubTitle;

    @SerializedName("benefit_icon_network")
    private String benefitIconNetwork;

    @SerializedName("benefit_icon_local")
    private String benefitIconLocal;

    private int viewType;

    public String getBenefitTitle() {
        return benefitTitle;
    }

    public String getBenefitSubTitle() {
        return benefitSubTitle;
    }

    public String getBenefitIconNetwork() {
        return benefitIconNetwork;
    }

    public String getBenefitIconLocal() {
        return benefitIconLocal;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setBenefitTitle(String benefitTitle) {
        this.benefitTitle = benefitTitle;
    }
}
