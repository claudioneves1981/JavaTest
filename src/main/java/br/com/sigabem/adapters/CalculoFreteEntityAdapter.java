package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFrete;
import br.com.sigabem.dto.request.CalculoFreteInputDTO;
import lombok.Data;


@Data
public class CalculoFreteEntityAdapter {

    private CalculoFrete calculoFrete;

    public CalculoFreteEntityAdapter(CalculoFreteInputDTO calculoFreteDTO){
        calculoFrete = toModel(calculoFreteDTO);
    }

    public CalculoFrete toModel(CalculoFreteInputDTO calculoFreteDTO){
        return CalculoFrete.builder()
                .id(calculoFreteDTO.getId())
                .peso(calculoFreteDTO.getPeso())
                .cepOrigem(calculoFreteDTO.getCepOrigem())
                .cepDestino(calculoFreteDTO.getCepDestino())
                .nomeDestinatario(calculoFreteDTO.getNomeDestinatario())
                .build();
    }
}

