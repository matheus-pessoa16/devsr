package com.report.example;

import java.time.LocalDate;
import java.util.Arrays;

public class FuncionarioFactory {
    
    public static Funcionario getFuncionario(String nome) {
        
        if(nome.equals("matheus")) {
            return new Funcionario("Matheus Estevam de Carvalho Pessoa", 
            LocalDate.of(1995, 2, 8), "2023123123", "Engenheiro de Software", 
            2000.0, LocalDate.of(2023, 5, 1), "123.123.123-99", "Mãe de Teste 1", 
            Arrays.asList("Java", "Python", "Javascript", "C", "SQL"),
            "matheus.jpeg"
            );
        } else if(nome.equals("marcel")) {
            return new Funcionario("Marcel Garcia", 
            LocalDate.of(1980, 5, 1), "2023456789", "Tech Lead", 
            10000.0, LocalDate.of(2020, 1, 1), "345.345.345-00", "Mãe de Marcel", 
            Arrays.asList("Java", "PHP", "Javascript", "C", "Ruby"),
            "marcel.jpeg"
            );
        }

        return new Funcionario();
    }

}
