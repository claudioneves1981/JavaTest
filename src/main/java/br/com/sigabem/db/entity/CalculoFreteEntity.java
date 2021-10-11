package br.com.sigabem.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="TB_CALCULO_FRETE")
@GenericGenerator(
        name="SEQ_CALCULO_FRETE",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name="sequence_name",value="SEQ_CALCULO_FRETE"),
                @Parameter(name = "initial_value",value="1"),
                @Parameter(name = "increment_size",value="1")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculoFreteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="SEQ_CALCULO_FRETE")
    @Column(name="ID")
    private Long id;

    @Column(name="PESO")
    private Double peso;

    @Column(name="CEP_ORIGEM")
    private String cepOrigem;

    @Column(name="CEP_DESTINO")
    private String cepDestino;

    @Column(name="NOME_DESTINATARIO")
    private String nomeDestinatario;

    @Column(name="VALOR_TOTAL_FRETE")
    private String vlTotalFrete;

    @Column(name="DATA_PREVISTA_ENTREGA")
    private LocalDate dataPrevistaEntrega;

    @Column(name="DATA_CONSULTA")
    private LocalDate dataConsulta;
}
