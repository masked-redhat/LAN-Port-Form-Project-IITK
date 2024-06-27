package me.skhull.compountinterestwebapplication;

import com.itextpdf.html2pdf.HtmlConverter;
import me.skhull.compountinterestwebapplication.model.Requisitioner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "PDFGenerateServlet", urlPatterns = "/pdf")
public class PDFGenerateServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // pdfLocation parameter in web.xml
        String source = getServletContext().getInitParameter("pdfLocation");

        // Input and Output Paths for Form HTML and PDF
        String HTMLPath = source + "FormPDF.html";
        String OutputPath = source + "output-path.pdf";

        // Getting the params of request using attribute requisitioner
        Requisitioner requisitioner = (Requisitioner) request.getAttribute("requisitioner");

        try {
            // Read HTML doc
            String htmlTemplate = new String(Files.readAllBytes(Paths.get(HTMLPath)));

            // Replace placeholders
            String htmlContent = htmlTemplate
                    .replace("{{name}}", requisitioner.getName())
                    .replace("{{phone}}", requisitioner.getPhone())
                    .replace("{{unit}}", requisitioner.getUnit())
                    .replace("{{pfNumber}}", requisitioner.getPfNumber())
                    .replace("{{email}}", requisitioner.getEmail())
                    .replace("{{description}}", requisitioner.getDescription());

            // Convert to PDF
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(OutputPath));

            System.out.println("PDF created successfully at " + OutputPath);

            // Serve the Generated PDF
            servePDF(request, response, OutputPath);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void servePDF(HttpServletRequest request, HttpServletResponse response, String FileName) throws ServletException, IOException {
        // PDF file
        File pdfFile = new File(FileName);

        // Response header
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=" + FileName);
        response.setContentLength((int) pdfFile.length());

        // Serving PDF
        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        OutputStream responseOutputStream = response.getOutputStream();
        int bytes;
        while ((bytes = fileInputStream.read()) != -1) {
            responseOutputStream.write(bytes);
        }

    }
}