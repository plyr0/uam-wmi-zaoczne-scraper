package pl.plyr0.scraper.model;

// Data	Od godz.	Sala 1	Sala 2	Przedmiot	Prowadzący	Godzin	Do godz.
//TODO: Lombok replacement

public class Row {
    private String date;
    private String hourStart;
    private String classroom1;
    private String classroom2;
    private String subject;
    private String lector;
    private String hoursLength;
    private String hourEnd;
    private String code;

    public Row() {
    }

    public Row(String date, String hourStart, String classroom1, String classroom2, String subject, String lector, String hoursLength, String hourEnd, String code) {
        this.date = date;
        this.hourStart = hourStart;
        this.classroom1 = classroom1;
        this.classroom2 = classroom2;
        this.subject = subject;
        this.lector = lector;
        this.hoursLength = hoursLength;
        this.hourEnd = hourEnd;
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHourStart() {
        return hourStart;
    }

    public void setHourStart(String hourStart) {
        this.hourStart = hourStart;
    }

    public String getClassroom1() {
        return classroom1;
    }

    public void setClassroom1(String classroom1) {
        this.classroom1 = classroom1;
    }

    public String getClassroom2() {
        return classroom2;
    }

    public void setClassroom2(String classroom2) {
        this.classroom2 = classroom2;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public String getHoursLength() {
        return hoursLength;
    }

    public void setHoursLength(String hoursLength) {
        this.hoursLength = hoursLength;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hourEnd) {
        this.hourEnd = hourEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
