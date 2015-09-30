package model;

/**
 * Created by jonathan on 30-9-15.
 */
public class AccessToken {


    private String token;

    public AccessToken(final User user) {
        generateToken(user);
    }

    public AccessToken() {
    }


    private void generateToken(final User user){

        token = user.getNickname() + user.getPassword();

    }



}
