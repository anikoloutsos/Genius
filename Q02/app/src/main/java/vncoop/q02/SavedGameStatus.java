package vncoop.q02;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class SavedGameStatus extends ActionBarActivity {


    int current_team;
    int number_of_teams;
    parcTeams[] teams;


    Typeface font;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_saved_game_status);

        Intent intent = getIntent();
        current_team = intent.getIntExtra("current_message", 1);
        number_of_teams = intent.getIntExtra("number_of_teams", 1);
        teams = new parcTeams[number_of_teams];
        for (int i = 0; i < number_of_teams; i++) {
            teams[i] = intent.getParcelableExtra("team" + i);
        }



        //Εμφάνιση Διαμαντιων ομάδας\\
        ImageView[] diamond_images;
        diamond_images = new ImageView[24];

        diamond_images[0] = (ImageView) findViewById(R.id.team1blueDiamId);
        diamond_images[1] = (ImageView) findViewById(R.id.team1pinkDiamId);
        diamond_images[2] = (ImageView) findViewById(R.id.team1redDiamId);
        diamond_images[3] = (ImageView) findViewById(R.id.team1purpleDiamId);
        diamond_images[4] = (ImageView) findViewById(R.id.team1greenDiamId);
        diamond_images[5] = (ImageView) findViewById(R.id.team1yellowDiamId);
        diamond_images[6] = (ImageView) findViewById(R.id.team2blueDiamId);
        diamond_images[7] = (ImageView) findViewById(R.id.team2pinkDiamId);
        diamond_images[8] = (ImageView) findViewById(R.id.team2redDiamId);
        diamond_images[9] = (ImageView) findViewById(R.id.team2purpleDiamId);
        diamond_images[10] = (ImageView) findViewById(R.id.team2greenDiamId);
        diamond_images[11] = (ImageView) findViewById(R.id.team2yellowDiamId);
        diamond_images[12] = (ImageView) findViewById(R.id.team3blueDiamId);
        diamond_images[13] = (ImageView) findViewById(R.id.team3pinkDiamId);
        diamond_images[14] = (ImageView) findViewById(R.id.team3redDiamId);
        diamond_images[15] = (ImageView) findViewById(R.id.team3purpleDiamId);
        diamond_images[16] = (ImageView) findViewById(R.id.team3greenDiamId);
        diamond_images[17] = (ImageView) findViewById(R.id.team3yellowDiamId);
        diamond_images[18] = (ImageView) findViewById(R.id.team4blueDiamId);
        diamond_images[19] = (ImageView) findViewById(R.id.team4pinkDiamId);
        diamond_images[20] = (ImageView) findViewById(R.id.team4redDiamId);
        diamond_images[21] = (ImageView) findViewById(R.id.team4purpleDiamId);
        diamond_images[22] = (ImageView) findViewById(R.id.team4greenDiamId);
        diamond_images[23] = (ImageView) findViewById(R.id.team4yellowDiamId);




        font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        TextView[] allTeamsTxt = new TextView[4];
        allTeamsTxt[0] = (TextView) findViewById(R.id.team1NameId);
        allTeamsTxt[1] = (TextView) findViewById(R.id.team2NameId);
        allTeamsTxt[2] = (TextView) findViewById(R.id.team3NameId);
        allTeamsTxt[3] = (TextView) findViewById(R.id.team4NameId);

        ImageView[] allTeamsDiamondTables = new ImageView[4];
        allTeamsDiamondTables[0] = (ImageView) findViewById(R.id.team1DiamId);
        allTeamsDiamondTables[1] = (ImageView) findViewById(R.id.team2DiamId);
        allTeamsDiamondTables[2] = (ImageView) findViewById(R.id.team3DiamId);
        allTeamsDiamondTables[3] = (ImageView) findViewById(R.id.team4DiamId);

        for (int i=0;i<number_of_teams;i++){
            allTeamsTxt[i].setTypeface(font);
            allTeamsTxt[i].setText(teams[i].get_name());
            allTeamsTxt[i].setVisibility(View.VISIBLE);
            allTeamsDiamondTables[i].setVisibility(View.VISIBLE);
        }

        for (int j =0; j<number_of_teams;j++) {

            for (int i = 0; i < 6; i++) {
                if (teams[j].get_diamonds()[i]) {
                    diamond_images[i+6*j].setVisibility(View.VISIBLE);
                }
            }
        }

        allTeamsTxt[current_team].setTextColor(Color.CYAN);

    }


    public void back_click(View view){
        finish();
    }
    public void MoveToMainGame(View view) {

        Intent mg = new Intent(this, MainGame.class);

        mg.putExtra("number_of_teams", number_of_teams);
        mg.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_teams;i++) {
            mg.putExtra("team" + i, (java.io.Serializable) teams[i]);
        }

        startActivity(mg);

        finish();

    }



}
