package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


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

        Intent goOn = new Intent(this,MainGame.class);
        //todo vale ta parcelables (teams current klp)
        //startActivity(goOn);
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
