package com.imran.unimonitestapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imran.unimonitestapp.R;
import com.imran.unimonitestapp.model.PlanBenefits;
import com.imran.unimonitestapp.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PlanBenefitsAdapter extends RecyclerView.Adapter<PlanBenefitsAdapter.ViewHolder> {


    private Context context;
    private List<PlanBenefits> memberPlanList;
    public static final int SAVE_VIEW_TYPE = 1;

    public PlanBenefitsAdapter(Context context, List<PlanBenefits> memberPlanList) {
        this.context = context;
        this.memberPlanList = memberPlanList;
    }

    @NonNull
    @Override
    public PlanBenefitsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewHolder viewHolder;
        switch (viewType) {
            case PlanBenefitsAdapter.SAVE_VIEW_TYPE:
                viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.save_row_layout, parent, false), viewType);
                break;
            default:
                viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.benefits_row_layout, parent, false), viewType);

        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return getViewTypeByPos(position);
    }

    private int getViewTypeByPos(int position) {
        return (memberPlanList != null ? memberPlanList.get(position).getViewType() : 0);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlanBenefitsAdapter.ViewHolder holder, final int pos) {
        int viewType = getViewTypeByPos(pos);
        PlanBenefits planBenefits = memberPlanList.get(pos);
        switch (viewType) {
            case SAVE_VIEW_TYPE:
                holder.showSaveAmount(planBenefits);
                break;
            default:
                holder.showBenefits(planBenefits);
                break;
        }


    }


    private int getIcon(String benefitIconLocal) {
        int icon = 0;
        switch (benefitIconLocal) {
            case Constants.IHO_CHECKUP_ICON:
                icon = R.drawable.health_chk;
                break;
            case Constants.IHO_DENTAL_ICON:
                icon = R.drawable.dental_chk;
                break;
            case Constants.IHO_DISCOUNT_CARD_ICON:
                icon = R.drawable.discount_card;
                break;
            case Constants.IHO_MOBILE_ICON:
            case Constants.IHO_CONSULATION_ICON:
                icon = R.drawable.tele_consult;
                break;
            case Constants.IHO_PHARMACY_ICON:
                icon = R.drawable.medicine;
                break;
            case Constants.IHO_DIET_ICON:
                icon = R.drawable.health_fitness;
                break;
        }
        return icon;
    }


    @Override
    public int getItemCount() {
        return memberPlanList != null && !memberPlanList.isEmpty() ? memberPlanList.size() : 0;
    }

    public void updateList(List<PlanBenefits> data) {
        memberPlanList = data;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView primaryText, secondaryText;
        private ImageView imageview;

        private ViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case SAVE_VIEW_TYPE:
                    primaryText = itemView.findViewById(R.id.primary_text);
                    imageview = itemView.findViewById(R.id.imageview);
                    break;
                default:
                    primaryText = itemView.findViewById(R.id.primary_text);
                    secondaryText = itemView.findViewById(R.id.secondary_text);
                    imageview = itemView.findViewById(R.id.imageview);
                    break;

            }
        }

        private void showBenefits(PlanBenefits planBenefits) {

            primaryText.setText(planBenefits.getBenefitTitle());
            if (planBenefits.getBenefitSubTitle() != null && !planBenefits.getBenefitSubTitle().isEmpty()) {
                String secondary_text = "(" + planBenefits.getBenefitSubTitle() + ")";
                secondaryText.setText(secondary_text);
            }
            int icon = getIcon(planBenefits.getBenefitIconLocal());
            Picasso.get().load(icon)
                    .placeholder(R.color.app_background)
                    .into(imageview);
        }

        private void showSaveAmount(PlanBenefits planBenefits) {
            primaryText.setText(planBenefits.getBenefitTitle());
        }
    }
}
