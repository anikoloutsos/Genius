package vncoop.q02;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class QuestionScreen extends ActionBarActivity {

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
            teams[i] = (parcTeams) intent.getParcelableExtra("team"+i);
        }
        isDiamond = intent.getBooleanExtra("isDiamond",false);
        category = intent.getIntExtra("categoryNum",0);

        DBHelper finder = new DBHelper(getApplicationContext());
        questionAndAnswer = finder.randFromCat(category);

        TextView questiontext = (TextView) findViewById(R.id.questionId);
        questiontext.setText(questionAndAnswer[0]);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        questiontext.setTypeface(font);

    }


    public void answerbtn(View view){
        Button correct = (Button) findViewById(R.id.correctbtnid);
        Button wrong = (Button) findViewById(R.id.wrongbtnid);

        correct.setVisibility(View.VISIBLE);
        wrong.setVisibility(View.VISIBLE);

        TextView answertext = (TextView) findViewById(R.id.answerid);
        answertext.setText(questionAndAnswer[1]);
        answertext.setVisibility(View.VISIBLE);
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        answertext.setTypeface(font);

        Button answerbtn = (Button) findViewById(R.id.answerbtnid);
        answerbtn.setVisibility(View.GONE);
    }

    public void onCorrect(View view){

        teams[current_team].set_stats_category_correct(category-1);


        Intent intent1 = new Intent(this,Winner.class);
        boolean WeDontHaveAWinner= true;
        //an itan erwtisi gia diamanti kane true to sigkekrimeno diamanti
        if (isDiamond == true){
            teams[current_team].set_diamonds(category-1,true);
            //AN EXEI OLA TA DIAMANTIA NIKAEI
            boolean[] AllDiamonds = new boolean[6];
            int diamondCounter=0;
            AllDiamonds = teams[current_team].get_diamonds();
            for (int i=0;i<6;i++){
                if (AllDiamonds[i] == true){
                    diamondCounter++;
                }
            }
            if (diamondCounter == 6){

                for (int i = 0;i<number_of_teams;i++) {
                    intent1.putExtra("team"+i,teams[i]);
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
            intent.putExtra("team"+i,teams[i]);
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
            intent.putExtra("team"+i,teams[i]);
        }

        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_screen, menu);
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
