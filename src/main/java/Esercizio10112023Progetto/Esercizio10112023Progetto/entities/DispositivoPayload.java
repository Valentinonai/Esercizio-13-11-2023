package Esercizio10112023Progetto.Esercizio10112023Progetto.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DispositivoPayload(
        @NotEmpty(message = "Il nome non può essere vuoto")
        @Size(message = "Il nome deve avere almeno 5 caratteri",min = 5)
        String tipo
) {
}
