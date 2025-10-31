package com.isac.bffagendador.controller;


import com.isac.bffagendador.business.TarefaService;
import com.isac.bffagendador.business.dto.in.TarefaDTORequest;
import com.isac.bffagendador.business.dto.out.TarefaDTOResponse;
import com.isac.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import com.isac.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "tarefa", description = "Cadastro de tarefas dos usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Cria nova tarefa", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<TarefaDTOResponse> gravarTarefas(@RequestBody TarefaDTORequest dto,
                                                           @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.gravarTarefa(dto, token));

    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas cadastradas por período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial,dataFinal,token));
    }

    @GetMapping
    @Operation(summary = "Busca tarefas cadastradas por email", description = "Busca lista de tarefas cadastradas por email")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<List<TarefaDTOResponse>> buscaTarefasAgendadasPorEmail(
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por id", description = "Deleta tarefas cadastradas por Id")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token){
         tarefaService.deletaTarefaPorId(id,token);

         return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tstatus da tarefa alteradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<TarefaDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id,token));

    }
    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<TarefaDTOResponse> updateTarefa(@RequestBody TarefaDTORequest dto, @RequestParam String id,
                                                          @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.atualizaTarefa(dto, id,token));
    }

}
