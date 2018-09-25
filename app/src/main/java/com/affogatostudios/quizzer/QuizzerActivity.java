package com.affogatostudios.quizzer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.affogatostudios.quizzer.R;

public class QuizzerActivity extends AppCompatActivity {

    private TextView textView;
    private TextView questionView;
    private TextView correctCountView;
    private TextView incorrectCountView;
    private Button startButton;
    private Button submitButton;
    private RadioGroup optionGroup;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private Question question;
    private String[] options;
    private int userAnswer;
    private int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzer);

        textView = findViewById(R.id.textView);
        questionView = findViewById(R.id.questionView);
        questionView.setVisibility(View.GONE);
        correctCountView = findViewById(R.id.correctCountView);
        correctCountView.setVisibility(View.GONE);
        incorrectCountView = findViewById(R.id.incorrectCountView);
        incorrectCountView.setVisibility(View.GONE);

        startButton = findViewById(R.id.startButton);
        startButton.setText("Start quizzing!");
        submitButton = findViewById(R.id.submitButton);
        submitButton.setText("Submit");
        submitButton.setVisibility(View.GONE);

        optionGroup = findViewById(R.id.optionGroup);

        option1 = findViewById(R.id.option1);
        option1.setVisibility(View.GONE);

        option2 = findViewById(R.id.option2);
        option2.setVisibility(View.GONE);

        option3 = findViewById(R.id.option3);
        option3.setVisibility(View.GONE);
        question = new Question();
        correctCountView.setText(question.getCorrectCount());
        incorrectCountView.setText(question.getIncorrectCount());
        questionView.setText(question.getQuestion());
        options = question.getOptions();
        option1.setText(options[0]);
        option2.setText(options[1]);
        option3.setText(options[2]);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
                questionView.setVisibility(View.VISIBLE);
                correctCountView.setVisibility(View.VISIBLE);
                incorrectCountView.setVisibility(View.VISIBLE);
                option1.setVisibility(View.VISIBLE);
                option2.setVisibility(View.VISIBLE);
                option3.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.GONE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an option is selected
                if (optionGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(QuizzerActivity.this, "Select an option!", Toast.LENGTH_SHORT).show();
                } else {
                    switch (optionGroup.getCheckedRadioButtonId()) {
                        case R.id.option1:
                            userAnswer = Integer.parseInt(options[0]);
                            break;
                        case R.id.option2:
                            userAnswer = Integer.parseInt(options[1]);
                            break;
                        case R.id.option3:
                            userAnswer = Integer.parseInt(options[2]);
                            break;
                    }
                    // Check answer and toast
                    if (question.checkAnswer(userAnswer)) {
                        correctCountView.setText(question.getCorrectCount());
                        Toast.makeText(QuizzerActivity.this, "Correct, Nice Job!", Toast.LENGTH_SHORT).show();
                    } else {
                        incorrectCountView.setText(question.getIncorrectCount());
                        Toast.makeText(QuizzerActivity.this, "Wrong, Try Again!", Toast.LENGTH_SHORT).show();
                    }
                    // Set new question and options
                    questionView.setText(question.getQuestion());
                    options = question.getOptions();
                    option1.setText(options[0]);
                    option2.setText(options[1]);
                    option3.setText(options[2]);
                    optionGroup.clearCheck();
                }
            }
        });
    }
}
