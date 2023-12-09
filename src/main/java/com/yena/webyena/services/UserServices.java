package com.yena.webyena.services;

import com.yena.webyena.customExceptions.ApplicationException;
import com.yena.webyena.entities.Users;
import com.yena.webyena.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    /*public Users saveOneUser(Users newUser) {
        return userRepository.save(newUser);
    }*/

    public Users getOneUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Users updateOneUser(Integer userId, Users newUser) {
        Optional<Users> user = userRepository.findById(userId);
        if(user.isPresent()) {
            Users foundUser = user.get();
            foundUser.setEmail(newUser.getEmail());
            foundUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            foundUser.setName(newUser.getName());
            foundUser.setPhone(newUser.getPhone());
            foundUser.setRelatedCompany(newUser.getRelatedCompany());
            userRepository.save(foundUser);
            return foundUser;
        }else
            return null;
    }

    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }
}
