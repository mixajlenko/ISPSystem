package com.mixajlenko.finaltask.ispsystem.controller.command;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mixajlenko.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.service.ITariffService;
import com.mixajlenko.finaltask.ispsystem.service.factory.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DownloadServiceCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //tell browser program going to return an application file
        //instead of html page
        ServiceFactory factory = ServiceFactory.getInstance();
        Optional<String> optionalFormat = Optional.of(request.getParameter("format"));
        String format = optionalFormat.orElse("");
        Optional<String> optionalServiceName = Optional.of(request.getParameter("serviceName"));
        String serviceName = optionalServiceName.orElse("All Tariffs");
        String serviceId = request.getParameter("serviceId");

        response.setContentType("application/octet-stream");

        switch (format) {
            case "txt":
                response.setHeader("Content-Disposition", "attachment;filename=Tariffs.txt");
                break;
            case "pdf":
                response.setHeader("Content-Disposition", "attachment;filename=Tariffs.pdf");
                break;
            case "docx":
                response.setContentType("application/msword; charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=Tariffs.doc");
                break;
            case "csv":
                response.setHeader("Content-Disposition", "attachment;filename=Tariffs.csv");
                break;
            default:
                response.setHeader("Content-Disposition", "attachment;filename=Tariffs.csv");

        }

        try {
            ITariffService tariffService = factory.getTariffService();
            ServletOutputStream out = response.getOutputStream();
            StringBuilder sb;
            if (Objects.isNull(serviceId)) {
                sb = generateCsvFileBuffer(serviceName, tariffService.getAll());
            } else {
                sb = generateCsvFileBuffer(serviceName, tariffService.getServiceTariff(Integer.parseInt(serviceId)));

            }
            InputStream in =
                    new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
            if (!format.equals("pdf")) {
                byte[] outputByte = new byte[4096];
                while (in.read(outputByte, 0, 4096) != -1) {
                    out.write(outputByte, 0, 4096);
                }
            } else {
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, out);
                document.open();
                document.add(new Paragraph(sb.toString()));
                document.close();
                writer.close();
            }

            in.close();
            out.flush();
            out.close();

        } catch (IOException | SQLException | NamingException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder generateCsvFileBuffer(String serviceName, List<Tariff> tariffs) {
        StringBuilder writer = new StringBuilder();

        writer.append(serviceName);
        writer.append('\n');
        writer.append("Name");
        writer.append(",");
        writer.append("Description");
        writer.append(",");
        writer.append("Price");
        writer.append('\n');

        for (Tariff tariff : tariffs) {
            writer.append(tariff.getName());
            writer.append(",");
            writer.append(tariff.getDescription());
            writer.append(",");
            writer.append(tariff.getPrice());
            writer.append('\n');
        }

        return writer;
    }

}
