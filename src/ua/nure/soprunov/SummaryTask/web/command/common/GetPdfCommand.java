package ua.nure.soprunov.SummaryTask.web.command.common;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.log4j.Logger;
import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.dao.implementation.FlightDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * GetPdfCommand command generate pdf list of flights. Command allowed  for all users.
 *
 *  @authors Soprunov Igor
 */

public class GetPdfCommand extends Command {


    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(GetPdfCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document doc = new Document(pdfDoc, new PageSize(595, 842));
        doc.setMargins(0, 0, 0, 0);

        Table table = new Table(new float[8]).useAllAvailableWidth();
        table.setMarginTop(0);
        table.setMarginBottom(0);

        Cell firstRow = new Cell(1, 8).add(new Paragraph("List auto flights"));
        firstRow.setTextAlignment(TextAlignment.CENTER);
        firstRow.setPadding(5);
        firstRow.setBackgroundColor(new DeviceRgb(140, 221, 8));
        table.addCell(firstRow);

        table.addCell("Number");
        table.addCell("Flight name");
        table.addCell("Date");
        table.addCell("Depart point");
        table.addCell("Arrival point");
        table.addCell("Driver name");
        table.addCell("Car model");
        table.addCell("Status");


        List<Flight> listFlights = new FlightDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).findAll();
        LOG.trace("Found in DB: listFlights --> " + listFlights);

        for (int i = 0; i < listFlights.size(); i++) {
            table.addCell(listFlights.get(i).getId().toString());
            table.addCell(listFlights.get(i).getName());
            table.addCell(listFlights.get(i).getDate());
            table.addCell(listFlights.get(i).getDepart());
            table.addCell(listFlights.get(i).getArrival());
            if (listFlights.get(i).getDriverName() != null & listFlights.get(i).getCarModel() != null) {
                table.addCell(listFlights.get(i).getDriverName());
                table.addCell(listFlights.get(i).getCarModel());
            } else {
                table.addCell("");
                table.addCell("");
            }
            table.addCell(listFlights.get(i).getStatus());

        }

        doc.add(table);

        doc.close();

        // setting some response headers
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();


        LOG.debug("Command finished");
        return Path.LIST_AUTO_FLIGHTS;
    }
}
