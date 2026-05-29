package com.aulaspring.aulajpa.configs;

import com.aulaspring.aulajpa.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {
    @Value("${security.jwt.expiracao}")
    private String expiracao;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId());
        claims.put("email", usuario.getEmail());
        claims.put("perfil", usuario.getPerfil());
        return Jwts.builder()
        .claims(claims)
        .expiration(data)
        .signWith(getSignKey(), Jwts.SIG.HS256)
        .compact();
    }
    public Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(chaveAssinatura.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validarToken(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataexpiracao = claims.getExpiration();
            LocalDateTime data = dataexpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception ex) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        Claims c = obterClaims(token);
        return (String) c.get("email");
    }
}
