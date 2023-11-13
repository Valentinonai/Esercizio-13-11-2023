package Esercizio10112023Progetto.Esercizio10112023Progetto.security;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.LogInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @PostMapping("/login")
    public TokenPayload login(@RequestBody LogInDTO logInDTO){

    }
}
