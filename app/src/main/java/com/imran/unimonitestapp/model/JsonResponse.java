package com.imran.unimonitestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {

    private int responseStatus;
    @SerializedName("plans")
    private List<MemberPlan> memberPlanList;

    public int getResponseStatus() {
        return responseStatus;
    }

    public List<MemberPlan> getMemberPlanList() {
        return memberPlanList;
    }
}
