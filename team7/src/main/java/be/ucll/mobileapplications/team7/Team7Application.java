package be.ucll.mobileapplications.team7;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan("be.ucll.mobileapplications.team7")
public class Team7Application {

    public static void main(String[] args) {
        SpringApplication.run(Team7Application.class, args);
    }
 
    @SuppressWarnings("deprecation")
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
                    new Movie("Avatar",
                            new HashSet<>(
                                    Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                            new Date(2009 - 1900, 11, 10),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH,
                                    Language.NEDERLANDS)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS, StreamingPlatform.HULU)),
                            "Enter the World of Pandora.",
                            "James Cameron",
                            "A marine on an alien planet fights to save it while discovering his own humanity.",
                            7.2),
                    new Movie("Pirates of the Caribbean: At World's End",
                            new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)),
                            new Date(2007 - 1900, 4, 19),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)),
                            "At the end of the world, the adventure begins.",
                            "Jerry Bruckheimer",
                            "Captain Jack Sparrow must be rescued from Davy Jones' Locker to unite the pirate lords.",
                            6.9),
                    new Movie("The Dark Knight",
                            new HashSet<>(Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA)),
                            new Date(2008 - 1900, 6, 18),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX)),
                            "Why so serious?",
                            "Christopher Nolan",
                            "Batman faces the Joker, a criminal mastermind wreaking havoc in Gotham City.",
                            9.0),
                    new Movie("Inception",
                            new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION,
                                    Genre.THRILLER)),
                            new Date(2010 - 1900, 6, 16),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.HBO_MAX)),
                            "Your mind is the scene of the crime.",
                            "Christopher Nolan",
                            "A skilled thief is tasked with planting an idea in someone's mind using dream invasion.",
                            8.8),
                    new Movie("The Matrix",
                            new HashSet<>(Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION)),
                            new Date(1999 - 1900, 2, 31),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH,
                                    Language.NEDERLANDS)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.AMAZON_FREEVEE)),
                            "Free your mind.",
                            "The Wachowskis",
                            "A computer hacker learns about the true nature of his reality and his role in the war against its controllers.",
                            8.7),
                    new Movie("Avengers: Endgame",
                            new HashSet<>(
                                    Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.SCIENCE_FICTION)),
                            new Date(2019 - 1900, 3, 26),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)),
                            "Part of the journey is the end.",
                            "Kevin Feige",
                            "The Avengers assemble one last time to reverse the damage caused by Thanos.",
                            8.4),
                    new Movie("Jurassic Park",
                            new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                            new Date(1993 - 1900, 5, 11),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.AMAZON_FREEVEE)),
                            "An adventure 65 million years in the making.",
                            "Steven Spielberg",
                            "A theme park showcasing cloned dinosaurs turns deadly when the creatures escape.",
                            8.2),

                    new Movie("Titanic",
                            new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                            new Date(1997 - 1900, 11, 18),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS, StreamingPlatform.HULU)),
                            "Nothing on Earth could come between them.",
                            "James Cameron",
                            "A love story unfolds on the ill-fated voyage of the Titanic.",
                            7.9),

                    new Movie("The Lion King",
                            new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY)),
                            new Date(1994 - 1900, 5, 15),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)),
                            "The king has returned.",
                            "Roger Allers and Rob Minkoff",
                            "A young lion prince flees his kingdom but returns to reclaim his throne.",
                            8.5),

                    new Movie("Forrest Gump",
                            new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                            new Date(1994 - 1900, 6, 6),
                            new HashSet<>(Arrays.asList(Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX)),
                            "Life is like a box of chocolates.",
                            "Robert Zemeckis",
                            "The life journey of Forrest Gump, a man with a unique perspective on historical events.",
                            8.8),

                    new Movie("Spider-Man: No Way Home",
                            new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                            new Date(2021 - 1900, 11, 17),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ENGLISH, Language.FRANÇAIS)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)),
                            "The multiverse unleashed.",
                            "Jon Watts",
                            "Spider-Man must face villains from different dimensions while trying to fix the multiverse.",
                            8.7),

                    new Movie("Frozen",
                            new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY, Genre.MUSICAL)),
                            new Date(2013 - 1900, 10, 27),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)),
                            "Let it go.",
                            "Chris Buck and Jennifer Lee",
                            "A young woman must save her kingdom and her sister from eternal winter.",
                            7.4),

                    new Movie("The Shawshank Redemption",
                            new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.CRIME)),
                            new Date(1994 - 1900, 9, 14),
                            new HashSet<>(Arrays.asList(Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX)),
                            "Fear can hold you prisoner. Hope can set you free.",
                            "Frank Darabont",
                            "Two imprisoned men bond over years and find solace and redemption.",
                            9.3),

                    new Movie("Toy Story",
                            new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY, Genre.COMEDY)),
                            new Date(1995 - 1900, 10, 22),
                            new HashSet<>(Arrays.asList(Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)),
                            "The toys are back in town.",
                            "John Lasseter",
                            "A group of toys come to life and navigate their world when their owner isn't looking.",
                            8.3),

                    new Movie("Black Panther",
                            new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                            new Date(2018 - 1900, 1, 16),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)),
                            "Long live the king.",
                            "Ryan Coogler",
                            "T'Challa returns home to Wakanda and faces challenges to his rule as Black Panther.",
                            7.3),

                    new Movie("Coco",
                            new HashSet<>(Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY, Genre.MUSICAL)),
                            new Date(2017 - 1900, 10, 27),
                            new HashSet<>(Arrays.asList(Language.ENGLISH, Language.ENGLISH)),
                            new HashSet<>(Arrays.asList(StreamingPlatform.DISNEY_PLUS)),
                            "The celebration of a lifetime.",
                            "Lee Unkrich",
                            "A young boy travels to the Land of the Dead to uncover his family's history.",
                            8.4),

                    new Movie("Inglourious Basterds", 
                        new HashSet<>(Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.WAR)),
                        new Date(2009-1900, 7, 21), 
                        new HashSet<>(Arrays.asList(Language.ENGLISH, Language.DEUTSCH, Language.FRANÇAIS)),
                        new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)),
                        "Quentin Tarantino", 
                        "Once upon a time in Nazi-occupied France.", 
                        "A revenge-fueled story set in World War II with a group of Jewish-American soldiers hunting down Nazis.", 
                        8.3),
                    
                    new Movie("The Revenant", new HashSet<>(Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.THRILLER)),
                        new Date(2015-1900, 11, 25), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                        new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.PRIME_VIDEO)),
                        "Alejandro González Iñárritu", "Revenge is in his nature.", "A frontiersman fights for survival after being mauled by a bear and left for dead by members of his hunting team.", 8.0),
                    
                    new Movie("Mad Max: Fury Road", new HashSet<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)),
                        new Date(2015-1900, 4, 15), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                        new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.HULU)),
                        "George Miller", "What a lovely day.", "In a post-apocalyptic wasteland, Max teams up with a runaway warrior to escape a tyrannical warlord and his army.", 8.1),
                    
                    new Movie("Django Unchained", new HashSet<>(Arrays.asList(Genre.DRAMA, Genre.WESTERN)),
                        new Date(2012-1900, 11, 25), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.DEUTSCH, Language.FRANÇAIS)),
                        new HashSet<>(Arrays.asList(StreamingPlatform.HBO_MAX, StreamingPlatform.PRIME_VIDEO)),
                        "Quentin Tarantino", "Life, liberty and the pursuit of vengeance.", "A freed slave sets out to rescue his wife from a brutal plantation owner, with the help of a German bounty hunter.", 8.4),
                    
                    new Movie("Interstellar", new HashSet<>(Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION)),
                        new Date(2014-1900, 10, 26), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                        new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)),
                        "Christopher Nolan", "Mankind was born on Earth. It was never meant to die here.", "A group of explorers travel through a wormhole in space to ensure humanity's survival as Earth faces an ecological collapse.", 8.6),
                    
                    new Movie("The Social Network", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA)),
                        new Date(2010-1900, 9, 1), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.NEDERLANDS)),
                        new HashSet<>(Arrays.asList(StreamingPlatform.NETFLIX, StreamingPlatform.HULU)),
                        "David Fincher", "You don't get to 500 million friends without making a few enemies.", "The story of how Mark Zuckerberg created Facebook, and the legal battles that followed its success.", 7.7),
                    
                    new Movie("The Wolf of Wall Street", new HashSet<>(Arrays.asList(Genre.BIOGRAPHY, Genre.COMEDY, Genre.CRIME)),
                        new Date(2013-1900, 11, 25), new HashSet<>(Arrays.asList(Language.ENGLISH, Language.FRANÇAIS, Language.DEUTSCH)),
                        new HashSet<>(Arrays.asList(StreamingPlatform.PRIME_VIDEO, StreamingPlatform.NETFLIX)),
                        "Martin Scorsese", "The story of a man who had everything and lost it all.", "The rise and fall of Jordan Belfort, a stockbroker who built a corrupt empire of wealth and excess.", 8.2));
                        
            movieRepository.saveAll(movies);
        };
    }
}
