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

        if (dblist[0] == null) {
            //Log.d("i have", dblist[0]);

            //this.deleteDatabase("FDB.sqlite");

            DBHelper dbCreator = new DBHelper(getApplicationContext());
            try {
                dbCreator.createDB();
            } catch (IOException ex) {
                throw new Error("ImpossibleToCreateDB");
            }
        }


        //Screen characteristics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        screenDensity = (double) dm.density;

        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;

        double buttonsRatio = 81.0/572.0;

        //Logo margins
        Left = 0.25*screenWidth;
        Top = 0.09*screenHeight;
        Right = Left;
        Bottom = (screenHeight- Top -(screenWidth-2*Left));
        setMargins(logo,(int) Left,(int) Top,(int) Right, (int) Bottom);


        //New game Button margins
        Left = 0.04*screenWidth;
        Top = 2*Right+Top + 0.06*screenHeight;
        Right = Left;
        Bottom =(screenHeight - Top - (buttonsRatio*(screenWidth-2*Left)));
        setMargins(newGame,(int) Left,(int) Top,(int) Right, (int) Bottom);

        //Continue Button margins
        Top = Top + (buttonsRatio*(screenWidth-2*Left))+0.04*screenHeight;
        Bottom = (screenHeight - Top - (buttonsRatio*(screenWidth-2*Left)));
        setMargins(Continue,(int) Left,(int) Top,(int) Right, (int) Bottom);

        //Rules Button margins
        Top = Top + (buttonsRatio*(screenWidth-2*Left)) + 0.04*screenHeight;
        Bottom = (screenHeight - Top - (buttonsRatio*(screenWidth-2*Left)));
        setMargins(rules,(int) Left,(int) Top,(int) Right, (int) Bottom);

        //Add Questions Button margins
        Top = Top + (buttonsRatio*(screenWidth-2*Left)) + 0.04*screenHeight;
        Bottom = screenHeight - Top - buttonsRatio* (screenWidth-2*Left);
        setMargins(AddQuestion,(int) Left,(int) Top,(int) Right, (int) Bottom);
    }


    ////////////BUTTONS\\\\\\\\\\\\\\
    public void NGClick(View view) {
        Intent new_game = new Intent(this, ChooseNumOfPlayers.class);
        startActivity(new_game);

    }

    public void GoOnClick(View view){

        Intent goOn = new Intent(this,SavedGameStatus.class);
        String FILE = "/data/data/vncoop.q02/databases/poutsa1";
        parcTeams[] teams = new parcTeams[2];
        int number_of_teams = 2;
        int current_team = 0;
        File file = new File(FILE);
        if (file.exists()) {
            Date lastModified = new Date(file.lastModified());
            Log.d("date", String.valueOf(lastModified));
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddhhmmss");
            String dateFile1 = date.format(lastModified);
            Log.d("date2", dateFile1);

        try {
            ObjectInputStream oIS = new ObjectInputStream(
                    new FileInputStream(FILE));

            number_of_teams = oIS.readInt();
            teams = new parcTeams[number_of_teams];
            for(int i=0;i<number_of_teams;i++) {
                teams[i] = (parcTeams) oIS.readObject();
            }
            current_team = oIS.readInt();

            oIS.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }


        goOn.putExtra("number_of_teams",number_of_teams);
        goOn.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_teams;i++) {
            goOn.putExtra("team"+i, (java.io.Serializable) teams[i]);
        }




        startActivity(goOn);
        }else{
            Toast.makeText(getApplicationContext(), " Δεν υπάρχουν αποθηκευμένα παιχνίδια", Toast.LENGTH_LONG).show();
        }


        //finish();
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
