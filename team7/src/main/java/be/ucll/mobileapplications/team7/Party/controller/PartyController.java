package be.ucll.mobileapplications.team7.Party.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.Party.model.PartyOptions;
import be.ucll.mobileapplications.team7.Party.service.PartyService;
import be.ucll.mobileapplications.team7.Party.service.PartyServiceException;
import be.ucll.mobileapplications.team7.ServiceException.ServiceException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/party")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping()
    public List<Party> getAllParties() {
        return partyService.getAllParties();
    }

    @PostMapping("/create/{userId}")
    public Party createParty(@PathVariable int userId, @RequestBody PartyOptions partyOptions) throws PartyServiceException {
        return partyService.createParty(userId, partyOptions);
    }

    @PostMapping("/join/{partyId}/{userId}/{code}")
    public Party joinParty(@PathVariable int partyId, @PathVariable int userId, @PathVariable String code) throws PartyServiceException {
        return partyService.joinParty(partyId, userId, code);
    }

    @PutMapping("/start/{partyId}/{userId}")
    public Party startParty(@PathVariable int partyId, @PathVariable int userId) throws PartyServiceException {
        return partyService.startParty(partyId, userId);
    }

    @PutMapping("/vote/{partyId}/{userId}/{movieId}/{vote}")
    public Boolean voteForMovie(@PathVariable int partyId, @PathVariable int userId, @PathVariable int movieId, @PathVariable Boolean vote) throws PartyServiceException {
        return partyService.voteForMovie(partyId, userId, movieId, vote);
    }

    @PutMapping("/close/{partyId}/{userId}")
    public Party closeParty(@PathVariable int partyId, @PathVariable int userId) throws PartyServiceException {
        return partyService.closeParty(partyId, userId);
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
