package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Prize;
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
