package io.github.victormqs.textextractor.service.extractor;

import io.github.victormqs.textextractor.service.TextExtractor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class TxtExtractor implements TextExtractor {

    @Override
    public String extractText(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        return new String(fileBytes);
    }

    @Override
    public boolean supports(String filename) {
        return filename.toLowerCase().endsWith(".txt");
    }
}