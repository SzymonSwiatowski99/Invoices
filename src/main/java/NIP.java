public class NIP {
    private String nip;
    private String name;
    private String adress;
    public NIP(String nip, String name, String adress){
        this.nip=nip;
        this.name=name;
        this.adress=adress;

    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String toString(){
        return getNip();
    }
}
