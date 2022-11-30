package pe.com.emilima.dms.service.impl;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import pe.com.emilima.dms.service.ReportService;
import pe.com.emilima.dms.util.JasperReportsLib;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl<T> implements ReportService<T> {
    @Override
    public void generateReportAndDownload(HttpServletResponse response, String filename, List<T> list) {
        try {
            List<T> listStream = new ArrayList<>(list);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listStream);
            File file = ResourceUtils.getFile(MessageFormat.format("classpath:{0}", filename));
            JasperPrint print = JasperReportsLib.generateReport(file, dataSource);

            response.setContentType("application/pdf");

            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(print, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
