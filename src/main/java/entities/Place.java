package entities;

public class Place {
    private int id;
    private int numero;
    private boolean etat;
    private int type;
    private Section section;

    public Place(int id, int numero, boolean etat, int type, Section section) {
        this.id = id;
        this.numero = numero;
        this.etat = etat;
        this.type = type;
        this.section = section;
    }

    public Place(int numero, boolean etat, int type, Section section) {
        this.numero = numero;
        this.etat = etat;
        this.type = type;
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public boolean getEtat() {
        return this.etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
