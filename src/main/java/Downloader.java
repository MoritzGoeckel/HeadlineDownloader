
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.html.HTMLDocument;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class Downloader {
    public static void main(String[] args) throws IOException {
        ResourceManager resources = new ResourceManager();

        for(Source s : resources.getSources()) {
            String content = download(s.getUrl());

            Document doc = Jsoup.parse(content);
            Elements newsHeadlines = doc.select("h1");
            for (Element headline : newsHeadlines) {
                System.out.println(headline.text());
            }
        }
    }

    public static String download(URL url){
        InputStream is = null;
        BufferedReader br;

        StringBuilder output = new StringBuilder();

        try {
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }

        return output.toString();
    }
}
