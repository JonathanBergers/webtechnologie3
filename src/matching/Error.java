package matching;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by jonathan on 6-10-15.
 */
@XmlRootElement
public abstract class Error {

    protected String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public abstract String toString();

}
