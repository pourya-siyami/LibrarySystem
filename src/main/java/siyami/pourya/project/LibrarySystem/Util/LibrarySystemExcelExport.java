package siyami.pourya.project.LibrarySystem.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import siyami.pourya.project.LibrarySystem.Model.Book;

import java.util.List;
import java.util.Map;

public class LibrarySystemExcelExport extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(
            Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        response.addHeader("Content-Disposition", "attachment;fileName=LibrarySystem.xlsx");

        @SuppressWarnings("unchecked")
        List<Book> list = (List<Book>) model.get("list");

        Sheet sheet = workbook.createSheet("Book");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Book_id");
        row0.createCell(1).setCellValue("Book_name");
        row0.createCell(2).setCellValue("Member_firstname");
        row0.createCell(3).setCellValue("Member_lastname");
        row0.createCell(4).setCellValue("Borrowing_day_of_this_month");
        row0.createCell(5).setCellValue("Returning_day_of_this_month");
        row0.createCell(6).setCellValue("Number_of_borrowed_books");

        int rowNum = 1;
        for (Book book : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(book.getBookId());
            row.createCell(1).setCellValue(book.getTitle());
            if (book.getBorrowedBy() != null) {
                row.createCell(2).setCellValue(book.getBorrowedBy().getFirstName());
                row.createCell(3).setCellValue(book.getBorrowedBy().getLastName());
                row.createCell(4).setCellValue(book.getBorrowedBy().getBorrowBookTime().getDayOfMonth());
                if (book.getBorrowedBy().getReturnBookTime() != null) {
                    row.createCell(5).setCellValue(book.getBorrowedBy().getReturnBookTime().getDayOfMonth());
                }
                row.createCell(6).setCellValue(book.getBorrowedBy().getBookNum());
            }
        }
    }
}
