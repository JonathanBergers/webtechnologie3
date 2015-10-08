package model;

/**
 * Created by jonathan on 30-9-15.
 */
public class AccessToken {


    private String token;
    private User user;


    public AccessToken(final User user, int tokenNumber) {
        this.user = user;
        generateToken(user, tokenNumber);
    }

    public AccessToken() {
    }


    private void generateToken(final User user, final int tokenNumber){

        token = tokenNumber + user.getPassword();

    }
    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }



}
