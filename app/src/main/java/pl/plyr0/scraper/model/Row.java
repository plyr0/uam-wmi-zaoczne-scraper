package pl.plyr0.scraper.model;

// Data	Od godz.	Sala 1	Sala 2	Przedmiot	Prowadzący	Godzin	Do godz.

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class Row {
    String date;
    String hourStart;
    String classroom1;
    String classroom2;
    String subject;
    String lector;
    String hoursLength;
    String hourEnd;
    String code;
}
