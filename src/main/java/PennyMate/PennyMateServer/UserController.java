package PennyMate.PennyMateServer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Repos userService;

    // API per ottenere tutti gli utenti
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // API per ottenere un singolo utente tramite ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // API per aggiungere un nuovo utente
    @PostMapping
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // API per aggiornare un utente esistente
    @PutMapping("/{id}")
    public int updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    // API per eliminare un utente
    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
