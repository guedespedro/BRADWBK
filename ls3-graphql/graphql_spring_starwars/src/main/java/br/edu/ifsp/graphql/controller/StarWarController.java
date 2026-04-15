package br.edu.ifsp.graphql.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.edu.ifsp.graphql.model.Character;
import br.edu.ifsp.graphql.model.Droid;
import br.edu.ifsp.graphql.model.Episode;
import br.edu.ifsp.graphql.model.Human;
import br.edu.ifsp.graphql.model.Review;
import br.edu.ifsp.graphql.model.ReviewInput;
import br.edu.ifsp.graphql.model.Starship;

@Controller
public class StarWarController {

    @QueryMapping
    public List<Human> humans() {
        return List.of(
                new Human("1001", "Luke Skywalker", List.of(Episode.NEWHOPE), List.of(), 1.80),
                new Human("1002", "Han Solo", List.of(Episode.NEWHOPE, Episode.EMPIRE), List.of(), 1.85)
        );
    }

    @QueryMapping
    public List<Starship> starships() {
        return List.of(
            //MIlennium gigante
                new Starship(3000, "Millenium Falcon", 50),
                new Starship(3001, "NAVE TOPZERA", 25)
        );
    }

    @QueryMapping
    public Character character(@Argument String id) {
        // Exemplo de lógica: se começar com 1 é Humano, se começar com 2 é Droid
        if (id.startsWith("1")) {
            return new Human(id, "Human Character", List.of(), List.of(), 1.80);
        } else {
            return new Droid(id, "Droid Character", List.of(), List.of(), "Protocol");
        }
    }

    /*
     * Mapeado no resources/graphql/scheme.graphqls
     * 
     * type Query {
     * hero(episode: Episode): Character
     * }
     */
    @QueryMapping
    public Character hero(@Argument Episode episode) {
        return new Human(
                "1001",
                "Luke Skaywalker",
                List.of(episode != null ? episode : Episode.NEWHOPE),
                List.of(),
                1.75);
    }

    /*
     * Mapeado no resources/graphql/scheme.graphqls
     * 
     * type Query {
     * droid(id: ID!): Droid
     * }
     */
    @QueryMapping
    public Droid droid(@Argument String id) {



        return new Droid(
                id,
                "R2-D2",
                List.of(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
                List.of(),
                "Astromech");
    }

    /*
     * Mapeado no resources/graphql/scheme.graphqls
     * 
     * type Query {
     * search(text: String!): [SearchResult!]!
     * }
     */
    @QueryMapping
    public List<Object> search(@Argument String text) {
        return List.of(
                new Droid("2001", "R2-D2", List.of(), List.of(), "Astromech"),
                new Human("1001", "Luke Skywalker", List.of(), List.of(), 1.72f),
                new Starship(3000, "Millenium Falcon", 1000));
    }

    /*
     * Mapeado no resources/graphql/scheme.graphqls
     * 
     * type Mutation {
     * createReview(episode: Episode!, review: ReviewInput!): Review
     * }
     */
    @MutationMapping
    public Review createReview(@Argument Episode episode, @Argument ReviewInput review) {
        return new Review(review.getStars(), review.getCommentary());
    }

    /*
     * createHuman(id: ID!, name: String!, height: Float): Human
     */
    @MutationMapping
    public Human createHuman(@Argument String id, @Argument String name, @Argument Double height) {
        return new Human(id, name, List.of(), List.of(), height);
    }

    /*
     * createDroid(id: ID!, name: String!, primaryFunction: String): Droid
     */
    @MutationMapping
    public Droid createDroid(@Argument String id, @Argument String name, @Argument String primaryFunction) {
        return new Droid(id, name, List.of(), List.of(), primaryFunction);
    }

    /*
     * createStarship(id: ID!, name: String!, length: Float): Starship
     */
    @MutationMapping
    public Starship createStarship(@Argument int id, @Argument String name, @Argument Double length) {
        // Note que o id da Starship no seu model é int
        return new Starship(id, name, length);
    }

    /*
     * addFriend(characterId: ID!, friendId: ID!): Character
     */
    @MutationMapping
    public Character addFriend(@Argument String characterId, @Argument String friendId) {
        // Simulação: retornando um personagem que agora teria o novo amigo
        return new Human(characterId, "Personagem com amigo novo", List.of(), List.of(), 1.80);
    }
}
