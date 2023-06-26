package com.user.service.controller;

import com.user.service.model.User;
import com.user.service.payload.ApiResponse;
import com.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User createuser = this.userService.createUser(user);
        return new ResponseEntity<User>(createuser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUSers()
    {
        List<User> aLlUser = this.userService.getALlUser();
        return new ResponseEntity<List<User>>(aLlUser,HttpStatus.OK);
    }

    @GetMapping("/s/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelBreaker")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId") int userId)
    {
        User sIngleUSer = this.userService.getSIngleUSer(userId);

        return new ResponseEntity<User>(sIngleUSer,HttpStatus.OK);
    }

    //creating fall back method for ckt -braker

    public ResponseEntity<User> ratingHotelBreaker(int userId,Exception ex)
    {
        User user = User.builder().name("dummy").email("fummy@gmail.com").about("this is user created dummy")
                .userId(12345).build();

        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
}
