package br.com.sigabem.service;

import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.dto.request.CalculoFreteInputDTO;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.exception.CalculoFreteException;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public interface CalculoFreteService {
    MessageResponseDTO createCalculoFrete(CalculoFreteInputDTO calculoFrete) throws JSONException, IOException;
    List<CalculoFreteDTO> listAll();
    void delete(Long id) throws CalculoFreteException;
    MessageResponseDTO updateById(Long id, CalculoFreteInputDTO calculoFreteController) throws CalculoFreteException;
}
