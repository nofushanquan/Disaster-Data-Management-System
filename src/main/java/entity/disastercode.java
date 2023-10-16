package entity;


public class disastercode {
    int dataid;
    String Soucdata;
    String infodata;
    String geodata;

    public int getDataid() {
        return dataid;
    }

    public void setDataid(int dataid) {
        this.dataid = dataid;
    }

    public String getSoucdata() {
        return Soucdata;
    }

    public void setSoucdata(String soucdata) {
        Soucdata = soucdata;
    }

    public String getInfodata() {
        return infodata;
    }

    public void setInfodata(String infodata) {
        this.infodata = infodata;
    }

    public String getGeodata() {
        return geodata;
    }

    public void setGeodata(String geodata) {
        this.geodata = geodata;
    }

    @Override
    public String toString() {
        return "disastercode{" +
                "dataid=" + dataid +
                ", Soucdata='" + Soucdata + '\'' +
                ", infodata='" + infodata + '\'' +
                ", geodata='" + geodata + '\'' +
                '}';
    }
    public disastercode()
    {
        this.dataid = -1;
        this.Soucdata = "";
        this.infodata = "";
        this.geodata = "";
    }
    public disastercode(int dataid, String soucdata, String infodata, String geodata) {
        this.dataid = dataid;
        this.Soucdata = soucdata;
        this.infodata = infodata;
        this.geodata = geodata;
    }
}
