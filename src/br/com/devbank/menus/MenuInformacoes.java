package br.com.devbank.menus;

import java.util.Scanner;

public class MenuInformacoes {
    private Scanner menuInformacoes = new Scanner(System.in);

    public int exibirMenuInformacoes() {
        System.out.println("""
                
        ====================================
                📊 MENU DE INFORMAÇÕES
        ====================================

        Escolha o que deseja visualizar:

            [1] Ver informações da conta 💼
            [2] Ver todos os investimentos 📈
            [3] Ver investimento específico 🔍
            [4] Voltar ao menu principal ↩️
        ------------------------------------
        """);

        System.out.print("Informe sua opção: ");
        int opcao = menuInformacoes.nextInt();
        System.out.println();
        return opcao;
    }

    public int solicitarIndiceInvestimento() {
        System.out.println("\n🔍 DETALHAR INVESTIMENTO");
        System.out.println("------------------------------------");
        System.out.print("Digite o número do investimento que deseja ver em detalhe: ");
        int indice = menuInformacoes.nextInt();
        System.out.println();
        return indice;
    }
}
