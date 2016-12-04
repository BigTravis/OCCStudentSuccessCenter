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
 * Created by Link on 12/3/2016.
 */

public class FaqListAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mFaqList = new ArrayList<>();
    private int mResourceId;

    public FaqListAdapter(Context context, int resourceId, List<String> questionList) {
        super(context, resourceId, questionList);
        mContext = context;
        mFaqList = questionList;
        mResourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String question = mFaqList.get(position);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);


        LinearLayout faqLinearLayout = (LinearLayout) view.findViewById(R.id.faqLinearLayout);

        TextView faqTextView = (TextView) view.findViewById(R.id.faqTextView);
        faqTextView.setText(mFaqList.get(position));
        faqTextView.setTag(question);



        return view;
    }

}