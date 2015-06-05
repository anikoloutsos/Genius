package vncoop.genius;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DBHelper extends SQLiteOpenHelper {


    //Ορισμός Χαρακτηριστικών της βάσης\\

    private static String db_path;
    private static String db_name = "FDB.sqlite";
    private static int db_version = 1;
    private SQLiteDatabase my_db;
    private final Context con;  //its the context of current state of the application/object.
    // It lets newly created objects understand what has been going on.
    // Μπαίνει για τον προσδιορισμό των στοιχείων του Main




    //Δημιουργία Constructor για την κλάση DBHelper\\
    public DBHelper(Context context) {

        super(context, db_name, null, db_version);
        db_path = context.getFilesDir().getParent()+"/databases/";
        con = context; //Αρχικοποίηση του context (υποχρεωτική)

    }


    //ΣΥΝΑΡΤΗΣΕΙΣ ΕΛΕΓΧΟΥ ΤΗΣ ΒΑΣΗΣ\\

    //Έλεγχος ύπαρξης της βάσης\\
    private boolean checkDBexist() {

        SQLiteDatabase checkDB = null;

        //try -> δοκίμασε να την ανοίξεις
        try{
            String myPath = db_path + db_name;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            //catch -> δεν υπάρχει
        } catch(SQLiteException e){
            //database does't exist yet.
        }

        //αν υπάρχει
        if(checkDB != null){

            checkDB.close(); //κλείσε την βάση checkDB
            return true;
        }
        else{
            return false;
        }
    }

    //Αντιγραφή της βασης στην βάση του android\\
    private void copyDB() throws IOException {

        //Χρήση Input Stream. Του βάζουμε τη βάση
        InputStream myInput = con.getAssets().open(db_name);

        // Path της φρέσκιας βάσης εντος Android συσκευής
        String outFileName = db_path+db_name;

        //Output stream η βάση εντος Android συσκευής
        OutputStream myOutput = new FileOutputStream(outFileName);

        //μεταφορά των bytes απο τη sqlite βάση στο android
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //κλείσιμο των streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }


    //Δημιουργία Κενής βάσης & αντιγραφή των στοιχείων της προϋπάρχουσας\\
    public void createDB() throws IOException{
        boolean dbExists = checkDBexist();
        if (!dbExists){

            //Δημιουργία Κενής Βάσης
            this.getReadableDatabase();
            // Δημιουργούμε την βάση στη default θεση
            // (έχει γίνει έλεγχος αν υπάρχει)
            //Αντιγραφή Δεδομένων
            try {
                copyDB();
            } catch (IOException e){
                throw new Error("Could not copy Database");
            }

        }
    }

    //Άνοιξε την βάση για να γράψεις\\
    public void openDBReadWrite() throws SQLException {
        String myPath = db_path+db_name;
        my_db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    //Κλείσε την βάση\\
    public synchronized void closeDB() {

        if(my_db!= null)
            my_db.close();

        super.close();

    }

    //Βασική συνάρτηση για να τραβάμε ερωτήσεις και απαντήσεις\\
    //επιστρέφει String[2] με String[0]=ερώτηση και String[1]=απάντηση
    public String[] randFromCat(int c){

        String[] QnA = new String[2];   //String εξόδου
        int q,a;    //αριθμό στήλης που έχει την κατηγορία question και answer αντιστοιχα
        Cursor cRandFromCat; //δημιουργία του Cursor

        int row_count;              //αριθμός γραμμών του αποτελέσματος του Query

        Random randomizer;          //για παραγωγή τυχαίου αριθμού
        int rand;                   //ο τυχαίος αριθμός
        ContentValues cv = new ContentValues();     //Απαραίτητο αφού το update απαιτεί σαν
        //δευτερο όρισμα cv

        //ENTOLES
        String table_name = "BT";
        String queryText = "SELECT * FROM "+ table_name +" WHERE CAT = "+c+" AND P=0";
        // Εντολή SQL για να ψάξει την κατηγορία υπ αριθμόν c (int c η είσοδος της συνάρτησης)
        // Όσες ερωτήσεις δεν έχουν παιχτεί

        openDBReadWrite();     // Άνοιγμα βάσης για να είναι Readable ////είναι και Writable


        cRandFromCat = my_db.rawQuery(queryText,null);  //Το αποτέλεσμα του query πάει στον Cursor


        q = cRandFromCat.getColumnIndex("QUESTION");    //ο q δείχνει τον αριθμό της στήλης
        // που έχει την κατηγορία question
        a = cRandFromCat.getColumnIndex("ANSWER");      // που έχει την κατηγορία answer

        row_count = cRandFromCat.getCount();    //πόσες γραμμες έχει το cursor

        //αν row_count =0 παίχτηκαν ολες οι ερωτήσεις
        if (row_count != 0) {

            randomizer = new Random();              //τυχαίος αριθμός
            rand = randomizer.nextInt(row_count);   //τυχαίος αριθμός 0-γραμμές cursor
            cRandFromCat.moveToPosition(rand);      //πήγαινε στη σειρα rand του cursor
            QnA[0] = cRandFromCat.getString(q);     //παρε την ερωτηση βάλτην στο String στη θεση 0
            QnA[1] = cRandFromCat.getString(a);     //παρε την απάντηση βάλτην στο String στη θεση 1
            int id = Integer.parseInt(cRandFromCat.getString(0)); //Παίρνω το id της επιλεγμένης ερώτησης
            String where = "_id = " + String.valueOf(id);
            cv.put("P",1);                            //Στο P(layed) δίνω τιμή 1
            my_db.update(table_name,cv,where,null);
        }
        else{
            //Δεν υπάρχουν άλλες ερωτήσεις σε αυτή την κατηγορία άρα κάντες όλες όχι παιγμένες
            cv.put("P",0);                          //Βάλε στο P(layed) τιμή 0
            String where = "CAT = " + c;            //Στην κατηγορία με τιμή c
            my_db.update(table_name,cv,where,null); //update όλα σε 0
            QnA = randFromCat(c);                   //τρέξε πάλι την συνάρτηση
            //το νέο τρέξιμο βρίσκει αποτέλεσμα και το βάζει στο string QnA το οποίο στη συνέχεια
            //επιστρέφεται ως έξοδος
        }

        try {
            QnA[0] = decrypt(QnA[0]);
            QnA[1] = decrypt(QnA[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }


        closeDB();      //κλείσε τη βάση
        cRandFromCat.close();
        return QnA;     //String QnA[0]=ερώτηση QnA[1] = απάντηση
    }

    //Default συναρτήσεις\\
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.decode(encryptedData, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
                    'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

}
