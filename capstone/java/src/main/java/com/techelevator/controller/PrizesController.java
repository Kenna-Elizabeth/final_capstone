package com.techelevator.controller;

import com.techelevator.dao.PrizeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Prize;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/prizes")
public class PrizesController {

    private final PrizeDao prizeDao;
    private final UserDao userDao;

    public PrizesController(PrizeDao prizeDao, UserDao userDao) {
        this.prizeDao = prizeDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Prize> getPrizes(Principal userPrincipal) {
        User user = getUserFromPrincipal(userPrincipal);

        try {
            return prizeDao.getPrizes(user.getFamilyId(), user.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Find Family Users");
        }
    }

    //Private Methods

    //TODO instead of making a Dao call for user details, add info to Principal by extending User
    //https://stackoverflow.com/questions/20349594/adding-additional-details-to-principal-object-stored-in-spring-security-context
    private User getUserFromPrincipal(Principal userPrincipal) {
        try {
            return userDao.getUserByUsername(userPrincipal.getName());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Not Found");
        }
    }
}

