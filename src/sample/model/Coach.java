package sample.model;

import java.time.LocalDate;
import java.util.Date;

public class Coach {

    private Integer id;
    private String name;
    private String surname;
    private Date birthDay;
    private String nationality;

    public Coach(Integer id, String name, String surname, Date birthDay, String nationality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.nationality = nationality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
