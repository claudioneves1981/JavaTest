package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.service.entity.CalculoFrete;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteAdapter {

    private CalculoFrete calculoFrete;
    public CalculoFreteAdapter(CalculoFreteDTO calculoFreteDTO){
        calculoFrete = converteCalculoFreteDTOParaCalculoFrete(calculoFreteDTO);
    }

    private CalculoFrete converteCalculoFreteDTOParaCalculoFrete(CalculoFreteDTO calculoFreteDTO){
        return CalculoFrete.builder()
                .id(calculoFreteDTO.getId())
                .peso(calculoFreteDTO.getPeso())
                .cepOrigem(calculoFreteDTO.getCepOrigem())
                .cepDestino(calculoFreteDTO.getCepDestino())
                .nomeDestinatario(calculoFreteDTO.getNomeDestinatario())
                .build();
    }

}
