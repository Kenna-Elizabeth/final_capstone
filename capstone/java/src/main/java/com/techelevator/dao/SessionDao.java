package com.techelevator.dao;

import com.techelevator.model.Session;

import java.util.List;

public interface SessionDao {

    List<Session> getSessions(int userId);

}
