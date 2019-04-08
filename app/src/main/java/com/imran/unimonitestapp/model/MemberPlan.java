package com.imran.unimonitestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberPlan {

    @SerializedName("plan_name")
    private String planName;

    @SerializedName("plan_cost")
    private double planCost;

    @SerializedName("plan_disc_cost")
    private double planDiscCost;

    @SerializedName("plan_save_upto")
    private double planSaveUpto;

    @SerializedName("benefits")
    private List<PlanBenefits> planBenefitsList;

    public String getPlanName() {
        return planName;
    }

    public double getPlanCost() {
        return planCost;
    }

    public double getPlanDiscCost() {
        return planDiscCost;
    }

    public double getPlanSaveUpto() {
        return planSaveUpto;
    }

    public List<PlanBenefits> getPlanBenefitsList() {
        return planBenefitsList;
    }
}
