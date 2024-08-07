package form.clss.cde;

public class Guichet {
    private int idguichet;
    private String locaguichet;
    private String typeguichet;
    private int idbanque;

    public Guichet(int idguichet, String locaguichet, String typeguichet, int idbanque) {
        this.idguichet = idguichet;
        this.locaguichet = locaguichet;
        this.typeguichet = typeguichet;
        this.idbanque = idbanque;
    }

    public int getIdguichet() {
        return idguichet;
    }

    public void setIdguichet(int idguichet) {
        this.idguichet = idguichet;
    }

    public String getLocaguichet() {
        return locaguichet;
    }

    public void setLocaguichet(String locaguichet) {
        this.locaguichet = locaguichet;
    }

    public String getTypeguichet() {
        return typeguichet;
    }

    public void setTypeguichet(String typeguichet) {
        this.typeguichet = typeguichet;
    }

    public int getIdbanque() {
        return idbanque;
    }

    public void setIdbanque(int idbanque) {
        this.idbanque = idbanque;
    }
}
