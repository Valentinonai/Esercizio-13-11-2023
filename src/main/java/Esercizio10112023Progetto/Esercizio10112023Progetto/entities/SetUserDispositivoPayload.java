package Esercizio10112023Progetto.Esercizio10112023Progetto.entities;

import jakarta.validation.constraints.NotNull;

public record SetUserDispositivoPayload(

        String stato,
        @NotNull(message = "devi attribuire un utente")
        int user_id
) {
}
