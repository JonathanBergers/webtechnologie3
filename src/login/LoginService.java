package login;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import model.AccessToken;
import model.Model;
import model.User;
import register.CustomRestResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by falco on 25-10-15.
 */
public class LoginService {
    private static Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9_]+$");
    private static Pattern PASSWORD_REGEX = Pattern.compile("^([^\\s]+)");

    private final JsonObject argRepr;


    private Model model;



    public LoginService(JsonObject argRepr, Model model) {
        this.argRepr = argRepr;
        this.model = model;
    }

    public CustomRestResponse validateUserCredentials(CustomRestResponse responseMessage){
        final String ID_FIRSTNAME = "firstname";
        final String ID_PASSWORD = "password";
        final String ID_TOKEN = "NotflixToken";


        final JsonPrimitive propertyToken = argRepr.getAsJsonPrimitive(ID_TOKEN);
        if(propertyToken!=null){
            String token = propertyToken.getAsString();
            User u = model.getUserWithAccessToken(token);
            if(u != null){
                responseMessage.addMessage("firstname", u.getFirstname());
            } else {
                responseMessage.addError("AccessToken", "AccessToken is not correct");
            }
            return responseMessage;
        }

        String firstname = "";
        String passWord = "";


        final JsonPrimitive propertyUsername = argRepr.getAsJsonPrimitive(ID_FIRSTNAME);
        final JsonPrimitive propertyPassword = argRepr.getAsJsonPrimitive(ID_PASSWORD);



        // check of alles is ingevuld en verkrijg parameter data


        if(propertyPassword != null){
            passWord = propertyPassword.getAsString();
        }else{
            responseMessage.addError("Field1", "NO_"+ "PASSWORD" + "_FIELD_SUBMITTED");
        }

        if(propertyUsername != null){
            firstname = propertyUsername.getAsString();
        }else{
            responseMessage.addError("Field2", "NO_"+ "FIRSTNAME" + "_FIELD_SUBMITTED");
        }

        // if errors, return message
        if(!responseMessage.isSuccess()){
            return responseMessage;
        }








        //username should be conform REGEX
        Matcher matcher = USERNAME_REGEX.matcher(firstname);
        if (!matcher.matches()) {
            responseMessage.addError("username", "ONE_AND_WORD_LETTERS_NUMBERS_ONLY");
        }


        //password should be conform REGEX
        matcher = PASSWORD_REGEX.matcher(passWord);
        if (!matcher.matches()) {
            responseMessage.addError("password", "NO_BLANKS_IN_PASSWORD");
        }



        // if errors, return message
        if(!responseMessage.isSuccess()){
            return responseMessage;
        }


        AccessToken token = (model.getAccessTokenByLogin(firstname, passWord));

        if(token==null){
            responseMessage.addError("login", "username or password not correct");
            return responseMessage;
        }else{
            responseMessage.addMessage("accesstoken", token.getToken());
        }



        return responseMessage;
    }

}
