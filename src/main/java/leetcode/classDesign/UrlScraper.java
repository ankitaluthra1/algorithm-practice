package leetcode.classDesign;

/*
* Design a scraper service (web crawler) that takes as input an array of urls of web pages. For each url, save every image link in the html document. Any other links on the page, follow them and recursively get the images on those pages too.
*
* */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UrlScraper {

    UrlScraperService urlScraperService;
    ImageUrlRepository imageUrlRepository;

    public UrlScraper(UrlScraperService urlScraperService, ImageUrlRepository imageUrlRepository) {
        this.urlScraperService = urlScraperService;
        this.imageUrlRepository = imageUrlRepository;
    }

    public void scrape(List<String> urls){
        Queue<String> urlTobeVisited = new LinkedList<>();
        urlTobeVisited.addAll(urls);

        while (!urlTobeVisited.isEmpty()){
            String currentUrl = urlTobeVisited.poll();
            List<String> allImageUrls = urlScraperService.getAllImageUrls(currentUrl);
            this.imageUrlRepository.saveAll(allImageUrls);
            urlTobeVisited.addAll(this.urlScraperService.getAllWebUrls(currentUrl));
        }
    }

}

class ImageUrlRepository {
    public int save(String imageUrl){
        return 1;
    }
    public boolean saveAll(List<String> imageUrl){
        return true;
    }
}

class UrlScraperService {

    HtmlParser htmlParser;
    WebClient webClient;

    public UrlScraperService(HtmlParser htmlParser, WebClient webClient) {
        this.htmlParser = htmlParser;
        this.webClient = webClient;
    }

    public List<String> getAllImageUrls(String currentUrl) {
        String htmlText = webClient.getHtmlTextFor(currentUrl);
        return htmlParser.getAllImageUrls(htmlText);
    }

    public List<String> getAllWebUrls(String currentUrl) {
        return new ArrayList<>();
    }
}

class WebClient{
    public String getHtmlTextFor(String currentUrl) {
        return "<html></html>";
    }

    //autowire some lib web client resttemplate or use java native client



}
class HtmlParser{

    //some library like Jsoup can use parse html
    public List<String> getAllImageUrls(String htmlText) {
        return null;
    }

}
