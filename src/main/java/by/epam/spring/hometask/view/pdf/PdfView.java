package by.epam.spring.hometask.view.pdf;

import by.epam.spring.hometask.domain.User;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

public class PdfView extends AbstractPdfView {

    @Override
    public void buildPdfDocument(Map model, Document document, PdfWriter writer,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        Set<User> users = (Set<User>) model.get("users");

        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell("First Name");
        table.addCell("Last Name");
        table.addCell("Email");

        for (User user : users) {
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(user.getEmail());
        }
        document.add(table);

    }

}
