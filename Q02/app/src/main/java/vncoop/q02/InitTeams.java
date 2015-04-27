package vncoop.q02;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class InitTeams extends ActionBarActivity {
    private int number_of_players;
    private RadioGroup radioGroup;


    EditText[] b = new EditText[6];
    parcTeams[] teams = new parcTeams[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_teams);
        getSupportActionBar().hide();

        //GIA NA MIN VGAZEI KATEFTHIAN TO PLIKTROLOGIO NA GRAPSEIS ONOMA
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        /////////DECLARATIONS////////////
        Intent intent = getIntent();

        number_of_players = intent.getIntExtra("n_team_message", 1);

        final int[] lastChecked = new int[number_of_players];

        for (int i = 0; i < number_of_players; i++) {
            lastChecked[i] = 0;
            teams[i] = new parcTeams();
            //Log.d("----------------",teams[0].name);
        }


        ////////////////////////////////


        //////////////SET EDIT TEXT VISIBLE//////////////
        for (int i = 1; i <= number_of_players; i++) {
            String buttonID = "textView" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            b[i - 1] = (EditText) findViewById(resID);
            b[i - 1].setVisibility(View.VISIBLE);
            teams[i - 1].set_name(b[i - 1].getHint().toString());
            //Log.d("----------------",b.getHint().toString());
        }

        for (int i = 1; i <= number_of_players; i++) {
            String buttonID2 = "radioGroup" + i;
            int resID2 = getResources().getIdentifier(buttonID2, "id", getPackageName());
            View b2 = findViewById(resID2);
            b2.setVisibility(View.VISIBLE);
        }
        ///////////////////////////////////////////////


        /////////////////////////SET RADIO GROUP LISTENERS//////////////////////////
        for (int k = 1; k <= number_of_players; k++) {


            String radioGroups = "radioGroup" + k;

            int RGID = getResources().getIdentifier(radioGroups, "id", getPackageName());

            /* Initialize Radio Group and attach click handler */
            radioGroup = (RadioGroup) findViewById(RGID);

            radioGroup.clearCheck();
            /* Attach CheckedChangeListener to radio group */
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {


                    RadioButton rb = (RadioButton) group.findViewById(checkedId);

                    if (null != rb && checkedId > -1) {

                        String tag = rb.getTag().toString();
                        String color_string = Character.toString(tag.charAt(2));
                        String team_string = Character.toString(tag.charAt(0));
                        int color_int = Integer.parseInt(color_string);
                        int team_int = Integer.parseInt(team_string);


                        if (lastChecked[team_int - 1] != 0) {

                            for (int i = 1; i <= number_of_players; i++) {

                                String aa = "color" + i + "_" + lastChecked[team_int - 1];
                                int ID = getResources().getIdentifier(aa, "id", getPackageName());
                                View btn = findViewById(ID);
                                btn.setEnabled(true);

                            }
                        }

                        for (int i = 1; i <= number_of_players; i++) {

                            String num_of_color = "color" + i + "_" + color_string;
                            int colorID = getResources().getIdentifier(num_of_color, "id", getPackageName());
                            View btn = findViewById(colorID);
                            btn.setEnabled(false);

                        }

                        lastChecked[team_int - 1] = color_int;
                        teams[team_int - 1].set_color(lastChecked[team_int - 1]);

                    }
                }


            });


        }


    }




    public void back_click(View view) {

        finish();
    }


    public void next_click(View view) {




        int counter=0;
        for (int i = 0; i < number_of_players; i++) {           //Για κάθε ομάδα
            teams[i].set_name(b[i].getText().toString());       //θέτει στην team[i] το όνομα που εισήγαγε
                                                                // η αντίστοιχη ομάδα από το edit view

            if(teams[i].get_name().matches("")){                //αν δεν έγραψε τίποτα
                teams[i].set_name(b[i].getHint().toString());   //πάρε το hint (Ομάδα i) για όνομα ομάδας
            }

            Log.d("-------+--------", teams[i].get_name());
            Log.d("-------)(--------", Integer.toString(teams[i].get_color()));

            if(teams[i].get_color()!=0) {
                counter++;                                       //για έλεγχο αν κάποιος δεν διάλεξε χρώμα
            }
        }

        int current_team = 0;

        //ΠΡΟΣ ΤΟ ΕΠΟΜΕΝΟ SCREEN\\

        Intent nextClick = new Intent(this, MainGame.class);

        //Εισαγωγή πληροφοριών
        nextClick.putExtra("number_of_teams",number_of_players);
        nextClick.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_players;i++) {
            nextClick.putExtra("team"+i,teams[i]);
        }


        if (counter!=number_of_players) {
            Toast.makeText(InitTeams.this, "Παρακαλω επιλέξτε χρώματα για τις ομάδες", Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(nextClick);
            finish();
        }

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_init_teams, menu);
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