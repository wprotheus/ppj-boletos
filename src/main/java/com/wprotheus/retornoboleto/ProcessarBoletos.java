package com.wprotheus.retornoboleto;

import lombok.Setter;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.wprotheus.retornoboleto.LeituraRetorno.FORMATO_DATA;
import static com.wprotheus.retornoboleto.LeituraRetorno.FORMATO_DATA_HORA;

@Setter
public class ProcessarBoletos {
    private Function<URI, List<Boleto>> leituraRetorno;

    public ProcessarBoletos(Function<URI, List<Boleto>> leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    private static Boleto pegarBoleto(String linha) {
        final String[] vetor = linha.split(";");
        final var boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);
        boleto.setDataVencimento(LocalDate.parse(vetor[2], FORMATO_DATA));
        boleto.setDataPagamento(LocalDate.parse(vetor[3], FORMATO_DATA).atTime(0, 0, 0));
        boleto.setCpfCliente(vetor[4]);
        boleto.setValor(Double.parseDouble(vetor[5]));
        boleto.setMulta(Double.parseDouble(vetor[6]));
        boleto.setJuros(Double.parseDouble(vetor[7]));
        return boleto;
    }

    public static List<Boleto> lerBancoBrasil(URI caminhoArquivo) {
        try {
            var listaLinhas = Files.readAllLines(Paths.get(caminhoArquivo));
            final var listaBoletos = new ArrayList<Boleto>();
            for (String linha : listaLinhas) {
                final var boleto = pegarBoleto(linha);
                listaBoletos.add(boleto);
            }
            return listaBoletos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Boleto> lerBradesco(URI caminhoArquivo) {
        try {
            var listaLinhas = Files.readAllLines(Paths.get(caminhoArquivo));
            final var listaBoletos = new ArrayList<Boleto>();
            for (String linha : listaLinhas) {
                final String[] vetor = linha.split(";");
                final var boletos = boleto(vetor);
                listaBoletos.add(boletos);
            }
            return listaBoletos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Boleto boleto(String[] vetor) {
        final var boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);
        boleto.setAgencia(vetor[2]);
        boleto.setContaBancaria(vetor[3]);
        boleto.setDataVencimento(LocalDate.parse(vetor[4], FORMATO_DATA));
        boleto.setDataPagamento(LocalDateTime.parse(vetor[5], FORMATO_DATA_HORA));
        boleto.setCpfCliente(vetor[6].replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1.$2.$3-$4"));
        boleto.setValor(Double.parseDouble(vetor[7]));
        boleto.setMulta(Double.parseDouble(vetor[8]));
        boleto.setJuros(Double.parseDouble(vetor[9]));
        return boleto;
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
        List<Boleto> boletos = leituraRetorno.apply(caminhoArquivo);
        boletos.forEach(System.out::println); // for(Boleto boleto : boletos) System.out.println(boleto);
    }
}