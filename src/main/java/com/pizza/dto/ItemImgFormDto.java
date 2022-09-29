package com.pizza.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.MultipartFile;

public class ItemImgFormDto {
	private MultipartFile data;
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDate time;
	
	public ItemImgFormDto() {
		// TODO Auto-generated constructor stub
	}

	public ItemImgFormDto(int itemid, MultipartFile image, LocalDate localDate) {
		super();
		this.data = image;
		this.time = localDate;
	}

	public MultipartFile getData() {
		return data;
	}

	public void setData(MultipartFile data) {
		this.data = data;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ItemImgFormDto [data=" + data + ", time=" + time + "]";
	}

	
}
