package com.optus.jwtgen.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.optus.jwtgen.request.JwtPayload;
import com.optus.jwtgen.utils.PemUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;



@Component
public class JwtgenService {

    public String generateToken(JwtPayload payload) throws IOException {

        URL fileLocation = JwtgenService.class.getResource("/keys/rsaPublicKey.pem");
        RSAPublicKey publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(fileLocation.getPath(), "RSA");

        fileLocation = JwtgenService.class.getResource("/keys/rsaPrivateKey.pem");
        RSAPrivateKey privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile(fileLocation.getPath(), "RSA");

        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        String token = JWT.create()
                .withNotBefore(new Date(payload.getNbf() * 1000))
                .withExpiresAt(new Date(payload.getExp() * 1000))
                .withIssuedAt(new Date(payload.getIat() * 1000))
                .withAudience(payload.getAud())
                .withJWTId(payload.getJti())
                .withClaim("emp", payload.getEmp())
                .withClaim("bra", payload.getBra())
                .withClaim("ini", payload.getIni())
                .withClaim("abt", payload.getAbt())
                .sign(algorithm);

        return token;
    }
}
