/*
 * JasperReports Modern PDF Exporter
 * Copyright © 2005 - 2018 TIBCO Software Inc.
 * http://www.jaspersoft.com.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperreports.export.pdf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class PdfExportTest
{
	private File outputDir;

	@BeforeClass
	public void init() throws JRException, IOException
	{
		outputDir = new File("target/reports");
		if (!outputDir.exists())
		{
			outputDir.mkdir();
		}
	}

	@Test
	public void testSomething() throws JRException
	{
		JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\SitharthSurya\\git\\jasper-export-demo\\src\\main\\resources\\Blank_Letter.jrxml");

		List<SampleBean> dataList = new ArrayList<SampleBean>();
		SampleBean sampleBean = new SampleBean();
		sampleBean.setName("some name");
		sampleBean.setColor("red");
		dataList.add(sampleBean);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
		Map parameters = new HashMap();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(new File("src/test/resources/FirstJasper.jrprint"));

		File destFile = new File(outputDir, jasperPrint.getName() + ".pdf");

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

		exporter.exportReport();
	}
}
