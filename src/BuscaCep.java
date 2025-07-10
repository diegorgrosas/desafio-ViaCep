import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaCep {
    public Endereco buscaEndereco(String cep) {
        String endereco = "https://viacep.com.br/ws/" + cep + "/json";

        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(endereco))
                                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                                            .send(request, HttpResponse
                                                    .BodyHandlers.ofString());
            String json = response.body();
            return new Gson().fromJson(json, Endereco.class);

        } catch (Exception e) {
            throw new RuntimeException("Não consegui buscar o endereço desse cep!");
        }


    }
}
