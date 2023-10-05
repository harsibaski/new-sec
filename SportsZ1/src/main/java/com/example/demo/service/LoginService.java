package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.UserResponse;
import com.example.demo.model.Login;
import com.example.demo.model.Role;
import com.example.demo.repo.LoginRepository;





@Service

public class LoginService {
      
    @Autowired
  	LoginRepository SportRepository;
  	public Login create(Login Sports) {
  		return SportRepository.save(Sports);
  	}
  	public List<Login> read(){
  		return SportRepository.findAll();
  	}
  	public Login update(Login Sports) {
  		return SportRepository.save(Sports);
  		
  	}
  	public void delete(Long Id) {
  		SportRepository.deleteById(Id);
  	}
  	public List<UserResponse> getAllUsers() {
        List<Login> userList = SportRepository.findAll();
        return userList.stream()
                .filter(user -> !user.getRole().equals(Role.ADMIN))
                .map(this::mapUserToUserResponse)
                .collect(Collectors.toList());
    }
  	 private UserResponse mapUserToUserResponse(Login user) {
         return UserResponse.builder()
                 .uid(user.getUid())
                 .name(user.getName())
                 .email(user.getEmail())
                 .role(user.getRole())
                 .isEnabled(user.getIsEnabled())
                 .build();
     }
  	
}