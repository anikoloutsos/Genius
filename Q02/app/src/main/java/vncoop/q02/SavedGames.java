package vncoop.q02;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SavedGames extends Activity {

    int[] sortedFiles;
    String[] FILE;
    parcTeams[] teams = new parcTeams[2];
    int number_of_teams = 2;
    int current_team = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);

        //check saved games to send the oldest
        FILE = new String[3];
        FILE[0] = "/data/data/vncoop.q02/databases/savegame1";
        FILE[1] = "/data/data/vncoop.q02/databases/savegame2";
        FILE[2] = "/data/data/vncoop.q02/databases/savegame3";



        long timeStamp = 0;
        long lastIndex = 0;
        long[] fileIndex = new long[3];
        sortedFiles = new int[3];
        int newest = 0;
        int count = 3;

        //find timestamps of saved files
        for (int i = 0; i < 3; i++) {
            File file = new File(FILE[i]);

            if (file.exists()) {
                Date lastModified = new Date(file.lastModified());
                SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
                String timeString = date.format(lastModified);
                timeStamp = Long.parseLong(timeString);
            } else {
                timeStamp = 0;
            }
            fileIndex[i] = timeStamp;

        }




        //sort to newest
        for (int j = 0; j < 3; j++) {

            lastIndex=0;

            for (int i = 0; i < 3; i++) {
               // Log.d(""+j+"FILEINDEX"," "+fileIndex[i]+" ");

                if (fileIndex[i] != 0) {

                    if (fileIndex[i] >lastIndex) {
                        lastIndex = fileIndex[i];
                        newest = i;
                    }
                } else if (j == 0) {
                    count--;
                }
            }

            sortedFiles[j] = newest;
            fileIndex[newest] = 0;
            Log.d("sdsfdsf",""+sortedFiles[j]);
        }


        File[] file = new File[count];
        TextView[] dateView = new TextView[3];
        dateView[0] = (TextView)findViewById(R.id.date1);
        dateView[1] = (TextView)findViewById(R.id.date2);
        dateView[2] = (TextView)findViewById(R.id.date3);
        RelativeLayout[] rl=new RelativeLayout[3];
        rl[0] = (RelativeLayout)findViewById(R.id.rl1);
        rl[1] = (RelativeLayout)findViewById(R.id.rl2);
        rl[2] = (RelativeLayout)findViewById(R.id.rl3);
        TextView[] omadesSave = new TextView[4];
        String dateString="";

        for (int i = 0; i < count; i++) {
            file[i] = new File(FILE[sortedFiles[i]]);

            if(i==0) {
                omadesSave[0] = (TextView) findViewById(R.id.omada1save1);
                omadesSave[1] = (TextView) findViewById(R.id.omada2save1);
                omadesSave[2] = (TextView) findViewById(R.id.omada3save1);
                omadesSave[3] = (TextView) findViewById(R.id.omada4save1);
            }else
            if(i==1) {
                omadesSave[0] = (TextView) findViewById(R.id.omada1save2);
                omadesSave[1] = (TextView) findViewById(R.id.omada2save2);
                omadesSave[2] = (TextView) findViewById(R.id.omada3save2);
                omadesSave[3] = (TextView) findViewById(R.id.omada4save2);
            }else
            if(i==2) {
                omadesSave[0] = (TextView) findViewById(R.id.omada1save3);
                omadesSave[1] = (TextView) findViewById(R.id.omada2save3);
                omadesSave[2] = (TextView) findViewById(R.id.omada3save3);
                omadesSave[3] = (TextView) findViewById(R.id.omada4save3);
            }

            rl[i].setVisibility(View.VISIBLE);

            if (file[i].exists()) {

                Date lastModified = new Date(file[i].lastModified());
                SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
                String timeString = date.format(lastModified);
                String year = timeString.substring(0, 4);
                String month = timeString.substring(4, 6);
                String day = timeString.substring(6, 8);
                String hour = timeString.substring(8, 10);
                String minute = timeString.substring(10, 12);
                dateString = day + "/" + month + "/" + year + " " + hour + ":" + minute;
                Log.d("DATE=", dateString);

                try {
                    ObjectInputStream oIS = new ObjectInputStream(
                            new FileInputStream(FILE[sortedFiles[i]]));

                    number_of_teams = oIS.readInt();
                    teams = new parcTeams[number_of_teams];
                    for (int j = 0; j < number_of_teams; j++) {
                        teams[j] = (parcTeams) oIS.readObject();
                    }
                    current_team = oIS.readInt();

                    oIS.close();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < number_of_teams; j++) {
                String name = teams[j].get_name();
                boolean[] diam = teams[j].get_diamonds();
                int teamcolorint = teams[j].get_color();
                String color=intColorToString(teamcolorint);
                int backId = getResources().getIdentifier(color + "_color", "drawable", getPackageName());
                int totaldiam=0;
                    for (int k=0;k<diam.length;k++){
                        if(diam[k]){
                            totaldiam++;
                        }
                    }
                String OmadaTxt=""+name + ": Διαμάντια " +totaldiam;
                omadesSave[j].setText(OmadaTxt);
                omadesSave[j].setBackgroundResource(backId);
                omadesSave[j].setVisibility(View.VISIBLE);
            }


            dateView[i].setText(dateString);
            dateView[i].setVisibility(View.VISIBLE);

        }


    }

    public String intColorToString(int col) {
        if (col == 1) {
            return "yellow";
        } else if (col == 2) {
            return "blue";
        } else if (col == 3) {
            return "green";
        } else if (col == 4) {
            return "pink";
        } else if (col == 5) {
            return "purple";
        } else {
            return "red";
        }
    }

          public void onsave1(View view){
        File file = new File(FILE[sortedFiles[0]]);

        if (file.exists()) {


            try {
                ObjectInputStream oIS = new ObjectInputStream(
                        new FileInputStream(FILE[sortedFiles[0]]));

                number_of_teams = oIS.readInt();
                teams = new parcTeams[number_of_teams];
                for (int j = 0; j < number_of_teams; j++) {
                    teams[j] = (parcTeams) oIS.readObject();
                }
                current_team = oIS.readInt();

                oIS.close();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent= new Intent(this, ContinueScreen.class);

        //Εισαγωγή πληροφοριών
        intent.putExtra("number_of_teams", number_of_teams);
        intent.putExtra("current_message", current_team);
        intent.putExtra("file_index",sortedFiles[0]);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team" + i, (android.os.Parcelable) teams[i]);
        }
        startActivity(intent);
        finish();

    }

    public void onsave2(View view){
        File file = new File(FILE[sortedFiles[1]]);

        if (file.exists()) {


            try {
                ObjectInputStream oIS = new ObjectInputStream(
                        new FileInputStream(FILE[sortedFiles[1]]));

                number_of_teams = oIS.readInt();
                teams = new parcTeams[number_of_teams];
                for (int j = 0; j < number_of_teams; j++) {
                    teams[j] = (parcTeams) oIS.readObject();
                }
                current_team = oIS.readInt();

                oIS.close();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent= new Intent(this, ContinueScreen.class);

        //Εισαγωγή πληροφοριών
        intent.putExtra("number_of_teams", number_of_teams);
        intent.putExtra("current_message", current_team);
        intent.putExtra("file_index",sortedFiles[1]);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team" + i, (android.os.Parcelable) teams[i]);
        }
        startActivity(intent);
        finish();

    }

    public void onsave3(View view){
        File file = new File(FILE[sortedFiles[2]]);

        if (file.exists()) {


            try {
                ObjectInputStream oIS = new ObjectInputStream(
                        new FileInputStream(FILE[sortedFiles[2]]));

                number_of_teams = oIS.readInt();
                teams = new parcTeams[number_of_teams];
                for (int j = 0; j < number_of_teams; j++) {
                    teams[j] = (parcTeams) oIS.readObject();
                }
                current_team = oIS.readInt();

                oIS.close();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent= new Intent(this, ContinueScreen.class);

        //Εισαγωγή πληροφοριών
        intent.putExtra("number_of_teams", number_of_teams);
        intent.putExtra("current_message", current_team);
        intent.putExtra("file_index",sortedFiles[2]);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team" + i, (android.os.Parcelable) teams[i]);
        }
        startActivity(intent);
        finish();

    }


    }


