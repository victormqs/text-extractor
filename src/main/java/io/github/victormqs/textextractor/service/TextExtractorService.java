package io.github.victormqs.textextractor.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class TextExtractorService {

    public String extractText(MultipartFile file) throws IOException {
        String filename = Objects.requireNonNull(file.getOriginalFilename()).toLowerCase();

        if (filename.endsWith(".docx")) {
            return extractDOCX(file);
        } else if (filename.endsWith(".txt")) {
            return extractTXT(file);
        } else if (filename.endsWith(".pdf")) {
            return extractPDF(file);
        }

        throw new IllegalArgumentException("Formato de arquivo não suportado.");
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

    private String extractPDF(MultipartFile file) throws IOException {
        try (PDDocument document = Loader.loadPDF(file.getBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
}
