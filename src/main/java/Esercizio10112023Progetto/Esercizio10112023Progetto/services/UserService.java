package Esercizio10112023Progetto.Esercizio10112023Progetto.services;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.*;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.NotFound;
import Esercizio10112023Progetto.Esercizio10112023Progetto.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CloudinaryService cloudinaryService;


    public Page<User> getAllUsers(int page,int size,String order){
        Pageable p= PageRequest.of(page,size, Sort.by(order));
        return userRepository.findAll(p);
    }

    public User getSingleUser(int id){
        User u=userRepository.findById(id).orElseThrow(()->new NotFound("User non trovato"));
        return u;
    }

    public User createUser(UserPayload userPayload){
        User u=User.builder().nome(userPayload.nome()).cognome(userPayload.cognome()).email(userPayload.email()).username(userPayload.nome()+"_"+userPayload.cognome()).password(userPayload.password()).imageUrl("https://picsum.photos/200/300").build();
        userRepository.save(u);
        return u;
    }

    public User modifyUser(UserPayload userPayload,int id){
        User u=this.getSingleUser(id);
        u.setNome(userPayload.nome());
        u.setCognome(userPayload.cognome());
        u.setEmail(userPayload.email());
        u.setUsername(userPayload.nome()+"_"+userPayload.cognome());
        userRepository.save(u);
        return u;
    }

    public void deleteUser(int id,DispositivoService dispositivoService){
        User u=this.getSingleUser(id);
        u.getListaDispositivi().forEach(elem->dispositivoService.setStato(new SetStatoDispositivoPayload("DISPONIBILE"), elem.getId()));
        if(!u.getImageUrl().equals("https://picsum.photos/200/300")){
            cloudinaryService.deleteImageByUrl(u.getImageUrl());
        }
        userRepository.delete(u);
    }

    public User modifyImage(MultipartFile file, int id) throws IOException {
        User u=this.getSingleUser(id);
        if(!u.getImageUrl().equals("https://picsum.photos/200/300")){
            cloudinaryService.deleteImageByUrl(u.getImageUrl());
        }

        String url=(String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        u.setImageUrl(url);
        userRepository.save(u);
        return  u;

    }

    public List<Dispositivo> getDispositiviById(int id){
 List<Dispositivo> dispositivoList=userRepository.findDispositiviById(id).orElseThrow(()->new NotFound("Nessun dispositivo associato all'utente selezionato"));
if(dispositivoList.isEmpty()) throw new NotFound("Nessun dispositivo associato all'utente selezionato");
        return dispositivoList;
    }


    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new NotFound("User selezionato inesistente"));
    }
}
