package vncoop.q02;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class addQuestion extends Activity {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText subQue,subAns;
    String rec, subject, textMessage,sender,senderPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);


        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        //Screen characteristics
        double screenWidth, screenHeight, screenDensity, statusBarHeight, Left, Top, Right, Bottom;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = (double) dm.widthPixels;
        screenHeight = (double) dm.heightPixels;
        screenDensity = (double) dm.density;
        statusBarHeight = (double) getStatusBarHeight();
        screenHeight -= statusBarHeight;

        //Number of teams text Size set
        TextView titleText = (TextView)findViewById(R.id.titleId);
        subQue = (EditText) findViewById(R.id.submitQuestion);
        subAns = (EditText) findViewById(R.id.submitAnswer);
        ScrollView sV = (ScrollView) findViewById(R.id.scrollView);
        ImageView separator = (ImageView) findViewById(R.id.separatorId);
        subQue.setTypeface(font);
        subAns.setTypeface(font);
        titleText.setTypeface(font);
        //Setting savedGameText margins
        Top = (0.035* screenHeight);
        Left = 0.05*screenWidth;

        Bottom = (1-0.135)*screenHeight;
        setMargins(titleText,(int) Left,(int) Top,(int) Left,(int) Bottom);
        titleText.setTextSize((float) ((0.07 / screenDensity) * screenHeight));


        //Separator Margins
        Top = (0.15)*screenHeight;
        Bottom = (0.83125)*screenHeight;
        setMargins(separator,0,(int) Top,0,(int) Bottom);
        RelativeLayout rl0 = (RelativeLayout) findViewById(R.id.rl0);
        //scrollView Margins
        Top = (0.16875*screenHeight);
        Bottom = (0.05*screenHeight);
        Left = 0.05*screenWidth;
        setMargins(sV,(int) Left, (int)Top, (int) Left, (int) Bottom);
        setMargins(rl0,0, 0, 0, (int) Top);


        CheckBox agree = (CheckBox) findViewById(R.id.checkBox);
        TextView legal = (TextView) findViewById(R.id.legalText);
        legal.setTypeface(font);
        legal.setTextSize((float) ((0.053 / screenDensity) * screenHeight));
        //Agree Text margins
        legal.setText(Html.fromHtml("<u>Διάβασα και αποδέχομαι τους όρους</u>"));
        //Top = (0.35+0.05)*;
        //Bottom = (0.15+0.03)*screenHeight;
        //setMargins(rl3,0,(int) Top,0,(int)Bottom);

        //style buttons
        Button addq = (Button)findViewById(R.id.sendQueId);
        Button back = (Button)findViewById(R.id.backId);
        addq.setTypeface(font);
        back.setTypeface(font);
        //Top = (0.85-0.16875)*screenHeight;
        Top =0.05*screenHeight;
        //Bottom = 0.05*screenHeight;
        Bottom = 0;
        //Left = 0.05*screenWidth;
        Left = 0;
        Right = 0.55*screenWidth;
        setMargins(back, (int) Left, (int) Top, (int) Right, (int)Bottom);
        setMargins(addq, (int) Right, (int) Top, (int) Left, (int) Bottom);
        back.setTextSize((float) ((0.045 / screenDensity) * screenHeight));
        addq.setTextSize((float) ((0.045 / screenDensity) * screenHeight));
        subQue.setTextSize((float) ((0.05 / screenDensity) * screenHeight));
        subAns.setTextSize((float) ((0.05 / screenDensity) * screenHeight));


        context = this;
        rec = "geniusquizapp@gmail.com";
        sender = "geniusquizhandler@gmail.com";
        senderPassword = "funiculifunicula";
        subject = "New Question";

    }

    public void onBack(View view){
        finish();
    }


    public void Terms(View view){
        Intent i = new Intent(this, Terms.class);
        startActivity(i);
    }

    public void sendQuestion(View view) {

        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        if(checkBox.isChecked()) {


            if (!NetworkIsAvailable()) {

                Toast.makeText(getApplicationContext(), "Παρακαλώ ελέγξτε τη σύνδεση σας στο Internet", Toast.LENGTH_LONG).show();

            } else {
                textMessage = subQue.getText() + "/n" + subAns.getText();

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(sender, senderPassword);
                    }
                });
                pdialog = ProgressDialog.show(context, "", "Αποστολή Ερώτησης...", true);

                RetrieveFeedTask task = new RetrieveFeedTask();
                task.execute();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Δεν έχετε αποδεχθεί τους όρους...",Toast.LENGTH_LONG).show();
        }

    }

    class RetrieveFeedTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params){

            try{
                javax.mail.Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender));
                message.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage,"text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result){
            pdialog.dismiss();
            subAns.setText("");
            subQue.setText("");
            Toast.makeText(getApplicationContext(),"Ευχαριστούμε που στείλατε την ερώτησή σας",Toast.LENGTH_LONG).show();
            finish();
        }



    }

    private boolean NetworkIsAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
