package org.team10.washcode.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.getToken(request);
        System.out.println(token);
        System.out.println(jwtProvider.validateToken(token));
        if(token!=null && jwtProvider.validateToken(token)){
            System.out.println(1);
            UserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(jwtProvider.getId(token));
            System.out.println(2);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            System.out.println(3);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(4);
        }
        filterChain.doFilter(request,response);
    }
}
