package com.ey.ejercicio.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    /**
     * Crea un JWT a partir del nombre y el correo electrónico del usuario.
     * @param name El nombre del usuario.
     * @param email El correo electrónico del usuario.
     * @return El JWT creado.
     */
    public String create(String name, String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);  //Genera una clave secreta para firmar el JWT usando el algoritmo HS256

        long nowMillis = System.currentTimeMillis();  //Obtiene la hora actual en milisegundos
        Date now = new Date(nowMillis);  //Crea un objeto Date a partir de la hora actual

        JwtBuilder builder = Jwts.builder()  //Crea un constructor de JWT usando la biblioteca JJWT
                .setId(name)  //Agrega el nombre proporcionado como el identificador del JWT
                .setIssuedAt(now)  //Agrega la fecha y hora actual como la fecha de emisión del JWT
                .setSubject(email)  //Agrega el correo electrónico proporcionado como el sujeto del JWT
                .setIssuer(issuer)  //Agrega el emisor (issuer) configurado como el emisor del JWT
                .signWith(key);  //Firma el JWT usando la clave secreta generada anteriormente

        if (ttlMillis >= 0) {  //Si se ha configurado un tiempo de vida para el JWT
            long expMillis = nowMillis + ttlMillis;  //Calcula la fecha de expiración del JWT sumando el tiempo de vida al tiempo actual
            Date exp = new Date(expMillis);  //Crea un objeto Date a partir de la fecha de expiración calculada
            builder.setExpiration(exp);  //Agrega la fecha de expiración al JWT construido
        }

        return builder.compact();  //Devuelve el JWT como una cadena compacta
    }



    /**
     * Obtiene el nombre del usuario a partir de un JWT.
     * @param jwt El JWT del usuario.
     * @return El nombre del usuario.
     */
    public String getName(String jwt) {
        // Analiza el JWT y establece la clave de firma con el secreto
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                // Analiza el JWT y devuelve el cuerpo (payload)
                .parseClaimsJws(jwt)
                .getBody();

        // Obtiene el ID del JWT a partir del cuerpo (payload)
        return claims.getId();
    }


    /**
     * Obtiene el correo electrónico del usuario a partir de un JWT.
     * @param jwt El JWT del usuario.
     * @return El correo electrónico del usuario.
     */
    public String getEmail(String jwt) {
        // Crea un objeto Claims a partir del token JWT y la clave secreta
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        // Devuelve el subject (email) extraido del objeto Claims
        return claims.getSubject();
    }

}
