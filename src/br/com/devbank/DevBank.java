package br.com.devbank;

import br.com.devbank.actions.UsuarioIntecacao;
import br.com.devbank.menus.MenuInformacoes;
import br.com.devbank.menus.MenuInvestimento;
import br.com.devbank.menus.MenuPrincipal;
import br.com.devbank.models.Conta;

public class DevBank {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        UsuarioIntecacao usuario = new UsuarioIntecacao();
        Conta contaUsuario = null;

        int opcao = 0;

        while (opcao != 4){
            opcao = menuPrincipal.ExibirMenu();

            switch (opcao){
                case 1 -> contaUsuario = usuario.criarConta();
                case 2 -> {
                    if (contaUsuario == null) {
                        System.out.println("❌ Crie uma conta primeiro!");
                        break;
                    }

                    MenuInvestimento menuInvestimento = new MenuInvestimento();
                    int opcaoInvestimento = 0;

                    while (opcaoInvestimento != 8) {
                        opcaoInvestimento = menuInvestimento.exibirMenuInvestimento();

                        String moedaOrigem = "BRL";
                        String moedaDestino = "USD";
                        boolean aptoResgate = false;

                        switch (opcaoInvestimento) {
                            case 1 -> {
                                moedaOrigem = "BRL";
                                moedaDestino = "USD";
                                aptoResgate = false;
                            }
                            case 2 -> {
                                moedaOrigem = "BRL";
                                moedaDestino = "EUR";
                                aptoResgate = false;
                            }
                            case 3 -> {
                                moedaOrigem = "BRL";
                                moedaDestino = "GBP";
                                aptoResgate = false;
                            }
                            case 4 -> {
                                moedaOrigem = "USD";
                                moedaDestino = "BRL";
                                aptoResgate = true;
                            }
                            case 5 -> {
                                moedaOrigem = "EUR";
                                moedaDestino = "BRL";
                                aptoResgate = true;
                            }
                            case 6 -> {
                                moedaOrigem = "GBP";
                                moedaDestino = "BRL";
                                aptoResgate = true;
                            }
                            case 7 -> {
                                String[] moedas = menuInvestimento.solicitarMoedasPersonalizadas();
                                moedaOrigem = moedas[0];
                                moedaDestino = moedas[1];

                                if (moedaDestino.equalsIgnoreCase("BRL")) {
                                    aptoResgate = true;
                                    System.out.println("\n🔄 Operação detectada: Resgate (" + moedaOrigem + " ➜ " + moedaDestino + ")");
                                } else if (moedaOrigem.equalsIgnoreCase("BRL")) {
                                    aptoResgate = false;
                                    System.out.println("\n💹 Operação detectada: Investimento (" + moedaOrigem + " ➜ " + moedaDestino + ")");
                                } else {
                                    System.out.println("⚠️ Operação inválida! Pelo menos uma das moedas deve ser BRL (Real).");
                                    continue;
                                }
                            }
                            case 8 -> {
                                System.out.println("↩️ Voltando ao menu principal...");
                                continue;
                            }
                            default -> {
                                System.out.println("❌ Opção inválida!");
                                continue;
                            }
                        }
                        double valor = menuInvestimento.solicitarValorInvestimento();
                        usuario.fazerInvestimento(contaUsuario, moedaOrigem, moedaDestino, valor, aptoResgate);
                    }

                }
                case 3 -> {
                    if (contaUsuario == null) {
                        System.out.println("❌ Crie uma conta primeiro!");
                        break;
                    }

                    MenuInformacoes menuInfo = new MenuInformacoes();
                    int opcaoInfo = 0;

                    while (opcaoInfo != 4){
                        opcaoInfo = menuInfo.exibirMenuInformacoes();

                        switch (opcaoInfo) {
                            case 1 -> {
                                System.out.println("\n====== 💼 INFORMAÇÕES DA CONTA ======");
                                System.out.println(contaUsuario.getInfoGeralConta());
                            }
                            case 2 -> {
                                System.out.println("\n====== 📈 INVESTIMENTOS REALIZADOS ======");
                                if (contaUsuario.getInvestimentos().isEmpty()) {
                                    System.out.println("⚠️ Nenhum investimento encontrado.");
                                } else {
                                    int i = 1;
                                    for (var investimento : contaUsuario.getInvestimentos()) {
                                        System.out.println(i++ + ". " + investimento.toString());
                                    }
                                }
                            }
                            case 3 -> {
                                if (contaUsuario.getInvestimentos().isEmpty()) {
                                    System.out.println("⚠️ Nenhum investimento encontrado.");
                                    break;
                                }

                                int indice = menuInfo.solicitarIndiceInvestimento();

                                if (indice < 1 || indice > contaUsuario.getInvestimentos().size()) {
                                    System.out.println("❌ Índice inválido!");
                                } else {
                                    var investimento = contaUsuario.getInvestimentos().get(indice - 1);
                                    System.out.println("\n====== 🔍 DETALHES DO INVESTIMENTO ======");
                                    System.out.println(investimento.getInfoInvestimento());
                                }
                            }
                            case 4 -> System.out.println("↩️ Voltando ao menu principal...");
                            default -> System.out.println("❌ Opção inválida!");
                        }
                    }

                }
                case 4 -> {
                    System.out.println("\n👋 Saindo do DevBank... Até a próxima!\n");
                    System.out.println("========================================\n");
                }
                default -> {
                    System.out.println("\n⚠️  Opção inválida! Tente novamente.\n");
                }

            }
        }

    }
}
