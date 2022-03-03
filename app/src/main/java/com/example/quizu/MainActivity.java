package com.example.quizu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcardQuestion = findViewById(R.id.flashcard_question);
        TextView flashcardAnswer1 = findViewById(R.id.flashcard_answer);
        TextView flashcardAnswer2 = findViewById(R.id.flashcard_answer2);
        TextView flashcardAnswer3 = findViewById(R.id.flashcard_answer3);
        ImageView eyeopen = findViewById(R.id.toggle_choices_visibility);
        ImageView eyeclosed = findViewById(R.id.toggle_choices_invisibility);

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




    }
}