package entities;

import java.util.Date;

public class Reservation {
    private int id;
    private User user;
    private Place place;
    private Date dateIn;
    private Date dateOut;
    private String matricule;

    public Reservation(int id, User user, Place place, Date dateIn, Date dateOut, String matricule) {
        this.id = id;
        this.user = user;
        this.place = place;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.matricule = matricule;
    }

    public Reservation(User user, Place place, Date dateIn, Date dateOut, String matricule) {
        this.user = user;
        this.place = place;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.matricule = matricule;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Place getPlace() {
        return place;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public String getMatricule() {
        return matricule;
    }
}
