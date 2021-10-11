package br.com.sigabem.controller.contracts.impl;

import br.com.sigabem.adapters.CalculoFreteAdapter;
import br.com.sigabem.adapters.CalculoFreteEntityControllerAdapter;
import br.com.sigabem.adapters.CalculoFreteServiceAdapter;
import br.com.sigabem.controller.contracts.CalculoFreteController;
import br.com.sigabem.controller.entity.CalculoFreteControllerEntity;
import br.com.sigabem.db.entity.CalculoFreteEntity;
import br.com.sigabem.service.entity.CalculoFrete;
import br.com.sigabem.service.exception.CalculoFreteException;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.impl.CalculoFreteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.*;
import javax.validation.Valid;
import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

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
    public MessageResponseDTO createCalculoFrete(@RequestBody @Valid CalculoFreteControllerEntity calculoFreteControllerEntity) throws JSONException, IOException {
        CalculoFreteServiceAdapter calculoFreteServiceAdapter = new CalculoFreteServiceAdapter(calculoFreteControllerEntity);
        return calculoFreteServiceImpl.createCalculoFrete(calculoFreteServiceAdapter.getCalculoFrete());
    }

    @GetMapping
    @Override
    public List<CalculoFreteControllerEntity> listAll(){
        List<CalculoFrete> allFretes = calculoFreteServiceImpl.listAll();
        CalculoFreteEntityControllerAdapter calculoFreteEntityControllerAdapter = new CalculoFreteEntityControllerAdapter(allFretes);
        return allFretes.stream()
                .map(calculoFreteEntityControllerAdapter::convertCalculoFreteParaCalculoFreteControllerEntity)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteById(@PathVariable Long id) throws CalculoFreteException{
        calculoFreteServiceImpl.delete(id);
    }

    @PutMapping("/{id}")
    @Override
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid CalculoFreteControllerEntity calculoFreteControllerEntity) throws CalculoFreteException {
        CalculoFreteServiceAdapter calculoFreteServiceAdapter = new CalculoFreteServiceAdapter(calculoFreteControllerEntity);
        return calculoFreteServiceImpl.updateById(id,calculoFreteServiceAdapter.getCalculoFrete());
    }
}
