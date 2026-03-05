package bl.inventarios.seguridad;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor(
            "mi-clave-secreta-inventario-2024-xyz".getBytes()
    );

    private final long EXPIRACION = 1000 * 60 * 60 * 10; // 10 horas

    // Genera el token con el username dentro
    public String generarToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRACION))
                .signWith(key)
                .compact();
    }

    // Extrae el username del token
    public String extraerUsername(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Verifica que el token sea válido
    public boolean validarToken(String token, String username) {
        return extraerUsername(token).equals(username)
                && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
}