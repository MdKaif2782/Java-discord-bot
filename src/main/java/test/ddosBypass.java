package test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ddosBypass {
    public static void main(String[] args) {
        try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            String url = "https://animedao.to/";
            HtmlPage htmlPage = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(5_000);
            System.out.println(htmlPage.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
