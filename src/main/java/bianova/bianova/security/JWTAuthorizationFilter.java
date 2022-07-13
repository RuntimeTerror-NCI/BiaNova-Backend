package bianova.bianova.security;

import java.io.IOException;
import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import bianova.bianova.security.*;


@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        System.out.println("dofilterinternal");
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            System.out.println("header not found");
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        System.out.println("authorizing");
        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (token != null) {
            String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
            .build()
            .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
            .getSubject();
//            log.info(tokenString);
            System.out.println(user);
            if (user != null) {
                System.out.println("authorised");
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            System.out.println("not authorised");
            return null;
        }
        System.out.println("not authorised token null");
        return null;
    }
}
