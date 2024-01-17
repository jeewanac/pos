package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.dto.UserPwdDTO;
import com.example.pos.entity.UserEntity;

@Service
public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity findUserById(Long id);
    UserEntity createUser(UserEntity userEntity);
    UserEntity changeUserPwd(Long id, UserPwdDTO userPwdDTO);
    void deleteUser(Long id);
}
