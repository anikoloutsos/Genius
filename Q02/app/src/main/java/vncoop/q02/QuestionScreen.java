package vncoop.q02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class QuestionScreen extends Activity {

    String[] questionAndAnswer = new String[2];
    int current_team;
    int number_of_teams;
    parcTeams[] teams;
    boolean isDiamond;
    int category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        //INTENTS FROM INITIALIZE
        Intent intent = getIntent();
        current_team = intent.getIntExtra("current_message", 1);
        number_of_teams = intent.getIntExtra("number_of_teams", 1);
        teams = new parcTeams[number_of_teams];
        for (int i=0;i<number_of_teams;i++) {
            teams[i] = intent.getParcelableExtra("team"+i);
        }
        isDiamond = intent.getBooleanExtra("isDiamond",false);
        category = intent.getIntExtra("categoryNum",0);

        //SAVE STATE SE PERIPTWSI POU VGEI
        String PATH = "/data/data/vncoop.q02/databases/";
        String FILE = "poutsa1";
        File f = new File(PATH + FILE);
        f.delete();
        try {

            ObjectOutputStream oOS = new ObjectOutputStream(

                    new FileOutputStream(PATH + FILE));
            oOS.writeInt(number_of_teams);
            for (int i = 0; i < number_of_teams; i++) {
                oOS.writeObject(teams[i]);
            }

            oOS.writeInt(current_team);
            oOS.flush();
            oOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //WS EDW TO SAVE STATE


        DBHelper finder = new DBHelper(getApplicationContext());
        questionAndAnswer = finder.randFromCat(category);

        TextView questiontext = (TextView) findViewById(R.id.questionId);
        questiontext.setText(questionAndAnswer[0]);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        questiontext.setTypeface(font);

        int backId= getResources().getIdentifier(intCatToString(category) + "_back", "drawable", getPackageName());
        RelativeLayout Layoutc= (RelativeLayout)findViewById(R.id.layout);
        Layoutc.setBackgroundResource(backId);

        int ansId= getResources().getIdentifier(intCatToString(category) + "_selector", "drawable", getPackageName());
        ImageButton ans = (ImageButton)findViewById(R.id.answerbtnid);
        ans.setImageResource(ansId);

        TextView catTitle = (TextView)findViewById(R.id.categoryTitle);
        catTitle.setTypeface(font);
        catTitle.setText(intCatToText(category));

    }


    public void answerbtn(View view){
        ImageButton correct = (ImageButton) findViewById(R.id.correctbtnid);
        ImageButton wrong = (ImageButton) findViewById(R.id.wrongbtnid);

        correct.setVisibility(View.VISIBLE);
        wrong.setVisibility(View.VISIBLE);

        TextView answertext = (TextView) findViewById(R.id.answerid);
        answertext.setText(questionAndAnswer[1]);
        answertext.setVisibility(View.VISIBLE);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        answertext.setTypeface(font);

        //Button answerbtn = (Button) findViewById(R.id.answerbtnid);
        //answerbtn.setVisibility(View.GONE);
    }

    public void onCorrect(View view){

        teams[current_team].set_stats_category_correct(category-1);


        Intent intent1 = new Intent(this,Winner.class);
        boolean WeDontHaveAWinner= true;
        //an itan erwtisi gia diamanti kane true to sigkekrimeno diamanti
        if (isDiamond){
            teams[current_team].set_diamonds(category-1,true);
            //AN EXEI OLA TA DIAMANTIA NIKAEI
            boolean[] AllDiamonds;
            int diamondCounter=0;
            AllDiamonds = teams[current_team].get_diamonds();
            for (int i=0;i<6;i++){
                if (AllDiamonds[i]){
                    diamondCounter++;
                }
            }
            if (diamondCounter == 6){

                for (int i = 0;i<number_of_teams;i++) {
                    intent1.putExtra("team"+i, (android.os.Parcelable) teams[i]);
                }
                intent1.putExtra("current_message",current_team);
                intent1.putExtra("number_of_teams",number_of_teams);
                WeDontHaveAWinner = false;
            }
        }
        Intent intent = new Intent(this, MainGame.class);

        intent.putExtra("number_of_teams",number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team"+i, (android.os.Parcelable) teams[i]);
        }
        if (WeDontHaveAWinner){
            startActivity(intent);
        }
        else{
            startActivity(intent1);
        }
        finish();
    }

    public void onWrong(View view){
        teams[current_team].set_stats_category_wrong(category-1);

        current_team++;
        current_team = current_team%number_of_teams;

        Intent intent = new Intent(this, MainGame.class);

        intent.putExtra("number_of_teams",number_of_teams);
        intent.putExtra("current_message", current_team);
        for (int i = 0;i<number_of_teams;i++) {
            intent.putExtra("team"+i, (android.os.Parcelable) teams[i]);
        }

        startActivity(intent);
        finish();
    }



    public String intCatToString(int col){
        if(col==1){
            return "geo";
        }else if(col==2){
            return "cim";
        }else if(col==3){
            return "his";
        }else if(col==4){
            return "art";
        }else if(col==5){
            return "sci";
        }else{
            return "spo";
        }
    }

    public String intCatToText(int col){
        if(col==1){
            return "Γεωγραφία";
        }else if(col==2){
            return "Ψυχαγωγία";
        }else if(col==3){
            return "Ιστορία";
        }else if(col==4){
            return "Τέχνες";
        }else if(col==5){
            return "Επιστήμη";
        }else{
            return "Χόμπυ";
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Σταμάτημα Παιχνιδιού")
                .setMessage("Είστε σίγουροι ότι θέλετε να επιστρέψετε στην αρχική οθόνη;")
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
