package vncoop.dbtest2;

import android.database.SQLException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;


public class oxiAllo extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxi_allo);
        String[] dblist = this.databaseList();

        Log.d("i have", dblist[0]);
        this.deleteDatabase("FDB.sqlite");

        Voithos kalavoitho = new Voithos(getApplicationContext());
        try{
            kalavoitho.createDB();
        }catch (IOException ex){
            throw new Error("Mpoulo");
        }

    }
    public void open_db(View view){

       Voithos myDbHelper = new Voithos(getApplicationContext());
        try {

            String[] text = myDbHelper.randFromCat(1);
            Log.d("QUESTION",text[0]);
            Log.d("ANSWER",text[1]);

        }catch(SQLException sqle){

            throw sqle;

        }

    }

    public void close_db(View view){

        Voithos myDbHelper = new Voithos(getApplicationContext());
        try {

            myDbHelper.close();

        }catch(SQLException sqle){

            throw sqle;

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_oxi_allo, menu);
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
