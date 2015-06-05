package vncoop.genius;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;


public class Winner extends Activity {
    private int fileIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.winning);
        mp.start();

        Intent intent = getIntent();
        int current_team = intent.getIntExtra("current_message", 1);
        int number_of_teams = intent.getIntExtra("number_of_teams", 1);
        Teams[] teams = new Teams[number_of_teams];
        for (int i=0;i<number_of_teams;i++) {
            teams[i] = intent.getParcelableExtra("team"+i);
        }
        fileIndex = intent.getIntExtra("file_index", 0);


        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        TextView statistics_team1 = (TextView) findViewById(R.id.Team1Stats);
        TextView statistics_team2 = (TextView) findViewById(R.id.Team2Stats);
        TextView statistics_team3 = (TextView) findViewById(R.id.Team3Stats);
        TextView statistics_team4 = (TextView) findViewById(R.id.Team4Stats);
        TextView team1 = (TextView) findViewById(R.id.Team1);
        TextView team2 = (TextView) findViewById(R.id.Team2);
        TextView team3 = (TextView) findViewById(R.id.Team3);
        TextView team4 = (TextView) findViewById(R.id.Team4);
        //TextView youWin = (TextView) findViewById(R.id.youwin);
        TextView statistics = (TextView) findViewById(R.id.statistika);
        ImageView prize = (ImageView) findViewById(R.id.prize);
        TextView winningteam = (TextView) findViewById(R.id.winningTeamTextId);
        TextView congrats = (TextView) findViewById(R.id.congrats);
        ImageView separator = (ImageView) findViewById(R.id.seperator);
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

        double[] screen = BasicMethods.getScreenChar(this);
        double Left, Top, Right, Bottom;

        //Prize Image Margins
        Top = 0.03*screen[1];
        Bottom = 0.8*screen[1];
        Left = (((screen[1]-Top-Bottom)*105.0/108.0)/2.0);
        BasicMethods.setMargins(prize,(int) Left, (int) Top,(int) Left,(int) Bottom);

        //Team Name Margins
        winningteam.setText(teams[current_team].get_name());
        winningteam.setTypeface(font);
        statistics.setTextSize((float) ((0.05 / screen[2]) * screen[1]));
        winningteam.setTextSize((float) ((0.067 / screen[2]) * screen[1]));
        BasicMethods.refitText(winningteam,(float) ((0.067 / screen[2]) * screen[1]),(int)(0.9*screen[0]));
        Top = 0.2*screen[1];
        Bottom = (0.7+0.03)*screen[1];
        Left = 0.05*screen[0];
        BasicMethods.setMargins(winningteam,(int) Left, (int) Top,(int) Left,(int) Bottom);

        //Congratulations Text Margins
        congrats.setTypeface(font);
        congrats.setTextSize((float) ((0.067 / screen[2]) * screen[1]));
        Top = (0.3-0.03)*screen[1];
        Bottom = (0.6+0.04)*screen[1];
        BasicMethods.setMargins(congrats,(int) Left, (int) Top,(int) Left,(int) Bottom);

        //Separator Margins
        Top = (0.4-0.04)*screen[1];
        Bottom =(0.58125+0.04)*screen[1];
        BasicMethods.setMargins(separator,(int) Left, (int) Top,(int) Left,(int) Bottom);

        //Home Button Margins
        Top = 0.85*screen[1];
        Bottom = 0.03*screen[1];
        BasicMethods.setMargins(homeButton,(int) Left, (int) Top,(int) Left,(int) Bottom);


        //ScrollView Margins
        Top = (0.41875-0.04)*screen[1];
        Bottom = 0.15*screen[1];
        BasicMethods.setMargins(scrollView,(int) Left, (int) Top,(int) Left,(int) Bottom);

        //set font

        team1.setTypeface(font);
        team1.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        team2.setTypeface(font);
        team2.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        team3.setTypeface(font);
        team3.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        team4.setTypeface(font);
        team4.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        statistics_team1.setTypeface(font);
        statistics_team2.setTypeface(font);
        statistics_team3.setTypeface(font);
        statistics_team4.setTypeface(font);

        //youWin.setTypeface(font);
        statistics.setTypeface(font);


        //telos


        //Statistics
        for(int i=0;i<number_of_teams;i++){
            float[] percentStats = new float[6];
            int[] stats_all = new int[6];
            int[] stats_corr = new int[6];
            for(int j=0;j<6;j++){
                percentStats[j] = 100*teams[i].get_category_stats(6+j)/teams[i].get_category_stats(j);
                stats_all[j] = (int) teams[i].get_category_stats(j);
                stats_corr[j] = (int) teams[i].get_category_stats(6+j);

            }
            if(i==0) {
                team1.setText(teams[i].get_name());
                team1.setTextSize((float) ((0.05 / screen[2]) * screen[1]));
                BasicMethods.refitText(team1, (float) ((0.05 / screen[2]) * screen[1]),(int)(0.9*screen[0]));

                statistics_team1.setText("Γεωγραφία: "+ (int)percentStats[0] +"% (" + stats_corr[0] +"/" + stats_all[0] + ")"
                        + "\nΨυχαγωγία: "+ (int)percentStats[1] +"% (" + stats_corr[1] +"/" + stats_all[1] + ")"
                        + "\nΙστορία: "+ (int)percentStats[2] +"% (" + stats_corr[2] +"/" + stats_all[2] + ")"
                        + "\nΤέχνες: "+ (int)percentStats[3] +"% (" + stats_corr[3] +"/" + stats_all[3] + ")"
                        + "\nΕπιστήμη: "+ (int)percentStats[4] +"% (" + stats_corr[4] +"/" + stats_all[4] + ")"
                        + "\nΧόμπυ: " + + (int)percentStats[5] +"% (" + stats_corr[5] +"/" + stats_all[5] + ")");
                statistics_team1.setTextSize((float) ((0.045 / screen[2]) * screen[1]));
            }
            else if(i==1){
                team2.setText(teams[i].get_name());
                team2.setTextSize((float) ((0.05 / screen[2]) * screen[1]));
                BasicMethods.refitText(team2, (float) ((0.05 / screen[2]) * screen[1]),(int)(0.9*screen[0]));

                statistics_team2.setText(
                        "Γεωγραφία: "+ (int)percentStats[0] +"% (" + stats_corr[0] +"/" + stats_all[0] + ")"
                                + "\nΨυχαγωγία: "+ (int)percentStats[1] +"% (" + stats_corr[1] +"/" + stats_all[1] + ")"
                                + "\nΙστορία: "+ (int)percentStats[2] +"% (" + stats_corr[2] +"/" + stats_all[2] + ")"
                                + "\nΤέχνες: "+ (int)percentStats[3] +"% (" + stats_corr[3] +"/" + stats_all[3] + ")"
                                + "\nΕπιστήμη: "+ (int)percentStats[4] +"% (" + stats_corr[4] +"/" + stats_all[4] + ")"
                                + "\nΧόμπυ: " + + (int)percentStats[5] +"% (" + stats_corr[5] +"/" + stats_all[5] + ")");
                statistics_team2.setTextSize((float) ((0.045 / screen[2]) * screen[1]));
            }else if(i==2){
                team3.setText(teams[i].get_name());
                team3.setTextSize((float) ((0.05 / screen[2]) * screen[1]));
                BasicMethods.refitText(team3, (float) ((0.05 / screen[2]) * screen[1]),(int)(0.9*screen[0]));
                statistics_team3.setText(
                        "Γεωγραφία: "+ (int)percentStats[0] +"% (" + stats_corr[0] +"/" + stats_all[0] + ")"
                                + "\nΨυχαγωγία: "+ (int)percentStats[1] +"% (" + stats_corr[1] +"/" + stats_all[1] + ")"
                                + "\nΙστορία: "+ (int)percentStats[2] +"% (" + stats_corr[2] +"/" + stats_all[2] + ")"
                                + "\nΤέχνες: "+ (int)percentStats[3] +"% (" + stats_corr[3] +"/" + stats_all[3] + ")"
                                + "\nΕπιστήμη: "+ (int)percentStats[4] +"% (" + stats_corr[4] +"/" + stats_all[4] + ")"
                                + "\nΧόμπυ: " + + (int)percentStats[5] +"% (" + stats_corr[5] +"/" + stats_all[5] + ")");
                statistics_team3.setTextSize((float) ((0.045 / screen[2]) * screen[1]));
                team3.setVisibility(View.VISIBLE);
                statistics_team3.setVisibility(View.VISIBLE);
            }else if(i==3){
                team4.setText(teams[i].get_name());
                team4.setTextSize((float) ((0.05 / screen[2]) * screen[1]));
                BasicMethods.refitText(team4, (float) ((0.05 / screen[2]) * screen[1]),(int)(0.9*screen[0]));
                statistics_team4.setText(
                        "Γεωγραφία: "+ (int)percentStats[0] +"% (" + stats_corr[0] +"/" + stats_all[0] + ")"
                                + "\nΨυχαγωγία: "+ (int)percentStats[1] +"% (" + stats_corr[1] +"/" + stats_all[1] + ")"
                                + "\nΙστορία: "+ (int)percentStats[2] +"% (" + stats_corr[2] +"/" + stats_all[2] + ")"
                                + "\nΤέχνες: "+ (int)percentStats[3] +"% (" + stats_corr[3] +"/" + stats_all[3] + ")"
                                + "\nΕπιστήμη: "+ (int)percentStats[4] +"% (" + stats_corr[4] +"/" + stats_all[4] + ")"
                                + "\nΧόμπυ: " + + (int)percentStats[5] +"% (" + stats_corr[5] +"/" + stats_all[5] + ")");
                statistics_team4.setTextSize((float) ((0.045 / screen[2]) * screen[1]));
                team4.setVisibility(View.VISIBLE);
                statistics_team4.setVisibility(View.VISIBLE);
            }
        }
        //delete save file
        String[] FILE = new String[3];
        FILE[0] = "/data/data/vncoop.genius/databases/savegame1";
        FILE[1] = "/data/data/vncoop.genius/databases/savegame2";
        FILE[2]= "/data/data/vncoop.genius/databases/savegame3";

        File f = new File(FILE[fileIndex]);
        f.delete();
        //ews edw

    }


    public void telos(View view){
        finish();
    }

}


