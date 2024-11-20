import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONObject;

public class Principal {

    private static final String API_KEY = "3a8a46f3b49b034e3fecb33a";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = obterOpcao(scanner);

            if (opcao == 7) {
                System.out.println("Encerrando o conversor. Até logo!");
                continuar = false;
            } else {
                String[] moedas = obterMoedasPorOpcao(opcao);

                if (moedas != null) {
                    String moedaBase = moedas[0];
                    String moedaDestino = moedas[1];
                    double quantidade = obterQuantidade(scanner, moedaBase);

                    double taxa = obterTaxaDeCambio(moedaBase, moedaDestino);
                    if (taxa > 0) {
                        double resultado = quantidade * taxa;
                        System.out.printf("%.2f %s equivalem a %.2f %s%n", quantidade, moedaBase, resultado, moedaDestino);
                    } else {
                        System.out.println("Erro ao obter a taxa de câmbio. Verifique sua conexão ou tente novamente mais tarde.");
                    }
                } else {
                    System.out.println("Opção inválida. Escolha um número entre 1 e 7.");
                }
            }

            System.out.println(); // Linha em branco para separar iterações
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("=== Conversor de Moedas ===");
        System.out.println("1. Real (BRL) para Dólar Americano (USD)");
        System.out.println("2. Real (BRL) para Euro (EUR)");
        System.out.println("3. Dólar Americano (USD) para Real (BRL)");
        System.out.println("4. Euro (EUR) para Real (BRL)");
        System.out.println("5. Dólar Americano (USD) para Euro (EUR)");
        System.out.println("6. Euro (EUR) para Dólar Americano (USD)");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int obterOpcao(Scanner scanner) {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            if (opcao >= 1 && opcao <= 7) {
                return opcao;
            } else {
                System.out.println("Opção inválida. Escolha um número entre 1 e 7.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número válido.");
        }
        return -1;
    }

    private static String[] obterMoedasPorOpcao(int opcao) {
        return switch (opcao) {
            case 1 -> new String[]{"BRL", "USD"};
            case 2 -> new String[]{"BRL", "EUR"};
            case 3 -> new String[]{"USD", "BRL"};
            case 4 -> new String[]{"EUR", "BRL"};
            case 5 -> new String[]{"USD", "EUR"};
            case 6 -> new String[]{"EUR", "USD"};
            default -> null;
        };
    }

    private static double obterQuantidade(Scanner scanner, String moedaBase) {
        while (true) {
            System.out.printf("Digite a quantidade em %s: ", moedaBase);
            try {
                double quantidade = Double.parseDouble(scanner.nextLine());
                if (quantidade > 0) {
                    return quantidade;
                } else {
                    System.out.println("A quantidade deve ser maior que zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
            }
        }
    }

    private static double obterTaxaDeCambio(String base, String destino) {
        try {
            String url = BASE_URL + base;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                JSONObject rates = json.optJSONObject("conversion_rates");

                if (rates != null && rates.has(destino)) {
                    return rates.getDouble(destino);
                } else {
                    System.out.println("Taxa de câmbio não encontrada para " + destino);
                }
            } else {
                System.out.println("Erro na requisição: Código HTTP " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Erro ao acessar a API: " + e.getMessage());
        }
        return -1; // Indica falha
    }
}
