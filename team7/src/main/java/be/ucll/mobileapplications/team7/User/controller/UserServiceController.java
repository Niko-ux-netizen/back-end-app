package be.ucll.mobileapplications.team7.User.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import be.ucll.mobileapplications.team7.Movie.model.Genre;
import be.ucll.mobileapplications.team7.ServiceException.ServiceException;
import be.ucll.mobileapplications.team7.User.model.User;
import be.ucll.mobileapplications.team7.User.service.UserService;
import be.ucll.mobileapplications.team7.User.service.UserServiceException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserServiceController {
  
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping()
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping()
    public User addUser(@RequestBody @Valid User user) throws UserServiceException {
        return userService.addUser(user);
    }


    @PostMapping("/history")
    public User addMovieToHistory(@RequestParam String email, @RequestParam String title) throws UserServiceException {
        return userService.addMovieToHistoryListUser(email, title);
    }

    @DeleteMapping("/history/remove")
    public User removeMovieFromHistory(@RequestParam String email, @RequestParam String title) throws UserServiceException {
        return userService.removeMovieFromHistory(email, title);
    }

    @PostMapping("/tobewatched")
    public User addMovieToBeWatched(@RequestParam String email, @RequestParam String title) throws UserServiceException {
        return userService.addMovieToBeWatchedListUser(email, title);
    }

    @DeleteMapping("/tobewatched/remove")
    public User removeMovieToBeWatched(@RequestParam String email, @RequestParam String title) throws UserServiceException {
        return userService.removeMovieFromToBeWatchedList(email, title);
    }

    @PostMapping("/denied")
    public User addMovieToDenied(@RequestParam String email, @RequestParam String title) throws UserServiceException {
        return userService.addMovieToDeniedListUser(email, title);
    }

    @PutMapping("/denied/reset")
    public User removeMovieDenied(@RequestParam String email) throws UserServiceException {
        return userService.resetDeniedList(email);
    }
    
    @PostMapping("/genres")
    public User addGenresToUser(@RequestParam String email, @RequestBody Set<Genre> genres) throws UserServiceException {
        return userService.addGenresToUser(email, genres);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class })
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ServiceException.class })
    public Map<String, String> handleServiceExceptions(ServiceException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("field", ex.getField());
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }
}
