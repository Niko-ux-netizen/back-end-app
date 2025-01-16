package be.ucll.mobileapplications.team7;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import be.ucll.mobileapplications.team7.Movie.model.Genre;
import be.ucll.mobileapplications.team7.Movie.model.Language;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Movie.model.StreamingPlatform;
import be.ucll.mobileapplications.team7.Movie.service.MovieRepository;
import be.ucll.mobileapplications.team7.User.model.User;
import be.ucll.mobileapplications.team7.User.service.UserRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
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

            @SuppressWarnings("deprecation")
            List<Movie> movies = Arrays.asList(
                new Movie("Avatar", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                    new Date(2009-1900, 11, 10), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS, StreamingPlatform.HULU)), "Enter the World of Pandora.", 7.2),
                new Movie("Pirates of the Caribbean: At World's End", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)),
                    new Date(2007-1900, 4, 19), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "At the end of the world, the adventure begins.", 6.9),
                new Movie("Spectre", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.CRIME)),
                    new Date(2015-1900, 9, 26), new HashSet<>(Arrays.asList(Language.DEUTSCH, Language.ENGLISH, Language.ESPAÑOL, Language.FRANÇAIS, Language.ITALIANO, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO)), "A Plan No One Escapes", 6.3),
                new Movie("The Dark Knight Rises", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                    new Date(2012-1900, 6, 16), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.NEDERLANDS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX)), "The Legend Ends", 7.6),
                new Movie("John Carter", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                    new Date(2012-1900, 2, 7), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "Lost in our world, found in another.", 6.1),
                new Movie("Spider-Man 3", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)),
                    new Date(2007-1900, 4, 1), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.PRIME_VIDEO)), "The battle within.", 5.9),
                new Movie("Tangled", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.FAMILY)),
                    new Date(2010-1900, 10, 24), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "They're taking adventure to new lengths.", 7.4),
                new Movie("Avengers: Age of Ultron", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                    new Date(2015-1900, 3, 22), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS, StreamingPlatform.HULU)), "A New Age Has Come.", 7.3),
                new Movie("Harry Potter and the Half-Blood Prince", new HashSet<>(Arrays.asList(Genre.ADVENTURE, Genre.FAMILY, Genre.FANTASY)),
                    new Date(2009-1900, 6, 7), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.DEUTSCH, Language.FRANÇAIS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX)), "Dark Secrets Revealed", 7.4),
                new Movie("Batman v Superman: Dawn of Justice", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)),
                    new Date(2016-1900, 2, 23), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.DEUTSCH, Language.FRANÇAIS, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX)), "Justice or revenge", 5.7),
                new Movie("Frozen", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.FAMILY, Genre.ADVENTURE)),
                    new Date(2013-1900, 10, 27), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "Let it go.", 7.5),
                new Movie("Shrek", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.COMEDY, Genre.FAMILY)),
                    new Date(2001-1900, 4, 18), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ESPAÑOL, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.HULU)), "The greatest fairy tale never told.", 7.8),
                new Movie("The Lion King", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ADVENTURE)),
                    new Date(1994-1900, 5, 15), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "Feel the love tonight.", 8.5),
                new Movie("Finding Nemo", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.COMEDY)),
                    new Date(2003-1900, 4, 30), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "Just keep swimming.", 8.1),
                new Movie("Inception", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)),
                    new Date(2010-1900, 6, 16), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.HBO_MAX)), "Your mind is the scene of the crime.", 8.8),
                new Movie("The Matrix", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)),
                    new Date(1999-1900, 2, 31), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.NETFLIX)), "Reality is a thing of the past.", 8.7),
                new Movie("The Godfather", new HashSet<>(Arrays.asList(Genre.CRIME, Genre.DRAMA)),
                    new Date(1972-1900, 2, 14), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ITALIANO, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.PEACOCK)), "An offer you can't refuse.", 9.2),
                new Movie("Pulp Fiction", new HashSet<>(Arrays.asList(Genre.CRIME, Genre.DRAMA)),
                    new Date(1994-1900, 9, 10), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.HULU)), "Just because you are a character doesn't mean you have character.", 8.9),
                new Movie("The Shawshank Redemption", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.CRIME)),
                    new Date(1994-1900, 9, 22), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.HBO_MAX)), "Fear can hold you prisoner. Hope can set you free.", 9.3),
                new Movie("The Incredibles", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ANIMATION, Genre.FAMILY)),
                    new Date(2004-1900, 10, 27), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "No gut, no glory.", 8.0),
                new Movie("Toy Story", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.COMEDY, Genre.FAMILY)),
                    new Date(1995-1900, 10, 22), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "The adventure takes off!", 8.3),
                new Movie("Coco", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY)),
                    new Date(2017-1900, 10, 27), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ESPAÑOL, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "Seize your moment.", 8.4),
                new Movie("WALL-E", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY)),
                    new Date(2008-1900, 5, 27), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "After 700 years of doing what he was built for, he'll discover what he's meant for.", 8.4),
                new Movie("Inside Out", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.COMEDY, Genre.FAMILY)),
                    new Date(2015-1900, 5, 15), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "Meet the little voices inside your head.", 8.1),
                new Movie("Up", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY)),
                    new Date(2009-1900, 4, 29), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "The adventure is out there!", 8.2),
                new Movie("Frozen II", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.FAMILY, Genre.ADVENTURE)),
                    new Date(2019-1900, 10, 22), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "The past is not what it seems.", 6.8),
                new Movie("Ratatouille", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.COMEDY, Genre.FAMILY)),
                    new Date(2007-1900, 5, 29), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "He's dying to become a chef.", 8.0),
                new Movie("Brave", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY)),
                    new Date(2012-1900, 5, 21), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "Change your fate.", 7.1),
                new Movie("Soul", new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY)),
                    new Date(2020-1900, 11, 24), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)), "Is all this living really worth dying for?", 8.1),
                new Movie("Inglourious Basterds", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.WAR)),
                    new Date(2009-1900, 7, 21), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.DEUTSCH, Language.FRANÇAIS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)), "Once upon a time in Nazi-occupied France.", 8.3),
                new Movie("The Revenant", new HashSet<>(Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.THRILLER)),
                    new Date(2015-1900, 11, 25), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.PRIME_VIDEO)), "Revenge is in his nature.", 8.0),
                new Movie("Mad Max: Fury Road", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                    new Date(2015-1900, 4, 15), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.HULU)), "What a lovely day.", 8.1),
                new Movie("Django Unchained", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.WESTERN)),
                    new Date(2012-1900, 11, 25), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.DEUTSCH, Language.FRANÇAIS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.PRIME_VIDEO)), "Life, liberty and the pursuit of vengeance.", 8.4),
                new Movie("Interstellar", new HashSet<>(Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION)),
                    new Date(2014-1900, 10, 26), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)), "Mankind was born on Earth. It was never meant to die here.", 8.6),
                new Movie("The Social Network", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA)),
                    new Date(2010-1900, 9, 1), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.HULU)), "You don't get to 500 million friends without making a few enemies.", 7.7),
                new Movie("The Wolf of Wall Street", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.COMEDY, Genre.CRIME)),
                    new Date(2013-1900, 11, 25), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)), "The story of a man who had everything and lost it all.", 8.2),
                new Movie("The Dark Knight", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA)),
                    new Date(2008-1900, 6, 14), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.NETFLIX)), "Why so serious?", 9.0),
                new Movie("Fight Club", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.THRILLER)),
                    new Date(1999-1900, 9, 15), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HULU, StreamingPlatform.NETFLIX)), "The first rule of Fight Club is: You do not talk about Fight Club.", 8.8),
                new Movie("Forrest Gump", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                    new Date(1994-1900, 6, 6), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)), "Life is like a box of chocolates.", 8.8),
                new Movie("Schindler's List", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY)),
                    new Date(1993-1900, 11, 30), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.DEUTSCH, Language.FRANÇAIS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.NETFLIX)), "Whoever saves one life, saves the world entire.", 9.0),
                new Movie("The Godfather Part II", new HashSet<>(Arrays.asList(Genre.CRIME, Genre.DRAMA)),
                    new Date(1974-1900, 11, 12), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ITALIANO, Language.FRANÇAIS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.PEACOCK)), "The sequel to the greatest film of all time.", 9.0),
                new Movie("The Irishman", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.CRIME, Genre.DRAMA)),
                    new Date(2019-1900, 10, 27), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ITALIANO, Language.FRANÇAIS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX)), "His story changed history.", 7.8),
                new Movie("Goodfellas", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.CRIME, Genre.DRAMA)),
                    new Date(1990-1900, 8, 12), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ITALIANO, Language.FRANÇAIS)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.PRIME_VIDEO)), "As far back as I can remember, I always wanted to be a gangster.", 8.7),
                new Movie("The Departed", new HashSet<>(Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.THRILLER)),
                    new Date(2006-1900, 9, 5), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.NETFLIX)), "Cops or criminals. When you're facing a loaded gun, what's the difference?", 8.5),
                new Movie("12 Years a Slave", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY)),
                    new Date(2013-1900, 10, 10), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.PRIME_VIDEO)), "The extraordinary true story of Solomon Northup.", 8.1),
                new Movie("Titanic", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                    new Date(1997-1900, 11, 1), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)), "Nothing on Earth could come between them.", 7.9),
                new Movie("Saving Private Ryan", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.WAR)),
                    new Date(1998-1900, 6, 22), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.NETFLIX)), "The mission is a man.", 8.6),
                new Movie("The Green Mile", new HashSet<>(Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.FANTASY)),
                    new Date(1999-1900, 11, 6), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                    new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)), "Miracles happen in the most unexpected places.", 8.6)
    );

            movieRepository.saveAll(movies);
        };
    }
}
