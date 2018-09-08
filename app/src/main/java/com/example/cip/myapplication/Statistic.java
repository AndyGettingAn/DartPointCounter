package com.example.cip.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class Statistic extends AppCompatActivity {

    private final List<GameHistory> noteList = new ArrayList<>();
    private ArrayAdapter<GameHistory> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        ListView listView = (ListView) findViewById(R.id.listView);
        DartDbHandler db = new DartDbHandler(this);
        final List<GameHistory> list = db.getAllNotes();
        this.noteList.addAll(list);
        this.listViewAdapter = new ArrayAdapter<>(this,
                R.layout.custom_listview_layout, android.R.id.text1, this.noteList);
        listView.setAdapter(this.listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gameTitle = list.get(position).getNoteTitle();
                String gameContent = list.get(position).getNoteContent();
                int gameId = list.get(position).getNoteId();
                FragmentManager fm = getSupportFragmentManager();
                GameHistoryFragment historyFragment = GameHistoryFragment.newInstance(gameTitle, gameContent, gameId);
                historyFragment.show(fm, "fragment_game_history");
            }
        });
    }

    public void dataChanged (){
        listViewAdapter.notifyDataSetChanged();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}