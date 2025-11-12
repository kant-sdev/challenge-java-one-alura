package br.com.devbank.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Investimentos {
    private String moedaOrigem;
    private String moedaDestino;
    private double valorInvestido;
    private double valorConvertido;
    private boolean aptoResgate;
    private LocalDateTime dataInvestimento;

    public Investimentos(
            String moedaOrigem,
            String moeadaDestino,
            double valorInvestido,
            double valorConvertido

    ){
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moeadaDestino;
        this.valorInvestido = valorInvestido;
        this.valorConvertido = valorConvertido;
        this.dataInvestimento = LocalDateTime.now();
    }

    public String getMoedaOrigem(){
        return moedaOrigem;
    }

    public  String getMoedaDestino(){
        return moedaDestino;
    }

    public double getValorInvestido(){
        return  valorInvestido;
    }

    public  double getValorConvertido(){
        return valorConvertido;
    }

    public String getDataFormatada() {
        return dataInvestimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getInfoInvestimento(){
        return String.format("""
            💰 INFORMAÇÕES DO INVESTIMENTO
            -----------------------------
            Moeda de origem:        %s
            Moeda de investimento:  %s
            Valor investido:        %.2f
            Valor convertido:       %.2f
            Data do investimento:   %s
            """,
                moedaOrigem, moedaDestino, valorInvestido, valorConvertido, getDataFormatada()
        );
    }

    public String getInfo(String campo) {
        if (campo == null) return "Campo inválido!";

        return switch (campo.toLowerCase()) {
            case "info" -> getInfoInvestimento();
            case "origem" -> "Origem da Conversão: " + getMoedaOrigem();
            case "destino" -> "Destino da Conversão: " + getMoedaDestino();
            case "investido" -> String.format("Valor Investido: %.2f", getValorInvestido());
            case "convertido" -> String.format("Valor Convertido: %.2f", getValorConvertido());
            case "data" -> "Data da Conversão: " + getDataFormatada();
            default -> "Campo inválido!";
        };
    }

    @Override
    public String toString() {
        return String.format("%s -> %s: %.2f -> %.2f (%s)",
                moedaOrigem, moedaDestino, valorInvestido, valorConvertido, getDataFormatada());
    }
}
