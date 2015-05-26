package vncoop.q02;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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


        context = this;
        subQue = (EditText) findViewById(R.id.submitQuestion);
        subAns = (EditText) findViewById(R.id.submitAnswer);

        rec = "geniusquizapp@gmail.com";

        sender = "geniusquizhandler@gmail.com";
        senderPassword = "funiculifunicula";

        subject = "New Question";

    }




    public void sendQuestion(View view) {

        if (!NetworkIsAvailable()){

            Toast.makeText(getApplicationContext(),"Παρακαλώ ελέγξτε τη σύνδεση σας στο Internet",Toast.LENGTH_LONG).show();

        }
        else{
            textMessage = subQue.getText()+"/n"+subAns.getText();

            Properties props = new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port","465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.port","465");

            session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
                protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                    return new javax.mail.PasswordAuthentication(sender,senderPassword);
                }
            });
            pdialog = ProgressDialog.show(context,"","Αποστολή Ερώτησης...",true);

            RetrieveFeedTask task = new RetrieveFeedTask();
            task.execute();
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

}
