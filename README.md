# Documentação do projeto

A imagem abaixo mostra os quatro microsserviços desenvolveidos no projeto:
- microsserviço usuario: responsável pelo CRUD de usuários. Banco de dados utilizado: PostgreSQL
  - acesse o repositório: https://github.com/IsacLopesS/usuario 
- microsserviço agendador-tarefas:  gerencia as tarefas dos usuarios - cria tarefa, atualiza, e exclui. Banco de dados utilizado: MongoDB
  - acesse o repositório: https://github.com/IsacLopesS/agendador-tarefas 
- microsserviço notificação: responsável por notificar o usuário da tarefa por email, com 1h de antecedência. (Requisições via CRON disparado min a min)
  - acesseo repositório: https://github.com/IsacLopesS/notificacao   
- microsserviço bff: por onde o frontend acessa os outros microsserviços. O bff recebe a requisição do frontend e direciona para os outros microsserviços.


![Arquitetura do Sistema](diagrama_microsservicos.png)


# Vídeo do projeto em execução
https://drive.google.com/file/d/1q-7oqfEYYKiznK3-GTxEo0Hj_w7wFRh_/view?usp=sharing


# Processo de desenvolvimento
O projeto foi desenvolvido simulando um ambiente real de desenvolvimento, seguindo boas práticas adotadas em equipes de tecnologia:
- Metodologia Kanban para organização e evolução das features
- Versionamento com Git seguindo fluxo de branches:
  - feature/* para desenvolvimento de novas funcionalidades
  - Pull Request para develop após testes
  - Merge final em master
- Processo de CI/CD conceitual, garantindo:
  - Código testado antes do merge
  - Histórico limpo e organizado
  - Facilidade de manutenção e evolução

Esse fluxo foi adotado com o objetivo de reproduzir a dinâmica de times reais, mesmo sendo um projeto individual.
