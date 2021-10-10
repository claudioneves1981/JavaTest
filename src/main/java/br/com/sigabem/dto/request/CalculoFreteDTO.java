package br.com.sigabem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculoFreteDTO {

    private Long id;

    @NotEmpty
    private Double peso;

    @NotEmpty
    @Size(min = 8, max = 8)
    private String cepOrigem;

    @NotEmpty
    @Size(min = 8, max = 8)
    private String cepDestino;

    @NotEmpty
    @Size(min =2, max = 100)
    private String nomeDestinatario;
}
