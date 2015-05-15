package vncoop.q02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;




public class ChooseNumOfPlayers extends ActionBarActivity {
    private RadioGroup radioGroup;
    public int number_of_teams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_num_of_players);


        TextView RithmiseisTxt = (TextView)findViewById(R.id.textView);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        RithmiseisTxt.setTypeface(font);


         /* Initialize Radio Group and attach click handler */
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup_id);
        //radioGroup.clearCheck();
    }
//////////////////////////


////ΠΙΣΩ ΚΟΥΜΠΙ
    public void back_click(View view){
        finish();
    }
////ΕΠΟΜΕΝΟ ΚΟΥΜΠΙ ΣΤΕΙΛΕ ΣΤΟ INIT_TEAMS ΤΟ NUMBER OF PLAYERS
    public void next_click(View view){
        Intent nextClick = new Intent(this, InitTeams.class);
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        number_of_teams=Integer.parseInt(rb.getTag().toString());
        nextClick.putExtra("n_team_message",number_of_teams);
        startActivity(nextClick);
        finish();
    }
    ///////////////////////


}
