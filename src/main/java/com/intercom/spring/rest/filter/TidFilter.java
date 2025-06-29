package com.intercom.spring.rest.filter;

import org.slf4j.MDC;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class TidFilter implements Filter {
    private static final String TID_HEADER = "tid";
    private static final String MDC_KEY = "tid";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String tid = null;
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            tid = ((HttpServletRequest) request).getHeader(TID_HEADER);
        }
        if (tid == null || tid.isEmpty()) {
            tid = UUID.randomUUID().toString();
        }
        MDC.put(MDC_KEY, tid);
        // Set tid as response header
        if (response instanceof HttpServletResponse) {
            ((HttpServletResponse) response).setHeader(TID_HEADER, tid);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_KEY);
        }
    }
}