package com.techmove.fixnow.iam.infrastructure.security;

import com.techmove.fixnow.iam.infrastructure.persistence.jpa.AccountRepository;
import com.techmove.fixnow.iam.infrastructure.tokens.jwt.BearerTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final BearerTokenService tokenService;
    private final AccountRepository accountRepository;

    public JwtAuthenticationFilter(BearerTokenService tokenService, AccountRepository accountRepository) {
        this.tokenService = tokenService;
        this.accountRepository = accountRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = tokenService.getBearerTokenFrom(request);
        if (StringUtils.hasText(jwt)) {
            try {
                String email = tokenService.getUsernameFromToken(jwt);
                var accountOpt = accountRepository.findByEmail(email);
                if (accountOpt.isPresent() && tokenService.validateToken(jwt)) {
                    var account = accountOpt.get();
                    var userDetails = User.withUsername(account.getEmail())
                            .password(account.getPassword())
                            .authorities(account.getRole().name())
                            .build();
                    var authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                // Token expirado, no autenticamos
            } catch (Exception e) {
                // Token inválido, no autenticamos
            }
        }
        filterChain.doFilter(request, response);
    }


}