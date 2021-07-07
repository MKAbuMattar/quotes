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
  private StringBuilder jsonFile= null;

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
    } catch (Exception e){
      e.printStackTrace();
    }
  }


  public void saveQuote (String fuck){
    Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    try {
      jsonFile = new StringBuilder();
      jsonFile.append("[");

      for (int i = 0; i < quote.size(); i++){
        jsonFile.append("{\"Author\":\"");
        jsonFile.append(quote.get(i).getAuthor());
        jsonFile.append("\",\"Text\":\"");
        String result = quote.get(i).getText().replaceAll("[^']*(?:'(.*?)')?.*", "$1");
        jsonFile.append(result);
        jsonFile.append("\"},");
      }
      jsonFile.append(fuck);
      jsonFile.append("]");
      JsonParser parser = new JsonParser();
      JsonObject o = parser.parse(String.valueOf(jsonFile)).getAsJsonObject();

      try (BufferedWriter writer = new BufferedWriter(new FileWriter("app/src/main/resources/recentQuotesOutput.json"))) {
        writer.write(GSON.toJson(o));
      }

    }  catch (Exception e){
      e.printStackTrace();
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
    return "Random Quote :\n" + printQuote;
  }
}
