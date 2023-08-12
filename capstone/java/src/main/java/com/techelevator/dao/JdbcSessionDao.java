package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Session;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSessionDao implements SessionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Session> getSessions(int userId) {
        List<Session> sessions = new ArrayList<>();

        String sql = "SELECT session_id, user_id, book_id, minutes, format, start_date_time, note " +
                "FROM sessions " + "" +
                "WHERE user_id = ? " + "" +
                "ORDER BY start_date_time DESC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Session session = mapRowToSession(results);
                sessions.add(session);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return sessions;
    }

    private Session mapRowToSession(SqlRowSet rs) {
        Session session = new Session();
        session.setId(rs.getInt("session_id"));
        session.setUserId(rs.getInt("user_id"));
        session.setBookId(rs.getInt("book_id"));
        session.setMinutes(rs.getInt("minutes"));
        session.setFormat(rs.getString("format"));
        session.setStartDateTime(rs.getTimestamp("start_date_time"));
        session.setNote(rs.getString("note"));
        return session;
    }
}
