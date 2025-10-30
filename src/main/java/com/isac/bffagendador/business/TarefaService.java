package com.isac.bffagendador.business;



import com.isac.bffagendador.business.dto.in.TarefaDTORequest;
import com.isac.bffagendador.business.dto.out.TarefaDTOResponse;
import com.isac.bffagendador.infrastructure.client.TarefaClient;
import com.isac.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaClient tarefaClient;

    public TarefaDTOResponse gravarTarefa(TarefaDTORequest dto, String token) {

        return tarefaClient.gravarTarefas(dto,token);
    }

    public List<TarefaDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return tarefaClient.buscaListaDeTarefasPorPeriodo(dataInicial,dataFinal,token);
    }

    public List<TarefaDTOResponse> buscaTarefasAgendadasPorEmail(String token) {

        return tarefaClient.buscaTarefasAgendadasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefaClient.deletaTarefaPorId(id, token);
    }

    public TarefaDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefaClient.alteraStatusNotificacao(status, id, token);

    }

    public TarefaDTOResponse atualizaTarefa(TarefaDTORequest dto, String id, String token){
           return tarefaClient.updateTarefa(dto, id, token);
    }


}
