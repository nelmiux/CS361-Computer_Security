import java.io.*;
import java.lang.String;
import java.lang.Object;
import java.util.*;

class PasswordCrack {

    public static void main (String args[]) throws Exception {

        long stime = System.currentTimeMillis();
        File dict = new File(args[0]);
        File toCrack = new File(args[1]);
        User[] users = new User[20];
        ArrayList<String> pass = new ArrayList<String>();
        ArrayList<String> w = new ArrayList<String>();
        ArrayList<String> myDict = new ArrayList<String>();

        int n = 0;
        Scanner dictWords = new Scanner(new FileReader(dict));
        while (dictWords.hasNextLine()) w.add(dictWords.nextLine());

        Scanner userWords = new Scanner(new FileReader(toCrack));
        String l;
        String sep = ":";
        String sp = " ";
        int i = 0;
        while (userWords.hasNextLine()) {
            l = userWords.nextLine();
            String[] wrd = l.split(sep);
            String uname = wrd[0];
            String salted = wrd[1];
            String[] name = wrd[4].split(sp);
            String salt = salted.substring(0, 2);
            String pw = salted.substring(2, salted.length());
            users[i] = new User(uname, salt, pw, name[0], name[1]);
            i++;
        }

        for (int j = 0; j < users.length; j++) {
            ArrayList<String> merge_helper = new ArrayList<String>(oneM(users[j].uname));
            ArrayList<String> mergeHelper4 = new ArrayList<String>(twoM(users[j].uname));
            ArrayList<String> mergeHelper7 = new ArrayList<String>(fourM(users[j].uname));
            ArrayList<String> mergeHelper10 = new ArrayList<String>(fiveM(users[j].uname));
            users[j].passwords.addAll(mergeHelper10);
            users[j].passwords.addAll(mergeHelper7);
            users[j].passwords.addAll(merge_helper);
            users[j].passwords.addAll(mergeHelper4);
            ArrayList<String> merge_helper2 = new ArrayList<String>(oneM(users[j].pname));
            ArrayList<String> mergeHelper5 = new ArrayList<String>(twoM(users[j].pname));
            ArrayList<String> mergeHelper8 = new ArrayList<String>(fourM(users[j].pname));
            ArrayList<String> mergeHelper11 = new ArrayList<String>(fiveM(users[j].pname));
            users[j].passwords.addAll(mergeHelper11);
            users[j].passwords.addAll(mergeHelper8);
            users[j].passwords.addAll(merge_helper2);
            users[j].passwords.addAll(mergeHelper5);
            ArrayList<String> mergeHelper3 = new ArrayList<String>(oneM(users[j].lname));
            ArrayList<String> mergeHelper6 = new ArrayList<String>(twoM(users[j].lname));
            ArrayList<String> mergeHelper9 = new ArrayList<String>(fourM(users[j].lname));
            ArrayList<String> mergeHelper12 = new ArrayList<String>(fiveM(users[j].lname));
            users[j].passwords.addAll(mergeHelper12);
            users[j].passwords.addAll(mergeHelper9);
            users[j].passwords.addAll(mergeHelper3);
            users[j].passwords.addAll(mergeHelper6);
            ArrayList<String> mergeHelper13 = new ArrayList<String>(oneM(users[j].name));
            ArrayList<String> mergeHelper14 = new ArrayList<String>(twoM(users[j].name));
            ArrayList<String> mergeHelper15 = new ArrayList<String>(fourM(users[j].name));
            ArrayList<String> mergeHelper16 = new ArrayList<String>(fiveM(users[j].name));
            users[j].passwords.addAll(mergeHelper13);
            users[j].passwords.addAll(mergeHelper14);
            users[j].passwords.addAll(mergeHelper15);
            users[j].passwords.addAll(mergeHelper16);
        }

        for (int j = 0; j < w.size(); j++) {
            ArrayList<String> merge_helper = new ArrayList<String>(oneM(w.get(j)));
            myDict.addAll(merge_helper);
            ArrayList<String> merge_helper2 = new ArrayList<String>(twoM(w.get(j)));
            myDict.addAll(merge_helper2);
            ArrayList<String> mergeHelper3 = new ArrayList<String>(fourM(w.get(j)));
            myDict.addAll(mergeHelper3);
            ArrayList<String> mergeHelper4 = new ArrayList<String>(fiveM(w.get(j)));
            myDict.addAll(mergeHelper4);
        }

        for (int c = 0; c < users[0].passwords.size(); c++)
            for(int j = 0; j < users.length; j++)
                if(users[j].done == false) n = PasswordCrack.match(users[j], users[j].passwords.get(c), n);

         String[] easies = {"oracle", "pinguin", "basketball", "pretty", "utexas", "michael", "Lacque", "liagiba", "amazing", "eeffoc", 
                            "letmein","myPassword", "healthy", "monday", "uPLIFTr", "password", "12345678", "abcd1234", "christian",
                            "qwerty", "baseball", "Pa$$word", "iloveyou", "1234567", "tobby", "johnny", "maria", "password0"};

        for (int c = 0; c < easies.length; c++)
            for(int j = 0; j < users.length; j++)
                if(users[j].done == false) n = PasswordCrack.match(users[j], easies[c], n);
        
        for (int c = 0; c < myDict.size(); c++)
            for(int j = 0; j < users.length; j++)
                if(users[j].done == false) n = PasswordCrack.match(users[j], myDict.get(c), n);
        double timeSpend = 0;
        String str;
        do {
            for(int c = 0; c < w.size(); c++) {
                str = PasswordCrack.manglesAll(w.get(c));
                int rr = new Random().nextInt(20);
                for(int k = 0; k < rr; k++) str = PasswordCrack.manglesAll(str);
                for(int j = 0; j < users.length; j++) {
                    if(users[j].done == false) n = PasswordCrack.match(users[j], str, n);
                    else timeSpend = (System.currentTimeMillis() - stime) / 1000.0;
                }
            } 
        } while (((System.currentTimeMillis() - stime) / 1000.0) < 300);

        System.out.println();
        for(int j = 0; j < users.length; j++)
                if(users[j].done == false) System.out.println("Uncracked:" + users[j].uname);

        System.out.println("\nI can crack " + n + " cases in " + timeSpend + " seconds.\n");
        int notDone = users.length - n;
        System.out.println("I cannot crack " + notDone + " cases.\n");
    }

