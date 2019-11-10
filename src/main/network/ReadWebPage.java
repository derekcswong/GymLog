package network;




import com.google.gson.*;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.parser.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//citations: https://www.srijan.net/blog/how-parse-json-data-rest-api-using-simple-json-library

public class ReadWebPage {
    public ReadWebPage() throws MalformedURLException, IOException {
        BufferedReader br = null;
        try {
            String theURL = "https://favqs.com/api/qotd";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            //opens url connection for reading? get request?
            String line;
            StringBuilder sb = new StringBuilder();
            //reads every line from get request and adds it to string builder
            while ((line = br.readLine()) != null) {
                sb.append(line);
                //adds comma after each line
                sb.append(System.lineSeparator());
            }
//            just parses string made above and use substring to get quote
            int body = sb.indexOf("body") + 6;
            int lastP = sb.lastIndexOf("\"") + 1;
            String quote = sb.substring(body, lastP);
            System.out.println(quote + "\n");

            //creates instance of jsonParser, takes string built from above which was an inline json String
            //declares it as a json object
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(sb.toString());

            //use gson to format the jsonObject into nicer format before printing
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonObject = gson.toJson(jsonObject);
            System.out.println(prettyJsonObject);



        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}