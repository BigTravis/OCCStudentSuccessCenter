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
 * Provided to create custom list views for the users questions
 */

public class QuestionsListAdapter extends ArrayAdapter<Questions> {

    private Context mContext;
    private List<Questions> mQuestionsList = new ArrayList<>();
    private int mResourceId;

    private CheckBox mIsAnswered;

    public QuestionsListAdapter(Context c, int rId, List<Questions> questions)
    {
        super(c, rId, questions);
        mContext = c;
        mResourceId = rId;
        mQuestionsList = questions;
    }

    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final Questions selectedQuestion = mQuestionsList.get(pos);
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(mResourceId, null);

        mIsAnswered = (CheckBox) v.findViewById(R.id.questionsCheckBox);
        mIsAnswered.setText(selectedQuestion.getQuestion());
        mIsAnswered.setChecked(selectedQuestion.getIsAnswered() == 1);

        mIsAnswered.setTag(selectedQuestion);

        return v;
    }
}
