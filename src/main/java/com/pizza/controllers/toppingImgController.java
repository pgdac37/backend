package com.pizza.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pizza.dto.ToppingImageDto;
import com.pizza.entities.ToppingImages;
import com.pizza.service.toppingImgService;

@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")

@RestController
@RequestMapping("/toppingImages")
public class toppingImgController {
	@Autowired
	private toppingImgService imgService;
	
	//add topping image
	@PostMapping("/add/{toppingId}")
	public ResponseEntity<?> addToppingImage(@PathVariable("toppingId") int toppingId,@RequestParam("file") MultipartFile  toppingImage){
		try {
			System.out.println("inside add topping image controller");
			ToppingImageDto toppingImageDto = new ToppingImageDto(toppingImage, Date.valueOf(LocalDate.now()));
			Map<String, Object> result = imgService.addToppingImage(toppingId, toppingImageDto);
			if(!result.isEmpty()) {
				return ResponseEntity.ok().body("Image modified");
			}else {
				return Response.error("not found");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	
	@GetMapping(value="/attachment/{toppingImgId}",produces = "image/png")
	public @ResponseBody byte[] showAttachment(@PathVariable("toppingImgId")int toppingImgId){
		try {
			ToppingImages attachment=imgService.findByImageId(toppingImgId);
			if(attachment==null)
				return null;
			return attachment.getData();
		} catch (Exception e) {
			return null;
		}
	}
	
//	@PostMapping("/upload/{itemid}")
//	public ResponseEntity<?> addItemImage_(@PathVariable("itemid") int itemid,  @RequestParam MultipartFile file) throws IOException {
//		
//		toppingImgService.upload(itemid, file);
//		
//		return ResponseEntity.ok().body("Image modified");
//	}
	
//	@GetMapping(value="/attachment",produces="image/png")
//	public @ResponseBody ResponseEntity<?> showAll()
//	{
//		List<ToppingImages> list=imgService.showAll();
//		System.out.println();
//		Stream<ToppingImageDto> result=list.stream().map(ar->
//		convertor.toToppingImg(ar));
//		return Response.success(result);
//	}
}
