package pe.com.emilima.dms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pe.com.emilima.dms.model.User;
import pe.com.emilima.dms.service.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.log(Level.INFO, "Authenticating...");
        User user = userService.findById(authentication.getName()).orElse(null);
        if (user == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!user.getPassword().equals(presentedPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
