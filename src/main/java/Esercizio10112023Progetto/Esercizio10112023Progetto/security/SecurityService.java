package Esercizio10112023Progetto.Esercizio10112023Progetto.security;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.LogInDTO;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.User;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.Unauthorized;
import Esercizio10112023Progetto.Esercizio10112023Progetto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import Esercizio10112023Progetto.Esercizio10112023Progetto.security.*;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;

    public String autenticazione(LogInDTO body){
        User u=userService.findByEmail(body.email());
        if(u.getPassword().equals(body.password())){
            return jwtTools.creaToken(u);
        }
        else{
            throw new Unauthorized("Password errrata");
        }
    }
}
