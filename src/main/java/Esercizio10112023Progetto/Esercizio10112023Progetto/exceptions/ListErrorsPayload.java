package Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ListErrorsPayload {
    private List<String> errorsList;
    private Date date;
}
