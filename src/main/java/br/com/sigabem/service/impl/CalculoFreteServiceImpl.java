package br.com.sigabem.service.impl;

import br.com.sigabem.adapters.CalculoFreteAdapter;
import br.com.sigabem.adapters.CalculoFreteDTOAdapter;
import br.com.sigabem.adapters.CalculoFreteEntityAdapter;
import br.com.sigabem.db.contracts.CalculoFreteRepository;
import br.com.sigabem.db.entity.CalculoFrete;
import br.com.sigabem.db.entity.Endereco;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.dto.request.CalculoFreteInputDTO;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.CalculoFreteService;
import br.com.sigabem.service.ViaCepService;
import br.com.sigabem.service.exception.CalculoFreteException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculoFreteServiceImpl implements CalculoFreteService {

    @Autowired
    private CalculoFreteRepository calculoFreteRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public List<CalculoFreteDTO> listAll() {
        List<CalculoFrete> allFretes = calculoFreteRepository.findAll();
        CalculoFreteDTOAdapter calculoFreteDTOAdapter = new CalculoFreteDTOAdapter(allFretes);
            return allFretes.stream()
                    .map(calculoFreteDTOAdapter::toDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public MessageResponseDTO createCalculoFrete(CalculoFreteInputDTO calculoFrete) throws JSONException, IOException {
        CalculoFrete calculoFreteEntity = verifyCepsExists(calculoFrete);
        calculoFreteRepository.save(calculoFreteEntity);
        return createMessageResponse(calculoFreteEntity.getId(), "Criado Calculo Frete com id ");
    }


    @Override
    public void delete(Long id) throws CalculoFreteException {
        verifyIdExists(id);
        calculoFreteRepository.deleteById(id);

    }

    @Override
    public MessageResponseDTO updateById(Long id, CalculoFreteInputDTO calculoFrete) throws CalculoFreteException {
        verifyIdExists(id);
        CalculoFrete updatedCalculoFrete = calculoFreteRepository.save(new CalculoFreteEntityAdapter(calculoFrete).getCalculoFrete());
        return createMessageResponse(updatedCalculoFrete.getId(), "Atualizado Calculo Frete com id ");
    }


    private void verifyIdExists(Long id) throws CalculoFreteException {
        calculoFreteRepository.findById(id).orElseThrow(() -> new CalculoFreteException(id));
    }

    private CalculoFrete freteDDDIgual(CalculoFreteInputDTO calculoFrete){
        LocalDate dataConsulta = LocalDate.now();
        Double peso = calculoFrete.getPeso();
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return CalculoFrete.builder()
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .peso(peso)
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .vlTotalFrete("R$"+decimalFormat.format(peso*0.50))
                .dataPrevistaEntrega(dataConsulta.plusDays(1L))
                .dataConsulta(dataConsulta)
                .build();
    }

    private CalculoFrete freteEstadoIgual(CalculoFreteInputDTO calculoFrete){
        LocalDate dataConsulta = LocalDate.now();
        Double peso = calculoFrete.getPeso();
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return CalculoFrete.builder()
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .peso(peso)
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .vlTotalFrete("R$"+decimalFormat.format(peso-peso*0.75))
                .dataPrevistaEntrega(dataConsulta.plusDays(3L))
                .dataConsulta(dataConsulta)
                .build();
    }

    private CalculoFrete freteEstadoDiferentes(CalculoFreteInputDTO calculoFrete){
        LocalDate dataConsulta = LocalDate.now();
        Double peso = calculoFrete.getPeso();
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return CalculoFrete.builder()
                .cepOrigem(calculoFrete.getCepOrigem())
                .cepDestino(calculoFrete.getCepDestino())
                .peso(peso)
                .nomeDestinatario(calculoFrete.getNomeDestinatario())
                .vlTotalFrete("R$"+decimalFormat.format(peso))
                .dataPrevistaEntrega(dataConsulta.plusDays(10L))
                .dataConsulta(dataConsulta)
                .build();
    }

    private CalculoFrete verifyCepsExists(CalculoFreteInputDTO calculoFrete) {

        String cepOrigem = calculoFrete.getCepOrigem();
        String cepDestino = calculoFrete.getCepDestino();

        Endereco origem = viaCepService.consultarCep(cepOrigem);
        Endereco destino = viaCepService.consultarCep(cepDestino);

        if(origem.getDdd().equals(destino.getDdd()) && origem.getUf().equals(destino.getUf())){
            return freteDDDIgual(calculoFrete);
        }else if(!origem.getDdd().equals(destino.getDdd()) && origem.getUf().equals(destino.getUf())){
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
