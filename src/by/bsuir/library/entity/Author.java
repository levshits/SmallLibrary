package by.bsuir.library.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Valentin on 29.04.2015.
 */
@XmlRootElement(name = "author")
@XmlType(propOrder = {"firstName", "secondName", "born", "dead"} )
public class Author {
    private String firstName;
    private String secondName;
    private String born;
    private String dead;

    public String getFirstName() {
        return firstName;
    }
    @XmlElement(required = true, name = "firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }
    @XmlElement(required = true, name = "secondName")
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBorn() {
        return born;
    }
    @XmlElement(required = true, name = "born")
    public void setBorn(String born) {
        this.born = born;
    }

    public String getDead() {
        return dead;
    }
    @XmlElement(required = true, name = "dead")
    public void setDead(String dead) {
        this.dead = dead;
    }
}
