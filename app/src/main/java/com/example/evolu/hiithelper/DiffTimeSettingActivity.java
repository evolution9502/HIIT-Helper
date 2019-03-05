package com.example.evolu.hiithelper;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
    private int moveCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_time_setting);
        addedMovesView = findViewById(R.id.move_list);
        registerForContextMenu(addedMovesView);
        moves = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.sp_move_type);
        initialDeviceAdapter();
        timeInput = findViewById(R.id.et_move_time);
        add_move = findViewById(R.id.btn_add_move);
        add_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(timeInput.getText().toString())>0) {
                    moveCount++;
                    addMoves();
                }
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
        spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.action_spinner_item, list);
        //set Adapter to Spinner
        spinner.setAdapter(spinnerAdapter);

        movesAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.exercise_list_item, moves) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                if (movesAdapter.getItem(position).contains("運動")) {
//                    convertView.setBackgroundColor(Color.BLUE);
//                } else if (movesAdapter.getItem(position).contains("休息")) {
//                    convertView.setBackgroundColor(Color.GREEN);
//                }

                View row = super.getView(position, convertView, parent);

                if(getItem(position).contains("運動")) {
                    // do something change color
                    row.setBackgroundColor (Color.BLUE); // some color
                } else {
                    // default state
                    row.setBackgroundColor (Color.WHITE); // default coloe
                }
                return super.getView(position, convertView, parent);
            }
        };
        addedMovesView.setAdapter(movesAdapter);
    }

    public void addMoves() {
        moves.add(moveCount+"."+spinner.getSelectedItem().toString()+":"+timeInput.getText().toString()+"秒");

        movesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.move_list) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.delete:
                // remove stuff here
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
