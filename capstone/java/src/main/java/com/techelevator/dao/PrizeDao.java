package com.techelevator.dao;

import com.techelevator.model.Prize;

import java.util.List;

public interface PrizeDao {

    List<Prize> getPrizes(int familyId, int userId);

}
