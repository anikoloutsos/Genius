package vncoop.genius;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InitTeams extends ActionBarActivity {
    private int number_of_players;
    private int fileIndex;


    EditText[] b;
    Teams[] teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_teams);
        getSupportActionBar().hide();

        /////////DECLARATIONS////////////
        Intent intent = getIntent();

        number_of_players = intent.getIntExtra("n_team_message", 1);
        b = new EditText[number_of_players];
        teams = new Teams[number_of_players];
        final int[] lastChecked = new int[number_of_players];

        for (int i = 0; i < number_of_players; i++) {
            lastChecked[i] = 0;
            teams[i] = new Teams();
        }


        ////////////////////////////////

        double Top,Left,Right,Bottom;
        double[] screen = BasicMethods.getScreenChar(this);
        TextView settingsText = (TextView)findViewById(R.id.teamSettingsText);
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButtonId);
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonId);
        ScrollView scrollview = (ScrollView) findViewById(R.id.scrollView);
        ImageView separator = (ImageView) findViewById(R.id.separator);
        ImageView separator2 = (ImageView) findViewById(R.id.separator2);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        //Setting settings text margins
        settingsText.setTypeface(font);
        Top = (0.035* screen[1]);
        Left = 0.05*screen[0];
        Bottom = (1-0.135)*screen[1];
        BasicMethods.setMargins(settingsText,(int) Left,(int) Top,(int) Left,(int) Bottom);
        settingsText.setTextSize((float) ((0.075 / screen[2]) * screen[1]));

        //Separator Margins
        Top = (0.15)*screen[1];
        Bottom = (0.83125)*screen[1];
        BasicMethods.setMargins(separator,0,(int) Top,0,(int) Bottom);

        //Separator2 Margins
        Top = (0.8*screen[1]);
        Bottom = 0.18125*screen[1];
        BasicMethods.setMargins(separator2,0,(int) Top,0,(int) Bottom);

        //Setting home and Next buttons margins
        Top = (0.83875) * screen[1];
        Bottom = 0.04*screen[1];
        Left = 0.05*screen[0];
        Right = screen[0]-Left-(screen[1]-Top-Bottom);
        BasicMethods.setMargins(homeButton, (int) Left, (int) Top, (int) Right, (int) Bottom);
        BasicMethods.setMargins(nextButton, (int) Right, (int) Top, (int) Left, (int) Bottom);


        //Setting scrollview margins
        Top = (0.16875)* screen[1];
        Bottom = (0.2*screen[1]);
        Left = (0.05*screen[0]);
        Right = Left;
        BasicMethods.setMargins(scrollview,(int) Left,(int) Top,(int) Right,(int) Bottom);

        //////////////SET EDIT TEXT VISIBLE//////////////
        for (int i = 0; i < number_of_players; i++) {
            String buttonID = "textView" + (i+1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            b[i] = (EditText) findViewById(resID);
            b[i].setVisibility(View.VISIBLE);
            b[i].setTypeface(font);
            teams[i].set_name(b[i].getHint().toString());
            b[i].setFocusable(false);
            b[i].setClickable(true);
            String buttonID2 = "radioGroup" + (i+1);
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
            RadioGroup radioGroup = (RadioGroup) findViewById(RGID);

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
                                int ls = lastChecked[team_int - 1];
                                String aa = "color" + i + "_" + ls;
                                int ID = getResources().getIdentifier(aa, "id", getPackageName());
                                View btn = findViewById(ID);
                                btn.setEnabled(true);
                                int Last_ID = getResources().getIdentifier(aa, "id", getPackageName());
                                RadioButton last_btn = (RadioButton) findViewById(Last_ID);
                                last_btn.setEnabled(true);
                                last_btn.setButtonDrawable(R.drawable.yellow_selector);
                                if (ls == 1) {
                                    last_btn.setButtonDrawable(R.drawable.yellow_selector);
                                } else if (ls == 2) {
                                    last_btn.setButtonDrawable(R.drawable.blue_selector);
                                } else if (ls == 3) {
                                    last_btn.setButtonDrawable(R.drawable.green_selector);
                                } else if (ls == 4) {
                                    last_btn.setButtonDrawable(R.drawable.pink_selector);
                                } else if (ls == 5) {
                                    last_btn.setButtonDrawable(R.drawable.purple_selector);
                                } else {
                                    last_btn.setButtonDrawable(R.drawable.red_selector);
                                }
                            }
                        }

                        for (int i = 1; i <= number_of_players; i++) {

                            String num_of_color = "color" + i + "_" + color_string;
                            int colorID = getResources().getIdentifier(num_of_color, "id", getPackageName());
                            RadioButton btn = (RadioButton) findViewById(colorID);
                            btn.setEnabled(false);
                            if (!btn.equals(rb)) {
                                if (color_int == 1) {
                                    btn.setButtonDrawable(R.drawable.yellow3);
                                } else if (color_int == 2) {
                                    btn.setButtonDrawable(R.drawable.blue3);
                                } else if (color_int == 3) {
                                    btn.setButtonDrawable(R.drawable.green3);
                                } else if (color_int == 4) {
                                    btn.setButtonDrawable(R.drawable.pink3);
                                } else if (color_int == 5) {
                                    btn.setButtonDrawable(R.drawable.purple3);
                                } else {
                                    btn.setButtonDrawable(R.drawable.red3);
                                }

                            }
                        }


                        lastChecked[team_int - 1] = color_int;
                        teams[team_int - 1].set_color(lastChecked[team_int - 1]);

                    }
                }


            });


        }

        //check saved games to send the oldest
        String[] FILE = new String[3];
        FILE[0] = BasicMethods.save1;
        FILE[1] = BasicMethods.save2;
        FILE[2] = BasicMethods.save3;

        long timeStamp;
        long lastTimeStamp=0;
        fileIndex =0;
        boolean flag =true;

        for (int i=0; i<3; i++) {
            File file = new File(FILE[i]);

            if (file.exists()) {
                Date lastModified = new Date(file.lastModified());
                SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
                String timeString = date.format(lastModified);
                timeStamp = Long.parseLong(timeString);
            } else {
                timeStamp = i;
            }

            if (flag){
                lastTimeStamp = timeStamp;
                flag = false;
            }

            if(timeStamp<=lastTimeStamp){
                lastTimeStamp=timeStamp;
                fileIndex=i;
            }

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
        nextClick.putExtra("file_index",fileIndex);
        for (int i = 0;i<number_of_players;i++) {
            nextClick.putExtra("team" + i, (android.os.Parcelable) teams[i]);
        }


        if (counter!=number_of_players) {
            Toast.makeText(InitTeams.this, "Παρακαλω επιλέξτε χρώματα για τις ομάδες", Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(nextClick);
            finish();
        }

    }

    public void throwPopup(View view){

        Context context = this;

        final int id1 = view.getId();
        final int  tv1 = R.id.textView1;
        final int  tv2 = R.id.textView2;
        final int  tv3 = R.id.textView3;


        LayoutInflater layoutInflater = LayoutInflater.from(context);



        View promptView = layoutInflater.inflate(R.layout.popup_insert_name, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        //AlertDialog test = alertDialogBuilder.create();
        alertDialogBuilder.setView(promptView);
        final EditText input = (EditText) promptView.findViewById(R.id.userInput);

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // get user input and set it to result
                        if (id1 == tv1) {
                            b[0].setText(input.getText());
                        }
                        else if (id1 == tv2) {
                            b[1].setText(input.getText());
                        }
                        else if (id1 == tv3) {
                            b[2].setText(input.getText());
                        }
                        else {
                            b[3].setText(input.getText());
                        }

                    }

                })
                .setNegativeButton("Ακύρωση",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();
        alertD.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        alertD.show();



    }

}
