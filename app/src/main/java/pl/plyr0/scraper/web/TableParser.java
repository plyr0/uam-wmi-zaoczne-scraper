package pl.plyr0.scraper.web;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import pl.plyr0.scraper.model.Row;

public class TableParser {
    public static List<Row> parse(Document doc) {
        Element tbody = doc.select("#DataGrid1 > tbody").first();
        Elements tableRows = tbody.getElementsByTag("tr");
        List<Row> result = new ArrayList<>(tableRows.size());
        for (int i = 1; i < tableRows.size(); i++) {
            Element tr = tableRows.get(i);
            Elements el = tr.getElementsByTag("td");

            Row row = new Row();
            row.setDate(expose(el, 0));
            row.setHourStart(expose(el, 1));
            row.setClassroom1(expose(el, 2));
            row.setClassroom2(expose(el, 3));

            String subjAll = expose(el, 4);
            /*int indexOfDash = subjAll.indexOf("-");
            row.setSubject(subjAll.substring(indexOfDash+2, subjAll.length()));
            row.setCode(subjAll.substring(0, indexOfDash));*/
            row.setSubject(subjAll);

            row.setLector(expose(el, 5));
            row.setHoursLength(expose(el, 6));
            row.setHourEnd(expose(el, 7));
            result.add(row);
        }
        return result;
    }

    private static String expose(Elements e, int i) {
        return e.get(i).text();
    }
}
