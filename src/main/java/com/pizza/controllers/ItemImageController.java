package com.pizza.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pizza.dto.ItemImgFormDto;
import com.pizza.entities.ItemImage;
import com.pizza.service.ItemImageService;

@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials ="true")
@RestController
@RequestMapping("/itemImage")
public class ItemImageController {
	@Autowired
	private ItemImageService itemImgService;

	// add image of a item
	@PostMapping("/add/{itemid}")
	public ResponseEntity<?> addItemImage(@PathVariable("itemid") int itemid, @RequestParam("file") MultipartFile imagefile) {
		try {
			System.out.println("inimg controller multipart file size" + imagefile.getSize());
			ItemImgFormDto itemImg = new ItemImgFormDto(itemid, imagefile,LocalDate.now());
			Map<String, Object> result = itemImgService.addItemImg(itemid, itemImg);
			if(!result.isEmpty()) {
				return Response.success(result);
			}else {
				return Response.error("No Item Found");
			}
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

	// get image by itemId
	@GetMapping(value = "/item/{itemId}", produces = "image/png")
	public @ResponseBody byte[] downloadItemImg(@PathVariable("itemId") int itemId) {
		try {
			ItemImage attachment = itemImgService.findItemById(itemId);
			if (attachment != null)
				return attachment.getData();
			return null;
		} catch (Exception e) {
			return null;
		}
	}

//	// Show all images
//	@GetMapping(value = "/allImages", produces = "image/png")
//	public ResponseEntity<?> imgList() {
//		List<byte[]> itemImgList = itemImgService.getAllImage();
//		System.out.println("in controller" + itemImgList);
//		if (itemImgList.isEmpty())
//			return null;
//		System.out.println("This is the line ===========>>");
//		return Response.success(itemImgList.listIterator());
//	}
	
	
	/**
	 * 
	 * 
	 */
	
	
	
	
}
