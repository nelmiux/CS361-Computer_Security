import java.util.*;

class User {

    String uname;
    String salt;
    String password;
    String pname;
    String lname;
    String name;
    boolean done;
    ArrayList<String> passwords;

    User (String u, String s, String p, String fn, String ln){
        this.uname = u;
        this.salt = s;
        this.password = p;
        this.pname = fn;
        this.lname = ln;
        this.name = fn + ln;
        this.done = false;
        passwords = new ArrayList<String>();
    }
}