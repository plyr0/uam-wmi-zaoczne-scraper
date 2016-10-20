package pl.plyr0.scraper;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Table extends AsyncTask<Void, Void, Void> {
    private static final String url = "https://zajecia.wmi.amu.edu.pl/plan_zaoczne/PlanZaoczne.aspx";
    private String value;
    private final ConsumerAble consumerAble;
    private boolean oneQuery = true;

    Table(ConsumerAble ts) {
        this.consumerAble = ts;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void result) {
        consumerAble.setText(value);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            scrap();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d(Table.class.getName(), e.getMessage());
        }
        return null;
    }

    private void scrap() throws IOException {
        String __VIEWSTATEGENERATOR, __VIEWSTATE, __EVENTVALIDATION;
        Map<String, String> cookies;
        if (oneQuery) {
            cookies = new HashMap<>();
            __VIEWSTATE = "/wEPDwUKMTI2MDMzNDkzMw9kFgICAw88KwALAGRkmjN9y/JUmvbPYfNFmc3wobLvA6Fz1tHPrKiQDpPorsA=";
            __EVENTVALIDATION = "/wEdABemX5Ei7Vj9XvKqm4un/gMyCquxE7SNF6Ev/GjCD3jAZoD24HBADxqSI7ixx7suY0tHw2QDrM6NtvhZWbmCAZg1flKN3Ua/42Kz0SJ6yyVrre8Y5E7Fk9X88CqbRfHIRr8g5oH1EyJrlQ7ApWalHgzw+uCzpEf8eDCpXTgjL0tjA5OKbT7LN0vPPnZT57+PPmJGyolEuA97T/1ylaQQMw4Ru94YU7YEsxuG0U4xJ19NOlSLZ57jSulZyKpmZa0tigEQtPgXODcz3FdDeindeg7Wp8a5wzQevkQSShpzUKtiPfyM1Zu9cyptVakAamhKNhNcEN8d795vtvCxyIcpkCObw5rQIw2Vq4hjgk287/mZvK6q3PoFuKWhw3PA5mVNLPY2HUpDx0hule5M5fktkWyMfEiiLvYHH5e2e994tkte/EG8Um2lVGFXkSoULFLrPnn2e5bTOaF/X8KHiEIFoVmdzfg78Z8BXhXifTCAVkevd94UbXsy9eNiK27eXnT1yjBzwdGRWcZ1Ix1b85MIXWEl";
            __VIEWSTATEGENERATOR = "E977E3D7";
        } else {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).execute();
            FormElement form = (FormElement) response.parse().getElementById("Form1");
            cookies = response.cookies();
            __VIEWSTATEGENERATOR = form.select("input[name=__VIEWSTATEGENERATOR]").first().attr("value");
            __VIEWSTATE = form.select("input[name=__VIEWSTATE]").first().attr("value");
            __EVENTVALIDATION = form.select("input[name=__EVENTVALIDATION]").first().attr("value");
        }
        Document result = Jsoup.connect(url)
                .cookies(cookies)
                .postDataCharset("UTF-8")
                .data("__VIEWSTATE", __VIEWSTATE)
                .data("__EVENTVALIDATION", __EVENTVALIDATION)
                .data("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR)
                .data("Studia", "*")
                .data("RokStudiow", "*")
                .data("Semestr", "2016Z")
                .data("Przedmiot", "")
                .data("Prowadzacy", "")
                .data("datepicker1", "2016-10-19")
                .data("datepicker2", "")
                .data("Button1", "Szukaj")
                .followRedirects(true)
                .post();

        //Log.d(Table.class.getName(), result.getElementById("DataGrid1").toString());
        Element table = result.getElementsByClass("row").first();
        if (table != null) {
            //table.getElementById("row");
            new TableParser(consumerAble, table.select("table > tbody").first());
            value = table.html();
        }
    }
}