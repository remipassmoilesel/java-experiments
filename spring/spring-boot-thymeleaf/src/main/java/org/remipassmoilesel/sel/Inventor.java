package org.remipassmoilesel.sel;

import java.util.Date;

/**
 * Created by remipassmoilesel on 28/02/17.
 */
public class Inventor {

    private String name;
    private String nationality;
    private Date birthdate;


    public Inventor(String name, String nationality, Date birthdate) {
        this.name = name;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Inventor{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
