package Esercizio10112023Progetto.Esercizio10112023Progetto.security;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.LogInDTO;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.User;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.UserPayload;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.BadRequest;
import Esercizio10112023Progetto.Esercizio10112023Progetto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public TokenPayload login(@RequestBody LogInDTO body){
            return new TokenPayload(securityService.autenticazione(body)) ;
    }
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenPayload createUser(@RequestBody @Validated UserPayload userPayload, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
         User u=userService.createUser(userPayload);
        return new TokenPayload(securityService.autenticazione(new LogInDTO(u.getEmail(),u.getPassword())));
    }
}
