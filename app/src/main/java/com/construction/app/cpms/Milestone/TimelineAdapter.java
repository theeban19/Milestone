package com.construction.app.cpms.Milestone;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.construction.app.cpms.R;

import java.util.ArrayList;
import java.util.List;

public class TimelineAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public TimelineAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(MilestoneView object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public  int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {

        View row;
        row = convertview;
        MilestoneHolder milestoneHolder;

        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.activity_time_adapter,parent,false);
            milestoneHolder = new MilestoneHolder();
            milestoneHolder.tx_name = (TextView) row.findViewById(R.id.txt_name);
            milestoneHolder.tx_date = (TextView) row.findViewById(R.id.txt_date);
            milestoneHolder.tx_daysleft = (TextView) row.findViewById(R.id.txt_daysleft);
            row.setTag(milestoneHolder);

        }
        else
        {
            milestoneHolder = (MilestoneHolder)row.getTag();
        }

        LayoutParams layoutparams = row.getLayoutParams();
        layoutparams.height = 200;
        row.setLayoutParams(layoutparams);

        MilestoneView milestoneView = (MilestoneView) this.getItem(position);
        milestoneHolder.tx_name.setText(milestoneView.getName());
        milestoneHolder.tx_date.setText(milestoneView.getDate());
        milestoneHolder.tx_daysleft.setText(milestoneView.getDaysLeft());

        return row;
    }

    static  class MilestoneHolder {

        TextView tx_name, tx_date, tx_daysleft;

    }
}
