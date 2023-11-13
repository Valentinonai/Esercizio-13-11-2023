package Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions;

import lombok.Getter;

@Getter
public class NotFound extends  RuntimeException{
    public NotFound(String message) {
        super(message);
    }
}
