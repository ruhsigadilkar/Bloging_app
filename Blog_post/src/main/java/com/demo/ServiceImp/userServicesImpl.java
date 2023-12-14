package com.demo.ServiceImp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Iservices.IUser;
import com.demo.Repo.UserRepo;
import com.demo.dtos.UserDto;
import com.demo.entities.User;
import com.demo.gloabalExceptions.ResourseNotFound;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class userServicesImpl implements IUser {

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper mapper; 
	@Override
	public UserDto Createuser(UserDto dto) {

		User user = this.dtoToUser(dto);

		User saveuser = repo.save(user);
		return this.UserToDto(saveuser);
	}

	@Override
	public UserDto upadteuser(UserDto dto, Long id) {
		Optional<User> user = repo.findById(id);
		User peruser = user.orElseThrow(() -> new ResourseNotFound("UserNot found " + id));
		peruser.setId(dto.getId());
		peruser.setAbout(dto.getAbout());
		peruser.setEmail(dto.getEmail());
		peruser.setName(dto.getName());
		peruser.setPassword(dto.getPassword());

		UserDto dtouser = this.UserToDto(peruser);

		return dtouser;
	}

	@Override
	public UserDto getuserById(Long id) {
		Date d= new Date();
		SimpleDateFormat fmt= new SimpleDateFormat("DD-MM-yyyy");
	String s=	fmt.format(d);
		Optional<User> user = repo.findById(id);
		User peruser = user.orElseThrow(() -> new ResourseNotFound("UserNot found id is " + id+"; time "+d));
		UserDto dto = this.UserToDto(peruser);
		return dto;
	}

	@Override
	public List<UserDto> getallusers() {

		List<User> user = repo.findAll();
		List<UserDto> userdto=user.stream().map(use -> this.UserToDto(use)).collect(Collectors.toList());

		return  userdto;
	}

	@Override
	public void deleteUser(Long id) {
		UserDto user=	getuserById(id);
		User us=this.dtoToUser(user);
		repo.delete(us);

	}

	User dtoToUser(UserDto user) {

		User usr = this.mapper.map(user, User.class);
		
		
//		usr.setId(user.getId());
//		usr.setAbout(user.getAbout());
//		usr.setEmail(user.getEmail());
//		usr.setName(user.getName());
//		usr.setPassword(user.getPassword());

		return usr;
	}

	UserDto UserToDto(User user) {
		UserDto usr = this.mapper.map(user, UserDto .class);
//		usr.setId(user.getId());
//		usr.setAbout(user.getAbout());
//		usr.setEmail(user.getEmail());
//		usr.setName(user.getName());
//		usr.setPassword(user.getPassword());

		return usr;
	}

}
