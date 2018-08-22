package com.example.cip.myapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {
    //implements NavigationView.OnNavigationItemSelectedListener {
    //ALLE KOMMENTARE STAMMEN AUS EINER VORLAGE AUS DEM INTERNET. EVENTUELL BRAUCHEN WIR SIE JA NOCH


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
/*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                TextView mainMessage = (TextView) findViewById(R.id.mainText);

                if (id == R.id.nav_home) {
                    mainMessage.setText("Du hast auf Home gedrückt, Wilkommen auf dem Homebildschirm");
                } else if (id == R.id.nav_counter) {
                    mainMessage.setText("Du hast auf Punktezähler gedrückt");
                    //Test Dartboard
                    //Intent intent = new Intent(MainActivity.this, Dartboard.class);
                   Intent intent = new Intent(MainActivity.this, PointsCounter.class);
                    startActivity(intent);
                } else if (id == R.id.nav_statistic) {
                    mainMessage.setText("Du hast auf Statistik gedrückt");
                    Intent intent = new Intent(MainActivity.this, Statistic.class);
                    startActivity(intent);

                }

               // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
               // drawer.closeDrawer(GravityCompat.START);
                return true;
            }


        });

    }



/*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // VORLAGE VON ANDROID STUDIO

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

        /*int id = item.getItemId();

        if (id == R.id.action_next) {
            do sth.
        }

        return super.onOptionsItemSelected(item);
    }*/

}
