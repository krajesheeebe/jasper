import com.aspose.pdf.*;

public class FillCheckboxAndMakeReadOnly {
    public static void main(String[] args) {
        // Load the PDF document
        Document pdfDocument = new Document("input.pdf");

        // Get the form fields
        com.aspose.pdf.forms.Field[] fields = pdfDocument.getForm().getFields();

        // Loop through fields to find checkboxes and set values
        for (com.aspose.pdf.forms.Field field : fields) {
            if (field instanceof com.aspose.pdf.forms.CheckBoxField) {
                CheckBoxField checkBox = (CheckBoxField) field;
                checkBox.setChecked(true); // Check the checkbox
            }
        }

        // Flatten the form to make it read-only
        pdfDocument.getForm().flatten();

        // Save the modified PDF
        pdfDocument.save("output.pdf");

        System.out.println("Checkboxes filled and form made read-only successfully.");
    }
}
