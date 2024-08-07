package form.clss.cde;

public class Emplacement {
    private int idempla;
    private int idbanque;
    private int idguichet;
    private String emplacement;

    public Emplacement( int idempla, String emplacement, int idbanque, int idguichet)
    {
        this.idempla = idempla;
        this.emplacement = emplacement;
        this.idbanque = idbanque;
        this.idguichet = idguichet;
    }

    public int getIdempla() {
        return idempla;
    }

    public void setIdempla(int idempla) {
        this.idempla = idempla;
    }

    public int getIdbanque() {
        return idbanque;
    }

    public void setIdbanque(int idbanque) {
        this.idbanque = idbanque;
    }

    public int getIdguichet() {
        return idguichet;
    }

    public void setIdguichet(int idguichet) {
        this.idguichet = idguichet;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }
}
