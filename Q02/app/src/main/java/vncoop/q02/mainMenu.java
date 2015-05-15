package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;


public class mainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //String[] dblist = this.databaseList();

        //Log.d("i have", dblist[0]);

        //this.deleteDatabase("FDB.sqlite");

        DBHelper dbCreator = new DBHelper(getApplicationContext());
        try{
            dbCreator.createDB();
        }catch (IOException ex){
            throw new Error("ImpossibleToCreateDB");
        }


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


        //finish();
    }

    public void rulesClick(View view){
        Intent rules = new Intent(this, Rules.class);
        startActivity(rules);

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
}
