package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.SecurityService;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Secured(SecurityRule.IS_AUTHENTICATED) // <1>
@Controller  // <2>
public class HomeController {

    private final SecurityService securityService;

    public HomeController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get // <3>
    public String index() throws InterruptedException {  // <4>
        callExternalAPI();
        Optional<Authentication> authentication = securityService.getAuthentication();
        if (authentication.isEmpty()) {
            throw new IllegalArgumentException("USER not found");
        } else {
            return authentication.get().getName();
        }
    }

    private static void callExternalAPI() throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(35));
    }
}
