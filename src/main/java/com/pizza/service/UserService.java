package com.pizza.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.custom_exceptions.ResourceNotFoundException;
import com.pizza.dto.CredentialsDto;
import com.pizza.dto.DtoEntityConvertor;
import com.pizza.dto.NewPasswordDTO;
import com.pizza.dto.UserDto;
import com.pizza.dto.UserSignUpDTO;
import com.pizza.entities.PasswordResetToken;
import com.pizza.entities.Users;
import com.pizza.repo.PasswordResetTokenRepository;
import com.pizza.repo.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DtoEntityConvertor convertor;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private ModelMapper mapper;
	@Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

	// to check user by email
	public Users searchByEmail(String email) {
		Optional<Users> emailCheck = userRepo.findByEmail(email);
		if (emailCheck.isEmpty())
			throw new ResourceNotFoundException("invalid email");
		else
			return emailCheck.get();
	}

	// to check user by mobile
	public Users searchByPhone(String phNumber) {
		Users phCheck = userRepo.findByPhoneNo(phNumber);
		if (phCheck == null)
			return null;
		else
			return phCheck;
	}

	// to add user
	public Users addUser(Users user) {
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);
		return user;
	}

	// to get user for sign in
	public Users getUser(CredentialsDto cred) {
		Optional<Users> signinUser = userRepo.findByEmail(cred.getEmail());
		System.out.println(signinUser.get().getEmail());
		String rawPasword = cred.getPassword();
//		encoder.ma
//		if (signinUser != null && encoder.matches(rawPasword, signinUser.getPassword()) && signinUser.getRole().equals("User")) {
		if (signinUser.isPresent() && encoder.matches(rawPasword, signinUser.get().getPassword())) 
			return signinUser.get();
		else
			return null;
	}

	// update user
	public Users editUsers(int userId, UserDto editUsers) {
		//Users checkId = userRepo.findByUserId(userId);
		Users checkId = userRepo.getReferenceById(userId);
		if (checkId != null) {
			//Users updateUsers = convertor.toUserEntity(editUsers);
			Users updateUsers = mapper.map(editUsers, Users.class);
			updateUsers.setUserId(checkId.getUserId());
			updateUsers.setPassword(checkId.getPassword());
			userRepo.save(updateUsers);
			return updateUsers;
		}
		return null;
	}
// user signup service
	public Users signup(UserSignUpDTO userDto) {
		Users tranUser = mapper.map(userDto, Users.class);
		tranUser.setRole("User");
		Optional<Users> check = userRepo.findByEmail(tranUser.getEmail());
		if(check.isEmpty()) {
			tranUser.setPassword(encoder.encode(userDto.getPassword()));
			return userRepo.save(tranUser);
		}
		
		return tranUser;
	}

	public int deleteUser(int userId) {
		
		userRepo.deleteById(userId);
		
		return 1;
	}
	
	public List<Users> getAllUsers() {
		
		return userRepo.findAllByRole("User");
	}
	
	public void createPasswordResetTokenForUser(Users user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
	    passwordTokenRepository.save(myToken);
		
	}

	public PasswordResetToken validatePasswordResetToken(String token) {
		
		PasswordResetToken prt = passwordTokenRepository.findByToken(token);	
		if(prt == null) throw new ResourceNotFoundException("invalid token");
		return prt;
	}
	
	public void resetPassword(PasswordResetToken prt, NewPasswordDTO newPassword ) {
	
		Users user = prt.getUser();
		user.setPassword(encoder.encode(newPassword.getPassword()));
		passwordTokenRepository.deleteById(prt.getId());
	}
}
