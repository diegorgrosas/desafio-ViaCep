import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String busca = "";

        System.out.println("==============================================================================");
        System.out.println("                   Bem vindos ao Aplicativo de busca ViaCep!                  ");
        System.out.println("==============================================================================");

        while (!busca.equalsIgnoreCase("sair")) {

            System.out.print("Digite um cep para buscar informações: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            String endereco = "https://viacep.com.br/ws/" + busca + "/json";
            System.out.println(endereco);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco))
                    .header("Accept", "application/vnd.github.v3+json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);
            System.out.println("Busca finalizada com sucesso!");
        }


    }
}