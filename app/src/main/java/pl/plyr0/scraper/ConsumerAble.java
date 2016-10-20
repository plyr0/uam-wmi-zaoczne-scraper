package pl.plyr0.scraper;

import java.util.List;

interface ConsumerAble {
    void setText(String text);
    void setData(List<Row> rows);
}
