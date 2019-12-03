package br.com.kairos.parking.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kairos.parking.config.property.ParkingApiProperty;

@Profile("oauth-security")
@RestController
@RequestMapping("/tokens")
public class TokenResource {
    
    @Autowired
    private ParkingApiProperty property;
    
    @DeleteMapping("/revoke")
    public void revoke(final HttpServletRequest req, final HttpServletResponse resp) {
        final Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(this.property.getSeguranca().isEnableHttps());
        cookie.setPath(req.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);
        
        resp.addCookie(cookie);
        resp.setStatus(HttpStatus.NO_CONTENT.value());
    }
    
}
