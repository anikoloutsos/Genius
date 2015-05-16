package vncoop.q02;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Winner extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);


        Intent intent = getIntent();
        int current_team = intent.getIntExtra("current_message", 1);
        int number_of_teams = intent.getIntExtra("number_of_teams", 1);
        parcTeams[] teams = new parcTeams[number_of_teams];
        for (int i=0;i<number_of_teams;i++) {
            teams[i] = intent.getParcelableExtra("team"+i);
        }


        TextView tv1 = (TextView) findViewById(R.id.winningTeamTextId);
        tv1.setText(teams[current_team].get_name());

        TextView statistics_team1 = (TextView) findViewById(R.id.statsT1id);
        TextView statistics_team2 = (TextView) findViewById(R.id.statsT2id);

        //Statistics
        for(int i=0;i<number_of_teams;i++){
            float[] percentStats = new float[6];
            for(int j=0;j<6;j++){
                percentStats[j] = 100*teams[i].get_category_stats(6+j)/teams[i].get_category_stats(j);

            } //eikonitsa dipla se kathe statistiko???
            if(i==0) {
                statistics_team1.setText(teams[i].get_name() + "\nΓεωγραφία:" + percentStats[0] + "\nΣινεμά/Μουσική:" + percentStats[1]);
            }
            else if(i==1){
                statistics_team2.setText(teams[i].get_name() + "\nΓεωγραφία:" + percentStats[0] + "\nΣινεμά/Μουσική:" + percentStats[1]);
            }
        }

    }


    public void telos(View view){
        finish();
    }

}
