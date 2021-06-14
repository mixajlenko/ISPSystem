package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mixajlenko.finaltask.ispsystem.controller.command.utils.CommandUtil;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceIdException;
import com.mixajlenko.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class DownloadServiceCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(DownloadServiceCommand.class);
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Start execution DownloadServiceCommand");
        var factory = ServiceFactory.getInstance();

        Optional<String> optionalFormat = Optional.of(request.getParameter("format"));

        String format = optionalFormat.orElse("");

        Optional<String> serviceName = Optional.of(request.getParameter("serviceName"));

        Optional<String> serviceId = Optional.of(request.getParameter("serviceId"));

        response.setContentType("application/octet-stream");

        switch (format) {
            case "txt":
                response.setHeader(CONTENT_DISPOSITION, "attachment;filename=Tariffs.txt");

                break;
            case "pdf":
                response.setHeader(CONTENT_DISPOSITION, "attachment;filename=Tariffs.pdf");
                break;
            case "docx":
                response.setContentType("application/msword; charset=UTF-8");
                response.setHeader(CONTENT_DISPOSITION, "attachment;filename=Tariffs.doc");
                break;
            default:
                response.setHeader(CONTENT_DISPOSITION, "attachment;filename=Tariffs.csv");
        }
        logger.info("Download tariffs in " + format + " format");

        try {
            ITariffService tariffService = factory.getTariffService();
            ServletOutputStream out = response.getOutputStream();
            StringBuilder sb;
            if (Objects.equals(Optional.of("empty"), serviceId)) {
                sb = CommandUtil
                        .generateDownloadFileBuffer(
                                serviceName.orElse("All Tariffs"),
                                tariffService.getAll());
            } else {
                sb = CommandUtil
                        .generateDownloadFileBuffer(
                                serviceName.orElse("All Tariffs"),
                                tariffService.getServiceTariff(Integer.parseInt(serviceId.orElseThrow(NotFoundServiceIdException::new))));

            }

            InputStream in =
                    new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
            if (!format.equals("pdf")) {
                var outputByte = new byte[4096];
                while (in.read(outputByte, 0, 4096) != -1) {
                    out.write(outputByte, 0, 4096);
                }
            } else {
                var document = new Document();
                var writer = PdfWriter.getInstance(document, out);
                document.open();
                document.add(new Paragraph(sb.toString()));
                document.close();
                writer.close();
            }

            in.close();
            out.flush();
            out.close();

        } catch (IOException | SQLException | NamingException | DocumentException | NotFoundServiceIdException e) {
            e.printStackTrace();
        }
    }

}
