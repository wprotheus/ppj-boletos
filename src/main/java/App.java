import com.wprotheus.retornoboleto.LeituraRetornoBancoBrasil;
import com.wprotheus.retornoboleto.LeituraRetornoBradesco;
import com.wprotheus.retornoboleto.ProcessarBoletos;

import java.net.URI;
import java.net.URISyntaxException;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        final ProcessarBoletos processarBoletos = new ProcessarBoletos(new LeituraRetornoBancoBrasil());
        URI caminhoArquivo = App.class.getResource("banco-brasil-1.csv").toURI();
        System.out.println("Lendo o arquivo -> " + caminhoArquivo + " <-\n");
        processarBoletos.processar(caminhoArquivo);

        final ProcessarBoletos processarBradesco = new ProcessarBoletos(new LeituraRetornoBradesco());
        URI caminhoBradesco = App.class.getResource("bradesco-1.csv").toURI();
        System.out.println("\nLendo o arquivo -> " + caminhoBradesco + " <-\n");
        processarBradesco.processar(caminhoBradesco);
    }
}
