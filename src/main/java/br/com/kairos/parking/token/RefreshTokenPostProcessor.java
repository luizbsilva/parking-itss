package br.com.kairos.parking.token;

import br.com.kairos.parking.config.property.ParkingApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Profile("oauth-security")
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {
    
    @Autowired
    private ParkingApiProperty property;
    
    @Override
    public boolean supports(final MethodParameter returnType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getName().equals("postAccessToken");
    }
    
    @Override
    public OAuth2AccessToken beforeBodyWrite(final OAuth2AccessToken body, final MethodParameter returnType,
            final MediaType selectedContentType, final Class<? extends HttpMessageConverter<?>> selectedConverterType,
            final ServerHttpRequest request, final ServerHttpResponse response) {
        
        final HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        final HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
        
        final DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
        
        final String refreshToken = body.getRefreshToken().getValue();
        this.adicionarRefreshTokenNoCookie(refreshToken, req, resp);
        this.removerRefreshTokenDoBody(token);
        
        return body;
    }
    
    private void removerRefreshTokenDoBody(final DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }
    
    private void adicionarRefreshTokenNoCookie(final String refreshToken, final HttpServletRequest req, final HttpServletResponse resp) {
        final Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(this.property.getSeguranca().isEnableHttps());
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(2592000);
        resp.addCookie(refreshTokenCookie);
    }
    
}
