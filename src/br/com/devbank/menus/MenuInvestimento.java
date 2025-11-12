package br.com.devbank.menus;

import java.util.Scanner;

public class MenuInvestimento {

    private  Scanner menuInvestimento = new Scanner(System.in);
    public int exibirMenuInvestimento() {
        System.out.println("""
        ====== 💹 MENU DE INVESTIMENTOS ======

        Escolha uma opção:

        [1] Investir: Real (BRL) ➜ Dólar (USD)
        [2] Investir: Real (BRL) ➜ Euro (EUR)
        [3] Investir: Real (BRL) ➜ Libra (GBP)
        [4] Resgatar: Dólar (USD) ➜ Real (BRL)
        [5] Resgatar: Euro (EUR) ➜ Real (BRL)
        [6] Resgatar: Libra (GBP) ➜ Real (BRL)
        [7] Conversão personalizada
        [8] Voltar ao menu principal

        Opção:
        """);

        return menuInvestimento.nextInt();
    }
    public double solicitarValorInvestimento() {
        System.out.print("💰 Informe o valor a investir: ");
        return menuInvestimento.nextDouble();
    }

    public String[] solicitarMoedasPersonalizadas() {
        menuInvestimento.nextLine();
        System.out.println("\n🌍 CONVERSÃO PERSONALIZADA");
        System.out.println("------------------------------------");

        System.out.print("Digite a moeda de origem (ex: BRL): ");
        String origem = menuInvestimento.nextLine().trim().toUpperCase();

        System.out.print("Digite a moeda de destino (ex: USD): ");
        String destino = menuInvestimento.nextLine().trim().toUpperCase();

        if (origem.length() != 3 || destino.length() != 3) {
            System.out.println("\n⚠️ Moeda inválida! Use o formato de 3 letras (ex: BRL, USD, EUR).");
            return solicitarMoedasPersonalizadas();
        }

        System.out.println();
        return new String[]{origem, destino};
    }
}
