package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * this adapter puts study groups into a linear layout for the list in StudyGroupActivity
 *
 * */
public class StudyGroupAdapter extends ArrayAdapter<StudyGroup>  {

    private Context mContext;
    private List<StudyGroup> mStudyGroupList = new ArrayList<>();
    private int mResourceId;

    public StudyGroupAdapter(Context context, int resourceId, List<StudyGroup> studyGroups) {
        super(context, resourceId, studyGroups);
        mContext = context;
        mStudyGroupList = studyGroups;
        mResourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final StudyGroup selectedStudyGroup = mStudyGroupList.get(position);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout studyGroupLinearLayout =
                (LinearLayout) view.findViewById(R.id.studyGroupLinearLayout);
        TextView courseTextView = (TextView) view.findViewById(R.id.courseTextView);
        TextView timeTextView = (TextView) view.findViewById(R.id.timeTextView);
        TextView instructorTextView = (TextView) view.findViewById(R.id.instructorTextView);
        TextView roomTextView = (TextView) view.findViewById(R.id.roomTextView);

        studyGroupLinearLayout.setTag(selectedStudyGroup);

        courseTextView.setText(selectedStudyGroup.getCourse().toString());

        String timeText = selectedStudyGroup.getDayTime().getDay() + " " + selectedStudyGroup.getDayTime().convertFloatTimeToString();
        timeTextView.setText(timeText);

        instructorTextView.setText(selectedStudyGroup.getInstructor());

        roomTextView.setText("Room " + selectedStudyGroup.getRoom());

        return view;


    }
}
