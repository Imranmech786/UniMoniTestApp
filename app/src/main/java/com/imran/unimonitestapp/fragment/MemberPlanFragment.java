package com.imran.unimonitestapp.fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.imran.unimonitestapp.R;
import com.imran.unimonitestapp.adapter.MemberPlanAdapter;
import com.imran.unimonitestapp.model.MemberPlan;
import com.imran.unimonitestapp.utils.Network;
import com.imran.unimonitestapp.utils.StateData;
import com.imran.unimonitestapp.viewModel.MemberPlanViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MemberPlanFragment extends DaggerFragment {

    private Context context;

    @Inject
    MemberPlanViewModel memberPlanViewModel;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MemberPlanAdapter memberPlanAdapter;
    private TextView retry;
    private int memberCount;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.member_plan_fragment, container, false);
        initializeWidgets(view);
        if (getArguments() != null) {
            memberCount = getArguments().getInt(context.getString(R.string.member_count));

            /*Observing the plan live data*/
            memberPlanViewModel.getJsonResponseLiveData().observe(this, getObservable());
            if (memberPlanViewModel.getJsonResponseLiveData().getValue() != null) {
                setData(memberPlanViewModel.getJsonResponseLiveData().getValue().getData(), memberCount);
            } else {
                getMembershipPlan();
            }
        }
        return view;
    }

    private void getMembershipPlan() {
        if (Network.isConnected(context.getApplicationContext())) {
            retry.setVisibility(View.GONE);
            /*Getting plans from view model*/
            memberPlanViewModel.getMemberPlan(memberCount);
        } else {
            retry.setVisibility(View.VISIBLE);
        }
    }

    private Observer<StateData<List<MemberPlan>>> getObservable() {
        return new Observer<StateData<List<MemberPlan>>>() {
            @Override
            public void onChanged(@Nullable StateData<List<MemberPlan>> listStateData) {
                assert listStateData != null;
                if (listStateData.getStatus() == StateData.DataStatus.SUCCESS) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    setData(listStateData.getData(), memberCount);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);

                }
            }
        };
    }

    private void setData(List<MemberPlan> data, int memberCount) {
        if (memberPlanAdapter != null) {
            memberPlanAdapter.updateList(data, memberCount);
        } else {
            memberPlanAdapter = new MemberPlanAdapter(context, data, memberCount);
            recyclerView.setAdapter(memberPlanAdapter);
        }
    }

    private void initializeWidgets(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        progressBar = view.findViewById(R.id.progressBar);
        retry = view.findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMembershipPlan();
            }
        });
    }

}
