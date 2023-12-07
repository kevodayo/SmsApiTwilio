package com.example.emailingservice;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

@Service
public class PdfService {
    public void createAndWriteToPdf(String content,String imagePath, String filePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(10, 700);
                contentStream.showText(content);
                contentStream.endText();

                if(imagePath != null){
                    addImageToPdf(document, contentStream, imagePath);
                }
            }
            document.save(filePath);
            System.out.println("PDF created: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addImageToPdf(PDDocument document, PDPageContentStream contentStream, String imagePath) throws IOException {
        try {
            // Load the image
            PDImageXObject image = LosslessFactory.createFromImage(document, ImageIO.read(new URL(imagePath)));

            // Define the image position and size
            float x = 100;
            float y = 500;
            float width = 200;
            float height = 100;

            // Draw the image onto the PDF
            contentStream.drawImage(image, x, y, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
