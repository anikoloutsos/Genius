package vncoop.q02;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainGame extends ActionBarActivity {


    String[] epiloges = new String[2];
    int current_team;
    int number_of_teams;
    parcTeams[] teams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);


        //INTENTS FROM INITIALIZE
        Intent intent = getIntent();
        current_team = intent.getIntExtra("current_message", 1);
        number_of_teams = intent.getIntExtra("number_of_teams", 1);
        teams = new parcTeams[number_of_teams];
        for (int i=0;i<number_of_teams;i++) {
            teams[i] = (parcTeams) intent.getParcelableExtra("team"+i);
        }

        //Τυχαία επιλογή κατηγοριών ή διαμαντιών\\
        randGen rg = new randGen();
        epiloges = rg.getRandomCategories(teams[current_team].get_diamonds());


        //Εμφάνιση ονόματος ομάδας\\
        TextView Omada= (TextView)findViewById(R.id.textViewOmada);
        Omada.setText(teams[current_team].get_name());

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

    public void getCatOrD(View view){
        ImageButton firstbtn = (ImageButton) findViewById(R.id.btnCat1Id);
        ImageButton secondbtn = (ImageButton) findViewById(R.id.btnCat2Id);
        ImageButton diamondbtn = (ImageButton) findViewById(R.id.btnDiaId);

        //Context context = getApplicationContext();
        //Drawable d1 = R.drawable.;

        if (epiloges[1].equals("naS")){
            int didd = getResources().getIdentifier(epiloges[0],"drawable",getPackageName());
            diamondbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
            diamondbtn.setImageResource(didd);
            diamondbtn.setVisibility(View.VISIBLE);
        }
        else {
            int did1 = getResources().getIdentifier(epiloges[0], "drawable", getPackageName());
            firstbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
            firstbtn.setImageResource(did1);
            firstbtn.setVisibility(View.VISIBLE);

            int did2 = getResources().getIdentifier(epiloges[1], "drawable", getPackageName());
            secondbtn.setScaleType(ImageButton.ScaleType.FIT_XY);
            secondbtn.setImageResource(did2);
            secondbtn.setVisibility(View.VISIBLE);
        }







    }

    public void choicebtn1(View view){
        int categoryNum;
        Intent intent = new Intent(this, QuestionScreen.class);
        switch (epiloges[0]) {
            case "geo_b":
                categoryNum = 1;
                break;
            case "cim_b":
                categoryNum = 2;
                break;
            case "his_b":
                categoryNum = 3;
                break;
            case "art_b":
                categoryNum = 4;
                break;
            case "sci_b":
                categoryNum = 5;
                break;
            default:
                categoryNum = 6;
                break;
        }

        intent.putExtra("number_of_teams", number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team" + i, teams[i]);
        }
        intent.putExtra("categoryNum",categoryNum);
        intent.putExtra("isDiamond", false);

        startActivity(intent);
        finish();
    }

    public void choicebtn2(View view){
        int categoryNum;
        Intent intent = new Intent(this, QuestionScreen.class);
        switch (epiloges[1]) {
            case "geo_b":
                categoryNum = 1;
                break;
            case "cim_b":
                categoryNum = 2;
                break;
            case "his_b":
                categoryNum = 3;
                break;
            case "art_b":
                categoryNum = 4;
                break;
            case "sci_b":
                categoryNum = 5;
                break;
            default:
                categoryNum = 6;
                break;
        }

        intent.putExtra("number_of_teams",number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team"+i,teams[i]);
        }
        intent.putExtra("categoryNum",categoryNum);
        intent.putExtra("isDiamond", false);

        startActivity(intent);
        finish();
    }

    public void diamondbtn(View view){
        int categoryNum;

        Intent intent = new Intent(this, QuestionScreen.class);
        switch (epiloges[0]) {
            case "geo":
                categoryNum = 1;
                break;
            case "cim":
                categoryNum = 2;
                break;
            case "his":
                categoryNum = 3;
                break;
            case "art":
                categoryNum = 4;
                break;
            case "sci":
                categoryNum = 5;
                break;
            default:
                categoryNum = 6;
                break;
        }

        intent.putExtra("number_of_teams",number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team"+i,teams[i]);
        }
        intent.putExtra("categoryNum",categoryNum);
        intent.putExtra("isDiamond", true);

        startActivity(intent);
        finish();
    }

}

