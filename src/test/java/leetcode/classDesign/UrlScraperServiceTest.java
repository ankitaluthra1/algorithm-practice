package leetcode.classDesign;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UrlScraperServiceTest {

    @Test
    public void shouldReturnListOfImageUrlsFromGivenWebUrl(){

        HtmlParser htmlParser = mock(HtmlParser.class);
        WebClient webClient = mock(WebClient.class);

        UrlScraperService urlScraperService = new UrlScraperService(htmlParser, webClient);
        when(webClient.getHtmlTextFor(any())).thenReturn("<html><html>");
        when(htmlParser.getAllImageUrls(any())).thenReturn(Arrays.asList("somr-url.jpeg"));

        List<String> allImageUrls = urlScraperService.getAllImageUrls("some-url");

        assertEquals(1, allImageUrls.size());
        assertEquals("somr-url.jpeg", allImageUrls.get(0));
    }

}