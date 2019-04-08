package com.imran.unimonitestapp.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.imran.unimonitestapp.model.JsonResponse;
import com.imran.unimonitestapp.model.MemberPlan;
import com.imran.unimonitestapp.retrofit.ApiInterface;
import com.imran.unimonitestapp.utils.StateLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberPlanViewModel extends ViewModel {

    private ApiInterface apiInterface;

    /*Live data to hold the Membership Plans*/
    private StateLiveData<List<MemberPlan>> jsonResponseLiveData = new StateLiveData<>();

    /*ApiInterface Injected via Dagger 2*/
    public MemberPlanViewModel(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public StateLiveData<List<MemberPlan>> getJsonResponseLiveData() {
        return jsonResponseLiveData;
    }

    public void getMemberPlan(int count) {
        apiInterface.getMemberPlan(count)
                .enqueue(new Callback<JsonResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<JsonResponse> call, @NonNull Response<JsonResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<MemberPlan> resultList = response.body().getMemberPlanList();
                            /*Setting success to live data*/
                            jsonResponseLiveData.postSuccess(resultList);
                        } else {
                            jsonResponseLiveData.postComplete();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<JsonResponse> call, @NonNull Throwable t) {
                        /*Setting Error to live data*/
                        jsonResponseLiveData.postError(t);
                    }
                });
    }
}
