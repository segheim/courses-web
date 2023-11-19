package org.example.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

@WebFilter(value = "/*", filterName = "1")
public class LoggerFilter implements Filter {

    private static Logger logger = Logger.getLogger(LoggerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Iterator<String> iterator = req.getHeaderNames().asIterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            logger.info(str + " : " + req.getHeader(str));
        }

        String body = req.getReader().lines().collect(Collectors.joining());
        logger.info(body);
        request.setAttribute("body", body);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
