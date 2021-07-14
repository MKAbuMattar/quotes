package quotes;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GetQuoteLocal {
  private String path;
  private List<Quote> quote = null;
  private StringBuilder printQuote = null;
  private String jsonFile= "";

  public GetQuoteLocal() {
  }

  public GetQuoteLocal(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }

  public void getQuote() {
    Gson quoteGSON = new Gson();
    BufferedReader in = null;
    try {
      in = Files.newBufferedReader(Paths.get(getPath()));
      // https://stackoverflow.com/questions/43117731/what-is-type-typetoken
      quote = quoteGSON.fromJson(in, new TypeToken<List<Quote>>() {}.getType());
      in.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void saveQuote() {
    //https://mkyong.com/java/how-to-enable-pretty-print-json-output-gson/
    Gson quoteGSON = new GsonBuilder().setPrettyPrinting().create();
    try {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("app/src/main/resources/recentQuotesOutput.json"))) {
        writer.write(quoteGSON.toJson(quote));
      }
    } catch (Exception ignored){

    }
  }


  public void saveQuote (String getNewQuote){
    Gson quoteGSON = new GsonBuilder().setPrettyPrinting().create();;
    try {
      JsonParser parser = new JsonParser();
      JsonObject o = parser.parse(getNewQuote).getAsJsonObject();

      try (BufferedWriter writer = new BufferedWriter(new FileWriter("app/src/main/resources/recentQuotesOutput.json"))) {
        writer.write(quoteGSON.toJson(o));
      }

    }  catch (Exception ignored){

    }
  }


  public void getRandomQuote(){
    try {
      int randomNumber = (int) (Math.random() * ((quote.size()) + 1));
      printQuote = new StringBuilder();
      printQuote.append("\tAuthor: ").append(quote.get(randomNumber).getAuthor());
      printQuote.append("\n");
      printQuote.append("\tText: ").append(quote.get(randomNumber).getText());
    } catch (Exception ex){
      ex.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return "Random Quote Local :\n" + printQuote;
  }
}
