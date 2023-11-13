package Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions;

import lombok.Getter;

@Getter
public class Unauthorized extends RuntimeException{
    public Unauthorized(String message) {
        super(message);
    }
}
