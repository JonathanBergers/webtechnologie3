package register;

import com.fasterxml.jackson.annotation.JsonRawValue;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeMap;

/**
 * Created by jonathan on 8-10-15.
 * soms serializeerd deze wel en soms niet .
 */
@XmlRootElement
public class CustomRestResponse {


    private boolean success;


    private TreeMap<String, String> errors = new TreeMap<>();

    public CustomRestResponse addError(final String subject, final String message){
        errors.put(subject, message);
        return this;
    }
    private TreeMap<String, String> messages = new TreeMap<>();

    public CustomRestResponse addMessage(final String subject, final String message){
        messages.put(subject, message);
        return this;
    }

    public TreeMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(TreeMap<String, String> messages) {
        this.messages = messages;
    }

    @XmlElementWrapper
    public TreeMap<String, String> getErrors() {
        return errors;
    }

    public boolean isSuccess() {
        return getErrors().size() == 0;
    }

    public CustomRestResponse(){}

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrors(TreeMap<String, String> errors) {
        this.errors = errors;
    }




}
