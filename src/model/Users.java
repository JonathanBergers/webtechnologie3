package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 30-9-15.
 */
@XmlRootElement
public class Users {



    private List<User> users = new ArrayList<>();

    public Users(List<User> users) {
        this.users = users;
    }

    public Users(){

    }


    @XmlElement
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**adds a user
     *
     * @param u
     * @return true if added, false if not
     */
    public boolean addUser(final User u){

        if(!getUsers().contains(u)){
            users.add(u);
            return true;
        }
        return false;
    }
}
