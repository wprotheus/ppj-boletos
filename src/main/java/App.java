import com.wprotheus.retornoboleto.LeituraRetornoBradesco;
import com.wprotheus.retornoboleto.ProcessarBoletos;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        final ProcessarBoletos processarBoletos = new ProcessarBoletos(new LeituraRetornoBradesco()); //LeituraRetornoBradesco  LeituraRetornoBancoBrasil
        URI caminhoArquivo = Objects.requireNonNull(App.class.getResource("bradesco-1.csv")).toURI(); //bradesco-1.csv banco-brasil-1.csv
        System.out.println("Lendo o arquivo -> " + caminhoArquivo + " <-\n");
        processarBoletos.processar(caminhoArquivo);
    }
}