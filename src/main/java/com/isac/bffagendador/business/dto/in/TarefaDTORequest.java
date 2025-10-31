package com.isac.bffagendador.business.dto.in;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.isac.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDTORequest {
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss" )
    private LocalDateTime dataEvento;
}
