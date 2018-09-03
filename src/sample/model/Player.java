package sample.model;

public class Player {

    private Integer id;
    private Club clubId;
    private String name;
    private String surname;
    private int age;
    private int height;
    private int scoredPoints;

    public Player(Club clubId, String name, String surname, int age, int height, int scoredPoints) {
        this.clubId = clubId;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.height = height;
        this.scoredPoints = scoredPoints;
    }

    public Player(Integer id, Club clubId, String name, String surname, int age, int height, int scoredPoints) {
        this.id = id;
        this.clubId = clubId;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.height = height;
        this.scoredPoints = scoredPoints;
    }

    public Player() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Club getClubId() {
        return clubId;
    }

    public void setClubId(Club clubId) {
        this.clubId = clubId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public void setScoredPoints(int scoredPoints) {
        this.scoredPoints = scoredPoints;
    }
}
