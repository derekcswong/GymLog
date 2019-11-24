package ui.network;




import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

//citations: https://www.srijan.net/blog/how-parse-json-data-rest-api-using-simple-json-library

public class ReadWebPage {
    private String quoteString;

    public ReadWebPage() throws IOException {
        BufferedReader br = null;
        try {
            String theURL = "https://favqs.com/api/qotd";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(sb.toString());
            quoteString = jsonObject.get("quote").getAsJsonObject().get("body") + "\n";

        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public String getQuoteString() {
        return quoteString;
    }
}