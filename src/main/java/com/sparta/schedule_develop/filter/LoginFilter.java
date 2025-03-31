package com.sparta.schedule_develop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {
            "/users/signup",
            "/users/login",
            "/swagger-ui/*",
            "/v3/api-docs",
            "/v3/api-docs/*",
            "/swagger-resources",
            "/swagger-resources/*",
            "/webjars/*"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("user") == null) {

                // 직접 JSON 응답 작성
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");

                String json = """
                        {
                          "timestamp": "%s",
                          "status": 401,
                          "error": "UNAUTHORIZED",
                          "code": "401",
                          "message": "로그인이 필요합니다.",
                          "path": "%s"
                        }
                        """.formatted(LocalDateTime.now(), requestURI);

                httpResponse.getWriter().write(json);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
