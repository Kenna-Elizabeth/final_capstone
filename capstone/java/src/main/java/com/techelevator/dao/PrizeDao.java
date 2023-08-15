package com.techelevator.dao;

import com.techelevator.model.Prize;

import java.util.List;

public interface PrizeDao {

    List<Prize> getPrizes(int familyId, int userId);

    Prize getPrizeById(int prizeId, int userId);

    Prize addPrize(Prize prize, int familyId, int userId);

    Prize updatePrize(Prize prize, int userId);

    void deletePrize(int prizeId);

}
