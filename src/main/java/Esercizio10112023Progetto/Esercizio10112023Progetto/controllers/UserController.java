package Esercizio10112023Progetto.Esercizio10112023Progetto.controllers;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.Dispositivo;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.User;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.UserPayload;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.BadRequest;
import Esercizio10112023Progetto.Esercizio10112023Progetto.services.DispositivoService;
import Esercizio10112023Progetto.Esercizio10112023Progetto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DispositivoService dispositivoService;
    @GetMapping()
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size, @RequestParam(defaultValue = "id") String order){
        return userService.getAllUsers(page,size>20?5:size,order);
    }
    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable int id){
        return userService.getSingleUser(id);
    }

    @PutMapping("/{id}")
    public User modifyUser(@RequestBody @Validated UserPayload userPayload,BindingResult validation,@PathVariable int id){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return userService.modifyUser(userPayload,id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id)
    {
        userService.deleteUser(id,dispositivoService);
    }


    @PutMapping("/{id}/image")
    public User modifyImage(@PathVariable int id,@RequestParam("immagineProfilo")MultipartFile file) throws IOException {
        return userService.modifyImage(file,id);
    }


    @GetMapping("/{id}/dispositivi")
    public List<Dispositivo> getDispositiviById(@PathVariable int id){
        return userService.getDispositiviById(id);
    }
}
