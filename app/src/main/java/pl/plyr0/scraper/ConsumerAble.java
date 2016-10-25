package pl.plyr0.scraper;

import java.util.List;

import pl.plyr0.scraper.model.Row;

interface ConsumerAble {
    void setText(String text);
    void setData(List<Row> rows);
}
