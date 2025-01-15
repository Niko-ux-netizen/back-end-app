package be.ucll.mobileapplications.team7;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import be.ucll.mobileapplications.team7.Movie.model.Genre;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Movie.service.MovieRepository;
import be.ucll.mobileapplications.team7.User.model.User;
import be.ucll.mobileapplications.team7.User.service.UserRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Team7Application {

    public static void main(String[] args) {
        SpringApplication.run(Team7Application.class, args);
    }

    @Bean
    CommandLineRunner seedDatabase(UserRepository userRepository, MovieRepository movieRepository) {
        return args -> {
            User user = new User();
            user.setUsername("Caann");
            user.setEmail("Caann@mail.com");
            user.setPassword("CaannCaann");
            user.setDateOfBirth(LocalDate.of(2024, 12, 15));
						user.setFavoriteGenres(new HashSet<>(Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));
            userRepository.save(user);

            List<Movie> movies = Arrays.asList(
                new Movie("Titanic", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.ROMANCE))),
                new Movie("Inception", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER))),
                new Movie("The Godfather", new HashSet<>(Arrays.asList(Genre.CRIME, Genre.DRAMA))),
                new Movie("The Dark Knight", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA))),
                new Movie("Pulp Fiction", new HashSet<>(Arrays.asList(Genre.CRIME, Genre.DRAMA))),
                new Movie("Forrest Gump", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.ROMANCE))),
                new Movie("The Shawshank Redemption", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.CRIME))),
                new Movie("The Lord of the Rings: The Fellowship of the Ring", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY))),
                new Movie("The Matrix", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION))),
                new Movie("Schindler's List", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY))),
                new Movie("Fight Club", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.THRILLER))),
                new Movie("The Lion King", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.FAMILY))),
                new Movie("Star Wars: Episode IV - A New Hope", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))),
                new Movie("Jurassic Park", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))),
                new Movie("The Avengers", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION))),
                new Movie("Gladiator", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.HISTORY))),
                new Movie("The Departed", new HashSet<>(Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.THRILLER))),
                new Movie("Braveheart", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.BIOGRAPHY, Genre.DRAMA))),
                new Movie("Coco", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY))),
                new Movie("Interstellar", new HashSet<>(Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION)))
            );

            movieRepository.saveAll(movies);
        };
    }
}
