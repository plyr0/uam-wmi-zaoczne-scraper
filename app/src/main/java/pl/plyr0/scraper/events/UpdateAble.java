package pl.plyr0.scraper.events;

import java.util.List;

import pl.plyr0.scraper.model.Row;

public interface UpdateAble {
    void setData(List<Row> rows);

    void setError(String error);
}
