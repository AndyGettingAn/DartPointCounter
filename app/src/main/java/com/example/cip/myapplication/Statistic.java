package com.example.cip.myapplication;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Statistic extends AppCompatActivity {


    private ListView listView;
    private final List<GameHistory> noteList = new ArrayList<GameHistory>();
    private ArrayAdapter<GameHistory> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        listView = (ListView) findViewById(R.id.listView);
        DartDbHandler db = new DartDbHandler(this);
        //db.createDefaultNotesIfNeed();
        List<GameHistory> list = db.getAllNotes();
        this.noteList.addAll(list);
        this.listViewAdapter = new ArrayAdapter<GameHistory>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.noteList);
        this.listView.setAdapter(this.listViewAdapter);
        //registerForContextMenu(this.listView);
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo)    {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Select The Action");

        // groupId, itemId, order, title
        menu.add(0, 11 , 0, "View Note");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final GameHistory selectedNote = (GameHistory) this.listView.getItemAtPosition(info.position);
        Toast.makeText(getApplicationContext(), selectedNote.getNoteContent(), Toast.LENGTH_LONG).show();
        return true;
    }*/
}