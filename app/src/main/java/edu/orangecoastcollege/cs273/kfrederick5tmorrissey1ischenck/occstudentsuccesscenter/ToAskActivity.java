package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ToAskActivity extends NavDrawerActivity {

    private DBHelper questionDB;
    private List<Questions> mQuestionsList;
    private QuestionsListAdapter mQuestionsListAdapter;

    private EditText questionEditText;
    private ListView questionsListView;
    private Animation slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_to_ask, contentFrameLayout);

        questionDB = new DBHelper(this);

        mQuestionsList = questionDB.getAllQuestions();

        mQuestionsListAdapter = new QuestionsListAdapter(this,
                R.layout.edit_course_item, mQuestionsList);

        questionsListView = (ListView) findViewById(R.id.questionsListView);

        questionsListView.setAdapter(mQuestionsListAdapter);

        questionEditText = (EditText) findViewById(R.id.questionEditText);
    }

    public void addQuestionOnClick(View v)
    {
        String question = questionEditText.getText().toString();

        if(question.isEmpty())
            Toast.makeText(this, "Question text cannot be empty.", Toast.LENGTH_SHORT).show();
        else
        {
            Questions newQuestion = new Questions(question, 0);

            mQuestionsListAdapter.add(newQuestion);

            questionDB.addQuestion(newQuestion);

            questionEditText.setText("");
        }
    }

    public void changeCourseStatus(View v)
    {
        if(v instanceof CheckBox) {
            CheckBox selectedCheck = (CheckBox) v;
            Questions selectedQuestion = (Questions) selectedCheck.getTag();

            selectedQuestion.setIsAnswered(selectedCheck.isChecked()? 1 : 0);

            questionDB.updateQuestion(selectedQuestion);
        }

    }

    public void clearSelectedOnClick(View v)
    {
        removeListItem();
    }

    public void removeListItem()
    {
        slide = AnimationUtils.loadAnimation(this, R.anim.slide_off_anim);
        for (int i = 0; i < mQuestionsList.size(); i++) {
            if (mQuestionsList.get(i).getIsAnswered() == 1) {
                View item = questionsListView.getChildAt(i);
                item.startAnimation(slide);
                item.setVisibility(View.INVISIBLE);
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mQuestionsList.size(); i++) {
                    if (mQuestionsList.get(i).getIsAnswered() == 1) {
                        mQuestionsList.remove(i);
                        i--;
                    }
                }

                mQuestionsListAdapter.notifyDataSetChanged();
                questionDB.deleteSelectedCourses();
            }
        }, 300);
    }
}

