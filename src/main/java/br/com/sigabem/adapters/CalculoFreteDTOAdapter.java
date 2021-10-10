package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.service.entity.CalculoFrete;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteDTOAdapter {

    private List<CalculoFreteDTO> calculoFretesDTO;
    private CalculoFreteDTO calculoFreteDTO;

    public CalculoFreteDTOAdapter(List<CalculoFreteEntity> calculoFretesEntity){
        calculoFretesDTO = convertListCalculoFreteEntityParaListCalculoFreteDTO(calculoFretesEntity);
    }

    public CalculoFreteDTOAdapter(CalculoFreteEntity calculoFreteEntity){
        calculoFreteDTO = convertCalculoFreteEntityParaCalculoFreteDTO(calculoFreteEntity);
    }

    public CalculoFreteDTOAdapter(CalculoFrete calculoFrete){
        calculoFreteDTO = converteCalculoFreteParaCalculoFreteDTO(calculoFrete);
    }


    public List<CalculoFreteDTO> convertListCalculoFreteEntityParaListCalculoFreteDTO(List<CalculoFreteEntity> calculoFretesEntity){
        List<CalculoFreteDTO> calculoFretesDTO = new ArrayList<>();
        for(CalculoFreteEntity calculoFreteEntity: calculoFretesEntity){
            calculoFretesDTO.add(convertCalculoFreteEntityParaCalculoFreteDTO(calculoFreteEntity));
        }
        return calculoFretesDTO;
    }

    public CalculoFreteDTO convertCalculoFreteEntityParaCalculoFreteDTO(CalculoFreteEntity calculoFreteEntity){
        return CalculoFreteDTO.builder()
                .id(calculoFreteEntity.getId())
                .peso(calculoFreteEntity.getPeso())
                .cepOrigem(calculoFreteEntity.getCepOrigem())
                .cepDestino(calculoFreteEntity.getCepDestino())
                .nomeDestinatario(calculoFreteEntity.getNomeDestinatario())
                .build();
    }

    public CalculoFreteDTO converteCalculoFreteParaCalculoFreteDTO(CalculoFrete calculoFrete){
        return CalculoFreteDTO.builder()
                .id(calculoFrete.getId())
                .peso(calculoFrete.getPeso())
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .build();
    }
}
