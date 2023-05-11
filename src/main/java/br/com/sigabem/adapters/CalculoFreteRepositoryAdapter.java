package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFrete;
import lombok.Data;

@Data
public class CalculoFreteRepositoryAdapter {
    private CalculoFrete calculoFrete;

    public CalculoFreteRepositoryAdapter(CalculoFrete calculoFrete){
        converteCalculoFreteEmCalculoFreteEntity(calculoFrete);
    }


    private void converteCalculoFreteEmCalculoFreteEntity(CalculoFrete calculoFrete){
        this.calculoFrete = CalculoFrete.builder()
                .id(calculoFrete.getId())
                .peso(calculoFrete.getPeso())
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .vlTotalFrete(calculoFrete.getVlTotalFrete())
                .dataPrevistaEntrega(calculoFrete.getDataPrevistaEntrega())
                .dataConsulta(calculoFrete.getDataConsulta())
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .build();
    }


}
