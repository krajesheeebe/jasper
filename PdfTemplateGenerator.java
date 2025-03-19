import com.aspose.pdf.*;
import java.io.FileInputStream;

public class ReplaceImageByPosition {
    public static void main(String[] args) throws Exception {
        // Load the PDF document
        Document pdfDocument = new Document("template.pdf");

        // Find all images in the PDF
        ImagePlacementAbsorber absorber = new ImagePlacementAbsorber();
        pdfDocument.getPages().accept(absorber);

        // Load the new image
        FileInputStream newImageStream = new FileInputStream("new-image.jpg");

        // Replace the first found image
        if (absorber.getImagePlacements().size() > 0) {
            absorber.getImagePlacements().get_Item(1).replace(newImageStream);
        }

        // Save the updated PDF
        pdfDocument.save("output.pdf");
        System.out.println("Image replaced successfully!");
    }
}
