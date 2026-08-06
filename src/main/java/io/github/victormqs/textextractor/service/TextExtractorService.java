package io.github.victormqs.textextractor.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class TextExtractorService {

    private final List<TextExtractor> extractors;

    public TextExtractorService(List<TextExtractor> extractors) {
        this.extractors = extractors;
    }

    public String extractText(MultipartFile file) throws IOException {
        String filename = Objects.requireNonNull(file.getOriginalFilename());

        TextExtractor extractor = extractors.stream()
                .filter(e -> e.supports(filename))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Formato de arquivo não suportado."));

        return extractor.extractText(file);
    }
}