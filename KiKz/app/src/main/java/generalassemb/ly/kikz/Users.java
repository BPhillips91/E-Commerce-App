package generalassemb.ly.kikz;

/**
 * Created by brendan on 6/30/16.
 */
public class Users {
    protected String USER_FIRST_NAME;
    protected String USER_LAST_NAME;
    protected String USER_EMAIL;
    protected String USER_ADDRESS;
    protected String USER_ID;


   /* public Users(String USER_FIRST_NAME, String USER_LAST_NAME, String USER_EMAIL, String USER_ADDRESS, String USER_ID) {
        this.USER_FIRST_NAME = USER_FIRST_NAME;
        this.USER_LAST_NAME = USER_LAST_NAME;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_ADDRESS = USER_ADDRESS;
        this.USER_ID = USER_ID;
    }
    */
    public Users (){}

    public String getUSER_FIRST_NAME() {
        return USER_FIRST_NAME;
    }

    public void setUSER_FIRST_NAME(String USER_FIRST_NAME) {
        this.USER_FIRST_NAME = USER_FIRST_NAME;
    }

    public String getUSER_LAST_NAME() {
        return USER_LAST_NAME;
    }

    public void setUSER_LAST_NAME(String USER_LAST_NAME) {
        this.USER_LAST_NAME = USER_LAST_NAME;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getUSER_ADDRESS() {
        return USER_ADDRESS;
    }

    public void setUSER_ADDRESS(String USER_ADDRESS) {
        this.USER_ADDRESS = USER_ADDRESS;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
