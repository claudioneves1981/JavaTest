package br.com.sigabem.adapters;

import br.com.sigabem.controller.entity.CalculoFreteControllerEntity;
import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.service.entity.CalculoFrete;

import java.util.ArrayList;
import java.util.List;

public class CalculoFreteEntityControllerAdapter {

    private List<CalculoFreteControllerEntity> calculoFretesControllerEntity;
    private CalculoFreteControllerEntity calculoFreteControllerEntity;

    public CalculoFreteEntityControllerAdapter(List<CalculoFrete> calculoFreteList) {
        calculoFretesControllerEntity = convertListCalculoFreteParaListCalculoFreteControllerEntity(calculoFreteList);
    }

    public CalculoFreteEntityControllerAdapter(CalculoFrete calculoFrete) {
        calculoFreteControllerEntity = convertCalculoFreteParaCalculoFreteControllerEntity(calculoFrete);
    }

    private List<CalculoFreteControllerEntity> convertListCalculoFreteParaListCalculoFreteControllerEntity(List<CalculoFrete> calculoFreteList){
        List<CalculoFreteControllerEntity> calculoFretesControllerEntity = new ArrayList<>();
        for(CalculoFrete calculoFrete: calculoFreteList){
            calculoFretesControllerEntity.add(convertCalculoFreteParaCalculoFreteControllerEntity(calculoFrete));
        }
        return calculoFretesControllerEntity;
    }

    public CalculoFreteControllerEntity convertCalculoFreteParaCalculoFreteControllerEntity(CalculoFrete calculoFrete) {
        return CalculoFreteControllerEntity.builder()
                .peso(calculoFrete.getPeso())
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .build();
    }
}
