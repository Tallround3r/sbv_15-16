/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbv;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Philipp Csernalabics
 */
public class PDF_Export {
       public static void jPanelPdfExport(Component panel) {
        try {
           
            Document doc = new Document(PageSize.A4); // mach das rotate() weg für Hochkant
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Beispiel_PDF.pdf"));
           
            // Attributes
            doc.addAuthor(System.getProperty("user.name"));
            doc.addCreationDate();
            doc.addCreator("Seminarkurs Programm");
            doc.addTitle("Beispiel, PDF-Export");
            doc.addSubject("Dies soll ein Beispiel eines PDF-Exports sein.");
           
            doc.open();
           
            java.awt.Image image = getImageFromPanel(panel);
            Image iTextImage = Image.getInstance(writer, image, 1);
            iTextImage.setAlignment(Image.MIDDLE); // funktioniert nicht...
            iTextImage.scaleToFit(PageSize.A4.rotate().getWidth()-100, PageSize.A4.rotate().getHeight()-100);
           
            doc.add(iTextImage);
           
            doc.close();
            writer.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
   public static void studentPDF(String studentID){
    
    try{   
    Document document = new Document(PageSize.A4); 
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(Students.SingelStudent(studentID,1)+"-"+Students.SingelStudent(studentID,2)+".pdf"));
     // Attributes
            document.addAuthor(System.getProperty("user.name"));
            document.addCreationDate();
            document.addCreator("Seminarkurs Programm Schulbuchverwaltung");
            document.addTitle("PDF-Export Schüler "+Students.SingelStudent(studentID,1)+" "+Students.SingelStudent(studentID,2));
            document.addSubject("PDF-Export des Schülers "+Students.SingelStudent(studentID,1)+" "+Students.SingelStudent(studentID,2));
            
        
            document.open();
         
            Paragraph titel1 = new Paragraph("Schüler: "+Students.SingelStudent(studentID,1)+" "+Students.SingelStudent(studentID,2),
             FontFactory.getFont(FontFactory.HELVETICA,16, Font.BOLDITALIC)) ;
        Chapter chapter1 = new Chapter(titel1, 1);
        chapter1.setNumberDepth(0);
        
        Paragraph titel2 = new Paragraph("Geburtsdatum: "+Students.SingelStudent(studentID,3),
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD));
   
      Section section1 = chapter1.addSection(titel2);
      
      ArrayList<String> a1 = new ArrayList();
      a1 = Books.studentBookList(studentID);
    
   
           PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(25);
      
      table.setSpacingAfter(25);
      
      PdfPCell c1 = new PdfPCell(new Phrase("Name"));  
      
      table.addCell(c1);
      
      PdfPCell c2 = new PdfPCell(new Phrase("Gekauft"));
      
      table.addCell(c2);
      
      PdfPCell c3 = new PdfPCell(new Phrase("Geliehen"));
      
      table.addCell(c3);
      
      PdfPCell c4 = new PdfPCell(new Phrase("Bezahlt"));
      
      table.addCell(c4);
      
      for(int i=0; i<((a1.size())/4);i++){
      table.addCell(a1.get(i));
      i++;
      table.addCell(a1.get(i));
      i++;
      table.addCell(a1.get(i));
      i++;
      table.addCell(a1.get(i));
      i++;
      }
    
        document.add(chapter1); 
        document.add(section1);
        document.add(table);
       
        
        
     document.close();
     writer.close();
           
        
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    }
   
    /* TAKEN FROM: http://stackoverflow.com/questions/25579480/how-to-export-jpanel-with-scrollable-into-pdf-file */
    public static java.awt.Image getImageFromPanel(Component component) {
       
        BufferedImage image = new BufferedImage(component.getWidth(),
            component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }
    
  
}
