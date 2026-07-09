package com.example.for_testdemo1.Config;

import com.example.for_testdemo1.Common.Result;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI();
        if (path.contains("/users/login") || path.contains("/users/register")) {
            chain.doFilter(request, response);
            return;
        }
        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            send401(res, "未登录");
            return;
        }
        String token = authHeader.substring(7);
        try {
            jwtUtil.validateToken(token);
        } catch (Exception e) {
            send401(res, "token 无效或已过期");
            return;
        }
        int userId = jwtUtil.extractUserId(token);
        int role = jwtUtil.extractRole(token);
        req.setAttribute("userId", userId);
        req.setAttribute("role", role);
        chain.doFilter(request, response);
    }


    private void send401(HttpServletResponse res, String msg)
            throws IOException {
        Result<Void> result = Result.error(401, msg);
        String json = new ObjectMapper().writeValueAsString(result);
        res.setStatus(401);
        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().write(json);
    }
}
