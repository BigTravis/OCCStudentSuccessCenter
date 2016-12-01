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

public class TutorListAdapter extends ArrayAdapter<Tutor> {
    private Context mContext;
    private List<Tutor> mTutorList = new ArrayList<>();
    private int mResourceId;

    public TutorListAdapter(Context context, int resourceId, List<Tutor> tutors) {
        super(context, resourceId, tutors);
        mContext = context;
        mTutorList = tutors;
        mResourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tutor tutorSelected = mTutorList.get(position);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout tutorListLinearLayout = (LinearLayout) view.findViewById(R.id.tutorListLinearLayout);
        TextView tutorListNameTextView = (TextView) view.findViewById(R.id.tutorListNameTextView);
        TextView tutorListHoursTextView = (TextView) view.findViewById(R.id.tutorListHoursTextView);

        tutorListLinearLayout.setTag(tutorSelected);
        tutorListNameTextView.setText(tutorSelected.getFirstName() + " " + tutorSelected.getLastName());
        // TODO add hours text to tutorListHoursTextView
        return view;
    }
}
