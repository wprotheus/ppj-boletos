package com.wprotheus.retornoboleto;

import lombok.Setter;

import java.net.URI;
import java.util.List;

@Setter
public class ProcessarBoletos {
    private LeituraRetorno leituraRetorno;

    public ProcessarBoletos(final LeituraRetorno leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    public final void processar(URI caminhoArquivo) {
        String banco = caminhoArquivo.toString();
        System.out.print("Boletos");
        if (banco.contains("brasil")) {
            System.out.println(" do Banco do Brasil\n---------------------------------------------------------------------------------------");
            mostrarBoleto(caminhoArquivo);
        } else if (banco.contains("bradesco")) {
            System.out.println(" do Banco Bradesco\n------------------------------------------------------------------------------------------------------------------");
            mostrarBoleto(caminhoArquivo);
        }
    }

    private void mostrarBoleto(URI caminhoArquivo) {
        final List<Boleto> boletos = leituraRetorno.lerArquivo(caminhoArquivo);
        boletos.forEach(System.out::println); // for(Boleto boleto : boletos) System.out.println(boleto);
    }
}