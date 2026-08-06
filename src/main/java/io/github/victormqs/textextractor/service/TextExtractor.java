package io.github.victormqs.textextractor.service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface TextExtractor {
    String extractText(MultipartFile file) throws IOException;
    boolean supports(String filename);
}
