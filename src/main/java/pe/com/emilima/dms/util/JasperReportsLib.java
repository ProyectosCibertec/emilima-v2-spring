package pe.com.emilima.dms.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;

public class JasperReportsLib {
	public static JasperPrint generateReport(File file,JRBeanCollectionDataSource data) throws JRException {
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JasperPrint jasperPrint;
		jasperPrint = JasperFillManager.fillReport(jasper, null, data);

		return jasperPrint;
	}
}
