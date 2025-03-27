import com.aspose.pdf.*;

public class ReadOnlyCheckbox {
    public static void main(String[] args) {
        // Load the PDF document
        Document document = new Document("input.pdf");

        // Get the first page
        Page page = document.getPages().get_Item(1);

        // Create a checkbox field
        RadioButtonField checkBox = new RadioButtonField(document);
        checkBox.setPartialName("agree_terms");

        // Create an option (appearance for checked state)
        RadioButtonOptionField option = new RadioButtonOptionField(document);
        option.setPage(page);
        option.setWidth(15);
        option.setHeight(15);
        option.setCaption("I agree to terms");
        option.setStyle(BoxStyle.Cross); // Style can be Cross, Check, etc.

        // Set the checkbox to checked or unchecked
        option.setChecked(true); // Set to false if unchecked

        // Make it read-only
        checkBox.setReadOnly(true);

        // Add option to checkbox field
        checkBox.add(option);

        // Add field to the page
        document.getForm().add(checkBox);

        // Save the updated PDF
        document.save("output.pdf");

        System.out.println("Read-only checkbox added!");
    }
}
import com.aspose.pdf.*;

public class ReadOnlyCheckbox {
    public static void main(String[] args) {
        // Load the PDF document
        Document document = new Document("input.pdf");

        // Get the first page
        Page page = document.getPages().get_Item(1);

        // Create a checkbox field
        RadioButtonField checkBox = new RadioButtonField(document);
        checkBox.setPartialName("agree_terms");

        // Create an option (appearance for checked state)
        RadioButtonOptionField option = new RadioButtonOptionField(document);
        option.setPage(page);
        option.setWidth(15);
        option.setHeight(15);
        option.setCaption("I agree to terms");
        option.setStyle(BoxStyle.Cross); // Style can be Cross, Check, etc.

        // Set the checkbox to checked or unchecked
        option.setChecked(true); // Set to false if unchecked

        // Make it read-only
        checkBox.setReadOnly(true);

        // Add option to checkbox field
        checkBox.add(option);

        // Add field to the page
        document.getForm().add(checkBox);

        // Save the updated PDF
        document.save("output.pdf");

        System.out.println("Read-only checkbox added!");
    }
}
