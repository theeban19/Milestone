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

public class milestoneAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public milestoneAdapter(Context context, int resource) {
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
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            milestoneHolder = new MilestoneHolder();
            milestoneHolder.tx_name = (TextView) row.findViewById(R.id.txt_name);
            milestoneHolder.tx_description = (TextView) row.findViewById(R.id.txt_description);
//            milestoneHolder.tx_task = (TextView) row.findViewById(R.id.txt_task);
            milestoneHolder.tx_employeeid = (TextView) row.findViewById(R.id.txt_employeeid);
//            milestoneHolder.tx_date = (TextView) row.findViewById(R.id.txt_date);
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
        milestoneHolder.tx_description.setText(milestoneView.getDesc());
//        milestoneHolder.tx_task.setText(milestoneView.getTask());
        milestoneHolder.tx_employeeid.setText(milestoneView.getEmpId());
//        milestoneHolder.tx_date.setText(milestoneView.getDate());

        return row;
    }

    static  class MilestoneHolder {

        TextView tx_name, tx_description, tx_employeeid;

    }
}
