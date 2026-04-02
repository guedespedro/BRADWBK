package ifsp.ls1.ex1.controller;

import ifsp.ls1.ex1.model.User;
import ifsp.ls1.ex1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> buscar(@PathVariable String login) {
        User user = userService.find(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> remover(@PathVariable String login) {
        boolean removido = userService.remove(login);
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}