package com.alina.mylibrary.service.Services.Pdf;

import com.alina.mylibrary.model.db.BookOrder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.text.TextAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

public class GeneratePdfReport {
    private static String FILE = "c:/temp/FirstPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 15,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream generateInvoice(List<BookOrder> orderList) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {




            PdfWriter.getInstance(document,out);
            document.open();

            Paragraph title= new Paragraph("Factura seria "+generateInvoiceNumber(),catFont);
            title.setLeading(0, 1);
            PdfPTable tableTitle = new PdfPTable(1);
            tableTitle.setWidthPercentage(100);
            tableTitle.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            PdfPCell cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setMinimumHeight(50);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.addElement(title);
            tableTitle.addCell(cell);
            document.add(tableTitle);
         //   document.close();

            Paragraph p1 = new Paragraph("Furnizor : ",smallBold);
            Paragraph p2 = new Paragraph( "EDEN LIBRARY SRL",smallBold);
            Paragraph p3 = new Paragraph( " Soseaua Berceni nr 17",smallBold);
            Paragraph p4 = new Paragraph( " Scara 3 , etaj 2 , Sector 4,Bucuresti",smallBold);
            Paragraph p5 = new Paragraph( "Nr.Reg.Com: J40/17329/2007",smallBold);
            Paragraph p6 = new Paragraph( "CUI/CIF: RO 22418650",smallBold);

            PdfPTable tableCompany = new PdfPTable(1);
            tableTitle.setWidthPercentage(60);
            tableTitle.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            PdfPCell cell1 = new PdfPCell();
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.addElement(p1);

            PdfPCell cell2 = new PdfPCell();
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setVerticalAlignment(Element.ALIGN_LEFT);
            cell2.addElement(p2);

            PdfPCell cell3 = new PdfPCell();
            cell3.setBorder(Rectangle.NO_BORDER);
            cell3.setVerticalAlignment(Element.ALIGN_LEFT);
            cell3.addElement(p3);

            PdfPCell cell4 = new PdfPCell();
            cell4.setBorder(Rectangle.NO_BORDER);
            cell4.setVerticalAlignment(Element.ALIGN_LEFT);
            cell4.addElement(p4);


            PdfPCell cell5 = new PdfPCell();
            cell5.setBorder(Rectangle.NO_BORDER);
            cell5.setVerticalAlignment(Element.ALIGN_LEFT);
            cell5.addElement(p5);

            PdfPCell cell6 = new PdfPCell();
            cell6.setBorder(Rectangle.NO_BORDER);
            cell6.setVerticalAlignment(Element.ALIGN_LEFT);
            cell6.addElement(p6);


            tableCompany.addCell(cell1);
            tableCompany.addCell(cell2);
            tableCompany.addCell(cell3);
            tableCompany.addCell(cell4);
            tableCompany.addCell(cell5);
            tableCompany.addCell(cell6);
            document.add(tableCompany);


            String data=" ";
            Paragraph p7 = new Paragraph("Data facturarii");
            for (BookOrder order : orderList) {

                if(order.getOrderId()==20) {
                     data = order.getOrderD().toString();
                }
                }

            Paragraph p8 = new Paragraph(data);
            Paragraph p9 = new Paragraph("Forma de plata");
            Paragraph p10 = new Paragraph("Valuta");
            Paragraph p11 = new Paragraph("lei");





            PdfPTable orderDetails = new PdfPTable(2);

            tableTitle.setWidthPercentage(60);
            tableTitle.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            PdfPCell cell7 = new PdfPCell();
            cell7.setBorder(Rectangle.NO_BORDER);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell7.addElement(p7);

            PdfPCell cell8 = new PdfPCell();
            cell8.setBorder(Rectangle.NO_BORDER);
            cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell8.addElement(p8);


            PdfPCell cell9 = new PdfPCell();
            cell9.setBorder(Rectangle.NO_BORDER);
            cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell9.addElement(p9);


            PdfPCell cell10 = new PdfPCell();
            cell10.setBorder(Rectangle.NO_BORDER);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell10.addElement(p10);

            PdfPCell cell11 = new PdfPCell();
            cell11.setBorder(Rectangle.NO_BORDER);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell11.addElement(p11);


            orderDetails.addCell(cell7);
            orderDetails.addCell(cell8);
            orderDetails.addCell(cell9);
            orderDetails.addCell(cell10);
            orderDetails.addCell(cell11);
            document.add(orderDetails);




            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("orderId", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("title", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("author", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (BookOrder order : orderList) {

                if(order.getOrderId()==20) {

                    String id = Integer.toString(order.getOrderId());
                    String subtotal = Double.toString(order.getSubtotal());
                    String transport = Double.toString(order.getShipping());
                    table.addCell(id);
                    table.addCell(subtotal);
                    table.addCell(transport);
                }


            }



            PdfPTable t1=new PdfPTable(2);
            t1.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell celA = new PdfPCell();
            celA.setBorder(Rectangle.NO_BORDER);
            celA.addElement(tableCompany);
            t1.addCell(celA);

            PdfPCell celB = new PdfPCell();
            celB.setBorder(Rectangle.NO_BORDER);
            celA.addElement(orderDetails);
            t1.addCell(celB);
            document.add(t1);

//            PdfWriter.getInstance(document, out);
//            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public static String generateInvoiceNumber(){
        final Random random = new Random();
        final Set<Integer> intSet = new HashSet<>();
        while (intSet.size() < 20) {
            intSet.add(random.nextInt(50) + 1);
        }
        final int[] numbers = new int[intSet.size()];
        final Iterator<Integer> iter = intSet.iterator();
        for (int i = 0; iter.hasNext(); ++i) {
            numbers[i] = iter.next();
        }
        String result=" ";
        result=Arrays.toString(numbers);
        return result;
    }

}
