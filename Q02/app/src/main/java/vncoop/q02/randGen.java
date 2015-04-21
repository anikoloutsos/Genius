package vncoop.q02;

import android.util.Log;

import java.util.Random;

/**
 * Created by Alexandros on 20/4/2015.
 *
 * ΓΙΑ ΝΑ ΠΑΡΩ ΚΑΙΝΟΥΡΙΑ ΕΠΙΛΟΓΗ ΚΑΛΏ ΤΗΝ getRandomCategories ΜΕ ΕΙΣΟΔΟ BOOLEAN[6]
 * ΠΟΥ ΠΕΡΙΕΧΕΙ ΤΑ ΔΙΑΜΑΝΤΙΑ ΤΗΣ ΟΜΑΔΑΣ
 *
 * Η ΣΥΝΑΡΤΗΣΗ getRandomCategories ΕΠΙΣΤΡΕΦΕΙ STRING[2]
 * STRING[0]=PRWTI EPILOGI
 * STRING[1]=DEYTERI EPILOGI
 * AN IPARXEI DIAMANTI  STRING[0]=TO DIAMANTI ME SIGKEKRIMENO XRWMA
 *                      STRING[1]="naS"
 */


public class randGen {


    //Δημιουργία Array\\
    //περιέχει τις αντιστοιχίσεις για όλα τα χρώματα και τις θέσεις
    public String[] makeArray(){

        // Declarations
        String[] CategoryArray = new String[60]; //Array Declaration
        String g1 = "geo1";
        String g2 = "geo2";
        String c1 = "cim1";
        String c2 = "cim2";
        String h1 = "his1";
        String h2 ="his2";
        String a1 = "art1";
        String a2 = "art2";
        String sc1 = "sci1";
        String sc2 = "sci2";
        String sp1 = "spo1";
        String sp2 = "spo2";

        //Initialization

        int i=0;
        for (i =0;i<5;i++) {
            CategoryArray[i] = g1;    //Geography on 1st pos
            CategoryArray[i + 45] = g2; //Geography on 2nd pos
            if (i != 4) {
                CategoryArray[i + 5] = c1;    //cinema on 1st
                CategoryArray[i + 50] = c2;   //cinema on 2nd
            } else {
                CategoryArray[15] = c1;       //cinema on 1st
                CategoryArray[30] = c2;       //cinema on 2nd
            }
        }

        //history 1st
        CategoryArray[9] = h1;
        CategoryArray[10] = h1;
        CategoryArray[11] = h1;
        CategoryArray[16] = h1;
        CategoryArray[20] = h1;
        //history 2nd
        CategoryArray[31] = h2;
        CategoryArray[35] = h2;
        CategoryArray[54] = h2;
        CategoryArray[55] = h2;
        CategoryArray[56] = h2;

        //art 1st
        CategoryArray[12] = a1;
        CategoryArray[13] = a1;
        CategoryArray[17] = a1;
        CategoryArray[21] = a1;
        CategoryArray[24] = a1;
        //art 2nd
        CategoryArray[32] = a2;
        CategoryArray[36] = a2;
        CategoryArray[39] = a2;
        CategoryArray[57] = a2;
        CategoryArray[58] = a2;

        //science 1st
        CategoryArray[14] = sc1;
        CategoryArray[18] = sc1;
        CategoryArray[22] = sc1;
        CategoryArray[25] = sc1;
        CategoryArray[27] = sc1;
        //science 2nd
        CategoryArray[33] = sc2;
        CategoryArray[37] = sc2;
        CategoryArray[40] = sc2;
        CategoryArray[42] = sc2;
        CategoryArray[59] = sc2;

        //sports 1st
        CategoryArray[19] = sp1;
        CategoryArray[23] = sp1;
        CategoryArray[26] = sp1;
        CategoryArray[28] = sp1;
        CategoryArray[29] = sp1;
        //sports 2nd
        CategoryArray[34] = sp2;
        CategoryArray[38] = sp2;
        CategoryArray[41] = sp2;
        CategoryArray[43] = sp2;
        CategoryArray[44] = sp2;

        return CategoryArray;

    }

