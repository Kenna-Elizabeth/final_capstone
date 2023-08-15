package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Prize;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPrizeDao implements PrizeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Prize> getPrizes(int familyId, int userId) {
        List<Prize> prizes = new ArrayList<>();

        String sql = "SELECT p.prize_id, p.family_id, p.prize_name, p.description, p.milestone, p.for_parents, p.for_children, p.max_prizes, p.start_date, p.end_date, " +
                "COALESCE(up.completed, false) AS completed, " +
                "up.completion_timestamp " +
                "FROM prizes AS p " +
                "LEFT JOIN users_prizes AS up " +
                "ON p.prize_id = up.prize_id AND up.user_id = ?" +
                "WHERE p.family_id = ? " +
                "ORDER BY start_date DESC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, familyId);
            while (results.next()) {
                Prize prize = mapRowToPrize(results);
                prizes.add(prize);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return prizes;
    }

    @Override
    public Prize getPrizeById(int prizeId, int userId) {
        Prize prize = null;

        String sql = "SELECT p.prize_id, p.family_id, p.prize_name, p.description, p.milestone, p.for_parents, p.for_children, p.max_prizes, p.start_date, p.end_date, " +
                "COALESCE(up.completed, false) AS completed, " +
                "up.completion_timestamp " +
                "FROM prizes AS p " +
                "LEFT JOIN users_prizes AS up " +
                "ON p.prize_id = up.prize_id AND up.user_id = ?" +
                "WHERE p.prize_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, prizeId);
            if (results.next()) {
                prize = mapRowToPrize(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return prize;
    }

    @Override
    public Prize addPrize(Prize prize, int familyId, int userId) {
        Prize newPrize = null;

        String sql = "INSERT INTO prizes (family_id, prize_name, description, milestone, for_parents, for_children, max_prizes, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING prize_id;";
        try {
            int newPrizeId = jdbcTemplate.queryForObject(sql, int.class, familyId, prize.getPrizeName(), prize.getDescription(),
                    prize.getMilestone(), prize.isForParents(), prize.isForChildren(), prize.getMaxPrizes(), prize.getStartDate(), prize.getEndDate());
            newPrize = getPrizeById(newPrizeId, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
        return newPrize;
    }

    @Override
    public Prize updatePrize(Prize prize, int userId) {
        Prize updatedPrize = null;

        String sql = "UPDATE prizes " +
                "SET prize_name = ?, description = ?, milestone = ?, for_parents = ?, for_children = ?, max_prizes = ?, start_date = ?, end_date = ? " +
                "WHERE prize_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, prize.getPrizeName(), prize.getDescription(), prize.getMilestone(),
                    prize.isForParents(), prize.isForChildren(), prize.getMaxPrizes(), prize.getStartDate(), prize.getEndDate(),
                    prize.getId());
            if (numberOfRows != 1) {
                throw new DaoException("Prize not found.");
            } else {
                updatedPrize = getPrizeById(prize.getId(), userId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
        return updatedPrize;
    }

    @Override
    public void deletePrize(int prizeId) {
        String sql = "DELETE FROM prizes WHERE prize_id = ?;";

        try {
            int numberOfRows = jdbcTemplate.update(sql, prizeId);
            if (numberOfRows != 1) {
                throw new DaoException("Prize not found.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    private Prize mapRowToPrize(SqlRowSet rs) {
        Prize prize = new Prize();
        prize.setId(rs.getInt("prize_id"));
        prize.setFamilyId(rs.getInt("family_id"));
        prize.setPrizeName(rs.getString("prize_name"));
        prize.setDescription(rs.getString("description"));
        prize.setMilestone(rs.getInt("milestone"));
        prize.setForParents(rs.getBoolean("for_parents"));
        prize.setForChildren(rs.getBoolean("for_children"));
        prize.setMaxPrizes(rs.getInt("max_prizes"));
        prize.setStartDate(rs.getTimestamp("start_date"));
        prize.setEndDate(rs.getTimestamp("end_date"));
        prize.setCompleted(rs.getBoolean("completed"));
        prize.setCompletionDate(rs.getTimestamp("completion_timestamp"));
        return prize;
    }

}