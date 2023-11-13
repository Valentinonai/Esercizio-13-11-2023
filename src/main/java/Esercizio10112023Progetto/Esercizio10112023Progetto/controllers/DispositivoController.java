package Esercizio10112023Progetto.Esercizio10112023Progetto.controllers;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.Dispositivo;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.DispositivoPayload;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.SetStatoDispositivoPayload;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.SetUserDispositivoPayload;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.BadRequest;
import Esercizio10112023Progetto.Esercizio10112023Progetto.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping()
    public Page<Dispositivo> getAllDispositivi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String order){
       return dispositivoService.getAllDispositivi(page,size>20?5:size,order);

    }

    @GetMapping("/{id}")
    public Dispositivo getSingleDispositivo(@PathVariable int id){
        return dispositivoService.getSingleDispositivo(id);

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo createDispositivo(@RequestBody @Validated DispositivoPayload dispositivo, BindingResult validation){
        if(validation.hasErrors())
            throw new BadRequest(validation.getAllErrors());
        return dispositivoService.createDispositivo(dispositivo);
    }
    @PutMapping("/{id}")
    public Dispositivo setUserDispositivo(@RequestBody @Validated SetUserDispositivoPayload dispositivo, BindingResult validation, @PathVariable int id){
        if(validation.hasErrors())  throw new BadRequest(validation.getAllErrors());
        return dispositivoService.setUserDispositivo(dispositivo,id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDispositivo(@PathVariable int id){
        dispositivoService.deleteDispositivo(id);
    }

    @PutMapping("/{id}/stato")
    public Dispositivo setStato(@PathVariable int id,@RequestBody SetStatoDispositivoPayload body){
        return dispositivoService.setStato(body,id);

    }

}
