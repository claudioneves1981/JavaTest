package br.com.sigabem.adapters;

import br.com.sigabem.dto.request.CalculoFreteInputDTO;
import br.com.sigabem.db.entity.CalculoFrete;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteServiceAdapter {

    private CalculoFrete calculoFrete;
    private List<CalculoFrete> calculoFretes;

    public CalculoFreteServiceAdapter(List<CalculoFrete> calculoFreteList){
        this.calculoFretes = convertListEntityEmListCalculoFrete(calculoFreteList);
    }

    public CalculoFreteServiceAdapter(CalculoFreteInputDTO calculoFreteInputDTO){
        this.calculoFrete = convertCalculoFreteControllerEntityEmCalculoFrete(calculoFreteInputDTO);
    }

    private List<CalculoFrete> convertListEntityEmListCalculoFrete(List<CalculoFrete> calculoFreteList){
        calculoFretes = new ArrayList<>();
        for(CalculoFrete calculoFrete : calculoFreteList){
            calculoFretes.add(convertCalculoFreteEntityEmCalculoFrete(calculoFrete));
        }
        return calculoFretes;
    }

    private CalculoFrete convertCalculoFreteEntityEmCalculoFrete(CalculoFrete calculoFrete){
        return CalculoFrete.builder()
                .id(calculoFrete.getId())
                .peso(calculoFrete.getPeso())
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .vlTotalFrete(calculoFrete.getVlTotalFrete())
                .dataPrevistaEntrega(calculoFrete.getDataPrevistaEntrega())
                .dataConsulta(calculoFrete.getDataConsulta())
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .build();
    }

    private CalculoFrete convertCalculoFreteControllerEntityEmCalculoFrete(CalculoFreteInputDTO calculoFreteInputDTO){
        return CalculoFrete.builder()
                .peso(calculoFreteInputDTO.getPeso())
                .cepOrigem(calculoFreteInputDTO.getCepOrigem())
                .cepDestino(calculoFreteInputDTO.getCepDestino())
                .nomeDestinatario(calculoFreteInputDTO.getNomeDestinatario())
                .build();
    }
}
