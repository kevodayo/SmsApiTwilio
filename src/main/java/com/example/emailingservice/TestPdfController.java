package com.example.emailingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class TestPdfController {
    private final PdfService pdfService;
    @GetMapping("/generate-pdf")
    public String generatePdf(){
        String content = "Hello, this is Oyanda Kevine!";
        String filePath = "example.pdf";
        String imagePath = "https://media.istockphoto.com/id/1299026534/photo/nairobi-kenya.jpg?s=1024x1024&w=is&k=20&c=LeOxyYOOTjEZ7CxOckrR_Y9_XKRG-ldEsQi4dA_yR2M=";

        pdfService.createAndWriteToPdf(content,imagePath, filePath);

        return "PDF generated successfully.";
    }
}
