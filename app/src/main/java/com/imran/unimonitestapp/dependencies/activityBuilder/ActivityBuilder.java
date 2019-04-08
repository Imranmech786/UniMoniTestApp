package com.imran.unimonitestapp.dependencies.activityBuilder;

import com.imran.unimonitestapp.dependencies.modules.MemberPlanFragmentModule;
import com.imran.unimonitestapp.fragment.MemberPlanFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    /*Injecting the View Model to Fragment*/
    @ContributesAndroidInjector(modules = MemberPlanFragmentModule.class)
    abstract MemberPlanFragment memberPlanFragment();

}
