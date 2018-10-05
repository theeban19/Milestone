package com.construction.app.cpms.Milestone;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Object> {
    @Override
    public int compare(Object currentDate, Object endDate) {
        MilestoneView milestoneView_one = (MilestoneView) currentDate;
        MilestoneView milestoneView_two = (MilestoneView) endDate;

        String mOne = milestoneView_one.getDaysLeft();
        String mTwo = milestoneView_two.getDaysLeft();

        try {
            int m1 = Integer.parseInt(mOne);
            int m2 = Integer.parseInt(mTwo);

            if (m1 > m2) {
                return 1;
            } else if (m1 < m2) {
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }
}
