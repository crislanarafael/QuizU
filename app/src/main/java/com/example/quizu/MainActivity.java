package com.example.quizu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView flashcardQuestion;
    TextView flashcardAnswer;
    TextView flashcardAnswer1;
    TextView flashcardAnswer2;
    TextView flashcardAnswer3;
    ImageView eyeopen;
    ImageView eyeclosed;
    ImageView add;
    ImageView edit;
    ImageView next;

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //week 5
        next = findViewById(R.id.next_icon);
        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_new_answer)).setText(allFlashcards.get(0).getAnswer());
        }
        //end week 5


        flashcardQuestion = findViewById(R.id.flashcard_question);
        flashcardAnswer = findViewById(R.id.flashcard_new_answer);
        //flashcardAnswer1 = findViewById(R.id.flashcard_answer);
        //flashcardAnswer2 = findViewById(R.id.flashcard_answer2);
        //flashcardAnswer3 = findViewById(R.id.flashcard_answer3);
        eyeopen = findViewById(R.id.toggle_choices_visibility);
        eyeclosed = findViewById(R.id.toggle_choices_invisibility);
        add = findViewById(R.id.add_icon);
        edit = findViewById(R.id.edit_icon);



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.v("string", (String) flashcardQuestion.getText());
                //Log.v("string", (String) flashcardAnswer.getText());
                String one = flashcardQuestion.getText().toString();
                String two = flashcardAnswer.getText().toString();

                Intent intent = new Intent(MainActivity.this, AddCardActivity.class); // create a new Intent, this is where we will put our data
                intent.putExtra("EditQuestion", one); // puts one string into the Intent, with the key as 'string1'
                intent.putExtra("EditAnswer", two); // puts another string into the Intent, with the key as 'string2
                //startActivity(data);
                setResult(100, intent); // set result code and bundle data for response
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFlashcards.size() == 0){
                    return;
                }
                currentCardDisplayedIndex++;

                if (currentCardDisplayedIndex >= allFlashcards.size()){
                    currentCardDisplayedIndex = 0;
                }

                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getAnswer());
                ((TextView) findViewById(R.id.flashcard_new_answer)).setText(flashcard.getQuestion());
            }
        });

        /*flashcardAnswer1.setOnClickListener(new View.OnClickListener() {
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
        });*/

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

                flashcardDatabase.insertCard(new Flashcard(ques, ans));
                allFlashcards = flashcardDatabase.getAllCards();
            }
        }
    }
}