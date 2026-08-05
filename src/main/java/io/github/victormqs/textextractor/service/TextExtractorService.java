package io.github.victormqs.textextractor.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class TextExtractorService {

    public String extractText(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();

        if (Objects.requireNonNull(filename).endsWith(".docx")) {
            return extractDOCX(file);
        }

        return extractTXT(file);
    }

    private String extractTXT(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();

        return new String(fileBytes);
    }

    private String extractDOCX(MultipartFile file) throws IOException {
        StringBuilder text = new StringBuilder();

        try (XWPFDocument document = new XWPFDocument(file.getInputStream())) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                text.append(paragraph.getText()).append("\n");
            }
        }

        return text.toString();
    }
}
