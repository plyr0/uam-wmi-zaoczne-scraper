package pl.plyr0.scraper;

// Data	Od godz.	Sala 1	Sala 2	Przedmiot	Prowadzący	Godzin	Do godz.

import lombok.Data;

@Data
public class Row {
    String date;
    String hourStart;
    String classroom1;
    String classroom2;
    String subject;
    String lector;
    String hoursLength;
    String hourEnd;
}
