package entity;

import java.sql.Time;
import java.sql.Timestamp;

public class disasterinfo {
    int dataid;
    String location;
    String longitude;
    String latitude;
    String category;
    String source;
    int grade;
    double magnitude;
    Timestamp created;
    public disasterinfo()
    {
        this.dataid = -1;
        this.location = "";
        this.longitude = "";
        this.latitude = "";
        this.category = "";
        this.source = "";
        this.grade = -1;
        this.magnitude = -1;
        this.created = Timestamp.valueOf("1970-01-01 00:00:00.0");
    }
    public disasterinfo(int dataid, String location, String longitude, String latitude, String category, String source, int grade, double magnitude, Timestamp created) {
        this.dataid = dataid;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.source = source;
        this.grade = grade;
        this.magnitude = magnitude;
        this.created = created;
    }

    @Override
    public String toString() {
        return "disasterinfo{" +
                "dataid=" + dataid +
                ", location='" + location + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", category='" + category + '\'' +
                ", source='" + source + '\'' +
                ", grade=" + grade +
                ", magnitude=" + magnitude +
                ", created=" + created +
                '}';
    }

    public int getDataid() {
        return dataid;
    }

    public void setDataid(int dataid) {
        this.dataid = dataid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
