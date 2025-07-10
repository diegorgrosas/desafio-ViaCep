import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<String> enderecos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        System.out.println("==============================================================================");
        System.out.println("                   Bem vindos ao Aplicativo de busca ViaCep!                  ");
        System.out.println("==============================================================================");

        while (!busca.equalsIgnoreCase("sair")) {

            System.out.print("Digite um cep para buscar informações: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                System.out.println("Programa finalizado!");
                break;
            }

            String endereco = "https://viacep.com.br/ws/" + busca + "/json";
            System.out.println(endereco);

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco))
                        .header("Accept", "application/vnd.github.v3+json")
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 400) {
                    throw new ErroConsultaCepException("Cep não encontrado!");
                }

                String json = response.body();
                System.out.println(json);
                enderecos.add(json);

                System.out.println("Busca finalizada com sucesso!");

            } catch (ErroConsultaCepException e) {
                System.out.println(e.getMessage());
                System.out.println("Tente novamente!");
            }


        }

        FileWriter escrita = new FileWriter("src/arquivo.json");
        escrita.write((enderecos).toString());
        escrita.close();


    }
}