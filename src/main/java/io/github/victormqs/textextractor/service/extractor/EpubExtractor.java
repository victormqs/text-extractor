package io.github.victormqs.textextractor.service.extractor;

import io.documentnode.epub4j.domain.Book;
import io.documentnode.epub4j.domain.Resource;
import io.documentnode.epub4j.epub.EpubReader;
import io.github.victormqs.textextractor.service.TextExtractor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class EpubExtractor implements TextExtractor {

    @Override
    public String extractText(MultipartFile file) throws IOException {
        StringBuilder text = new StringBuilder();

        Book book = new EpubReader().readEpub(file.getInputStream());

        for (Resource resource : book.getContents()) {
            String html = new String(resource.getData(), resource.getInputEncoding());
            String plainText = Jsoup.parse(html).text();
            text.append(plainText).append("\n");
        }

        return text.toString();
    }

    @Override
    public boolean supports(String filename) {
        return filename.toLowerCase().endsWith(".epub");
    }
}