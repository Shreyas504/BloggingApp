package com.shreyas.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreyas.blog.entities.User;
import com.shreyas.blog.exceptions.ResourceNotFoundException;
import com.shreyas.blog.payloads.UserDto;
import com.shreyas.blog.repositories.UserRepo;
import com.shreyas.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUserEntity(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.UserEntityToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		
		User updatedUser = userRepo.save(user);
		
		return UserEntityToDto(updatedUser);
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		return UserEntityToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user -> UserEntityToDto(user)).collect(Collectors.toList());
		
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		userRepo.delete(user);

	}
	
	
	
	
	
	private User dtoToUserEntity(UserDto userDto) {
		/* Manual mappingDone
		 * User user = new User(); user.setAbout(userDto.getAbout());
		 * user.setEmail(userDto.getEmail()); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setPassword(userDto.getPassword());
		 * 
		 * return user;
		 */
		
		User user = modelMapper.map(userDto, User.class);
		return user;
		
	}
	
	private UserDto UserEntityToDto(User user) {
		/* Manual Mapping handled
		 * UserDto userDto = new UserDto(); userDto.setAbout(user.getAbout());
		 * userDto.setEmail(user.getEmail()); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setPassword(user.getPassword());
		 * 
		 * return userDto;
		 */
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
		
	}

}
