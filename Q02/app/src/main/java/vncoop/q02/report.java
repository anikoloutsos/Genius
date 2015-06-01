package vncoop.q02;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class report extends Activity {
    EditText writeOther;

    RadioGroup reportRG;
    RadioButton b1,b2,b3,b4,b5,b6,b7;
    ProgressDialog pdialog = null;
    Context context;
    Session session = null;
    String rec, subject, textMessage,sender,senderPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        rec = "geniusquizapp@gmail.com";

        sender = "geniusquizhandler@gmail.com";
        senderPassword = "funiculifunicula";

        subject = "";
        context = this;
        Intent i = getIntent();
        textMessage = i.getStringExtra("Question");
        writeOther = (EditText) findViewById(R.id.repEditTextId);
        reportRG = (RadioGroup) findViewById(R.id.repRG);
        b1 = (RadioButton) findViewById(R.id.repId1);
        b2 = (RadioButton) findViewById(R.id.repId2);
        b3 = (RadioButton) findViewById(R.id.repId3);
        b4 = (RadioButton) findViewById(R.id.repId4);
        b5 = (RadioButton) findViewById(R.id.repId5);
        b6 = (RadioButton) findViewById(R.id.repId6);
        b7 = (RadioButton) findViewById(R.id.repId7);

        reportRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.repId6) {

                    writeOther.setVisibility(View.VISIBLE);
                } else {
                    writeOther.setVisibility(View.GONE);
                }
            }


        });


        //style buttons
        Typeface font = Typeface.createFromAsset(getAssets(), "VAG-HandWritten.otf");
        Button b8 = (Button)findViewById(R.id.backId);
        Button b9 = (Button)findViewById(R.id.submitReportId);
        TextView t1 = (TextView)findViewById(R.id.titleId);
        t1.setTypeface(font);
        b8.setTypeface(font);
        b9.setTypeface(font);
        //
    }

    public void onBack(View view){
        finish();
    }

    public void submitReport(View view){

        if (!NetworkIsAvailable()){

            Toast.makeText(getApplicationContext(), "Παρακαλώ ελέγξτε τη σύνδεση σας στο Internet", Toast.LENGTH_LONG).show();

        }
        else{

            int selectedId = reportRG.getCheckedRadioButtonId();
            if (selectedId ==b1.getId()){
                subject = (String) b1.getText();

            }else if (selectedId ==b2.getId()){

                subject = (String) b2.getText();

            }else if (selectedId ==b3.getId()){

                subject = (String) b3.getText();

            }else if (selectedId ==b4.getId()){

                subject = (String) b4.getText();

            }else if (selectedId ==b5.getId()){

                subject = (String) b5.getText();

            } else if (selectedId ==b7.getId()){

            subject = (String) b7.getText();

            }
            else {
                subject = String.valueOf(writeOther.getText());

            }



            Properties props = new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port","465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.port","465");

            session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(sender, senderPassword);
                }
            });
            pdialog = ProgressDialog.show(context, "", "Αποστολή Αναφοράς...", true);

            RetrieveFeedTask task = new RetrieveFeedTask();
            task.execute();
        }

    }

    class RetrieveFeedTask extends AsyncTask<String,Void,String> {
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
            writeOther.setText("");

            Toast.makeText(getApplicationContext(),"Ευχαριστούμε για την αναφορά",Toast.LENGTH_LONG).show();
            finish();
        }



    }

    private boolean NetworkIsAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }





}
