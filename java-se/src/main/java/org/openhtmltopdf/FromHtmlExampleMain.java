package org.openhtmltopdf;

import com.openhtmltopdf.DOMBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.*;
import java.net.URL;

public class FromHtmlExampleMain {

    // see https://github.com/danfickle/openhtmltopdf/blob/open-dev-v1/docs/integration-guide.md

    // Images must have a full path

    public static void main(String[] args) throws Exception {
        try (OutputStream os = new FileOutputStream("/tmp/out.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            Document doc = html5ParseDocument("file:///home/remipassmoilesel/.IdeaIC2018.2/config/scratches/scratch.html", 10000);
            builder.withW3cDocument(doc, "");
            builder.toStream(os);
            builder.run();
        }
    }

    private static org.w3c.dom.Document html5ParseDocument(String urlStr, int timeoutMs) throws IOException {
        URL url = new URL(urlStr);
        org.jsoup.nodes.Document doc;

        if (url.getProtocol().equalsIgnoreCase("file")) {
            doc = Jsoup.parse(new File(url.getPath()), "UTF-8");
        }
        else {
            doc = Jsoup.parse(url, timeoutMs);
        }

        return DOMBuilder.jsoup2DOM(doc);
    }
}
