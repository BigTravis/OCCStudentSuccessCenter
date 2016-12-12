package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to populate the edit profile user courses list with selectable checkboxes
 */

public class ProfileListAdapter extends ArrayAdapter<UserCourse> {

    private Context mContext;
    private List<UserCourse> mCoursesList = new ArrayList<>();
    private int mResourceId;
    private CheckBox selectedCheckBox;

    public ProfileListAdapter(Context context, int resource, List<UserCourse> coursesList) {
        super(context, resource, coursesList);
        mContext = context;
        mResourceId = resource;
        mCoursesList = coursesList;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final UserCourse selectedCourse = mCoursesList.get(pos);
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        selectedCheckBox = (CheckBox) view.findViewById(R.id.selectedCheckBox);
        selectedCheckBox.setText(selectedCourse.getDepartment() + " " + selectedCourse.getNumber());
        selectedCheckBox.setChecked(selectedCourse.getIsSelected() == 1);

        selectedCheckBox.setTag(selectedCourse);

        return view;
    }
}
