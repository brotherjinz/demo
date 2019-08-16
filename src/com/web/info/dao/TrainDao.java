package com.web.info.dao;

import java.util.List;
import java.util.Set;

import com.web.info.args.TrainArgs;
import com.web.info.dto.TrainDto;
import com.web.info.model.Train;

public interface TrainDao extends IBase<Train> {
	List<TrainDto> queryListTrain(TrainArgs args);
	
	List<TrainDto> queryTrainSet(Set<String> trainSet);
	
	List<TrainDto> queryList(TrainArgs args);
}
