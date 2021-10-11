package br.com.sigabem.adapters;

import br.com.sigabem.controller.entity.CalculoFreteControllerEntity;
import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.service.entity.CalculoFrete;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CalculoFreteServiceAdapter {

    private CalculoFrete calculoFrete;
    private List<CalculoFrete> calculoFretes;

    public CalculoFreteServiceAdapter(List<CalculoFreteEntity> calculoFreteEntityList){
        this.calculoFretes = convertListEntityEmListCalculoFrete(calculoFreteEntityList);
    }

    public CalculoFreteServiceAdapter(CalculoFreteControllerEntity calculoFreteControllerEntity){
        this.calculoFrete = convertCalculoFreteControllerEntityEmCalculoFrete(calculoFreteControllerEntity);
    }

    private List<CalculoFrete> convertListEntityEmListCalculoFrete(List<CalculoFreteEntity> calculoFreteEntityList){
        calculoFretes = new ArrayList<>();
        for(CalculoFreteEntity calculoFreteEntity : calculoFreteEntityList){
            calculoFretes.add(convertCalculoFreteEntityEmCalculoFrete(calculoFreteEntity));
        }
        return calculoFretes;
    }

    private CalculoFrete convertCalculoFreteEntityEmCalculoFrete(CalculoFreteEntity calculoFreteEntity){
        return CalculoFrete.builder()
                .id(calculoFreteEntity.getId())
                .peso(calculoFreteEntity.getPeso())
                .cepOrigem(calculoFreteEntity.getCepOrigem())
                .cepDestino(calculoFreteEntity.getCepDestino())
                .vlTotalFrete(calculoFreteEntity.getVlTotalFrete())
                .dataPrevistaEntrega(calculoFreteEntity.getDataPrevistaEntrega())
                .dataConsulta(calculoFreteEntity.getDataConsulta())
                .nomeDestinatario(calculoFreteEntity.getNomeDestinatario())
                .build();
    }

    private CalculoFrete convertCalculoFreteControllerEntityEmCalculoFrete(CalculoFreteControllerEntity calculoFreteControllerEntity){
        return CalculoFrete.builder()
                .peso(calculoFreteControllerEntity.getPeso())
                .cepOrigem(calculoFreteControllerEntity.getCepOrigem())
                .cepDestino(calculoFreteControllerEntity.getCepDestino())
                .nomeDestinatario(calculoFreteControllerEntity.getNomeDestinatario())
                .build();
    }
}
