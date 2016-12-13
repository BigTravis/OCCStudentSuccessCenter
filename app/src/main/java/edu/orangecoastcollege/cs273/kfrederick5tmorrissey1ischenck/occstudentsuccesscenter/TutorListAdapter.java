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
 * Custom ListAdapter class for displaying tutors in TutorListActivity
 */
public class TutorListAdapter extends ArrayAdapter<TutorTimeRelation> {
    private Context mContext;
    private List<TutorTimeRelation> mTutorTimeList = new ArrayList<>();
    private int mResourceId;

    /**
     * Constructor for TutorListAdapter
     * @param context The context of the current activity
     * @param resourceId The resource ID for a layout file containing a TextView to use when instantiating views.
     * @param tutorTimes The objects to represent in the ListView.
     */
    public TutorListAdapter(Context context, int resourceId, List<TutorTimeRelation> tutorTimes) {
        super(context, resourceId, tutorTimes);
        mContext = context;
        mTutorTimeList = tutorTimes;
        mResourceId = resourceId;
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     * @param position Position of the view
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TutorTimeRelation selectedTutorTimeRelation = mTutorTimeList.get(position);

        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout tutorListLinearLayout = (LinearLayout) view.findViewById(R.id.tutorListLinearLayout);
        TextView tutorListNameTextView = (TextView) view.findViewById(R.id.tutorListNameTextView);
        TextView tutorListTimesTextView = (TextView) view.findViewById(R.id.tutorListHoursTextView);

        tutorListLinearLayout.setTag(selectedTutorTimeRelation);
        tutorListNameTextView.setText(selectedTutorTimeRelation.getTutor().getFirstName() + " "
                + selectedTutorTimeRelation.getTutor().getLastName());

        tutorListTimesTextView.setText
                (selectedTutorTimeRelation.getStartTime().getDay() + ": " +
                        selectedTutorTimeRelation.getStartTime().convertFloatTimeToString() + " - "
                        + selectedTutorTimeRelation.getEndTime().convertFloatTimeToString());

        return view;
    }
}
