package br.com.sigabem.controller.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculoFreteControllerEntity {

    @NotEmpty
    private Double peso;

    @NotEmpty
    @Size(min = 8, max = 8)
    private String cepOrigem;

    @NotEmpty
    @Size(min = 8, max = 8)
    private String cepDestino;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String nomeDestinatario;
}
