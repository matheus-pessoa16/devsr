package com.report.example;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportGenerator {

    public static void generateReport(Report report) {
        try {
            createReportFile(createMapObjectToReport(report));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Map<String, Object> createMapObjectToReport(Report report) {

        Map<String, Object> map = new HashMap<>();

        map.put("nome", report.getNome());
        map.put("dataNascimento", report.getDataNascimento());
        map.put("matricula", report.getMatricula());
        map.put("cpf", report.getCpf());
        map.put("nomeMae", report.getNomeMae());
        map.put("dataAdmissao", report.getDataAdmissao());
        map.put("cargo", report.getCargo());
        map.put("remuneracao", report.getRemuneracao());

        map.put("linguagensProgramacao", report.getLinguagensProgramacao());

        map.put("logo", getImg("logos/logo.png"));
        map.put("funcionarioImg", getImg("images/"+report.getFuncionarioImg()));

        return map;
    }


    private static void createReportFile(Map<String, Object> reportMap) throws IOException {

        String file = "/src/main/reports/Relatorio.jasper";

        try {
            Path currentRelativePath = Paths.get("");
            String jasperFilePath = currentRelativePath.toAbsolutePath().toString()
                    + file;
            System.out.println(jasperFilePath);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, reportMap, new JREmptyDataSource());
            byte[] report = JasperExportManager.exportReportToPdf(jasperPrint);
          
            writeBytesToFileNio("relatorios/Relatorio"+LocalDateTime.now().toString()+".pdf", report);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeBytesToFileNio(String fileOutput, byte[] bytes)
        throws IOException {

        Path path = Paths.get(fileOutput);
        Files.write(path, bytes);
    }

    private static synchronized Image getImg(String filename) {

        Image image = null;
        InputStream inputStream = null;
        try {
            File initialFile = new File("src/main/reports/" + filename);
            inputStream = new FileInputStream(initialFile);
            byte[] bytesImage = inputStream.readAllBytes();
            ImageIcon imageIcon = new ImageIcon(bytesImage);
            image = imageIcon.getImage();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return image;
    }


}
