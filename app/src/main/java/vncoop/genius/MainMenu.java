package vncoop.genius;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainMenu extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        double[] screen = BasicMethods.getScreenChar(this);
        double Left,Top,Right,Bottom;
        ImageView logo = (ImageView) findViewById(R.id.logo);
        Button newGame = (Button) findViewById(R.id.newGame);
        Button Continue = (Button) findViewById(R.id.continueGame);
        Button rules = (Button) findViewById(R.id.rules);
        Button AddQuestion = (Button) findViewById(R.id.addQuestion);

        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        BasicMethods.setDirectory(getApplicationContext());

        newGame.setTypeface(font);
        Continue.setTypeface(font);
        rules.setTypeface(font);
        AddQuestion.setTypeface(font);

        //DB creation
        DBHelper dbCreator = new DBHelper(getApplicationContext());
        try {
            dbCreator.createDB();
        } catch (IOException ex) {
            throw new Error("ImpossibleToCreateDB");
        }

         //Logo margins
        Left = 0.30081*screen[0];
        Top = 0.07009*screen[1];
        Right = 0.29614*screen[0];
        Bottom =0.64953*screen[1];
        BasicMethods.setMargins(logo, (int) Left, (int) Top, (int) Right, (int) Bottom);


        //New game Button margins

        Top = 0.41082*screen[1];
        Left = 0.05*screen[0];
        Bottom = 0.47858*screen[1];
        Right=Left;

        BasicMethods.setMargins(newGame, (int) Left, (int) Top, (int) Right, (int) Bottom);
        newGame.setTextSize((float) (0.057/screen[2]*screen[1]));

        //Continue Button margins
        Top = Top + 0.14057*screen[1];
        Bottom = Bottom - 0.14057*screen[1];
        BasicMethods.setMargins(Continue, (int) Left, (int) Top, (int) Right, (int) Bottom);
        Continue.setTextSize((float) (0.057/screen[2]*screen[1]));

        //Rules Button margins
        Top = Top + 0.14057*screen[1];
        Bottom = Bottom -  0.14057*screen[1];
        BasicMethods.setMargins(rules, (int) Left, (int) Top, (int) Right, (int) Bottom);
        rules.setTextSize((float) (0.057/screen[2]*screen[1]));

        //Add Questions Button margins
        Top = Top + 0.14057*screen[1];
        Bottom = Bottom -  0.14057*screen[1];
        BasicMethods.setMargins(AddQuestion, (int) Left, (int) Top, (int) Right, (int) Bottom);
        AddQuestion.setTextSize((float) (0.057/screen[2]*screen[1]));
    }


    ////////////BUTTONS\\\\\\\\\\\\\\

    public void NewGame(View view) {
        Intent new_game = new Intent(this, ChooseNumOfPlayers.class);
        startActivity(new_game);

    }

    public void ContinueGame(View view){

        boolean notSaved = true;
        String[] FILE = new String[3];
        FILE[0] = BasicMethods.save1;
        FILE[1] = BasicMethods.save2;
        FILE[2] = BasicMethods.save3;

        for (int i = 0; i < 3; i++) {
            File file = new File(FILE[i]);

            if (file.exists()) {
                notSaved = false;
            }
        }

        if(notSaved){
            Toast.makeText(MainMenu.this, "Δεν υπάρχουν αποθηκευμένα παιχνίδια", Toast.LENGTH_SHORT).show();
        }else{

            Intent savedGames = new Intent(this, SavedGames.class);
            startActivity(savedGames);
            //finish();

        }

    }




    public void Rules(View view){
        Intent rules = new Intent(this, Rules.class);
        startActivity(rules);

    }

    public void AddQuestion(View view){
        Intent addQuestion = new Intent(this, AddQuestion.class);
        startActivity(addQuestion);

    }
    public void  onFBClick(View view){
        Intent intent = getOpenFacebookIntent(this);
        startActivity(intent);
    }

    public void onAboutUs(View view){
        Intent aboutUs = new Intent(this, AboutUs.class);
        startActivity(aboutUs);

    }



    /////////END OF BUTTONS\\\\\\\\\\\


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Κλείσιμο Εφαρμογής")
                .setMessage("Είστε σίγουροι ότι θέλετε να κλείσετε το παιχνίδι;")
                .setPositiveButton("Ναι", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Όχι", null)
                .show();
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1002199319791228"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Genius-quiz/1002199319791228"));
        }
    }


}
