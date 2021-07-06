package quotes;

import com.google.gson.Gson;

import com.google.common.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GetQuote {

    private String path;

    public GetQuote(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void getQuote() {
        Gson quoteGSON = new Gson();
        List<Quote> renderQuote = null;
        BufferedReader in = null;
        try {
            in = Files.newBufferedReader(Paths.get(getPath()));
            // https://stackoverflow.com/questions/43117731/what-is-type-typetoken
            renderQuote = quoteGSON.fromJson(in, new TypeToken<List<Quote>>() {}.getType());
            in.close();
            toJSONQuote(renderQuote);
            getRandomQuote(renderQuote);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void toJSONQuote(List<Quote> insert) {
        Gson quoteGSON = new Gson();
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("app/src/main/resources/recentQuotesOutput.json"))) {
                writer.write(quoteGSON.toJson(insert));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getRandomQuote(List<Quote> insert){
        try {
            int randomNumber = 0 + (int)(Math.random() * ((insert.size() - 0) + 1));
            StringBuilder printQuote = new StringBuilder();
            printQuote.append("Author: ").append(insert.get(randomNumber).getAuthor());
            printQuote.append("\n");
            printQuote.append("Text: ").append(insert.get(randomNumber).getText());
            System.out.println(printQuote);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
