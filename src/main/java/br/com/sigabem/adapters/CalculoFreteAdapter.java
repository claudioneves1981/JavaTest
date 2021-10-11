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
    private List<CalculoFrete> calculoFretes;
    public CalculoFreteAdapter(CalculoFreteDTO calculoFreteDTO){
        calculoFrete = converteCalculoFreteDTOParaCalculoFrete(calculoFreteDTO);
    }

    public CalculoFreteAdapter(List<CalculoFreteEntity> calculoFreteEntity){
        calculoFretes= convertListCalculoFreteEntityParaListCalculoFrete(calculoFreteEntity);
    }

    public CalculoFreteAdapter(CalculoFreteEntity calculoFreteEntity){
        calculoFrete = convertCalculoFreteEntityParaCalculoFrete(calculoFreteEntity);
    }

    public List<CalculoFrete> convertListCalculoFreteEntityParaListCalculoFrete(List<CalculoFreteEntity> calculoFretesEntity){
        List<CalculoFrete> calculoFretes= new ArrayList<>();
        for(CalculoFreteEntity calculoFreteEntity: calculoFretesEntity){
            calculoFretes.add(convertCalculoFreteEntityParaCalculoFrete(calculoFreteEntity));
        }
        return calculoFretes;
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

    public CalculoFrete convertCalculoFreteEntityParaCalculoFrete(CalculoFreteEntity calculoFreteEntity) {
        return CalculoFrete.builder()
                .id(calculoFreteEntity.getId())
                .peso(calculoFreteEntity.getPeso())
                .cepOrigem(calculoFreteEntity.getCepOrigem())
                .cepDestino(calculoFreteEntity.getCepDestino())
                .nomeDestinatario(calculoFreteEntity.getNomeDestinatario())
                .dataConsulta(calculoFreteEntity.getDataConsulta())
                .vlTotalFrete(calculoFreteEntity.getVlTotalFrete())
                .dataPrevistaEntrega(calculoFreteEntity.getDataPrevistaEntrega())
                .build();
    }
}
