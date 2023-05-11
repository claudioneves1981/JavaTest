package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFrete;
import br.com.sigabem.dto.request.CalculoFreteInputDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteInputDTOAdapter {

    private List<CalculoFreteInputDTO> calculoFretesControllerEntity;
    private CalculoFreteInputDTO calculoFreteInputDTO;

    public CalculoFreteInputDTOAdapter(List<CalculoFrete> calculoFreteList) {
        calculoFretesControllerEntity = toDTOList(calculoFreteList);
    }

    public CalculoFreteInputDTOAdapter(CalculoFrete calculoFrete) {
        calculoFreteInputDTO = toDTO(calculoFrete);
    }

    private List<CalculoFreteInputDTO> toDTOList(List<CalculoFrete> calculoFreteList){
        List<CalculoFreteInputDTO> calculoFretesControllerEntity = new ArrayList<>();
        for(CalculoFrete calculoFrete: calculoFreteList){
            calculoFretesControllerEntity.add(toDTO(calculoFrete));
        }
        return calculoFretesControllerEntity;
    }

    public CalculoFreteInputDTO toDTO(CalculoFrete calculoFrete) {
        return CalculoFreteInputDTO.builder()
                .peso(calculoFrete.getPeso())
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .build();
    }
}
