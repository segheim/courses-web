package org.example.filters;

import org.example.entity.User;
import org.example.exception.AuthException;
import org.example.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@WebFilter(urlPatterns = "/user", filterName = "0")
public class AuthFilter implements Filter {

    private final UserService userService = new UserService();

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String authorization = req.getHeader("Authorization").split(" ")[1];
            Base64.Decoder decoder = Base64.getDecoder();
            String[] data = new String(decoder.decode(authorization), StandardCharsets.UTF_8).split(":");
            User user = userService.getUserByUsername(data[0]);
            System.out.println("Parameter: " + req.getParameter("id"));

            if (user.getPassword().equals(data[1])) {
                req.getSession().setAttribute("roles", user.getRole());
                chain.doFilter(request, response);
            } else {
                throw new ServletException(" incorrect password");
            }
        } catch (Exception e) {
            throw new AuthException(String.format("Authentication fail. %s", e.getMessage()));
        }
    }

    @Override
    public void destroy() {
    }
}
