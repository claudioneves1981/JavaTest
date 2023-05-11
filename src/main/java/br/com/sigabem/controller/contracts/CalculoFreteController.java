package br.com.sigabem.controller.contracts;

import br.com.sigabem.dto.request.CalculoFreteDTO;
import br.com.sigabem.dto.request.CalculoFreteInputDTO;
import br.com.sigabem.dto.response.MessageResponseDTO;
import br.com.sigabem.service.exception.CalculoFreteException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface CalculoFreteController {

    @ApiOperation(value="Cria um frete")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem se criou o frete"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável de erro para o usuário"),
    })
    MessageResponseDTO createCalculoFrete(CalculoFreteInputDTO calculoFreteInputDTO) throws JSONException, IOException;

    @ApiOperation(value="Lista todos os fretes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os fretes"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável de erro para o usuário"),
    })
    List<CalculoFreteDTO> listAll();

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
    MessageResponseDTO updateById(Long id, CalculoFreteInputDTO calculoInputDTO) throws CalculoFreteException;
}


