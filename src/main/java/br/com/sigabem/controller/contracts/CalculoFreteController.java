package br.com.sigabem.controller.contracts;

import br.com.sigabem.controller.entity.CalculoFreteControllerEntity;
import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.entity.CalculoFrete;
import br.com.sigabem.service.exception.CalculoFreteException;
import br.com.sigabem.service.exception.CepException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface CalculoFreteController {

    @ApiOperation(value="Cria um frete")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem se criou o frete"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável de erro para o usuário"),
    })
    MessageResponseDTO createCalculoFrete(CalculoFreteControllerEntity calculoFreteControllerEntity) throws JSONException, IOException;

    @ApiOperation(value="Lista todos os fretes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os fretes"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável de erro para o usuário"),
    })
    List<CalculoFreteControllerEntity> listAll();

    @ApiOperation(value="deleta frete pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "deleta um frete pelo id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável de erro para o usuário"),
    })
    void deleteById(Long id) throws CalculoFreteException;

    @ApiOperation(value="Atualiza Frete pelo Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza Frete pelo Id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável de erro para o usuário"),
    })
    MessageResponseDTO updateById(Long id, CalculoFreteControllerEntity calculoFreteControllerEntity) throws CalculoFreteException;
}


