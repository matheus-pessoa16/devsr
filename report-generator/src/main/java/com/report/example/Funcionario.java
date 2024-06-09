package com.report.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Funcionario {

    private String nome;

    private LocalDate dataNascimento;

    private String matricula;

    private String cargo;

    private Double remuneracao;

    private LocalDate dataAdmissao;

    private String cpf;

    private String nomeMae;

    private List<String> linguagensProgramacao = new ArrayList<String>();

    private String funcionarioImgPath;


    public Funcionario() {
        
    }

    public Funcionario(
            String nome,
            LocalDate dataNascimento,
            String matricula,
            String cargo,
            Double remuneracao,
            LocalDate dataAdmissao,
            String cpf,
            String nomeMae,
            List<String> linguagensProgramacao,
            String funcionarioImgPath) {
        
                this.nome = nome;
                this.dataNascimento = dataNascimento;
                this.matricula = matricula;
                this.cargo = cargo;
                this.remuneracao = remuneracao;
                this.dataAdmissao = dataAdmissao;
                this.cpf = cpf;
                this.nomeMae = nomeMae;
                this.linguagensProgramacao = linguagensProgramacao;
                this.funcionarioImgPath = funcionarioImgPath;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(Double remuneracao) {
        this.remuneracao = remuneracao;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public List<String> getLinguagensProgramacao() {
        return linguagensProgramacao;
    }

    public void setLinguagensProgramacao(List<String> linguagensProgramacao) {
        this.linguagensProgramacao = linguagensProgramacao;
    }

    public String getFuncionarioImgPath() {
        return funcionarioImgPath;
    }

    public void setFuncionarioImgPath(String funcionarioImgPath) {
        this.funcionarioImgPath = funcionarioImgPath;
    }

}

