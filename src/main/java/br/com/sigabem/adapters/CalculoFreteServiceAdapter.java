package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.service.entity.CalculoFrete;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteServiceAdapter {

    private CalculoFrete calculoFrete;
    private List<CalculoFrete> calculoFretes;

    public CalculoFreteServiceAdapter(CalculoFreteDTO calculoFreteDTO){ this.calculoFrete =
            convertCalculoFreteDTOemCalculoFrete(calculoFreteDTO);
    }
    public CalculoFreteServiceAdapter(CalculoFreteEntity calculoFreteEntity){this.calculoFrete = convertCalculoFreteEntityEmCalculoFrete(calculoFreteEntity);}

    public CalculoFreteServiceAdapter(List<CalculoFreteEntity> calculoFreteEntities){
        calculoFretes = convertListEntityEmListCalculoFrete(calculoFreteEntities);
    }

    private List<CalculoFrete> convertListEntityEmListCalculoFrete(List<CalculoFreteEntity> calculoFreteEntityList){
        calculoFretes = new ArrayList<>();
        for(CalculoFreteEntity calculoFreteEntity : calculoFreteEntityList){
            calculoFretes.add(convertCalculoFreteEntityEmCalculoFrete(calculoFreteEntity));
        }
        return calculoFretes;
    }

    private CalculoFrete convertCalculoFreteEntityEmCalculoFrete(CalculoFreteEntity calculoFreteEntity){
        System.out.print("Calculo Frete Service Adapter"+calculoFreteEntity.getPeso());
        return CalculoFrete.builder()
                .id(calculoFreteEntity.getId())
                .peso(calculoFreteEntity.getPeso())
                .cepOrigem(calculoFreteEntity.getCepOrigem())
                .cepDestino(calculoFreteEntity.getCepDestino())
                .vlTotalFrete(calculoFreteEntity.getVlTotalFrete())
                .dataPrevistaEntrega(calculoFreteEntity.getDataPrevistaEntrega())
                .dataConsulta(LocalDate.now())
                .nomeDestinatario(calculoFreteEntity.getNomeDestinatario())
                .build();
    }

    private CalculoFrete convertCalculoFreteDTOemCalculoFrete(CalculoFreteDTO calculoFreteDTO){
        return CalculoFrete.builder()
                .id(calculoFreteDTO.getId())
                .peso(calculoFreteDTO.getPeso())
                .cepOrigem(calculoFreteDTO.getCepOrigem())
                .cepDestino(calculoFreteDTO.getCepDestino())
                .nomeDestinatario(calculoFreteDTO.getNomeDestinatario())
                .build();
    }
}
