package Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;
@Getter
public class BadRequest extends RuntimeException {
    List<ObjectError> errorsList;
    public BadRequest(String message) {
        super(message);
    }
    public BadRequest(List<ObjectError> errorsList)
    {
        super("Errori:");
        this.errorsList=errorsList;
    }
}
