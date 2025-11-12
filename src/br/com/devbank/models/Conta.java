package br.com.devbank.models;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String nomeTitular;
    private double saldoConta;
    private String pais;
    private List<Investimentos> investimentos;

    public Conta(String nome, double saldo, String pais){
        this.nomeTitular = nome;
        this.saldoConta = saldo;
        this.pais = pais;
        this.investimentos = new ArrayList<>();

    }

    public String getNomeUsuario() {
        return  nomeTitular;
    }

    public double getSaldoConta(){
        return  saldoConta;
    }

    public  List<Investimentos> getInvestimentos(){
        return investimentos;
    }

    public String getInfoGeralConta() {
        return String.format("""
            \n📊 INFORMAÇÕES DA CONTA
            ------------------------------------
            Titular da Conta: %s
            Saldo Atual: R$ %.2f
            Nacionalidade: %s
            Total de Investimentos: %d
            ------------------------------------
            """, nomeTitular, saldoConta, pais, investimentos.size());
    }

    public String getInfo(String campo) {
        if (campo == null) return "Campo inválido!";
        return switch (campo.toLowerCase()) {
            case "info" -> getInfoGeralConta();
            case "titular" -> "Titular: " + getNomeUsuario();
            case "saldo" -> String.format("Saldo Atual: %.2f", getSaldoConta());
            case "pais" -> "Nacionalidade: " + pais;
            case "totalinvestimentos" -> "Total de Investimentos: " + investimentos.size();
            default -> "Campo não encontrado.";
        };
    }


    public void fazerInvesitmento(Investimentos investimento){
        this.investimentos.add(investimento);
    }

    public void  setSaldoConta(double saldo){
        this.saldoConta = saldo;
    }

    @Override
    public String toString() {
        return String.format("""
            Titular: %s
            Saldo Atual: R$ %.2f
            País: %s
            """, nomeTitular, saldoConta, pais);
    }
}
