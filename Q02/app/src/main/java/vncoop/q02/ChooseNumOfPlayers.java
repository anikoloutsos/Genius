package vncoop.q02;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;




public class ChooseNumOfPlayers extends ActionBarActivity {
    private RadioGroup radioGroup;
    public int number_of_teams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_num_of_players);


         /* Initialize Radio Group and attach click handler */
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup_id);
        radioGroup.clearCheck();

        /* Attach CheckedChangeListener to radio group */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){
                    Toast.makeText(ChooseNumOfPlayers.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });
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
        number_of_teams=Integer.parseInt(rb.getText().toString());
        nextClick.putExtra("n_team_message",number_of_teams);
        startActivity(nextClick);
        finish();
    }
    ///////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_num_of_players, menu);
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
