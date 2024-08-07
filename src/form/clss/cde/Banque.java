package form.clss.cde;

public class Banque {
    private int idbanque;
    private String nombanque;
    private int telbanque;

    public Banque(int idbanque, String nombanque, int telbanque){
        this.idbanque = idbanque;
        this.nombanque = nombanque;
        this.telbanque = telbanque;
    }
    //getters
    public int getIdbanque(){
        return this.idbanque;
    }
    public String getNombanque(){
        return this.nombanque;
    }
    public int getTelbanque(){
        return this.telbanque;
    }

    //seters
    public void setIdbanque(int idbanque){
        this.idbanque = idbanque;
    }
    public void setNombanque(String nombanque){
        this.nombanque = nombanque;
    }
    public void setTelbanque(int telbanque){
        this.telbanque = telbanque;
    }

}
