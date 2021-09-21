package br.com.biopark.sistemacadastro.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.biopark.sistemacadastro.dto.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

    @PostMapping("login")
    public Usuario login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        // Tentar buscar usuário do banco de dados por login e senha, se for diferente de null, dados estão corretos.
        if (username.equals("admin") && pwd.equals("senha123")) {

            String token = getJWTToken(username);
            Usuario user = new Usuario();
            user.setLogin(username);
            user.setToken(token);
            return user;
        } else {
            return null;
        }
    }

    private String getJWTToken(String username) {
        String secretKey = "chaveSecreta";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}