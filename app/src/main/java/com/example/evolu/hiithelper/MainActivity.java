package com.example.evolu.hiithelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button toFixedTimer;
    Button toDiffTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toFixedTimer = (Button) findViewById(R.id.btn_to_fixed);
        toDiffTimer = (Button) findViewById(R.id.btn_to_diff);
        toFixedTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, FixedTimeSettingActivity.class);
                startActivity(i);
            }
        });
        toDiffTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, DiffTimeSettingActivity.class);
                startActivity(i);
                //Toast.makeText(MainActivity.this, "此項功能尚未做好，近期更新，請耐心等候...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
