package br.com.devbank.tests;

import br.com.devbank.services.ApiService;

public class TesteApi {
    public static void main(String[] args) {
        System.out.println("🧪 INICIANDO TESTES DA API\n");

        ApiService api = new ApiService();

        // 1. Teste de conexão básica
        System.out.println("=== TESTE DE CONEXÃO ===");
        boolean conexaoOk = api.testarConexao();
        System.out.println("Conexão OK: " + conexaoOk + "\n");

        // 2. Teste de conversões principais
        System.out.println("=== TESTE DE CONVERSÕES ===");
        testarConversao(api, "BRL", "USD", "Real para Dólar");
        testarConversao(api, "BRL", "EUR", "Real para Euro");
        testarConversao(api, "BRL", "GBP", "Real para Libra");
        testarConversao(api, "USD", "BRL", "Dólar para Real");
        testarConversao(api, "EUR", "BRL", "Euro para Real");
        testarConversao(api, "GBP", "BRL", "Libra para Real");

        // 3. Teste de moeda inválida
        System.out.println("=== TESTE DE ERROS ===");
        testarConversao(api, "BRL", "XYZ", "Moeda inválida");

        System.out.println("🎯 TESTES FINALIZADOS");
    }

    private static void testarConversao(ApiService api, String de, String para, String descricao) {
        System.out.println("🔍 Testando: " + descricao);
        double taxa = api.obterTaxaDeCambio(de, para);

        if (taxa > 0) {
            System.out.printf("✅ %s → %s: %.4f\n\n", de, para, taxa);
        } else {
            System.out.printf("❌ %s → %s: FALHA (taxa: %.4f)\n\n", de, para, taxa);
        }
    }
}