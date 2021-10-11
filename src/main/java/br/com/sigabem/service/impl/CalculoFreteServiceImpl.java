package br.com.sigabem.service.impl;

import br.com.sigabem.adapters.CalculoFreteAdapter;
import br.com.sigabem.adapters.CalculoFreteDTOAdapter;
import br.com.sigabem.adapters.CalculoFreteEntityAdapter;
import br.com.sigabem.db.contracts.CalculoFreteRepository;
import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.CalculoFreteService;
import br.com.sigabem.service.entity.CalculoFrete;
import br.com.sigabem.service.exception.CalculoFreteException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.sigabem.service.util.JsonReader.readJsonFromUrl;

@Service
public class CalculoFreteServiceImpl implements CalculoFreteService {

    private final CalculoFreteRepository calculoFreteRepository;

    @Autowired
    private CalculoFreteServiceImpl(CalculoFreteRepository calculoFreteRepository){
        this.calculoFreteRepository = calculoFreteRepository;
    }


    @Override
    public List<CalculoFrete> listAll() {
        List<CalculoFreteEntity> allFretes = calculoFreteRepository.findAll();
        CalculoFreteAdapter calculoFreteAdapter = new CalculoFreteAdapter(allFretes);
            return allFretes.stream()
                    .map(calculoFreteAdapter::convertCalculoFreteEntityParaCalculoFrete)
                    .collect(Collectors.toList());
    }

    @Override
    public MessageResponseDTO createCalculoFrete(CalculoFrete calculoFrete) throws JSONException, IOException {
        CalculoFreteEntity calculoFreteEntity = verifyCepsExists(calculoFrete);
        calculoFrete = new CalculoFreteAdapter(calculoFreteEntity).getCalculoFrete();
        CalculoFreteEntity savedCalculoFreteEntity = calculoFreteRepository.save(new CalculoFreteEntityAdapter(calculoFrete).getCalculoFreteEntity());
        return createMessageResponse(savedCalculoFreteEntity.getId(), "Criado Calculo Frete com id ");
    }


    @Override
    public void delete(Long id) throws CalculoFreteException {
        verifyIdExists(id);
        calculoFreteRepository.deleteById(id);

    }

    @Override
    public MessageResponseDTO updateById(Long id, CalculoFrete calculoFrete) throws CalculoFreteException {
        verifyIdExists(id);
        CalculoFreteEntity updatedCalculoFreteEntity = calculoFreteRepository.save(new CalculoFreteEntityAdapter(calculoFrete).getCalculoFreteEntity());
        return createMessageResponse(updatedCalculoFreteEntity.getId(), "Atualizado Calculo Frete com id ");
    }


    private void verifyIdExists(Long id) throws CalculoFreteException {
        calculoFreteRepository.findById(id).orElseThrow(() -> new CalculoFreteException(id));
    }

    private CalculoFreteEntity freteDDDIgual(CalculoFrete calculoFrete){
        LocalDate dataConsulta = LocalDate.now();
        Double peso = calculoFrete.getPeso();
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return CalculoFreteEntity.builder()
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .peso(peso)
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .vlTotalFrete("R$"+decimalFormat.format(peso*0.50))
                .dataPrevistaEntrega(dataConsulta.plusDays(1L))
                .dataConsulta(dataConsulta)
                .build();
    }

    private CalculoFreteEntity freteEstadoIgual(CalculoFrete calculoFrete){
        LocalDate dataConsulta = LocalDate.now();
        Double peso = calculoFrete.getPeso();
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return CalculoFreteEntity.builder()
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .peso(peso)
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .vlTotalFrete("R$"+decimalFormat.format(peso-peso*0.75))
                .dataPrevistaEntrega(dataConsulta.plusDays(3L))
                .dataConsulta(dataConsulta)
                .build();
    }

    private CalculoFreteEntity freteEstadoDiferentes(CalculoFrete calculoFrete){
        LocalDate dataConsulta = LocalDate.now();
        Double peso = calculoFrete.getPeso();
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return CalculoFreteEntity.builder()
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .peso(peso)
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .vlTotalFrete("R$"+decimalFormat.format(peso))
                .dataPrevistaEntrega(dataConsulta.plusDays(10L))
                .dataConsulta(dataConsulta)
                .build();
    }

    private CalculoFreteEntity verifyCepsExists(CalculoFrete calculoFrete) throws JSONException, IOException {
        JSONObject origem = readJsonFromUrl("https://viacep.com.br/ws/"+calculoFrete.getCepOrigem()+"/json/");
        JSONObject destino = readJsonFromUrl("https://viacep.com.br/ws/"+calculoFrete.getCepDestino()+"/json/");
        if(origem.getString("ddd").equals(destino.getString("ddd")) && origem.getString("uf").equals(destino.getString("uf"))){
            return freteDDDIgual(calculoFrete);
        }else if(!origem.getString("ddd").equals(destino.getString("ddd")) && origem.getString("uf").equals(destino.getString("uf"))) {
            return freteEstadoIgual(calculoFrete);
        }else{
           return freteEstadoDiferentes(calculoFrete);
        }
    }

        private MessageResponseDTO createMessageResponse (Long id, String message){
            return MessageResponseDTO
                    .builder()
                    .message(message + id)
                    .build();
        }



}