    //Βασική Συνάρτηση\\
    //επιλογή κατηγοριών ή διαμαντιού συγκεκριμένης κατηγορίας
    public String[] getRandomCategories(boolean[] diamonds){

        Random r1;                          //random 0-21+dummy
        int rand1;                          //μπαίνει εδω
        int length = diamonds.length;       //τιμή 6 (όλα τα διαμάντια που μπορεί να πάρει)
        int dummy = 0;                      //dummy παίρνει τιμή 0 κάθε φορά και αυξάνεται μετά

        String[] choices = new String[2];   //Epiloges edw gia return

        //Όσα διαμάντια έχει τόσα dummies υπάρχουν
        for (int i=0;i<length;i++){
            if (diamonds[i] == true){
                dummy++;
            }
        }


        r1 = new Random();
        rand1 = r1.nextInt(21+dummy);

        //ΘΕΣΕΙΣ ΔΙΑΜΑΝΤΙΩΝ 15,16,17,18,19,20
        if (rand1 > 14 && rand1 < 21){
            String[] da = createDiamondArray();
            choices[0] = findDiamond(diamonds,da);
            choices[1] = "naS"; //Η δευτερη θέση του στρινγκ δεν ειναι χρήσιμη  naS: not a String
        }

        //ΘΕΣΕΙΣ DUMMIES 21,22,23,24,25
        else if(rand1>20){
            r1 =new Random();
            rand1 = r1.nextInt(15);
            choices = searchLookUpArray(rand1);
        }

        //ΘΕΣΕΙΣ ΕΡΩΤΗΣΕΩΝ 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14
        else{
            choices = searchLookUpArray(rand1);
        }


        return choices;
    }

    //ΣΥΝΑΡΤΗΣΗ ΓΙΑ ΕΥΡΕΣΗ ΔΙΑΜΑΝΤΙΟΥ (RECURSIVE)\\
    private String findDiamond(boolean[] diamonds, String[] da) {

        String diamondChoice;
        Random r1 = new Random();
        int rand1 = r1.nextInt(6);

        if (diamonds[rand1] == false){
            diamondChoice = da[rand1];
        }
        else{
            diamondChoice = findDiamond(diamonds,da);
        }

        return diamondChoice;
    }

    //ΔΗΜΙΟΥΡΓΙΑ ΠΙΝΑΚΑ ΔΙΑΜΑΝΤΙΩΝ\\
    private String[] createDiamondArray() {
        String[] da = new String[6];
        da[0] = "diamondgeo";
        da[1] = "diamondcim";
        da[2] = "diamondhis";
        da[3] = "diamondart";
        da[4] = "diamondsci";
        da[5] = "diamondspo";
        return da;
    }

    //ΑΝΑΖΗΤΗΣΗ ΣΤΟΝ ΠΙΝΑΚΑ ΜΕ ΤΙΣ ΚΑΤΗΓΟΡΙΕΣ
    public String[] searchLookUpArray(int rand1){

        String[] array = makeArray();           //Δημιουργώ array
        Random r2 = new Random();               //ορίζω random για να πάρω 0 ή 1
        int rand2 = r2.nextInt(2);              //βάζω 0 ή 1 εδώ
        String[] choices = new String[2];       //οι προς επιστροφή επιλογές

        //Προσθήκη απαντήσεων το rand2*15 ορίζει τις θέσεις όπου θα είναι τα δύο χρώματα
        choices[0] = array[rand1+rand2*15];     //βάζω στη θέση 0 την πρώτη επιλογή
        choices[1] = array[rand1+30+rand2*15];  //βάζω στη θέση 1 την δευτερη επιλογή
                                                //το +30 ειναι λόγω μονοδιάστατου πίνακα
                                                // και με πηγαίνει στην δευτερη επιλογη
        return choices;
    }
}
