package com.example.quizu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
                // get the center for the clipping circle
                int cx = flashcardAnswer.getWidth() / 2;
                int cy = flashcardAnswer.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(flashcardAnswer, cx, cy, 0f, finalRadius);

                // hide the question and show the answer to prepare for playing the animation!
                flashcardQuestion.setVisibility(View.VISIBLE);
                flashcardAnswer.setVisibility(View.INVISIBLE);

                anim.setDuration(1000);
                anim.start();
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
                /*
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.flashcard_new_answer)).setText(flashcard.getQuestion());
                ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getAnswer());

                flashcardQuestion.setVisibility(View.VISIBLE);
                flashcardAnswer.setVisibility(View.INVISIBLE);*/


                final Animation leftOutAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.right_in);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                        //findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        findViewById(R.id.flashcard_question).startAnimation(rightInAnim);

                        //moved previous logic here
                        Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                        ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                        ((TextView) findViewById(R.id.flashcard_new_answer)).setText(flashcard.getAnswer());

                        flashcardQuestion.setVisibility(View.VISIBLE);
                        flashcardAnswer.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });

                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);

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

        //Animation testAnimation = AnimationUtils.loadAnimation(this, R.anim.left_out);
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
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
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