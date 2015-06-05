package vncoop.genius;

import java.util.Random;

/**
 * ΓΙΑ ΝΑ ΠΑΡΩ ΚΑΙΝΟΥΡΙΑ ΕΠΙΛΟΓΗ ΚΑΛΏ ΤΗΝ getRandomCategories ΜΕ ΕΙΣΟΔΟ BOOLEAN[6]
 * ΠΟΥ ΠΕΡΙΕΧΕΙ ΤΑ ΔΙΑΜΑΝΤΙΑ ΤΗΣ ΟΜΑΔΑΣ
 *
 * Η ΣΥΝΑΡΤΗΣΗ getRandomCategories ΕΠΙΣΤΡΕΦΕΙ STRING[2]
 * STRING[0]=PRWTI EPILOGI
 * STRING[1]=DEYTERI EPILOGI
 * AN IPARXEI DIAMANTI  STRING[0]=TO DIAMANTI ME SIGKEKRIMENO XRWMA
 *                      STRING[1]="naS"
 */


public class RandomGenerator {

    //Βασική Συνάρτηση\\
    //επιλογή κατηγοριών ή διαμαντιού συγκεκριμένης κατηγορίας
    public static String[] getRandomCategories(boolean[] diamonds){

        Random r1;                          //random 0-21+dummy
        int rand1;                          //μπαίνει εδω
//        int length = diamonds.length;       //τιμή 6 (όλα τα διαμάντια που μπορεί να πάρει)
        int dummy = 0;                      //dummy παίρνει τιμή 0 κάθε φορά και αυξάνεται μετά

        String[] choices = new String[2];   //Epiloges edw gia return

        //Όσα διαμάντια έχει τόσα dummies υπάρχουν
        for (boolean diamond : diamonds) {
            if (diamond) {
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
        //else if(rand1>20){
        //    r1 =new Random();
        //    rand1 = r1.nextInt(15);
        //    choices = searchCategoryArray();
        //}

        //ΘΕΣΕΙΣ ΕΡΩΤΗΣΕΩΝ 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14
        else{
            choices = searchCategoryArray();
        }


        return choices;
    }


    private static String[] createCategoryArray(){
        String g = "geo_b";
        String c = "cim_b";
        String h = "his_b";
        String a = "art_b";
        String sc = "sci_b";
        String sp = "spo_b";

        String[] categoryArray = new String[6];
        categoryArray[0]=g;
        categoryArray[1]=c;
        categoryArray[2]=h;
        categoryArray[3]=a;
        categoryArray[4]=sc;
        categoryArray[5]=sp;

        return categoryArray;

    }

    private static String[] searchCategoryArray(){
        String[] array = createCategoryArray();           //Δημιουργώ array
        Random r = new Random();               //ορίζω random για να πάρω 0 ή 1
        int rand = r.nextInt(6);              //βάζω 0 ή 1 εδώ
        String[] choices = new String[2];       //οι προς επιστροφή επιλογές

        Random r1;
        r1 = new Random();
        int rand1 = r1.nextInt(6);
        while (rand1 == rand){
            r1 = new Random();
            rand1 = r1.nextInt(6);
        }

        choices[0] = array[rand];
        choices[1] = array[rand1];


        return choices;

    }

    //ΣΥΝΑΡΤΗΣΗ ΓΙΑ ΕΥΡΕΣΗ ΔΙΑΜΑΝΤΙΟΥ (RECURSIVE)\\
    private static String findDiamond(boolean[] diamonds, String[] da) {

        String diamondChoice;
        Random r1 = new Random();
        int rand1 = r1.nextInt(6);

        if (!diamonds[rand1]){
            diamondChoice = da[rand1];
        }
        else{
            diamondChoice = findDiamond(diamonds,da);
        }

        return diamondChoice;
    }

    //ΔΗΜΙΟΥΡΓΙΑ ΠΙΝΑΚΑ ΔΙΑΜΑΝΤΙΩΝ\\
    private static String[] createDiamondArray() {
        String[] da = new String[6];
        da[0] = "geo";
        da[1] = "cim";
        da[2] = "his";
        da[3] = "art";
        da[4] = "sci";
        da[5] = "spo";
        return da;
    }






}

