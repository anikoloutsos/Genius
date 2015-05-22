package vncoop.q02;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;


public class Winner extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.winning);
        mp.start();

        Intent intent = getIntent();
        int current_team = intent.getIntExtra("current_message", 1);
        int number_of_teams = intent.getIntExtra("number_of_teams", 1);
        parcTeams[] teams = new parcTeams[number_of_teams];
        for (int i=0;i<number_of_teams;i++) {
            teams[i] = intent.getParcelableExtra("team"+i);
        }


        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        TextView winningteam = (TextView) findViewById(R.id.winningTeamTextId);
        TextView statistics_team1 = (TextView) findViewById(R.id.Team1Stats);
        TextView statistics_team2 = (TextView) findViewById(R.id.Team2Stats);
        TextView statistics_team3 = (TextView) findViewById(R.id.Team3Stats);
        TextView statistics_team4 = (TextView) findViewById(R.id.Team4Stats);
        TextView team1 = (TextView) findViewById(R.id.Team1);
        TextView team2 = (TextView) findViewById(R.id.Team2);
        TextView team3 = (TextView) findViewById(R.id.Team3);
        TextView team4 = (TextView) findViewById(R.id.Team4);
        TextView congrats = (TextView) findViewById(R.id.congrats);
        TextView youWin = (TextView) findViewById(R.id.youwin);
        TextView statistics = (TextView) findViewById(R.id.statistika);

        //set font
        winningteam.setText(teams[current_team].get_name());
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
        winningteam.setTypeface(font);
        congrats.setTypeface(font);
        youWin.setTypeface(font);
        statistics.setTypeface(font);

        refitText(winningteam, 40);
        winningteam.requestLayout();
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

            } //eikonitsa dipla se kathe statistiko???
            if(i==0) {
                team1.setText(teams[i].get_name());
                refitText(team1, 20);
                statistics_team1.setText(
                        "Γεωγραφία: "+ (int)percentStats[0] +"% (" + stats_corr[0] +"/" + stats_all[0] + ")"
                        + "\nΨυχαγωγία: "+ (int)percentStats[1] +"% (" + stats_corr[1] +"/" + stats_all[1] + ")"
                        + "\nΙστορία: "+ (int)percentStats[2] +"% (" + stats_corr[2] +"/" + stats_all[2] + ")"
                        + "\nΤέχνες: "+ (int)percentStats[3] +"% (" + stats_corr[3] +"/" + stats_all[3] + ")"
                        + "\nΕπιστήμη: "+ (int)percentStats[4] +"% (" + stats_corr[4] +"/" + stats_all[4] + ")"
                        + "\nΧόμπυ: " + + (int)percentStats[5] +"% (" + stats_corr[5] +"/" + stats_all[5] + ")");
            }
            else if(i==1){
                team2.setText(teams[i].get_name());
                refitText(team2, 25);
                statistics_team2.setText(
                        "Γεωγραφία: "+ (int)percentStats[0] +"% (" + stats_corr[0] +"/" + stats_all[0] + ")"
                                + "\nΨυχαγωγία: "+ (int)percentStats[1] +"% (" + stats_corr[1] +"/" + stats_all[1] + ")"
                                + "\nΙστορία: "+ (int)percentStats[2] +"% (" + stats_corr[2] +"/" + stats_all[2] + ")"
                                + "\nΤέχνες: "+ (int)percentStats[3] +"% (" + stats_corr[3] +"/" + stats_all[3] + ")"
                                + "\nΕπιστήμη: "+ (int)percentStats[4] +"% (" + stats_corr[4] +"/" + stats_all[4] + ")"
                                + "\nΧόμπυ: " + + (int)percentStats[5] +"% (" + stats_corr[5] +"/" + stats_all[5] + ")");
            }else if(i==2){
                team3.setText(teams[i].get_name());
                refitText(team3, 25);
                statistics_team3.setText(
                        "Γεωγραφία: "+ (int)percentStats[0] +"% (" + stats_corr[0] +"/" + stats_all[0] + ")"
                                + "\nΨυχαγωγία: "+ (int)percentStats[1] +"% (" + stats_corr[1] +"/" + stats_all[1] + ")"
                                + "\nΙστορία: "+ (int)percentStats[2] +"% (" + stats_corr[2] +"/" + stats_all[2] + ")"
                                + "\nΤέχνες: "+ (int)percentStats[3] +"% (" + stats_corr[3] +"/" + stats_all[3] + ")"
                                + "\nΕπιστήμη: "+ (int)percentStats[4] +"% (" + stats_corr[4] +"/" + stats_all[4] + ")"
                                + "\nΧόμπυ: " + + (int)percentStats[5] +"% (" + stats_corr[5] +"/" + stats_all[5] + ")");
            }else if(i==3){
                team4.setText(teams[i].get_name());
                refitText(team4, 25);
                statistics_team4.setText(
                        "Γεωγραφία: "+ (int)percentStats[0] +"% (" + stats_corr[0] +"/" + stats_all[0] + ")"
                                + "\nΨυχαγωγία: "+ (int)percentStats[1] +"% (" + stats_corr[1] +"/" + stats_all[1] + ")"
                                + "\nΙστορία: "+ (int)percentStats[2] +"% (" + stats_corr[2] +"/" + stats_all[2] + ")"
                                + "\nΤέχνες: "+ (int)percentStats[3] +"% (" + stats_corr[3] +"/" + stats_all[3] + ")"
                                + "\nΕπιστήμη: "+ (int)percentStats[4] +"% (" + stats_corr[4] +"/" + stats_all[4] + ")"
                                + "\nΧόμπυ: " + + (int)percentStats[5] +"% (" + stats_corr[5] +"/" + stats_all[5] + ")");
            }
        }

    }


    public void telos(View view){
        finish();
    }

    public int getDisplaywidth(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if(height<=width){width=height;}
        return width;
    }


    public void refitText(TextView tv,float maxTextSize) {
        tv.measure(0, 0);
        int width=getDisplaywidth();
        int textWidth =tv.getMeasuredWidth();

        int availableWidth = width;
        float trySize = maxTextSize;

        while (textWidth > availableWidth) {
            trySize -= 1;
            tv.setTextSize(trySize);
            tv.measure(0, 0);
            textWidth =tv.getMeasuredWidth();
            Log.d("textwidth " + textWidth, "textsize " + trySize);
            //tv.requestLayout();
        }

        tv.setTextSize(trySize);

    }

}


