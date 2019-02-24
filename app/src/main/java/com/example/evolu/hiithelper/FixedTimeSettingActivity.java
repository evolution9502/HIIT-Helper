package com.example.evolu.hiithelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.CALM_TIME;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.CIRCLES;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.EXERCISE_TIME;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.MOVE_COUNT;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.PREPARE_TIME;
import static com.example.evolu.hiithelper.Utlities.ReferenceStrings.REST_TIME;

public class FixedTimeSettingActivity extends AppCompatActivity {
    EditText prepareTime, exerciseTime, restTime, calmTime, moveCount, circles;
    Button goExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_time_setting);
        prepareTime = findViewById(R.id.et_prepare_time);
        exerciseTime = findViewById(R.id.et_exercise_time);
        restTime = findViewById(R.id.et_rest_time);
        calmTime = findViewById(R.id.et_calm_time);
        moveCount = findViewById(R.id.et_move_num);
        circles = findViewById(R.id.et_circle_num);
        goExercise = findViewById(R.id.btn_go);

        goExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FixedTimeSettingActivity.this, ExerciseActivity.class);
                i.putExtra(PREPARE_TIME, prepareTime.getText().toString());
                i.putExtra(EXERCISE_TIME, exerciseTime.getText().toString());
                i.putExtra(REST_TIME, restTime.getText().toString());
                i.putExtra(CALM_TIME, calmTime.getText().toString());
                i.putExtra(MOVE_COUNT, moveCount.getText().toString());
                i.putExtra(CIRCLES, circles.getText().toString());
                startActivity(i);
            }
        });
    }
}
