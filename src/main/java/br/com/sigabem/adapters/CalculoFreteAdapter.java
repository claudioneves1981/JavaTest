package br.com.sigabem.adapters;

import br.com.sigabem.db.entity.CalculoFrete;
import br.com.sigabem.dto.request.CalculoFreteDTO;
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

    public CalculoFreteAdapter(List<CalculoFrete> calculoFrete){
        calculoFretes= convertListCalculoFreteEntityParaListCalculoFrete(calculoFrete);
    }

    public CalculoFreteAdapter(CalculoFrete calculoFrete){
        this.calculoFrete = convertCalculoFreteEntityParaCalculoFrete(calculoFrete);
    }

    public List<CalculoFrete> convertListCalculoFreteEntityParaListCalculoFrete(List<CalculoFrete> calculoFretesEntity){
        List<CalculoFrete> calculoFretes= new ArrayList<>();
        for(CalculoFrete calculoFrete : calculoFretesEntity){
            calculoFretes.add(convertCalculoFreteEntityParaCalculoFrete(calculoFrete));
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

    public CalculoFrete convertCalculoFreteEntityParaCalculoFrete(CalculoFrete calculoFrete) {
        return CalculoFrete.builder()
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
