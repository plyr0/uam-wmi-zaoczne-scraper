package pl.plyr0.scraper.web;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import pl.plyr0.scraper.model.Row;

public class TableParser {
    public static List<Row> parse(Element tbody) {
        Elements tableRows = tbody.getElementsByTag("tr");
        List<Row> result = new ArrayList<>(tableRows.size());
        for (Element tr : tableRows) {
            Elements el = tr.getElementsByTag("td");
            Row row = Row.builder()
                    .date(el.get(0).text())
                    .hourStart((el.get(1).text()))
                    .classroom1((el.get(2).text()))
                    .classroom2((el.get(3).text()))
                    .subject((el.get(4).text()))
                    .lector((el.get(5).text()))
                    .hoursLength((el.get(6).text()))
                    .hourEnd((el.get(7).text()))
                    .build();
            result.add(row);
        }
        return result;
    }
}
