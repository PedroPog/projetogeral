package br.com.codehive.projetogeral.config;

import br.com.codehive.projetogeral.model.dto.RetorneToken;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class UtilidadeGerais {

    public static RetorneToken validaToken(String authorizationHeader){
        RetorneToken retorneToken = new RetorneToken();
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            retorneToken.setValidacao(false);
            retorneToken.setResposta("Missing or invalid Authorization header");
            return retorneToken;
        }

        String token = authorizationHeader.substring(7);

        // Valida o token
        if (!JwtTokenUtil.isTokenValid(token)) {
            retorneToken.setValidacao(false);
            retorneToken.setResposta("Invalid or expired token");
            return retorneToken;
        }

        // Obtém o UUID do token
        UUID userUuid = JwtTokenUtil.getUuidFromToken(token);

        retorneToken.setValidacao(true);
        retorneToken.setResposta("Secure data accessed by user with UUID: " + userUuid);
        return retorneToken;
    }
}
