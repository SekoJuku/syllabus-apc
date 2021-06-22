package kz.syllabus.controllers.teachers;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import kz.syllabus.entity.*;
import kz.syllabus.repository.PersonalInfoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.apache.bcel.generic.Instruction;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
public class TeacherPDFExporter {
    private Syllabus syllabus;
    private PersonalInfo personalInfo;
    private Prerequisite prerequisite;
    private Postrequisite postrequisite;
    private Discipline discipline1;
    private Discipline discipline2;
    private SyllabusProgram syllabusProgram;
    private ProgramDetail programDetail;


//    private void writeTableHeader(PdfPTable table, PdfPTable table4){
//        PdfPCell cell = new PdfPCell();
//        cell.setPadding(5);
//        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//        font.setSize(13);
//        cell.setPhrase(new Phrase("Week", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Theme", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Hours", font));
//        table.addCell(cell);
//        table.addCell("");
//        table.addCell("");
//        table.addCell(table4);
//        table4.addCell("Lectures");
//        table4.addCell("Practices");
//        table4.addCell("ISW");
//        table4.addCell("ISWP");
//    }

    private void writeTableData(PdfPTable table){
        table.addCell("Name of the course");
        table.addCell(String.valueOf(syllabus.getName()));
        table.addCell("Full name");
        table.addCell(String.valueOf(personalInfo.getName()+" "+personalInfo.getSname()+" "+personalInfo.getMname()));
        table.addCell("Academic degree");
        table.addCell(String.valueOf(personalInfo.getAcademicDegree()));
        table.addCell("Academic rank");
        table.addCell(String.valueOf(personalInfo.getAcademicRank()));
        table.addCell("Email");
        table.addCell(String.valueOf(personalInfo.getEmail()));
        table.addCell("Phone number");
        table.addCell(String.valueOf(personalInfo.getPhone()));
        table.addCell("Research and scientific interests");
        table.addCell(String.valueOf(personalInfo.getDescription()));
    }

    public void export(HttpServletResponse response, Integer id) throws IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(15);

        Paragraph title = new Paragraph("Syllabus", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(2);
        PdfPTable table2 = new PdfPTable(2);
        PdfPTable table3 = new PdfPTable(3);
        PdfPTable table4 = new PdfPTable(4);
        PdfPTable table5= new PdfPTable(6);
        PdfPTable gradingTable = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setSpacingAfter(15);
        table2.setWidthPercentage(100);
        table2.setSpacingBefore(15);
        table2.setSpacingAfter(15);
        table3.setWidthPercentage(100);
        table3.setSpacingBefore(15);
        table3.setSpacingAfter(15);
        table5.setWidthPercentage(100);
        table5.setSpacingBefore(15);
        table5.setSpacingAfter(15);
        gradingTable.setWidthPercentage(100);
        gradingTable.setSpacingBefore(15);
        gradingTable.setSpacingAfter(15);

//        writeTableHeader(table3, table4);
        writeTableData(table);
        writeSecondTableData(table2, syllabus);
//        writeFifthTableData(table5, syllabus);
        writeGradingTableData(gradingTable);
        document.add(table);
        document.add(table2);
        document.add(table3);
        document.add(table5);
        document.add(gradingTable);

        document.close();
    }

    private void writeSecondTableData(PdfPTable table2, Syllabus syllabus){
        table2.addCell("Number of credits");
        table2.addCell(String.valueOf(syllabus.getCredits()));
        table2.addCell("Aims of the discipline");
        table2.addCell(String.valueOf(syllabus.getAim()));
        table2.addCell("Objectives of the discipline");
        table2.addCell(String.valueOf(syllabus.getTasks()));
        table2.addCell("Results");
        table2.addCell(String.valueOf(syllabus.getResults()));
        table2.addCell("Prerequisites");
        table2.addCell(String.valueOf(discipline1.getName()));
        table2.addCell("Postrequisites");
        table2.addCell(String.valueOf(discipline2.getName()));
        table2.addCell("Methodology");
        table2.addCell(String.valueOf(syllabus.getMethodology()));
        table2.addCell("Instructors");
        table2.addCell(String.valueOf(personalInfo.getName()+" "+ personalInfo.getSname()));
    }


//    private void writeFifthTableData(PdfPTable table5, Syllabus syllabus){
//        table5.addCell("Week");
//        table5.addCell("Topic");
//        table5.addCell("Form of control");
//        table5.addCell("Basic/additional literature");
//        table5.addCell("Evaluation criteria");
//        table5.addCell("Competences");
//        table5.addCell("1");
//        table5.addCell(String.valueOf(syllabusProgram.getWeek()));
//        table5.addCell(String.valueOf(syllabusProgram.getLectureTheme()));
//        table5.addCell(String.valueOf(programDetail.getLectureFof()));
//        table5.addCell(String.valueOf(programDetail.getLectureLiterature()));
//        table5.addCell(String.valueOf(programDetail.getLectureAssessment()));
//        table5.addCell(String.valueOf(syllabus.getCompetences()));
//    }

    private void writeGradingTableData(PdfPTable gradingTable){
        PdfPCell cell = new PdfPCell();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(13);
        cell.setPhrase(new Phrase("Letter grade", font));
        gradingTable.addCell(cell);
        cell.setPhrase(new Phrase("Numerical equivalent", font));
        gradingTable.addCell(cell);
        cell.setPhrase(new Phrase("Percentage", font));
        gradingTable.addCell(cell);
        cell.setPhrase(new Phrase("Grade according to the traditional system", font));
        gradingTable.addCell(cell);
        gradingTable.addCell("A");
        gradingTable.addCell("4,0");
        gradingTable.addCell("95-100");
        gradingTable.addCell("Excellent");

        gradingTable.addCell("A-");
        gradingTable.addCell("3,67");
        gradingTable.addCell("90-94");
        gradingTable.addCell("Excellent");

        gradingTable.addCell("B+");
        gradingTable.addCell("3,33");
        gradingTable.addCell("85-89");
        gradingTable.addCell("Good");

        gradingTable.addCell("B");
        gradingTable.addCell("3,0");
        gradingTable.addCell("80-84");
        gradingTable.addCell("Good");

        gradingTable.addCell("B-");
        gradingTable.addCell("2,67");
        gradingTable.addCell("75-79");
        gradingTable.addCell("Good");

        gradingTable.addCell("C+");
        gradingTable.addCell("2,33");
        gradingTable.addCell("70-74");
        gradingTable.addCell("Good");

        gradingTable.addCell("C");
        gradingTable.addCell("2,0");
        gradingTable.addCell("65-69");
        gradingTable.addCell("Satisfactory");

        gradingTable.addCell("C-");
        gradingTable.addCell("1,67");
        gradingTable.addCell("60-64");
        gradingTable.addCell("Satisfactory");

        gradingTable.addCell("D+");
        gradingTable.addCell("1,33");
        gradingTable.addCell("55-59");
        gradingTable.addCell("Satisfactory");

        gradingTable.addCell("D");
        gradingTable.addCell("1,0");
        gradingTable.addCell("50-54");
        gradingTable.addCell("Satisfactory");

        gradingTable.addCell("FX");
        gradingTable.addCell("0");
        gradingTable.addCell("25-49");
        gradingTable.addCell("Fail");

        gradingTable.addCell("F");
        gradingTable.addCell("0");
        gradingTable.addCell("0-24");
        gradingTable.addCell("Fail");
    }

}
