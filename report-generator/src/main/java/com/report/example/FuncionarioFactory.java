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
        } 

        return new Funcionario();
    }

}
