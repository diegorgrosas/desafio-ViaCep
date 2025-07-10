import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String cep;
        String resposta = "";
        System.out.println("==============================================================================");
        System.out.println("               Bem vindos ao Aplicativo de busca endereços!                   ");
        System.out.println("==============================================================================");

        while (!resposta.equalsIgnoreCase("n")) {
            System.out.print("\nDigite um cep para buscar informações: ");
            cep = leitura.nextLine();

            try {
                BuscaCep buscaCep = new BuscaCep();
                Endereco novoEndereco = buscaCep.buscaEndereco(cep);

                if (!(novoEndereco.cep() == null)) {
                    System.out.println(novoEndereco);
                    GedradorDeArquivo gerador = new GedradorDeArquivo();
                    gerador.salvaJson(novoEndereco);
                    System.out.println("Busca finalizada com sucesso!");
                } else {
                    System.out.println("Cep não encontrado! Tente novamente!");
                }

            } catch (RuntimeException | IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("\nDeseja continuar buscando?[S/N]: ");
            resposta = leitura.nextLine();
       }
        System.out.println("\nPrograma finalizado!");
    }
}
