package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ToAskActivity extends AppCompatActivity {

    private DBHelper questionDB;
    private List<Questions> mQuestionsList;
    private QuestionsListAdapter mQuestionsListAdapter;

    private EditText questionEditText;
    private ListView questionsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_ask);

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
        for(int i = 0; i < mQuestionsList.size(); i++){
            if(mQuestionsList.get(i).getIsAnswered() == 1)
            {
                mQuestionsList.remove(i);
                i--;
            }
        }

        questionDB.deleteSelectedQuestions();

        mQuestionsListAdapter.notifyDataSetChanged();
    }
}

