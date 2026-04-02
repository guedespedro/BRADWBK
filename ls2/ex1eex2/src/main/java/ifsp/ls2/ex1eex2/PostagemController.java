package ifsp.ls2.ex1eex2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostagemController {

    private List<Postagem> postagens = new ArrayList<>();

    @GetMapping 
    public List<Postagem> listar() {
        return postagens;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Postagem criar(@RequestBody Postagem postagem) {
        postagem.setId((long) (postagens.size() + 1));
        postagem.setDataCriacao(LocalDateTime.now());
        postagens.add(postagem);
        return postagem;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
        return postagens.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}") // Atualização [cite: 108, 117]
    public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @RequestBody Postagem novaPostagem) {
        for (int i = 0; i < postagens.size(); i++) {
            if (postagens.get(i).getId().equals(id)) {
                novaPostagem.setId(id);
                postagens.set(i, novaPostagem);
                return ResponseEntity.ok(novaPostagem);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        boolean removido = postagens.removeIf(p -> p.getId().equals(id));
        return removido ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}