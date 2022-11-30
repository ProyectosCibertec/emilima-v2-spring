package pe.com.emilima.dms.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportService<T> {
    void generateReportAndDownload(HttpServletResponse response, String filename, List<T> list);
}
