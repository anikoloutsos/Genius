package vncoop.q02;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainGame extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        //INTENTS FROM INITIALIZE

        Intent intent = getIntent();
        int current_team = intent.getIntExtra("current_message", 1);
        int number_of_teams = intent.getIntExtra("number_of_teams", 1);
        parcTeams[] teams = new parcTeams[number_of_teams];
        for (int i=0;i<number_of_teams;i++) {
            teams[i] = (parcTeams) intent.getParcelableExtra("team"+i);
        }



        TextView Omada= (TextView)findViewById(R.id.textViewOmada);

        Log.d("ddddddddddddddd", teams[current_team].get_name());


        Omada.setText(teams[current_team].get_name());

        randGen rg = new randGen();
        String[] epiloges = new String[2];
        epiloges = rg.getRandomCategories(teams[current_team].get_diamonds());
        TextView tv1 = (TextView) findViewById(R.id.TV1);
        tv1.setText(epiloges[0]);
        TextView tv2 = (TextView) findViewById(R.id.TV2);
        if (epiloges[1] != "naS") {

            tv2.setText(epiloges[1]);
        }
        else {
            tv2.setText("FUCK YEAH ITS A DIAMOND");
        }

        DBHelper finder = new DBHelper(getApplicationContext());

        if(epiloges[0] == "geo1"){
            epiloges = finder.randFromCat(1);
        }
        else if(epiloges[0] == "cim1"){
            epiloges = finder.randFromCat(2);
        }
        else if(epiloges[0] == "his1"){
            epiloges = finder.randFromCat(3);
        }
        else if(epiloges[0] == "art1"){
            epiloges = finder.randFromCat(4);
        }
        else if(epiloges[0] == "sci1"){
            epiloges = finder.randFromCat(5);
        }
        else if(epiloges[0] == "spo1"){
            epiloges = finder.randFromCat(6);
        }
        TextView tv3 = (TextView) findViewById(R.id.TV3);
        tv3.setText("ερώτηση: "+ epiloges[0]+ " απαντηση: "+epiloges[1]);
        /*
        if(epiloges[1] == "geo2"){

        }
        if(epiloges[1] == "cim2"){

        }
        if(epiloges[1] == "his2"){

        }
        if(epiloges[1] == "art2"){

        }
        if(epiloges[1] == "sci2"){

        }
        if(epiloges[1] == "spo2"){

        }
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
