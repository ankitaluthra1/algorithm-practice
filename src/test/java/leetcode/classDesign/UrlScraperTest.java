package leetcode.classDesign;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class UrlScraperTest {

    @Test
    void shouldCallSaveAllForGivenImageUrls() {
        UrlScraperService urlScraperService = mock(UrlScraperService.class);
        ImageUrlRepository imageUrlRepository = mock(ImageUrlRepository.class);
        UrlScraper urlScraper = new UrlScraper(urlScraperService, imageUrlRepository);
        List<String> inputUrls = Arrays.asList("https://some-url.com", "https://some-other-url.com");

        when(urlScraperService.getAllImageUrls(any())).thenReturn(Arrays.asList("image-url"));
        urlScraper.scrape(inputUrls);

        verify(urlScraperService, times(1)).getAllWebUrls("https://some-url.com");
        verify(imageUrlRepository, times(2)).saveAll(Arrays.asList("image-url"));
    }
}