package com.example.quizu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCardActivity extends AppCompatActivity {

    EditText question;
    EditText answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card2);

        ImageView cancel = findViewById(R.id.cancel_button);
        ImageView save = findViewById(R.id.save_button);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);

        //Load animation from XML
        Animation upAnimation = AnimationUtils.loadAnimation(this, R.anim.up_out);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = question.getText().toString();
                String str2 = answer.getText().toString();

                Log.v("String", str1);
                Log.v("String", str2);

                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("question", str1); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("answer", str2); // puts another string into the Intent, with the key as 'string2
                //startActivity(data);
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == 100){
            if (intent != null){
                String ques = intent.getExtras().getString("EditQuestion");
                String ans = intent.getExtras().getString("EditAnswer");
                Log.v("hi", ques);
                Log.v("ho", ques);
                question.setText(ques);
                answer.setText(ans);
            }
        }
    }



   
}