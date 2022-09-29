package com.pizza.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartException;

import com.pizza.entities.Address;
import com.pizza.entities.Cart;
import com.pizza.entities.DeliveryStatus;
import com.pizza.entities.Item;
import com.pizza.entities.ItemImage;
import com.pizza.entities.ItemSize;
import com.pizza.entities.Payments;
import com.pizza.entities.ToppingImages;
import com.pizza.entities.Toppings;
import com.pizza.entities.Users;
import com.pizza.repo.ItemDao;

@Component
public class DtoEntityConvertor {
	
	//addressDto to Address
//	public Address toAddressEntity(AddressDto addressDto) {
//		Address convertedAddress = new Address();
//		Users useraddress = new Users();
//		useraddress.setUserId(addressDto.getUserId());
//		convertedAddress.setUser(useraddress);
//		convertedAddress.setPlotNo(addressDto.getPlotNo());
//		convertedAddress.setStreetName(addressDto.getStreetName());
//		convertedAddress.setCity(addressDto.getCity());
//		convertedAddress.setDistrict(addressDto.getDistrict());
//		convertedAddress.setSoverignState(addressDto.getSoverignState());
//		convertedAddress.setPincode(addressDto.getPincode());
//		return convertedAddress;
//	}
	
	public Users toUserEntity(UserDto dto) {
		Users entity = new Users();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setRole(dto.getRole());
		entity.setPhoneNo(dto.getPhoneNo());
		return entity;		
	}
	
	//Address to addressDto
	
	/* Don't know where to use but may be used in future for fast perfoemance */
//	public AddressDto toAddressDtoEntity(Address address) {
//		System.out.println("In convertor");
//		AddressDto convertedAddressDto = new AddressDto();
//		convertedAddressDto.setUserId(address.getUser().getUserId());
//		convertedAddressDto.setPlotNo(address.getPlotNo());
//		convertedAddressDto.setStreetName(address.getStreetName());
//		convertedAddressDto.setCity(address.getCity());
//		convertedAddressDto.setState(address.getState());
//		convertedAddressDto.setPincode(address.getPincode());
//		return convertedAddressDto;
//	}
	
	// cartdto to cart
	public Cart tocartEntityNoTopping(CartDtoWithoutTopping cartWithoutTopping) {
		System.out.println("In convertor");
		Cart convertedcart = new Cart();
		Users user= new Users();
		user.setUserId(cartWithoutTopping.getUserId());
		ItemSize itemSize = new ItemSize();
		itemSize.setSizeId(cartWithoutTopping.getSizeId());
		convertedcart.setUser(user);
		convertedcart.setItemsize(itemSize);
		convertedcart.setQuantity(cartWithoutTopping.getQuantity());
		convertedcart.setPrice(cartWithoutTopping.getPrice());
		convertedcart.setCartstatus(1);
		return convertedcart;
	}
	
	public Cart tocartEntity(CartDto cartDto) {
		System.out.println("In convertor");
		Cart convertedcart = new Cart();
		Users user= new Users();
		user.setUserId(cartDto.getUserId());
		ItemSize itemSize = new ItemSize();
		itemSize.setSizeId(cartDto.getSizeId());
		Toppings topping = new Toppings();
		topping.setToppingId(cartDto.getToppingId());
		convertedcart.setUser(user);
		convertedcart.setItemsize(itemSize);
		convertedcart.setToppings(topping);
		convertedcart.setQuantity(cartDto.getQuantity());
		convertedcart.setPrice(cartDto.getPrice());
		convertedcart.setCartstatus(1);
		return convertedcart;
	}
	
	//for inserting image
	
	@Autowired
	public ItemDao itemdao;
	
	public ItemImage toItemEntity(ItemImgFormDto itemDto, int itemId) {
		System.out.println("in convertor before");
		System.out.println("image file data " + itemDto.getData().getSize());
		
		if(itemDto == null)
			return null;
		ItemImage itemImg = new ItemImage();
		Item item = itemdao.getReferenceById(itemId);
		itemImg.setItem(item);
		itemImg.setItemImgTime(itemDto.getTime());
		System.out.println(itemDto.getTime());
		try {
			itemImg.setData(itemDto.getData().getBytes());
		} catch (Exception e) {
			throw new MultipartException("Can't convert MultipartFile to bytes : " + itemDto.getData(), e);
		}
		return itemImg;
	}
	
	//insert image of toppings
	public ToppingImages toToppingImageEntity(ToppingImageDto toppingImageDto,int toppingId) {
		System.out.println("In convertor");
		if(toppingImageDto==null)
			return null;
		ToppingImages toppingImg=new ToppingImages();
		Toppings topping=new Toppings();
		topping.setToppingId(toppingId);
		toppingImg.setToppings(topping);
		toppingImg.setToppingImgTime(toppingImageDto.getTopTime());
		System.out.println("time ==>"+toppingImageDto.getTopTime());
		try {
			toppingImg.setData(toppingImageDto.getData().getBytes());
		} catch (Exception e) {
			throw new MultipartException("Can't convert MultipartFile to bytes : " + toppingImageDto.getData(), e);

		}
		return toppingImg;
	}
	
	public ItemDto toItemDto(Item itemList){
		ItemDto itemDto= new ItemDto();
		itemDto.setItemName(itemList.getItemName());
		itemDto.setItemid(itemList.getItemid());
		itemDto.setItemImages(null);
		itemDto.setDescription(itemList.getDescription());
		itemDto.setType(itemList.getType());
		return itemDto;
	}
	
	public Payments toPaymentEntity(PaymentDto paymentDto, int totalamount)
	{
		Payments convertPayment=new Payments();
		Users user=new Users();
		user.setUserId(paymentDto.getUserId());
		convertPayment.setPayStatus(paymentDto.getPayStatus());
		convertPayment.setUsers(user);
		convertPayment.setMode(paymentDto.getMode());
		convertPayment.setTotalAmount(totalamount);
		convertPayment.setPayTimeStamp(paymentDto.getPayTimeStamp());
		return convertPayment;	
	}
	
	public DeliveryStatus toDelivery(DeliveryDto dto)
	{
		System.out.println("In convertor");
		DeliveryStatus convertDelivery=new DeliveryStatus();
		Payments convertPay=new Payments();
		Users user = new Users();
		Address address = new Address();
		address.setAddressId(dto.getAddressid());
		user.setUserId(dto.getUserId());
		convertPay.setPayId(dto.getPayId());
		convertDelivery.setPayments(convertPay);
		convertDelivery.setDeliveryStatus(dto.getDeliveryStatus());
		convertDelivery.setDeliveryTime(dto.getDeliveryTime());
		convertDelivery.setUsers(user);
		convertDelivery.setAddress(address);
		return convertDelivery;
	}
}