    public static ArrayList<String> prepend (String str) {
        ArrayList<String> g = new ArrayList<String>();
        for(int i = 32; i < 125; i++) g.add(Character.toString((char)i) + str);
        return g;
    }

    public static ArrayList<String> append (String str) {
        ArrayList<String> g = new ArrayList<String>();
            for(int i = 32; i < 125; i++) g.add(str + Character.toString((char)i));
        return g;
    }

    public static String prepend2 (String str) {
        Random r = new Random();
        int i = r.nextInt((125 - 32) + 1) + 32;
        return Character.toString((char)i) + str;
    }

    public static String append2 (String str) {
        Random r = new Random();
        int i = r.nextInt((125 - 32) + 1) + 32;
        return str + Character.toString((char)i);
    }

    public static String upper (String str) { return str.toUpperCase(); }

    public static String lower (String str) { return str.toLowerCase(); }

    public static String duplicate (String str) { return str + str; }

    public static String reverse (String str) { return new StringBuilder(str).reverse().toString(); }

    public static String deleteFirst (String str) {
        if(str.length() > 0) return str.substring(1, str.length());
        else return str;
    }

    public static String deleteLast (String str) {
        if(str.length() > 0) return str.substring(0, str.length() - 1);
        else return str;
    }    

    public static String reflect (String str) { return str + new StringBuilder(str).reverse().toString(); }

    public static String capitalize (String str){
        if(str.length() > 0) return str.substring(0, 1).toUpperCase() + str.substring(1, str.length()).toLowerCase();
        else return str;
    }

    public static String ncapitalize (String str) {
        if(str.length() > 0) return str.substring(0, 1).toLowerCase() + str.substring(1, str.length()).toUpperCase();
        else return str;
    }

