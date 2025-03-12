import com.aspose.pdf.*;

import java.io.File;

public class PdfDataFiller {
    public static void main(String[] args) {
        // Load the existing template
        Document pdfDocument = new Document("banking-form-template.pdf");

        // Enable accessibility tagging (WCAG compliance)
        pdfDocument.setTagged(com.aspose.pdf.Tagged.TagType.Tagged);

        Page page = pdfDocument.getPages().get_Item(1);

        // Fill text fields dynamically
        TextFragment customerName = new TextFragment("Customer Name: John Doe");
        customerName.getTextState().setFontSize(14);
        page.getParagraphs().add(customerName);

        TextFragment accountNumber = new TextFragment("Account Number: 123456789");
        accountNumber.getTextState().setFontSize(14);
        page.getParagraphs().add(accountNumber);

        TextFragment email = new TextFragment("Email: john.doe@example.com");
        email.getTextState().setFontSize(14);
        page.getParagraphs().add(email);

        // Check the checkbox (agreement)
        for (Annotation annotation : page.getAnnotations()) {
            if (annotation instanceof CheckBoxField) {
                ((CheckBoxField) annotation).setChecked(true);
            }
        }

        // Save the modified PDF
        pdfDocument.save("banking-form-filled.pdf");
        System.out.println("PDF filled successfully!");
    }
}
