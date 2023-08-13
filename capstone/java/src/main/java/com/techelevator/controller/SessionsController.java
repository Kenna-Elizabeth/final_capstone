package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Session;
import com.techelevator.dao.SessionDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/sessions")
public class SessionsController {

    private final SessionDao sessionDao;
    private final UserDao userDao;

    public SessionsController(SessionDao sessionDao, UserDao userDao) {
        this.sessionDao = sessionDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Session> getSessions(Principal userPrincipal) {
        User user = getUserFromPrincipal(userPrincipal);

        try {
            return sessionDao.getSessions(user.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Find Sessions");
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Session> getSessionsByBookId(@PathVariable int id, Principal userPrincipal) {
        User user = getUserFromPrincipal(userPrincipal);

        try {
            return sessionDao.getSessionsByBookId(id, user.getId());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Find Sessions");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Session addSession(@RequestBody Session session, Principal userPrincipal) {
        User user = getUserFromPrincipal(userPrincipal);

        if (user.getId() != session.getUserId()) {
            try {
                User targetUser = userDao.getUserById(session.getUserId());
                if (user.getFamilyId() != targetUser.getFamilyId()) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Session User Not In Current Family");
                }
            } catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Confirm User in Family");
            }
        }

        Session newSession;
        try {
            newSession = sessionDao.addSession(session);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Adding Session");
        }
        return newSession;

    }

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
