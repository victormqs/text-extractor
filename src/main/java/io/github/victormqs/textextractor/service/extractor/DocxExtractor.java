package io.github.victormqs.textextractor.service.extractor;

import io.github.victormqs.textextractor.service.TextExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class DocxExtractor implements TextExtractor {

    @Override
    public String extractText(MultipartFile file) throws IOException {
        StringBuilder text = new StringBuilder();

        try (XWPFDocument document = new XWPFDocument(file.getInputStream())) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                text.append(paragraph.getText()).append("\n");
            }
        }

        return text.toString();
    }

    @Override
    public boolean supports(String filename) {
        return filename.toLowerCase().endsWith(".docx");
    }
}