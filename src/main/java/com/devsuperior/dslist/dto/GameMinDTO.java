package com.devsuperior.dslist.dto;


import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;

import lombok.Data;


@Data
public class GameMinDTO {

	private Long id;
	private String title;
	private Integer year;
	private String imgUrl;
	private String shortDescription;

	public GameMinDTO(Game entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.year = entity.getGameYear();
		this.imgUrl= entity.getImgUrl();
		this.shortDescription = entity.getShortDescription();
		
	}
	

	public GameMinDTO(GameMinProjection projection) {
		this.id = projection.getId();
		this.title = projection.getTitle();
		this.year =projection.getgameYear();
		this.imgUrl= projection.getImgUrl();
		this.shortDescription = projection.getShortDescription();
		
	}
	
	
}
