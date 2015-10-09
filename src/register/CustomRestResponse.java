package register;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeMap;

/**
 * Created by jonathan on 8-10-15.
 */
@XmlRootElement
public class CustomRestResponse {


    private boolean success;


    private TreeMap<String, String> errors = new TreeMap<>();

    public CustomRestResponse addError(final String subject, final String message){
        errors.put(subject, message);
        return this;
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
