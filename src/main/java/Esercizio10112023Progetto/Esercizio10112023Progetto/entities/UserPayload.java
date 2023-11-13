package Esercizio10112023Progetto.Esercizio10112023Progetto.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.aspectj.bridge.IMessage;

public record UserPayload(
        @NotEmpty(message = "Il nome non può essere vuoto")
        @Size(message = "Il nome deve avere almeno 5 caratteri",min = 5)
        String nome,
        @NotEmpty(message = "Il cognome non può essere vuoto")
        @Size(message = "Il cognome deve avere almeno 5 caratteri",min = 5)
        String cognome,
        @NotEmpty(message = "La mail non può essere vuota")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,

        @NotEmpty(message = "la password non può essere vouta")
        String password
) {
}
