package br.com.sigabem.service.impl;

import br.com.sigabem.adapters.CalculoFreteDTOAdapter;
import br.com.sigabem.adapters.CalculoFreteEntityAdapter;
import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.exception.CalculoFreteException;
import br.com.sigabem.db.contracts.CalculoFreteRepository;
import br.com.sigabem.service.CalculoFreteService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
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
    public List<CalculoFreteDTO> listAll() {
        List<CalculoFreteEntity> allFretes = calculoFreteRepository.findAll();
        CalculoFreteDTOAdapter calculoFreteDTOAdapter = new CalculoFreteDTOAdapter(allFretes);
            return allFretes.stream()
                    .map(calculoFreteDTOAdapter::convertCalculoFreteEntityParaCalculoFreteDTO)
                    .collect(Collectors.toList());

    }

    @Override
    public MessageResponseDTO createCalculoFrete(CalculoFreteDTO calculoFreteDTO) throws JSONException, IOException {
        CalculoFreteEntity calculoFreteEntity = verifyCepsExists(calculoFreteDTO.getCepOrigem(),calculoFreteDTO.getCepDestino());
        CalculoFreteDTOAdapter calculoFreteDTOAdapter = new CalculoFreteDTOAdapter(calculoFreteEntity);
        calculoFreteDTO = calculoFreteDTOAdapter.getCalculoFreteDTO();
        CalculoFreteEntity savedCalculoFrete = calculoFreteRepository.save(new CalculoFreteEntityAdapter(calculoFreteDTO).getCalculoFreteEntity());
        return createMessageResponse(savedCalculoFrete.getId(), "Criado Calculo Frete com id ");
    }


    @Override
    public void delete(Long id) throws CalculoFreteException {
        verifyIdExists(id);
        calculoFreteRepository.deleteById(id);

    }

    @Override
    public MessageResponseDTO updateById(Long id, CalculoFreteDTO calculoFreteDTO) throws CalculoFreteException {
        verifyIdExists(id);
        CalculoFreteEntity updatedCalculoFrete = calculoFreteRepository.save(new CalculoFreteEntityAdapter(calculoFreteDTO).getCalculoFreteEntity());
        return createMessageResponse(updatedCalculoFrete.getId(), "Atualizado Calculo Frete com id ");
    }


    private void verifyIdExists(Long id) throws CalculoFreteException {
        calculoFreteRepository.findById(id).orElseThrow(() -> new CalculoFreteException(id));
    }

    private CalculoFreteEntity freteDDDIgual(CalculoFreteEntity calculoFreteEntity){
        return CalculoFreteEntity.builder()
                .cepOrigem(calculoFreteEntity.getCepOrigem())
                .cepDestino(calculoFreteEntity.getCepDestino())
                .vlTotalFrete(calculoFreteEntity.getPeso()*0.50)
                .dataPrevistaEntrega(LocalDate.now().plusDays(1))
                .build();
    }

    private CalculoFreteEntity freteEstadoIgual(CalculoFreteEntity calculoFreteEntity){
        return CalculoFreteEntity.builder()
                .cepOrigem(calculoFreteEntity.getCepOrigem())
                .cepDestino(calculoFreteEntity.getCepDestino())
                .vlTotalFrete(calculoFreteEntity.getPeso()-calculoFreteEntity.getPeso()*0.75)
                .dataPrevistaEntrega(LocalDate.now().plusDays(3))
                .build();
    }

    private CalculoFreteEntity freteEstadoDiferentes(CalculoFreteEntity calculoFreteEntity){
        return CalculoFreteEntity.builder()
                .cepOrigem(calculoFreteEntity.getCepOrigem())
                .cepDestino(calculoFreteEntity.getCepDestino())
                .vlTotalFrete(calculoFreteEntity.getPeso())
                .dataPrevistaEntrega(LocalDate.now().plusDays(10))
                .build();
    }

    private CalculoFreteEntity verifyCepsExists(String cepOrigem, String cepDestino) throws JSONException, IOException {
        JSONObject origem = readJsonFromUrl("https://viacep.com.br/ws/"+cepOrigem+"/json/");
        JSONObject destino = readJsonFromUrl("https://viacep.com.br/ws/"+cepDestino+"/json/");
        CalculoFreteEntity calculoFreteEntity = new CalculoFreteEntity();
        if(origem.getString("ddd").equals(destino.getString("ddd")) && origem.getString("uf").equals(destino.getString("uf"))){
            return freteDDDIgual(calculoFreteEntity);
        }else if(!origem.getString("ddd").equals(destino.getString("ddd")) && origem.getString("uf").equals(destino.getString("uf"))) {
            return freteEstadoIgual(calculoFreteEntity);
        }else{
           return  freteEstadoDiferentes(calculoFreteEntity);
        }
    }

        private MessageResponseDTO createMessageResponse (Long id, String message){
            return MessageResponseDTO
                    .builder()
                    .message(message + id)
                    .build();
        }



}
