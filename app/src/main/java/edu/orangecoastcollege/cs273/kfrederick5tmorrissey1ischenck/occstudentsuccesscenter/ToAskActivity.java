package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.content.res.Configuration;
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

import static edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter.DBHelper.mQuestions;

/**
 * Provides the user a place to add questions that they wish to ask the tutor later.
 */
public class ToAskActivity extends NavDrawerActivity {

    private DBHelper questionDB;
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
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        if (screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE)
            mQuestionsListAdapter = new QuestionsListAdapter(this,
                    R.layout.questions_list_item_large, mQuestions);

        else
            mQuestionsListAdapter = new QuestionsListAdapter(this,
                    R.layout.questions_list_item, mQuestions);


        questionsListView = (ListView) findViewById(R.id.questionsListView);

        questionsListView.setAdapter(mQuestionsListAdapter);

        questionEditText = (EditText) findViewById(R.id.questionEditText);
    }

    /**
     * When the user presses the add question button the test they have entered in the
     * edit text window will be added to the questions list database
     * @param v the add question button
     */
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

    /**
     * Changes the checkbox value on the indicated selected item depending on its current
     * status.
     * @param v the checkbox selected by the user
     */
    public void changeQuestionStatus(View v)
    {
        if (v instanceof CheckBox) {
            CheckBox selectedCheck = (CheckBox) v;
            Questions selectedQuestion = (Questions) selectedCheck.getTag();

            selectedQuestion.setIsAnswered(selectedCheck.isChecked()? 1 : 0);

            questionDB.updateQuestion(selectedQuestion);
        }

    }

    /**
     * When the clear selected button is clicked all items that are selected will be removed
     * from the questions database and list
     * @param v the clear selected button
     */
    public void clearSelectedOnClick(View v)
    {
        removeListItem();
    }

    /**
     * Preforms the actual removal of the item from the database and list and includes an
     * animation of the items sliding off the screen before being removed.
     */
    public void removeListItem()
    {
        slide = AnimationUtils.loadAnimation(this, R.anim.slide_off_anim);
        int size = mQuestions.size();
        for (int i = 0; i < size; ++i) {
            if (mQuestions.get(i).getIsAnswered() == 1) {
                View item = questionsListView.getChildAt(i);
                if (item != null) {
                    item.startAnimation(slide);
                    item.setVisibility(View.INVISIBLE);
                }
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mQuestions.size(); ++i) {
                    if (mQuestions.get(i).getIsAnswered() == 1) {
                        mQuestions.remove(i);
                        --i;
                    }
                }
                mQuestionsListAdapter.notifyDataSetChanged();
                questionDB.deleteSelectedQuestions();
            }
        }, 300);
    }
}

