package br.com.sigabem.service;

import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.exception.CalculoFreteException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface CalculoFreteService {
    MessageResponseDTO createCalculoFrete(CalculoFreteDTO calculoFreteDTO) throws JSONException, IOException;
    List<CalculoFreteDTO> listAll();
    void delete(Long id) throws CalculoFreteException;
    MessageResponseDTO updateById(Long id, CalculoFreteDTO calculoFreteDTO) throws CalculoFreteException;
}
