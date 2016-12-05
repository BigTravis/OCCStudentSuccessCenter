package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Link on 12/5/2016.
 */

public class UserListAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private List<User> coursesList = new ArrayList<>();
    private int mResourceId;

    public UserListAdapter(Context c, int rId, List<User> courses)
    {
        super (c, rId, courses);
        mContext = c;
        mResourceId = rId;
        coursesList = courses;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final User selectedCourse = coursesList.get(pos);

        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(mResourceId, null);

        LinearLayout coursesListLineatLayout = (LinearLayout) v.findViewById(R.id.courseListLinearLayout);
        TextView userSubjectTextView = (TextView) v.findViewById(R.id.userSubjectTextView);
        TextView userClassTextView = (TextView) v.findViewById(R.id.userClassTextView);

        coursesListLineatLayout.setTag(selectedCourse);
        userSubjectTextView.setText(selectedCourse.getSubject());
        userClassTextView.setText(selectedCourse.getuClass());

        return v;
    }
}
