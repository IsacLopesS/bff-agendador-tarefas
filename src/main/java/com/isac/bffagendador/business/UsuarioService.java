package com.isac.bffagendador.business;
import com.isac.bffagendador.business.dto.in.EnderecoDTORequest;
import com.isac.bffagendador.business.dto.in.TelefoneDTORequest;
import com.isac.bffagendador.business.dto.in.UsuarioDTORequest;
import com.isac.bffagendador.business.dto.in.LoginRequestDTO;
import com.isac.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.isac.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.isac.bffagendador.business.dto.out.UsuarioDTOResponse;
import com.isac.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDTO dto){
        return usuarioClient.login(dto);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
      return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmil(String email, String token){

        usuarioClient.deletaUsuarioPorEmail(email,token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO){

       return usuarioClient.atualizaDadosUsuario(usuarioDTO,token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest dto, String token){
       return usuarioClient.atualizaEndereco(dto,idEndereco,token);

    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token){
        return usuarioClient.atualizaTelefone(dto, idTelefone,token);

    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto){
       return usuarioClient.cadastraEndereco(dto,token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){
        return usuarioClient.cadastraTelefone(dto,token);
    }



}
