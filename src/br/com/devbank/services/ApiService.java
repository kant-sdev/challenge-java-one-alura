package br.com.devbank.services;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ApiService {
    Dotenv dotenv = Dotenv.load();

    private String API_URL = dotenv.get("API_URL");
    private String apiKey = dotenv.get("API_KEY");
    private HttpClient clientService;
    private Gson gson;

    public  ApiService(){
        this.clientService = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public  double obterTaxaDeCambio(String origem, String destino){

        try {
            String urlApi = String.format("%s%s/latest/%s", API_URL, apiKey, origem.toUpperCase());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlApi))
                    .GET()
                    .build();

            HttpResponse<String> response = clientService.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200){
                Map<String, Object> jsonResponse = gson.fromJson(response.body(), Map.class);

                Object valoresConversao = jsonResponse.get("conversion_rates");

                if(valoresConversao instanceof  Map){
                    @SuppressWarnings("unchecked")
                    Map<String, Double> taxas = (Map<String, Double>) valoresConversao;

                    String destinoConversao = destino.toUpperCase();
                    if (taxas.containsKey(destinoConversao)){
                        return taxas.get(destinoConversao);
                    } else {
                        System.out.println("⚠️ Moeda de destino '" + destino + "' não encontrada.");
                        return 0.0;
                    }
                } else {
                    System.out.println("❌ Formato inesperado da resposta da API");
                    return 0.0;
                }
            }else {
                System.out.println("❌ Erro na requisição. Código HTTP: " + response.statusCode());
                return 0.0;
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao consultar a API: " + e.getMessage());
            return 0.0;
        }
    }

    public boolean testarConexao() {
        double taxa = obterTaxaDeCambio("USD", "BRL");
        boolean sucesso = taxa > 0;
        if (sucesso) {
            System.out.println("✅ API conectada com sucesso! USD→BRL: " + taxa);
        } else {
            System.out.println("❌ Falha na conexão com API");
        }
        return sucesso;
    }

    private double getTaxaFallback(String origem, String destino) {
        System.out.println("⚠️  Usando valores simulados (API offline)");

        Map<String, Double> fallbackRates = Map.of(
                "BRL-USD", 0.19, "BRL-EUR", 0.17, "BRL-GBP", 0.15,
                "USD-BRL", 5.26, "EUR-BRL", 5.88, "GBP-BRL", 6.67
        );

        String key = origem.toUpperCase() + "-" + destino.toUpperCase();
        return fallbackRates.getOrDefault(key, 1.0);
    }

    public double taxaDeCambioComFallback(String origem, String destino) {
        double taxa = obterTaxaDeCambio(origem, destino);
        if (taxa == 0.0) {
            return getTaxaFallback(origem, destino);
        }
        return taxa;
    }
}
