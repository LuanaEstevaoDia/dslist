package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	GameRepository gameRepository;


	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(X -> new GameListDTO(X)).toList();

	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> listPosition = gameRepository.searchByList(listId);
		GameMinProjection objIndex =listPosition.remove(sourceIndex);
		listPosition.add(destinationIndex,objIndex);
		
		int minValueIndex = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int maxValueIndex = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for(int i = minValueIndex; i <= maxValueIndex; i++) {
			gameListRepository.updateBelongingPosition(listId, listPosition.get(i).getId(), i);
			
		}
		
		
	}
	
	

}
