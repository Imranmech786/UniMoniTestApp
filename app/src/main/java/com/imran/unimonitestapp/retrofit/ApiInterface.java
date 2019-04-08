package com.imran.unimonitestapp.retrofit;


import com.imran.unimonitestapp.model.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    /*Api for getting the Memebership Plans*/
    @GET("IHOPolicyDetails")
    Call<JsonResponse> getMemberPlan(
            @Query("member_count") int id
    );

}
