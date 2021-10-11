package br.com.sigabem.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculoFrete {

    private Long id;
    private Double peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;
    private String vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private LocalDate dataConsulta;

}
