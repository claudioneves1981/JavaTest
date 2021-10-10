package br.com.sigabem.controller.contracts.impl;

import br.com.sigabem.controller.contracts.CalculoFreteController;
import br.com.sigabem.service.entity.CalculoFrete;
import br.com.sigabem.service.exception.CalculoFreteException;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.exception.CepException;
import br.com.sigabem.service.impl.CalculoFreteServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/frete")
@Controller
public class CalculoFreteControllerImpl implements CalculoFreteController {

    private CalculoFreteServiceImpl calculoFreteServiceImpl;

    @Autowired
    private CalculoFreteControllerImpl(CalculoFreteServiceImpl calculoFreteServiceImpl){
        this.calculoFreteServiceImpl = calculoFreteServiceImpl;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public MessageResponseDTO createCalculoFrete(@RequestBody @Valid CalculoFreteDTO calculoFreteDTO) throws JSONException, IOException {
        return calculoFreteServiceImpl.createCalculoFrete(calculoFreteDTO);
    }

    @GetMapping
    @Override
    public List<CalculoFreteDTO> listAll(){
        return calculoFreteServiceImpl.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteById(@PathVariable Long id) throws CalculoFreteException{
        calculoFreteServiceImpl.delete(id);
    }

    @PutMapping("/{id}")
    @Override
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid CalculoFreteDTO calculoFreteDTO) throws CalculoFreteException {
        return calculoFreteServiceImpl.updateById(id,calculoFreteDTO);
    }
}
