package model;

/**
 * Created by jonathan on 30-9-15.
 */
public class AccessToken {


    private String token;


    public AccessToken(String token) {
        this.token = token;

    }



    private void generateToken(final User user, final int tokenNumber){

        token = tokenNumber + user.getPassword();

    }
    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object obj) {
        return getToken().equals(obj);
    }
}
