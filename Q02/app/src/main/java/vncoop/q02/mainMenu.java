package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class mainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        double screenWidth,screenHeight,statusBarHeight,Left,Top,Right,Bottom,screenDensity;
        ImageView logo = (ImageView) findViewById(R.id.logoId);
        Button newGame = (Button) findViewById(R.id.ngid);
        Button Continue = (Button) findViewById(R.id.goid);
        Button rules = (Button) findViewById(R.id.rulesId);
        Button AddQuestion = (Button) findViewById(R.id.addQueId);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");

        newGame.setTypeface(font);
        Continue.setTypeface(font);
        rules.setTypeface(font);
        AddQuestion.setTypeface(font);
        String[] dblist = this.databaseList();

            //this.deleteDatabase("FDB.sqlite");
            DBHelper dbCreator = new DBHelper(getApplicationContext());
            try {
                dbCreator.createDB();
            } catch (IOException ex) {
                throw new Error("ImpossibleToCreateDB");
            }



        //Screen characteristics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        screenDensity = (double) dm.density;

        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;

        double buttonsRatio = 105.0/572.0;

        //Logo margins
        Left = 0.30081775700934*screenWidth;
        Top = 0.0700934579*screenHeight;
        Right = 0.2961448598*screenWidth;
        Bottom =0.64953271*screenHeight;
        setMargins(logo,(int) Left,(int) Top,(int) Right, (int) Bottom);


        //New game Button margins

        Top = 0.41082555*screenHeight;
        Left = 0.05*screenWidth;
        Bottom = 0.47858255451*screenHeight;
        Right=Left;

        setMargins(newGame,(int) Left,(int) Top,(int) Right, (int) Bottom);
        newGame.setTextSize((float) (0.057/screenDensity*screenHeight));

        //Continue Button margins
        Top = Top + 0.140576323987*screenHeight;
        Bottom = Bottom -  0.140576323987*screenHeight;
        setMargins(Continue,(int) Left,(int) Top,(int) Right, (int) Bottom);
        Continue.setTextSize((float) (0.057/screenDensity*screenHeight));

        //Rules Button margins
        Top = Top + 0.140576323987*screenHeight;
        Bottom = Bottom -  0.140576323987*screenHeight;
        setMargins(rules,(int) Left,(int) Top,(int) Right, (int) Bottom);
        rules.setTextSize((float) (0.057/screenDensity*screenHeight));

        //Add Questions Button margins
        Top = Top + 0.140576323987*screenHeight;
        Bottom = Bottom -  0.140576323987*screenHeight;
        setMargins(AddQuestion,(int) Left,(int) Top,(int) Right, (int) Bottom);
        AddQuestion.setTextSize((float) (0.057/screenDensity*screenHeight));
    }


    ////////////BUTTONS\\\\\\\\\\\\\\
    public void NGClick(View view) {
        Intent new_game = new Intent(this, ChooseNumOfPlayers.class);
        startActivity(new_game);

    }

    public void GoOnClick(View view){
        boolean notsaved = true;
        String[] FILE = new String[3];
        FILE[0] = "/data/data/vncoop.q02/databases/savegame1";
        FILE[1] = "/data/data/vncoop.q02/databases/savegame2";
        FILE[2] = "/data/data/vncoop.q02/databases/savegame3";

        for (int i = 0; i < 3; i++) {
            File file = new File(FILE[i]);
            //file.delete();
            if (file.exists()) {
                notsaved = false;
            }
        }

            if(notsaved){
                Toast.makeText(mainMenu.this, "Δεν υπάρχουν αποθηκευμένα παιχνίδια", Toast.LENGTH_SHORT).show();
            }else{

                Intent savedGames = new Intent(this, SavedGames.class);
                startActivity(savedGames);
                //finish();

            }

        }




    public void rulesClick(View view){
        Intent rules = new Intent(this, Rules.class);
        startActivity(rules);

    }

    public void addQue(View view){
        Intent addque = new Intent(this, addQuestion.class);
        startActivity(addque);

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


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    public static void setMargins(View v, int l, int t, int r, int b) {

        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {

            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
