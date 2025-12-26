package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilter {

    private final JwtTokenProvider jwt;

    public JwtFilter(JwtTokenProvider jwt) {
        this.jwt = jwt;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest r = (HttpServletRequest) req;
        String h = r.getHeader("Authorization");

        if (h != null && h.startsWith("Bearer ")) {
            try {
                Long id = jwt.getUserIdFromToken(h.substring(7));
                Authentication a = new UsernamePasswordAuthenticationToken(id, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(a);
            } catch (Exception e) {
                ((HttpServletResponse) res).setStatus(401);
                return;
            }
        }

        chain.doFilter(req, res);
    }
}
