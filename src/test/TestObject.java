package test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jonathan on 6-10-15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TestObject {


    private String name ;

    public TestObject(String name) {
        this.name = name;
    }

    public TestObject(){};

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }


    public void setName(String name) {
        this.name = name;
    }

}