    public static String toggleCase (String str, int c) {
        int t;
        if(c % 2 == 0) t = 0;
        else t = 1;;
        StringBuilder srt1 = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(i % 2 == t) srt1.append(str.substring(i, i + 1).toUpperCase());
            else srt1.append(str.substring(i, i + 1).toLowerCase());
        }
        return srt1.toString();
    }

    public static String toggleCase2 (String str) {
        StringBuilder srt1 = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(i % 2 == 0) srt1.append(str.substring(i, i + 1).toUpperCase());
            else srt1.append(str.substring(i, i + 1).toLowerCase());
        }
        return srt1.toString();
    }

    public static String toggleCase3 (String str) {
        StringBuilder srt1 = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(i % 2 == 1) srt1.append(str.substring(i, i + 1).toUpperCase());
            else srt1.append(str.substring(i, i + 1).toLowerCase());
        }
        return srt1.toString();
    }

    public static String replace2 (String str) {
        String t1 = str.replace("e", "3");
        String t2 = t1.replace("E", "3");
        return t2;
    }

    public static String replace3 (String str) {
        String t1 = str.replace("o", "0");
        String t2 = t1.replace("O", "0");
        return t2;
    }

    public static String replace4 (String str) {
        String t1 = str.replace("a", "@");
        String t2 = t1.replace("A", "@");
        return t2;
    }

    public static String replace5 (String str) {
        String t1 = str.replace("l", "1");
        String t2 = t1.replace("L", "1");
        return t2;
    }

    public static String replace6 (String str) {
        String t1 = str.replace("i", "!");
        String t2 = t1.replace("I", "!");
        return t2;
    }

    public static String replace7 (String str) {
        String t1 = PasswordCrack.replace2(PasswordCrack.replace3(PasswordCrack.replace6(PasswordCrack.replace4(PasswordCrack.replace5(str)))));
        return t1;
    }

    public static ArrayList<String> oneM (String str) {
        ArrayList<String> guess = new ArrayList<String>();
        guess.add(PasswordCrack.lower(str));
        guess.add(PasswordCrack.upper(str));
        guess.add(PasswordCrack.duplicate(str));
        guess.add(PasswordCrack.reverse(str));
        guess.add(PasswordCrack.deleteFirst(str));
        guess.add(PasswordCrack.deleteLast(str));
        guess.add(PasswordCrack.reflect(str));
        guess.add(PasswordCrack.capitalize(str));
        guess.add(PasswordCrack.ncapitalize(str));
        guess.add(PasswordCrack.toggleCase(str, 1));
        guess.add(PasswordCrack.toggleCase(str, 2));
        ArrayList<String> merge_helper = new ArrayList<String>(PasswordCrack.prepend(str));
        guess.addAll(merge_helper);
        ArrayList<String> merge_helper2 = new ArrayList<String>(PasswordCrack.append(str));
        guess.addAll(merge_helper2);
        return guess;
    }

    public static ArrayList<String> twoM (String str) {
        ArrayList<String> guess = new ArrayList<String>();
        guess.add(PasswordCrack.reverse(str) + str);
        ArrayList<String> merge_helper = new ArrayList<String>(PasswordCrack.append(PasswordCrack.capitalize(str)));
        guess.addAll(merge_helper);
        ArrayList<String> merge_helper2 = new ArrayList<String>(PasswordCrack.prepend(PasswordCrack.ncapitalize(str)));
        guess.addAll(merge_helper2);
        ArrayList<String> mergeHelper3 = new ArrayList<String>(PasswordCrack.append(PasswordCrack.capitalize(str)));
        guess.addAll(mergeHelper3);
        ArrayList<String> mergeHelper4 = new ArrayList<String>(PasswordCrack.prepend(PasswordCrack.ncapitalize(str)));
        guess.addAll(mergeHelper4);
        return guess;
    }

    public static ArrayList<String> threeM (String str) {
        ArrayList<String> guess = new ArrayList<String>();
        guess.add(PasswordCrack.reverse(str) + str);
        ArrayList<String> merge_helper = new ArrayList<String>(PasswordCrack.append(PasswordCrack.capitalize(str)));
        guess.addAll(merge_helper);
        ArrayList<String> merge_helper2 = new ArrayList<String>(PasswordCrack.prepend(PasswordCrack.ncapitalize(str)));
        guess.addAll(merge_helper2);
        ArrayList<String> mergeHelper3 = new ArrayList<String>(PasswordCrack.append(PasswordCrack.capitalize(str)));
        guess.addAll(mergeHelper3);
        ArrayList<String> mergeHelper4 = new ArrayList<String>(PasswordCrack.prepend(PasswordCrack.ncapitalize(str)));
        guess.addAll(mergeHelper4);
        return guess;
    }

    public static ArrayList<String> fourM (String str) {
        ArrayList<String> guess = new ArrayList<String>();
        guess.add(PasswordCrack.replace2(str));
        guess.add(PasswordCrack.replace5(str));
        guess.add(PasswordCrack.replace4(str));
        guess.add(PasswordCrack.replace6(str));
        guess.add(PasswordCrack.replace3(str));
        guess.add(PasswordCrack.replace7(str));
        return guess;
    }

    public static ArrayList<String> fiveM (String str) {
        ArrayList<String> guess = new ArrayList<String>();
        guess.add(PasswordCrack.replace7(PasswordCrack.capitalize(str)));
        guess.add(PasswordCrack.replace7(PasswordCrack.ncapitalize(str)));
        guess.add(PasswordCrack.replace7(PasswordCrack.upper(str)));
        guess.add(PasswordCrack.replace7(PasswordCrack.lower(str)));
        guess.add(PasswordCrack.replace7(PasswordCrack.toggleCase(str, 1)));
        guess.add(PasswordCrack.replace7(PasswordCrack.toggleCase(str, 2)));
        guess.add(PasswordCrack.replace7(PasswordCrack.duplicate(str)));
        guess.add(PasswordCrack.replace7(PasswordCrack.reverse(str)));
        guess.add(PasswordCrack.replace7(PasswordCrack.reflect(str)));
        guess.add(PasswordCrack.replace7(PasswordCrack.deleteLast(str)));
        guess.add(PasswordCrack.replace7(PasswordCrack.deleteFirst(str)));
        return guess;
    }

    public static int match (User str, String entry, int n) {
        String enc = jcrypt.crypt(str.salt, entry);
        if(enc.regionMatches(0, str.salt + str.password, 0, 8)) {
            ++n;
            String t = "";
            String u = str.uname;
            for (int i = 1; i < (16 - u.length()); ++i) t = t + " ";
            if (n < 10) System.out.println("0" + n + ".- Username: " + u + t + "\tPassword: " + entry);
            else System.out.println(n + ".- Username: " + u + t + "\tPassword: " + entry);
            str.done = true;
        }
        return n;
    }

    public static String manglesAll(String str) {
        int t = new Random().nextInt(17);
        String cstr = "";
        if (t == 0) cstr = PasswordCrack.upper(str);
        if (t == 1) cstr = PasswordCrack.lower(str);
        if (t == 2) cstr = PasswordCrack.duplicate(str);
        if (t == 3) cstr = PasswordCrack.reverse(str);
        if (t == 4) cstr = PasswordCrack.deleteFirst(str);
        if (t == 5) cstr = PasswordCrack.deleteLast(str);
        if (t == 6) cstr = PasswordCrack.reflect(str);
        if (t == 7) cstr = PasswordCrack.capitalize(str);
        if (t == 8) cstr = PasswordCrack.ncapitalize(str);
        if (t == 9) cstr = PasswordCrack.toggleCase2(str);
        if (t == 10) cstr = PasswordCrack.toggleCase3(str);
        if (t == 11) cstr = PasswordCrack.replace2(str);
        if (t == 12) cstr = PasswordCrack.replace3(str);
        if (t == 13) cstr = PasswordCrack.replace4(str);
        if (t == 14) cstr = PasswordCrack.replace5(str);
        if (t == 15) cstr = PasswordCrack.replace6(str);
        if (t == 16) cstr = PasswordCrack.replace7(str);

        return cstr;      
    }
}