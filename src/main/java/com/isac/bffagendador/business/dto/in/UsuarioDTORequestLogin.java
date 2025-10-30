package com.isac.bffagendador.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequestLogin {
    private String email;
    private String senha;


}
