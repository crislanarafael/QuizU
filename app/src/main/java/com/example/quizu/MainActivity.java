package com.example.quizu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView flashcardQuestion;
    TextView flashcardAnswer;
    TextView flashcardAnswer1;
    TextView flashcardAnswer2;
    TextView flashcardAnswer3;
    ImageView eyeopen;
    ImageView eyeclosed;
    ImageView add;


    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("String", "Hi");
        if (resultCode == RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            final String string1 = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            final String string2 = data.getExtras().getString("answer");

            flashcardQuestion.setText(string1);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardQuestion = findViewById(R.id.flashcard_question);
        flashcardAnswer = findViewById(R.id.flashcard_new_answer);
        flashcardAnswer1 = findViewById(R.id.flashcard_answer);
        flashcardAnswer2 = findViewById(R.id.flashcard_answer2);
        flashcardAnswer3 = findViewById(R.id.flashcard_answer3);
        eyeopen = findViewById(R.id.toggle_choices_visibility);
        eyeclosed = findViewById(R.id.toggle_choices_invisibility);
        add = findViewById(R.id.add_icon);



        flashcardQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                flashcardQuestion.setVisibility(View.INVISIBLE);
                flashcardAnswer.setVisibility(View.VISIBLE);
            }
        });

        flashcardAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                flashcardQuestion.setVisibility(View.VISIBLE);
                flashcardAnswer.setVisibility(View.INVISIBLE);
            }
        });

        flashcardAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        flashcardAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashcardAnswer1.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        flashcardAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashcardAnswer1.setBackgroundColor(getResources().getColor(R.color.green));
                flashcardAnswer2.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        flashcardAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashcardAnswer1.setBackgroundColor(getResources().getColor(R.color.green));
                flashcardAnswer3.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        eyeopen.setOnClickListener(new View.OnClickListener() { //if clicked, eye will become invisible and hide answers
            @Override
            public void onClick(View view) {
                eyeclosed.setVisibility(View.VISIBLE);
                eyeopen.setVisibility(View.INVISIBLE);
                flashcardAnswer1.setVisibility(View.INVISIBLE);
                flashcardAnswer2.setVisibility(View.INVISIBLE);
                flashcardAnswer3.setVisibility(View.INVISIBLE);
            }
        });

        eyeclosed.setOnClickListener(new View.OnClickListener() { //if clicked, eye will become open and show answers
            @Override
            public void onClick(View view) {
                eyeopen.setVisibility(View.VISIBLE);
                eyeclosed.setVisibility(View.INVISIBLE);
                flashcardAnswer1.setVisibility(View.VISIBLE);
                flashcardAnswer2.setVisibility(View.VISIBLE);
                flashcardAnswer3.setVisibility(View.VISIBLE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddCardActivity();
            }
        });


    }



    private void goToAddCardActivity(){
        Intent toAddCardActivity = new Intent(this, AddCardActivity.class);
        startActivity(toAddCardActivity);
        startActivityForResult(toAddCardActivity, 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            if (data != null){
                String ques = data.getExtras().getString("question");
                String ans = data.getExtras().getString("answer");
                flashcardQuestion.setText(ques);
                flashcardAnswer.setText(ans);
            }
        }
    }
}