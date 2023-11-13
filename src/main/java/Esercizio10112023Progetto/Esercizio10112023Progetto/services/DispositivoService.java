package Esercizio10112023Progetto.Esercizio10112023Progetto.services;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.*;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.BadRequest;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.NotFound;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.SingleBadRequest;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.Unauthorized;
import Esercizio10112023Progetto.Esercizio10112023Progetto.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private UserService userService;

    public Page<Dispositivo> getAllDispositivi(int page, int size, String order){
        Pageable p= PageRequest.of(page,size, Sort.by(order));
        return dispositivoRepository.findAll(p);

    }

    public Dispositivo getSingleDispositivo(int id){
        return dispositivoRepository.findById(id).orElseThrow(()->new NotFound(("Elemento non trovato")));
    }

    public Dispositivo createDispositivo(DispositivoPayload d){
        Dispositivo dispositivo=Dispositivo.builder().tipo(d.tipo()).stato(Stato.DISPONIBILE).build();
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo setUserDispositivo(SetUserDispositivoPayload d, int id){
        Dispositivo dispositivo=this.getSingleDispositivo(id);
        User u=userService.getSingleUser(d.user_id());
        if(d.stato().equals("DISPONIBILE")||d.stato().equals("ASSEGNATO")||d.stato().equals("DISMESSO")||d.stato().equals("MANUTENZIONE")) {

            if (dispositivo.getStato() == Stato.DISPONIBILE && Stato.valueOf(d.stato()) == Stato.ASSEGNATO) {
                dispositivo.setStato(Stato.valueOf(d.stato()));
                dispositivo.setUser(u);
                dispositivoRepository.save(dispositivo);
                return dispositivo;
            } else throw new Unauthorized("Endpoint sbagliato o dispositivo già assegnato o con stato non DISPONIBILE");
        }
        else
            throw new SingleBadRequest("Stato scelto inesistente");

    }

    public void deleteDispositivo(int id){
        Dispositivo d=this.getSingleDispositivo(id);
        dispositivoRepository.delete(d);
    }


   public Dispositivo setStato(SetStatoDispositivoPayload body,int id){
        Dispositivo d=this.getSingleDispositivo(id);
        if(body.stato().equals("DISPONIBILE")||body.stato().equals("ASSEGNATO")||body.stato().equals("DISMESSO")||body.stato().equals("MANUTENZIONE")) {
            switch (Stato.valueOf(body.stato())) {
                case ASSEGNATO -> {
                    throw new Unauthorized("EndPoint sbagliato non puoi assegnare uno user qui");
                }
                case DISMESSO -> {
                    d.setUser(null);
                    d.setStato(Stato.DISMESSO);
                    dispositivoRepository.save(d);
                    return d;
                }
                case DISPONIBILE -> {
                    d.setUser(null);
                    d.setStato(Stato.DISPONIBILE);
                    dispositivoRepository.save(d);
                    return d;
                }
                case MANUTENZIONE -> {
                    d.setUser(null);
                    d.setStato(Stato.MANUTENZIONE);
                    dispositivoRepository.save(d);
                    return d;
                }
            }
            throw new SingleBadRequest("Stato scelto inesistente");
        }
        else
        throw new SingleBadRequest("Stato scelto inesistente");
    }


}
