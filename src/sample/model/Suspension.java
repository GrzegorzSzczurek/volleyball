package sample.model;

import java.util.Date;

public class Suspension {

    private Integer id;
    private Date start;
    private Date end;
    private Player playerId;

    public Suspension(Integer id, Date start, Date end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Suspension(Date start, Date end, Player playerId) {
        this.start = start;
        this.end = end;
        this.playerId = playerId;
    }

    public Suspension() {
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
