package quotes;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GetQuoteAPI {
  private String path;
  private QuoteAPI quote = null;
  private JsonParser parser = null;
  private JsonObject parserObject  = null;
  private String printQuote = null;
  private  int responseCode;
  private boolean isAPIWork = false;

  public GetQuoteAPI(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }

  public void getQuote(){
    try {
      URL url = new URL(getPath());
      Gson gson = new Gson();
      HttpURLConnection connection = null;
      BufferedReader in = null;
      try {

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        responseCode = connection.getResponseCode();
        in = new BufferedReader(
            new InputStreamReader(connection.getInputStream())
        );
        quote = gson.fromJson(in, QuoteAPI.class);
        isAPIWork = true;
      } catch (IOException e) {
        e.printStackTrace();
        isAPIWork = false;
      }

    } catch (MalformedURLException ignored){
      isAPIWork = false;
    }
  }

  public void getRandomQuote(){
    try {

      if (responseCode == HttpURLConnection.HTTP_OK) {

        printQuote = "\tAuthor: " + quote.getQuoteAuthor();
        printQuote += "\n";
        printQuote += "\tText: " + quote.getQuoteText();

        String json = "{\"Author\":\""+quote.getQuoteAuthor()+"\",\"Text\":\""+ quote.getQuoteText()+"\"}";


        String localPath = "app/src/main/resources/recentquotes.json";
        GetQuoteLocal getNewQuote = new GetQuoteLocal(localPath);
        getNewQuote.getQuote();
        getNewQuote.saveQuote(json);
        isAPIWork = true;
        System.out.println(printQuoteString());
      } else {
        String localPath = "app/src/main/resources/recentquotes.json";
        GetQuoteLocal quote = new GetQuoteLocal(localPath);
        quote.getQuote();
        quote.getRandomQuote();
        System.out.println(quote);
        isAPIWork = false;
      }

    } catch (Exception ignored){
      isAPIWork = false;
    }
  }

  public boolean isAPIWork() {
    return isAPIWork;
  }

  public String printQuoteString(){
    return "Random Quote API :\n" + printQuote;
  }
}
