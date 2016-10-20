package pl.plyr0.scraper;

import android.util.Log;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

class TableParser {
    private List<Row> rows = new ArrayList<>();

    public TableParser(ConsumerAble ts, Element tbody) {
        for (Element tr : tbody.getElementsByTag("tr")) {
            Elements el = tr.getElementsByTag("td");
            //Log.d(TableParser.class.getName(), el.get(0).text());
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
            rows.add(row);
        }
        ts.setData(rows);
    }
}
