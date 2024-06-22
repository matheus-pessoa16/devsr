package com.report.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Funcionario funcionario1 = FuncionarioFactory.getFuncionario("matheus");

        Report reportFuncionario1 = convertFuncionarioToReport(funcionario1);
        ReportGenerator.generateReport(reportFuncionario1);
    }

    static Report convertFuncionarioToReport(Funcionario funcionario) {

        Report report = new Report();

        report.setCargo(funcionario.getCargo());
        report.setNome(funcionario.getNome());
        report.setCpf(funcionario.getCpf());
        report.setMatricula(funcionario.getMatricula());
        report.setNomeMae(funcionario.getNomeMae());
        report.setRemuneracao(funcionario.getRemuneracao().toString());
        
        report.setDataAdmissao(formatDate(funcionario.getDataAdmissao()));
        report.setDataNascimento(formatDate(funcionario.getDataNascimento()));

        String linguagens = "";

        for (String l : funcionario.getLinguagensProgramacao()) {
            linguagens += l + "\n";
        }

        report.setLinguagensProgramacao(linguagens);

        report.setFuncionarioImg(funcionario.getFuncionarioImgPath());

        return report;

    }

    static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return date.format(formatter);
    }
}
