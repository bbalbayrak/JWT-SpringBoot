package com.yena.webyena.controllers;
import com.yena.webyena.customExceptions.ApplicationException;
import com.yena.webyena.entities.Users;
import com.yena.webyena.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;


    @GetMapping(value = "/users")
    //public List<Users> getAllUsers(){return userServices.getAllUsers();}
    public ResponseEntity<?> getAllUsers(){
        var user = userServices.getAllUsers();
        if(user == null) {
            throw new ApplicationException(
                    "Users can not be listed",
                    String.format("User can not be listed"),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{userId}")
    public Users getOneUser(@PathVariable Integer userId) {
        var user = userServices.getOneUser(userId);
        if (user == null) {
            throw new ApplicationException(
                    "User cannot found", String.format("User with id=%d not found", userId),
                    HttpStatus.NOT_FOUND
            );
        }
        return user;
    }

    @PutMapping("/users/{userId}")
    public Users updateOneUser(@PathVariable Integer userId, @RequestBody Users newUser) {
        var user = userServices.updateOneUser(userId, newUser);
        if(user == null) {
            throw new ApplicationException(
                    "User update failed",String.format("User update with id=%d failed",userId),
                    HttpStatus.BAD_REQUEST
            );
        }
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public void deleteOneUser(@PathVariable Integer userId) {
        userServices.deleteById(userId);
    }


}
