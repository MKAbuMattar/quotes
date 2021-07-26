/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class AppTest {
    private String url = null;
    private GetQuoteAPI quoteAPI = null;

    @Test
    public void testBreakingURL() {
        url = "htt://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";
        quoteAPI = new GetQuoteAPI(url);
        quoteAPI.getQuote();
        quoteAPI.getRandomQuote();
        assertFalse(quoteAPI.isAPIWork());
    }

    @Test
    public void testWorkURL() {
        url = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";
        quoteAPI = new GetQuoteAPI(url);
        quoteAPI.getQuote();
        quoteAPI.getRandomQuote();
        assertTrue(quoteAPI.isAPIWork());
    }

    @Test
    public void testQuote() {
        url = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";
        quoteAPI = new GetQuoteAPI(url);
        quoteAPI.getQuote();
        quoteAPI.getRandomQuote();
        assertNotNull(quoteAPI.toString());
    }

    @Test
    public void testBreakingURLGetLocalQuote() {
        url = "htt://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";
        quoteAPI = new GetQuoteAPI(url);
        quoteAPI.getQuote();
        quoteAPI.getRandomQuote();
        assertNotNull(quoteAPI.toString());
    }
}
