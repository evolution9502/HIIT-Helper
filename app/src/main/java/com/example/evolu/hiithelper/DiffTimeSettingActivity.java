package com.example.evolu.hiithelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiffTimeSettingActivity extends AppCompatActivity {
    private Spinner spinner;
    private ListView addedMovesView;
    private Button add_move, start_exercise;
    private EditText timeInput;
    protected ArrayList<String> list, originalSpeakerList;
    private ArrayAdapter<String> spinnerAdapter, movesAdapter;
    private ArrayList<String> moves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_time_setting);
        addedMovesView = findViewById(R.id.move_list);
        moves = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.sp_move_type);
        initialDeviceAdapter();
        timeInput = findViewById(R.id.et_move_time);
        add_move = findViewById(R.id.btn_add_move);
        add_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoves();
            }
        });
    }


    private void initialDeviceAdapter() {
        //create arraylist
        list = new ArrayList<String>();
        list.add(getResources().getString(R.string.exercise_move));
        list.add(getResources().getString(R.string.rest_move));
        list.add(getResources().getString(R.string.prepare_move));
        list.add(getResources().getString(R.string.calm_move));
        //create adapter (Context，layout，List<>)
        spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        movesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, moves);
        //set Adapter to Spinner
        spinner.setAdapter(spinnerAdapter);
        addedMovesView.setAdapter(movesAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    if (spinner.getSelectedItem().toString().contains("Direct")) {
//
//                    } else {
//
//                    }
//                } catch (NullPointerException e) {
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });
    }

    public void addMoves() {
        moves.add(spinner.getSelectedItem().toString()+":"+timeInput.getText().toString()+"秒");
        movesAdapter.notifyDataSetChanged();
    }
}
