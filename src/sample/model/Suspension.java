package sample.model;

import java.util.Date;

public class Suspension {

    private Integer id;
    private Date start;
    private Date end;

    public Suspension(Integer id, Date start, Date end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Suspension() {
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
