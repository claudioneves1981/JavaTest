package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFrete;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteDTOAdapter {

    private List<CalculoFreteDTO> calculoFretesDTO;
    private CalculoFreteDTO calculoFreteDTO;

    public CalculoFreteDTOAdapter(List<CalculoFrete> calculoFretesEntity){
        calculoFretesDTO = toDTOList(calculoFretesEntity);
    }

    public CalculoFreteDTOAdapter(CalculoFrete calculoFrete){
        calculoFreteDTO = toDTO(calculoFrete);
    }

    public List<CalculoFreteDTO> toDTOList(List<CalculoFrete> calculoFretesEntity){
        List<CalculoFreteDTO> calculoFretesDTO = new ArrayList<>();
        for(CalculoFrete calculoFrete : calculoFretesEntity){
            calculoFretesDTO.add(toDTO(calculoFrete));
        }
        return calculoFretesDTO;
    }

    public CalculoFreteDTO toDTO(CalculoFrete calculoFrete){
        return CalculoFreteDTO.builder()
                .id(calculoFrete.getId())
                .peso(calculoFrete.getPeso())
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .dataConsulta(calculoFrete.getDataConsulta())
                .vlTotalFrete(calculoFrete.getVlTotalFrete())
                .dataPrevistaEntrega(calculoFrete.getDataPrevistaEntrega())
                .build();
    }
}
