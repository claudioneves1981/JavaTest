package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.service.entity.CalculoFrete;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteEntityAdapter {

    private CalculoFreteEntity calculoFreteEntity;
    private List<CalculoFreteEntity> calculoFretesEntity;


    public CalculoFreteEntityAdapter(CalculoFreteDTO calculoFreteDTO){
        calculoFreteEntity = convertCalculoFreteDTOParaCalculoFreteEntity(calculoFreteDTO);
    }

    private List<CalculoFreteEntity> convertListCalculoFreteParaListCalculoFreteEntity(List<CalculoFreteDTO> calculoFretesDTO){
        List<CalculoFreteEntity> calculoFretesEntity = new ArrayList<>();
        for(CalculoFreteDTO calculoFreteDTO: calculoFretesDTO){
            calculoFretesEntity.add(convertCalculoFreteDTOParaCalculoFreteEntity(calculoFreteDTO));
        }
        return calculoFretesEntity;
    }

    private CalculoFreteEntity convertCalculoFreteParaCalculoFreteEntity(CalculoFrete calculoFrete){
        return CalculoFreteEntity.builder()
                .id(calculoFrete.getId())
                .dataConsulta(LocalDate.now())
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

