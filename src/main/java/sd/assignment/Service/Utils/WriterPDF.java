package sd.assignment.Service.Utils;

import sd.assignment.Service.DTO.FoodDTO;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;

/**
 * The type Writer pdf.
 */
public class WriterPDF {
    private final Document document;
    private final Font titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 26, BaseColor.BLACK);
    private final Font subtitleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
    private final Font textFont  = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    /**
     * Instantiates a new Writer pdf.
     *
     */
    public WriterPDF() {
        document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the menu details in the pdf.
     *
     * @param foods
     */
    public void write(java.util.List<FoodDTO> foods) {
        Paragraph paragraph = new Paragraph();
        foods.forEach(food -> {
            paragraph.add(new Chunk(food.getName() + "\n", titleFont));
            paragraph.add(new Chunk(food.getCategory() + "\n", subtitleFont));
            paragraph.add(new Chunk(food.getDescription() + "  ", textFont));
            paragraph.add(new Chunk(food.getPrice().toString() + "\n\n", textFont));
        });
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the pdf.
     *
     * @return
     */
    public byte[] close() {
        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}
