package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.service.entity.CalculoFrete;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteEntityAdapter {

    private CalculoFreteEntity calculoFreteEntity;
    private List<CalculoFreteEntity> calculoFretesEntity;


    public CalculoFreteEntityAdapter(CalculoFreteDTO calculoFreteDTO){
        calculoFreteEntity = convertCalculoFreteDTOParaCalculoFreteEntity(calculoFreteDTO);
    }

    public CalculoFreteEntityAdapter(CalculoFrete calculoFrete){
        calculoFreteEntity = convertCalculoFreteParaCalculoFreteEntity(calculoFrete);
    }

    public CalculoFreteEntityAdapter(List<CalculoFrete> calculoFreteList) {
        calculoFretesEntity = convertListCalculoFreteParaListCalculoFreteEntity(calculoFreteList);
    }

    private List<CalculoFreteEntity> convertListCalculoFreteParaListCalculoFreteEntity(List<CalculoFrete> calculoFreteList){
        List<CalculoFreteEntity> calculoFretesEntity = new ArrayList<>();
        for(CalculoFrete calculoFrete: calculoFreteList){
            calculoFretesEntity.add(convertCalculoFreteParaCalculoFreteEntity(calculoFrete));
        }
        return calculoFretesEntity;
    }

    private CalculoFreteEntity convertCalculoFreteParaCalculoFreteEntity(CalculoFrete calculoFrete){
        return CalculoFreteEntity.builder()
                .id(calculoFrete.getId())
                .dataConsulta(calculoFrete.getDataConsulta())
                .vlTotalFrete(calculoFrete.getVlTotalFrete())
                .dataPrevistaEntrega(calculoFrete.getDataPrevistaEntrega())
                .peso(calculoFrete.getPeso())
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .build();
    }

    public CalculoFreteEntity convertCalculoFreteDTOParaCalculoFreteEntity(CalculoFreteDTO calculoFreteDTO){
        return CalculoFreteEntity.builder()
                .id(calculoFreteDTO.getId())
                .peso(calculoFreteDTO.getPeso())
                .cepOrigem(calculoFreteDTO.getCepOrigem())
                .cepDestino(calculoFreteDTO.getCepDestino())
                .nomeDestinatario(calculoFreteDTO.getNomeDestinatario())
                .build();
    }
}

