package com.example.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.dto.UserPwdDTO;
import com.example.pos.entity.UserEntity;
import com.example.pos.repository.UserRepository;
import com.example.pos.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
         return userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long id) {
         return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
         return userRepository.save(userEntity);
    }

    @Override
    public UserEntity changeUserPwd(Long id, UserPwdDTO userPwdDTO) {
        UserEntity exist = userRepository.findById(id).orElse(null);
        System.out.println(userPwdDTO.getPassword());
        if(exist!=null){
            exist.setPassword(userPwdDTO.getPassword());
            return userRepository.save(exist);
        }else{
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }
    
}
