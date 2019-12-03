package br.com.kairos.parking.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Profile("oauth-security")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {
    
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        
        if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
                && "refresh_token".equals(req.getParameter("grant_type"))
                && req.getCookies() != null) {
            for (final Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("refreshToken")) {
                    final String refreshToken = cookie.getValue();
                    req = new MyServletRequestWrapper(req, refreshToken);
                }
            }
        }
        
        chain.doFilter(req, response);
    }
    
    @Override
    public void destroy() {
        
    }
    
    @Override
    public void init(final FilterConfig arg0) throws ServletException {
        
    }
    
    static class MyServletRequestWrapper extends HttpServletRequestWrapper {
        
        private final String refreshToken;
        
        public MyServletRequestWrapper(final HttpServletRequest request, final String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }
        
        @Override
        public Map<String, String[]> getParameterMap() {
            final ParameterMap<String, String[]> map = new ParameterMap<>(this.getRequest().getParameterMap());
            map.put("refresh_token", new String[] {this.refreshToken});
            map.setLocked(true);
            return map;
        }
        
    }
    
}
