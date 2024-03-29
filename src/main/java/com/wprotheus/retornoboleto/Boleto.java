package com.wprotheus.retornoboleto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Boleto implements Serializable {
    private int id;
    private String codBanco;
    private LocalDate dataVencimento;
    private LocalDateTime dataPagamento;
    private String cpfCliente;
    private double valor;
    private double multa;
    private double juros;
    private String agencia;
    private String contaBancaria;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID: %2d Banco: %3s", id, codBanco));
        if (agencia != null && !agencia.isEmpty() && contaBancaria != null && !contaBancaria.isEmpty())
            sb.append(String.format(" Ag.: %4s CC: %4s", agencia, contaBancaria));
        sb.append(String.format(" Venc.: %s Pag.: %s Valor: %8.2f CPF: %2s",
                LeituraRetorno.FORMATO_DATA.format(dataVencimento),
                LeituraRetorno.FORMATO_DATA_HORA.format(dataPagamento), valor, cpfCliente));
        if (multa >= 0)
            sb.append(String.format(" Multa: %8.2f", multa));
        if (juros >= 0)
            sb.append(String.format(" Juros: %8.2f", juros));
        return sb.toString();
    }
}