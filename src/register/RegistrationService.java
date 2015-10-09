package register;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import model.Model;
import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jonathan on 8-10-15.
 * doet de validatie
 */
public class RegistrationService {


    private static Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9_]+$");
    private static Pattern PASSWORD_REGEX = Pattern.compile("^([^\\s]+)");
    //    private static Pattern EMAIL_REGEX = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private static Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}$");

//    private static Pattern PHONE_REGEX = Pattern.compile("(^(((0)[1-9]{2}[0-9][-| ]?[1-9]( ?[0-9]){5})|((\\+31|0|0031)[-| ]?[1-9][0-9][1-9]( ?[0-9]){6}))$)"
//            + "|(^(((\\\\+31|0|0031)6){1}[-| ]?[1-9]{1}( ?[0-9]){7})$)");
    private final JsonObject argRepr;


    private Model model;



    public RegistrationService(JsonObject argRepr, Model model) {
        this.argRepr = argRepr;
        this.model = model;
    }



    /**checks if the credentials given are correct
     * if they are, creates the User
     * @return
     */
    public CustomRestResponse validateUserCredentials(CustomRestResponse responseMessage){




        final String ID_FIRSTNAME = "firstname";
        final String ID_LASTNAME = "lastname";
        final String ID_PASSWORD = "password";
        final String ID_EMAIL = "email";
        final String ID_PASSWORD_CONFIRM = "passwordConfirm";



        String firstname = "";
        String lastName = "";
        String passWord = "";
        String email = "";
        String passWordConfirm = "";


        final JsonPrimitive propertyUsername = argRepr.getAsJsonPrimitive(ID_FIRSTNAME);
        final JsonPrimitive propertyPassword = argRepr.getAsJsonPrimitive(ID_PASSWORD);
        final JsonPrimitive propertyPasswordConfirm = argRepr.getAsJsonPrimitive(ID_PASSWORD_CONFIRM);
        final JsonPrimitive propertyEmail = argRepr.getAsJsonPrimitive(ID_EMAIL);
        final JsonPrimitive propertyLastname = argRepr.getAsJsonPrimitive(ID_LASTNAME);



        // check of alles is ingevuld en verkrijg parameter data


        if(propertyPassword != null){
            passWord = propertyPassword.getAsString();
        }else{
            responseMessage.addError("Field1", "NO_"+ "PASSWORD" + "_FIELD_SUBMITTED");
        }
        if(propertyLastname != null){
            lastName = propertyLastname.getAsString();
        }else{
            responseMessage.addError("Field2", "NO_"+ "LASTNAME" + "_FIELD_SUBMITTED");
        }

        if(propertyPasswordConfirm != null){
            passWordConfirm = propertyPasswordConfirm.getAsString();
        }else {
            responseMessage.addError("Field3", "NO_"+ "PASSWORDCONFIRM" + "_FIELD_SUBMITTED");
        }

        if(propertyEmail != null){
            email = propertyEmail.getAsString();
        }else{
            responseMessage.addError("Field4", "NO_"+ "EMAIL" + "_FIELD_SUBMITTED");
        }
            if(propertyUsername != null){
                firstname = propertyUsername.getAsString();
            }else{
                responseMessage.addError("Field5", "NO_"+ "FIRSTNAME" + "_FIELD_SUBMITTED");
            }

        // if errors, return message
        if(!responseMessage.isSuccess()){
            return responseMessage;
        }




        //input validation

        //passwords should match
        if (!passWord.equals(passWordConfirm)) {
            responseMessage.addError("password", "PASSWORD_NOT_MATCHING");
        }



            //username should be conform REGEX
            Matcher matcher = USERNAME_REGEX.matcher(firstname);
            if (!matcher.matches()) {
                responseMessage.addError("username", "ONE_AND_WORD_LETTERS_NUMBERS_ONLY");
            }


        //password should be conform REGEX
        matcher = PASSWORD_REGEX.matcher(passWord);
        if (!matcher.matches()) {
            responseMessage.addError("password1", "NO_BLANKS_IN_PASSWORD");
        }

        //email should be conform REGEX
        matcher = EMAIL_REGEX.matcher(email);
        if (!matcher.matches()) {
            responseMessage.addError("email", "NO_VALID_EMAIL_ADDRESS");
        }

        // if errors, return message
        if(!responseMessage.isSuccess()){
            return responseMessage;
        }





        if (model.getUserByName(firstname) != null) {


            responseMessage.addError("firstname", "ALREADY_REGISTERED");
            return responseMessage;


        }else{ model.addUser(new User(firstname, lastName, passWord, email));
           }

        return responseMessage;
    }







}
