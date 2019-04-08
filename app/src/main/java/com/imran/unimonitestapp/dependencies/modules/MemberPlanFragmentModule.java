package com.imran.unimonitestapp.dependencies.modules;

import com.imran.unimonitestapp.retrofit.ApiInterface;
import com.imran.unimonitestapp.viewModel.MemberPlanViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MemberPlanFragmentModule {

    @Provides
    MemberPlanViewModel memberPlanViewModel(ApiInterface apiInterface) {
        return new MemberPlanViewModel(apiInterface);
    }
}
