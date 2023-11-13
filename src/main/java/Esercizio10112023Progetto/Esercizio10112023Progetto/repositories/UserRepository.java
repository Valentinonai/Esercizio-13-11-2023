package Esercizio10112023Progetto.Esercizio10112023Progetto.repositories;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.Dispositivo;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select u.listaDispositivi from User u where u.id=:id")
    Optional<List<Dispositivo>> findDispositiviById(int id);

    Optional<User> findByEmail(String email);
}
