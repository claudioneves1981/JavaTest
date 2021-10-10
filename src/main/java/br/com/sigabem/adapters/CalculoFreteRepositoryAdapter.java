package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.service.entity.CalculoFrete;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CalculoFreteRepositoryAdapter {
    private CalculoFreteEntity calculoFreteEntity;

    public CalculoFreteRepositoryAdapter(CalculoFrete calculoFrete){
        converteCalculoFreteEmCalculoFreteEntity(calculoFrete);
    }


    private void converteCalculoFreteEmCalculoFreteEntity(CalculoFrete calculoFrete){
        calculoFreteEntity = CalculoFreteEntity.builder()
                .id(calculoFrete.getId())
                .peso(calculoFrete.getPeso())
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .vlTotalFrete(calculoFrete.getVlTotalFrete())
                .dataPrevistaEntrega(calculoFrete.getDataPrevistaEntrega())
                .dataConsulta(LocalDate.now())
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .build();
    }


}
