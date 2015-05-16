package vncoop.q02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
/**
 * Created by Alexandros on 20/4/2015.
 */
public class DBHelper extends SQLiteOpenHelper{


    //Ορισμός Χαρακτηριστικών της βάσης\\
    private static String db_path = "/data/data/vncoop.q02/databases/";
    private static String db_name = "FDB.sqlite";
    private static int db_version = 1;
    private static String table_name = "BT";
    private SQLiteDatabase my_db;
    private final Context con;  //its the context of current state of the application/object.
    // It lets newly created objects understand what has been going on.
    // Μπαίνει για τον προσδιορισμό των στοιχείων του Main




    //Δημιουργία Constructor για την κλάση Voithos\\
    public DBHelper(Context context) {

        super(context, db_name, null, db_version);

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

            Log.d("CheckDBexist", "DB not yet exists");
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

        //return checkDB != null;  // ? true : false;
    }

    //Αντιγραφή της βασης στην βάση του android\\
    private void copyDB() throws IOException{

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
        if (dbExists==true){
            Log.d("Existance","Oh YEEEEEah");
        }
        else{
            Log.d("I AM","TRYING TO CREATE DB");
            //Δημιουργία Κενής Βάσης
            this.getReadableDatabase(); //Create and/or open a database. This will be the same
            // object returned by getWritableDatabase() unless some
            // problem, such as a full disk, requires the database to
            // be opened read-only. In that case, a read-only database
            // object will be returned.
            // Δημιουργούμε την βάση στη default θεση
            // (έχει γίνει έλεγχος αν υπάρχει)
            //Αντιγραφή Δεδομένων
            try {
                Log.d("Copy","Trying to copy files");
                copyDB();
            } catch (IOException e){
                throw new Error("Skata antegrapsa");
            }

        }
    }

    //Άνοιξε την βάση\\
    public void openDataBase() throws SQLException {
        String myPath = db_path+db_name;
        my_db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        //Query();

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

    //Δημιουργία Query\\
    public void Query() {

        //String[] questio = new String[]{"1"};
        //Log.d("path = ",myDataBase.getPath());

        //Cursor cur = null;
        Cursor cur=null;

        cur = my_db.rawQuery("SELECT * FROM BT WHERE CAT = 1", null);
        String text,text1;
        int count=0;

        count = cur.getColumnCount();
        for (int i=0; i<5; i++) {
            text = cur.getColumnName(i);
            //text1 = cur.getString(cur.getColumnIndex(text));
            Log.d("try",text);
            //Log.d("try2",text1);
        }

        Log.d("count = ",Integer.toString(count));
        int q = cur.getColumnIndex("QUESTION");
        if (cur != null ) {

            cur.moveToPosition(2);

            text1 = cur.getString(q);
            Log.d("try",text1);
            //while (cur.moveToNext()) {

            //text1 = cur.getString(cur.getColumnIndex("CAT"));
            //text1 = String.valueOf(cur.getColumnIndex("QUESTION"));

            //int a = cur.getColumnIndex("ANSWER");

            // }


        }
        cur.close();



        //c.getColumnIndex("QUESTION");
        //text = cur.getString(cur.getColumnIndex("question"));
        //text = c.getString(c.getColumnIndex(c.getString(3)));
        //Log.d("try",text);
    }

    //Βασική συνάρτηση για να τραβάμε ερωτήσεις και απαντήσεις\\
    //επιστρέφει String[2] με String[0]=ερώτηση και String[1]=απάντηση
    public String[] randFromCat(int c){

        String[] QnA = new String[2];   //String εξόδου
        int q,a;    //αριθμό στήλης που έχει την κατηγορία question και answer αντιστοιχα
        Cursor cRandFromCat = null; //δημιουργία του Cursor

        int row_count;              //αριθμός γραμμών του αποτελέσματος του Query

        Random randomizer;          //για παραγωγή τυχαίου αριθμού
        int rand;                   //ο τυχαίος αριθμός
        ContentValues cv = new ContentValues();     //Απαραίτητο αφού το update απαιτεί σαν
        //δευτερο όρισμα cv

        //ENTOLES
        String queryText = "SELECT * FROM "+table_name+" WHERE CAT = "+c+" AND P=0";
        // Εντολή SQL για να ψάξει την κατηγορία υπ αριθμόν c (int c η είσοδος της συνάρτησης)
        // Όσες ερωτήσεις δεν έχουν παιχτεί

        openDBReadWrite();     // Άνοιγμα βάσης για να είναι Readable ////είναι και Writable


        cRandFromCat = my_db.rawQuery(queryText,null);  //Το αποτέλεσμα του query πάει στον Cursor


        q = cRandFromCat.getColumnIndex("QUESTION");    //ο q δείχνει τον αριθμό της στήλης
        // που έχει την κατηγορία question
        a = cRandFromCat.getColumnIndex("ANSWER");      // που έχει την κατηγορία answer

        row_count = cRandFromCat.getCount();    //πόσες γραμμες έχει το cursor

        //αν row_count =0 παίχτηκαν ολες οι ερωτήσεις
        if (cRandFromCat != null && row_count !=0) {

            randomizer = new Random();              //τυχαίος αριθμός
            rand = randomizer.nextInt(row_count);   //τυχαίος αριθμός 0-γραμμές cursor


            cRandFromCat.moveToPosition(rand);      //πήγαινε στη σειρα rand του cursor

            QnA[0] = cRandFromCat.getString(q);     //παρε την ερωτηση βάλτην στο String στη θεση 0
            QnA[1] = cRandFromCat.getString(a);     //παρε την απάντηση βάλτην στο String στη θεση 1
            int id = Integer.parseInt(cRandFromCat.getString(0)); //Παίρνω το id της επιλεγμένης ερώτησης



            Log.d("test", String.valueOf(id));
            String where = "_id = " + String.valueOf(id);


            cv.put("P",1);                            //Στο P(layed) δίνω τιμή 1


            my_db.update(table_name,cv,where,null);


            //todo γράψε στην ερώτηση ότι παίχτηκε

        }
        else{
            //Δεν υπάρχουν άλλες ερωτήσεις σε αυτή την κατηγορία άρα κάντες όλες όχι παιγμένες
            Log.d("Poutsa","Kai Ksilo");
            cv.put("P",0);                          //Βάλε στο P(layed) τιμή 0
            String where = "CAT = " + c;            //Στην κατηγορία με τιμή c
            my_db.update(table_name,cv,where,null); //update όλα σε 0
            QnA = randFromCat(c);                   //τρέξε πάλι την συνάρτηση
            //το νέο τρέξιμο βρίσκει αποτέλεσμα και το βάζει στο string QnA το οποίο στη συνέχεια
            //επιστρέφεται ως έξοδος
        }

        closeDB();      //κλείσε τη βάση
        return QnA;     //String QnA[0]=ερώτηση QnA[1] = απάντηση
    }

    //Default συναρτήσεις\\
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
