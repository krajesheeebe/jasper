import com.aspose.pdf.*;

import java.io.FileOutputStream;

public class PdfTemplateGenerator {
    public static void main(String[] args) {
        // Create a new PDF document
        Document pdfDocument = new Document();
        Page page = pdfDocument.getPages().add();

        // Enable tagging for accessibility (WCAG compliance)
        pdfDocument.setTagged(com.aspose.pdf.Tagged.TagType.Tagged);

        // Add a heading
        TextFragment title = new TextFragment("Banking Form");
        title.getTextState().setFontSize(18);
        title.getTextState().setFontStyle(FontStyles.Bold);
        page.getParagraphs().add(title);

        // Add an image
        Image image = new Image();
        image.setFile("src/main/resources/bank-logo.png");  // Replace with actual path
        page.getParagraphs().add(image);

        // Add an ordered list
        TextFragment olTitle = new TextFragment("\nRequired Documents:");
        olTitle.getTextState().setFontStyle(FontStyles.Bold);
        page.getParagraphs().add(olTitle);
        ListSection orderedList = new ListSection(true, 1);
        orderedList.getListItems().add(new ListItem("Government-issued ID"));
        orderedList.getListItems().add(new ListItem("Proof of Address"));
        orderedList.getListItems().add(new ListItem("Recent Bank Statement"));
        page.getParagraphs().add(orderedList);

        // Add an unordered list
        TextFragment ulTitle = new TextFragment("\nAvailable Account Types:");
        ulTitle.getTextState().setFontStyle(FontStyles.Bold);
        page.getParagraphs().add(ulTitle);
        ListSection unorderedList = new ListSection(false);
        unorderedList.getListItems().add(new ListItem("Savings Account"));
        unorderedList.getListItems().add(new ListItem("Current Account"));
        unorderedList.getListItems().add(new ListItem("Fixed Deposit"));
        page.getParagraphs().add(unorderedList);

        // Add checkboxes for agreement section
        FormEditor formEditor = new FormEditor(pdfDocument);
        CheckBoxField checkBox = new CheckBoxField(page, new Rectangle(100, 550, 120, 570));
        checkBox.setPartialName("agreeTerms");
        page.getAnnotations().add(checkBox);

        TextFragment checkBoxLabel = new TextFragment("I agree to the terms and conditions");
        checkBoxLabel.setPosition(new Position(130, 555));
        page.getParagraphs().add(checkBoxLabel);

        // Add an external link
        TextFragment linkText = new TextFragment("\nClick here to visit our website");
        linkText.getTextState().setForegroundColor(Color.getBlue());
        linkText.setHyperlink(new WebHyperlink("https://www.bankwebsite.com"));
        page.getParagraphs().add(linkText);

        // Add a footnote
        TextFragment footnoteText = new TextFragment("\nNote: Please bring original documents for verification.");
        footnoteText.getTextState().setFontSize(10);
        page.getParagraphs().add(footnoteText);

        // Save the template
        pdfDocument.save("banking-form-template.pdf");
        System.out.println("Template created successfully!");
    }
}
