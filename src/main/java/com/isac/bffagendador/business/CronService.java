package com.isac.bffagendador.business;

import com.isac.bffagendador.business.dto.in.LoginRequestDTO;
import com.isac.bffagendador.business.dto.out.TarefaDTOResponse;
import com.isac.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaLoginDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaFutura = LocalDateTime.now().plusMinutes(5);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(2).plusMinutes(5);



       List<TarefaDTOResponse> listaTarefas =  tarefaService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Tarefas encontradas "+ listaTarefas);
       listaTarefas.forEach(tarefa -> {
           emailService.enviaEmail(tarefa);
           log.info("email enviado para o usuario "+tarefa.getEmailUsuario());
           tarefaService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),token);
       });
       log.info("finalizada a busca e notificação das tarefas");
    }

    public String login(LoginRequestDTO dto){
        return usuarioService.loginUsuario(dto);
    }

    LoginRequestDTO converterParaLoginDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }



}
