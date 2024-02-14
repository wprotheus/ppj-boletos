package com.wprotheus.retornoboleto;

import java.net.URI;
import java.util.List;

public class ProcessarBoletos {
    private LeituraRetorno leituraRetorno;

    public ProcessarBoletos(final LeituraRetorno leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    public final void processar(URI caminhoArquivo) {
        System.out.println("Boletos");
        System.out.println("---------------------------------------------------------------------------------------");
        final List<Boleto> boletos = leituraRetorno.lerArquivo(caminhoArquivo);
        boletos.forEach(System.out::println);
    }

    public void setLeituraRetorno(final LeituraRetorno leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }
}