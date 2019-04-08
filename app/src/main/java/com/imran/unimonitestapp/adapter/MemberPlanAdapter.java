package com.imran.unimonitestapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imran.unimonitestapp.R;
import com.imran.unimonitestapp.model.MemberPlan;
import com.imran.unimonitestapp.model.PlanBenefits;

import java.util.List;


public class MemberPlanAdapter extends RecyclerView.Adapter<MemberPlanAdapter.ViewHolder> {


    private Context context;
    private List<MemberPlan> memberPlanList;
    private int memberCount;

    public MemberPlanAdapter(Context context, List<MemberPlan> memberPlanList, int memberCount) {
        this.context = context;
        this.memberPlanList = memberPlanList;
        this.memberCount = memberCount;
    }

    @NonNull
    @Override
    public MemberPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.member_plan_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MemberPlanAdapter.ViewHolder holder, final int pos) {

        MemberPlan memberPlan = memberPlanList.get(pos);

        holder.title.setText(memberPlan.getPlanName());
        String rupees = context.getResources().getString(R.string.rupee_symbol);
        String price = rupees + " " + String.valueOf(memberPlan.getPlanCost());
        holder.price.setText(price);

        String primaryText = context.getString(R.string.buy_now_for) + " " + String.valueOf(memberPlan.getPlanCost());
        holder.primaryText.setText(primaryText);

        String secondaryText = "(Up to " + String.valueOf(memberCount) + " family members)";
        holder.secondaryText.setText(secondaryText);
        holder.showBenefits(memberPlan);
    }


    @Override
    public int getItemCount() {
        return memberPlanList != null && !memberPlanList.isEmpty() ? memberPlanList.size() : 0;
    }

    public void updateList(List<MemberPlan> data, int memberCount) {
        memberPlanList = data;
        this.memberCount = memberCount;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {


        private TextView title, price,
                primaryText, secondaryText;
        private PlanBenefitsAdapter planBenefitsAdapter;
        private RecyclerView recyclerview;

        private ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            primaryText = itemView.findViewById(R.id.primary_text);
            secondaryText = itemView.findViewById(R.id.secondary_text);
            recyclerview = itemView.findViewById(R.id.recyclerview);
            recyclerview.setLayoutManager(new GridLayoutManager(context, 2));
        }

        private void showBenefits(MemberPlan memberPlan) {
            PlanBenefits planBenefits = new PlanBenefits();
            planBenefits.setViewType(PlanBenefitsAdapter.SAVE_VIEW_TYPE);
            String save = "Save Upto Rs " + String.valueOf(memberPlan.getPlanSaveUpto());
            planBenefits.setBenefitTitle(save);
            List<PlanBenefits> planBenefitsList = memberPlan.getPlanBenefitsList();
            planBenefitsList.add(planBenefits);
            if (planBenefitsAdapter != null) {
                planBenefitsAdapter.updateList(planBenefitsList);

            } else {
                planBenefitsAdapter = new PlanBenefitsAdapter(context, planBenefitsList);
                recyclerview.setAdapter(planBenefitsAdapter);
            }
        }
    }
}
