package vncoop.q02;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
        int numberOfSaves = 3;

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
                    numberOfSaves--;
                }
            }

            sortedFiles[j] = newest;
            fileIndex[newest] = 0;
            Log.d("sdsfdsf",""+sortedFiles[j]);
        }


        File[] file = new File[numberOfSaves];
        TextView[] dateView = new TextView[3];
        dateView[0] = (TextView)findViewById(R.id.date1);
        dateView[1] = (TextView)findViewById(R.id.date2);
        dateView[2] = (TextView)findViewById(R.id.date3);
        RelativeLayout[] rl=new RelativeLayout[3];
        rl[0] = (RelativeLayout)findViewById(R.id.rl1);
        rl[1] = (RelativeLayout)findViewById(R.id.rl2);
        rl[2] = (RelativeLayout)findViewById(R.id.rl3);
        TextView[] omadesSave = new TextView[12];
        String dateString="";
        omadesSave[0] = (TextView) findViewById(R.id.omada1save1);
        omadesSave[1] = (TextView) findViewById(R.id.omada2save1);
        omadesSave[2] = (TextView) findViewById(R.id.omada3save1);
        omadesSave[3] = (TextView) findViewById(R.id.omada4save1);
        omadesSave[4] = (TextView) findViewById(R.id.omada1save2);
        omadesSave[5] = (TextView) findViewById(R.id.omada2save2);
        omadesSave[6] = (TextView) findViewById(R.id.omada3save2);
        omadesSave[7] = (TextView) findViewById(R.id.omada4save2);
        omadesSave[8] = (TextView) findViewById(R.id.omada1save3);
        omadesSave[9] = (TextView) findViewById(R.id.omada2save3);
        omadesSave[10] = (TextView) findViewById(R.id.omada3save3);
        omadesSave[11] = (TextView) findViewById(R.id.omada4save3);

        ImageButton[] ImageSave = new ImageButton[3];
        ImageSave[0] = (ImageButton) findViewById(R.id.ImageSave1Id);
        ImageSave[1] = (ImageButton) findViewById(R.id.ImageSave2Id);
        ImageSave[2] = (ImageButton) findViewById(R.id.ImageSave3Id);


        //////////////MARGINS\\\\\\\\\\\\\\\\\\\
        double screenWidth,screenHeight,statusBarHeight,Left,Top,Right,Bottom,screenDensity;
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        TextView savedGamesText = (TextView) findViewById(R.id.savedGamesId);
        ImageView separator = (ImageView) findViewById(R.id.seperator);
        RelativeLayout basicRelativeLayout = (RelativeLayout) findViewById(R.id.basicRelativeLayout);


        //Screen characteristics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        screenDensity = (double) dm.density;

        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;


        //Setting savedGameText margins
        savedGamesText.setTypeface(font);
        Top = (0.035* screenHeight);
        Left = 0.05*screenWidth;
        Bottom = (1-0.135)*screenHeight;
        setMargins(savedGamesText,(int) Left,(int) Top,(int) Left,(int) Bottom);
        savedGamesText.setTextSize((float) ((0.065 / screenDensity) * screenHeight));

        //Separator Margins
        Top = (0.15)*screenHeight;
        Bottom = (0.83125)*screenHeight;
        setMargins(separator,0,(int) Top,0,(int) Bottom);

        //Setting Basic Relative Layout Margins
        Top = screenHeight-Bottom;
        Bottom = 0.04*screenHeight;
        setMargins(basicRelativeLayout,(int) Left,(int) Top,(int) Left, (int) Bottom);
        double basicRelativeLayoutHeight = screenHeight-Top-Bottom;
        double basicRelativeLayoutWidth = screenWidth-2*Left;
        double rlMarginHeight=0.0;

        //Setting total margins for 1, 2 or 3 saved games
        float letterHeight;

        if (numberOfSaves==1){
            rlMarginHeight=0.3*basicRelativeLayoutHeight;
            letterHeight =(float) ((0.06/ screenDensity)* screenHeight);
        }
        else if (numberOfSaves==2){
            rlMarginHeight=0.06*basicRelativeLayoutHeight;
            letterHeight =(float) ((0.05/ screenDensity)* screenHeight);
        }
        else{
            rlMarginHeight=0.04*basicRelativeLayoutHeight;
            letterHeight =(float) ((0.04/ screenDensity)* screenHeight);
        }
        double numOfSaves;
        numOfSaves = (double) numberOfSaves;
        double rlHeight = ((1.0/ numOfSaves)*basicRelativeLayoutHeight-2.0*rlMarginHeight);
        Left = 0.05*basicRelativeLayoutWidth;
        Right = Left;

        //Setting every save game layout
        for (int i=0;i<numberOfSaves;i++){
            Top = (double) (i)*rlHeight+(2.0*(double)(i)+1.0)*rlMarginHeight;
            Bottom = (numOfSaves-1.0-(double)i)*rlHeight+(2*(numOfSaves-1.0-(double)i)+1.0)*rlMarginHeight;
            setMargins(rl[i], 0, (int) Top, 0, (int) Bottom);
            ImageSave[i].setVisibility(View.VISIBLE);
            setMargins(ImageSave[i], 0, (int) (Top-0.015*basicRelativeLayoutHeight), 0, (int) (Bottom-0.015*basicRelativeLayoutHeight));


            //find out which elements exist in every saved Game
            file[i] = new File(FILE[sortedFiles[i]]);
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



            double textHeight = (rlHeight/(double) (number_of_teams+1));
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
                //Setting each teams name and margins
                omadesSave[j+4*i].setText(OmadaTxt);
                omadesSave[j+4*i].setBackgroundResource(backId);
                omadesSave[j+4*i].setVisibility(View.VISIBLE);
                omadesSave[j+4*i].setTypeface(font);
                omadesSave[j+4*i].setTextSize(letterHeight);
                setMargins(omadesSave[j + 4 * i], (int) Left, (int) ((double) (j + 1) * textHeight), (int) Right, (int) ((double) (number_of_teams - j - 1) * textHeight));
                refitText(omadesSave[j+4*i],letterHeight,(int) (0.87*basicRelativeLayoutWidth));
                Log.d(String.valueOf(j)+":"+(int) ((double)(j+1)*textHeight),""+(int) ((double)(number_of_teams-j-1)*textHeight));
            }
            Log.d("textH", String.valueOf(textHeight));
            Log.d("rlHeight", String.valueOf(rlHeight));

            //Setting date's name and margin
            dateView[i].setText(dateString);
            dateView[i].setVisibility(View.VISIBLE);
            dateView[i].setTypeface(font);
            dateView[i].setTextSize(letterHeight);
            setMargins(dateView[i],(int) Left,0,(int) Right,(int) ((double)(number_of_teams)*textHeight));
            refitText(dateView[i], letterHeight, (int) (0.87 * basicRelativeLayoutWidth));


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
              Log.d("FileIndex1", String.valueOf(sortedFiles[0]));
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
        Log.d("FileIndex2", String.valueOf(sortedFiles[1]));
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
        Log.d("FileIndex3", String.valueOf(sortedFiles[2]));
        startActivity(intent);
        finish();

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




    public void refitText(TextView tv, float maxTextSize, int width) {
        tv.measure(0, 0);
        int textWidth = tv.getMeasuredWidth();
        int availableWidth = width;
        float trySize = maxTextSize;

        while (textWidth > availableWidth) {
            trySize -= 1;
            tv.setTextSize(trySize);
            tv.measure(0, 0);
            textWidth = tv.getMeasuredWidth();
        }

        tv.setTextSize(trySize);

    }

    }


