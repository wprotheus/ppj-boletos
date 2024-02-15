package com.wprotheus.retornoboleto;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeituraRetornoBradesco implements LeituraRetorno {
    private static Boleto pegarBoleto(String[] vetor) {
        final var boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);
        boleto.setAgencia(vetor[2]);
        boleto.setContaBancaria(vetor[3]);
        boleto.setDataVencimento(LocalDate.parse(vetor[4], FORMATO_DATA));
        boleto.setDataPagamento(LocalDateTime.parse(vetor[5], FORMATO_DATA_HORA));
        boleto.setCpfCliente(vetor[6]);
        boleto.setValor(Double.parseDouble(vetor[7]));
        boleto.setMulta(Double.parseDouble(vetor[8]));
        boleto.setJuros(Double.parseDouble(vetor[9]));
        return boleto;
    }

    @Override
    public List<Boleto> lerArquivo(final URI caminhoArquivo) {
        try {
            var listaLinhas = Files.readAllLines(Paths.get(caminhoArquivo));
            final var listaBoletos = new ArrayList<Boleto>();
            for (String linha : listaLinhas) {
                final String[] vetor = linha.split(";");
                final var boleto = pegarBoleto(vetor);
                listaBoletos.add(boleto);
            }
            return listaBoletos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}