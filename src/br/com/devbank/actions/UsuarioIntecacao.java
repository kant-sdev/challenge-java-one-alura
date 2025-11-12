package br.com.devbank.actions;

import br.com.devbank.models.Conta;
import br.com.devbank.models.Investimentos;
import br.com.devbank.services.ApiService;

import java.util.Scanner;

public class UsuarioIntecacao {
    public Conta criarConta(){
        Scanner contaUsuario = new Scanner(System.in);

        System.out.println("\n🧾 CRIAÇÃO DE CONTA");
        System.out.println("------------------------------------");

        System.out.println("Informe seu nome: ");
        String nome = contaUsuario.nextLine();

        System.out.println("Iforme o seu saldo: ");
        double saldo = contaUsuario.nextDouble();
        contaUsuario.nextLine();

        System.out.println("Informe o seu Pais de Origem: ");
        String pais = contaUsuario.nextLine();

        Conta contaAtual = new Conta(nome, saldo, pais);

        System.out.println("\n✅ Conta criada com sucesso!");
        System.out.println("------------------------------------");
        System.out.println(contaAtual.toString());

        return contaAtual;
    }

    public Investimentos fazerInvestimento(Conta conta, String origem, String destino, double valorInvestido, boolean aptoResgate){
        if (conta == null){
            System.out.println("❌ Nenhuma conta encontrada! Crie uma conta antes de investir.");
            return null;
        }

        ApiService api = new ApiService();

        if(valorInvestido <= 0){
            System.out.println("⚠️ Valor inválido! Informe um número maior que zero.");
            return null;
        }

        double taxaConversao = api.taxaDeCambioComFallback(origem, destino);

        if(taxaConversao <= 0){
            System.out.println("❌ Falha ao obter taxa de câmbio. Tente novamente mais tarde.");
            return null;
        }

        double valorConvertido = valorInvestido * taxaConversao;

        if(aptoResgate) {
            conta.setSaldoConta(conta.getSaldoConta() + valorConvertido);
        } else {
            if (valorInvestido > conta.getSaldoConta()){
                System.out.println("❌ Saldo insuficiente para realizar o investimento!");
                return null;
            }

            conta.setSaldoConta(conta.getSaldoConta() - valorInvestido);
        }

        Investimentos investimento = new Investimentos(origem, destino, valorInvestido, valorConvertido);
        conta.fazerInvesitmento(investimento);

        System.out.println("\n✅ " + (aptoResgate ? "Resgate" : "Investimento") + " realizado com sucesso!\n");
        System.out.println(investimento.getInfoInvestimento());
        System.out.printf("💼 Saldo atual da conta: %.2f BRL%n", conta.getSaldoConta());

        return investimento;
    }
}
