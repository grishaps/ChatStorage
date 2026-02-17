package net.chatstorage.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        logger.info("Incoming Request: {} {}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI());

        chain.doFilter(request, response);
    }
}
