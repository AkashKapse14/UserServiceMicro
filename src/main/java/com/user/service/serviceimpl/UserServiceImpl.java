package com.user.service.serviceimpl;

import com.user.service.dao.UserRepoDao;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.model.Hotel;
import com.user.service.model.Rating;
import com.user.service.model.User;
import com.user.service.service.UserService;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepoDao userRepoDao;

    @Autowired
    private RestTemplate restTemplate;




    @Override
    public User createUser(User user) {
        //genrate unique id
//        String randomUserId = UUID.randomUUID().toString();
//          user.setUserId(Integer.parseInt(randomUserId));
//

        return userRepoDao.save(user);
    }

    @Override
    public List<User> getALlUser() {

        List<User> all = this.userRepoDao.findAll();


        return all;
    }

    @Override
    public User getSIngleUSer(int userId) {
        User singleuser = this.userRepoDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found in server !!" + userId));
       //fetch rating of the above user from rating service
        //http://localhost:8083/rating/user/1

        Rating[] ratingofuser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+singleuser.getUserId() , Rating[].class);

        List<Rating> list = Arrays.stream(ratingofuser).toList();

        //kis hotel ka rating
        List<Rating> ratinglist = list.stream().map(rating -> {
            //api call to hotel service get hotel service
         //http://localhost:8082/hotel/s/1

            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/s/" + rating.getHotelId(), Hotel.class);
            //set hotel to rating
             rating.setHotel(hotel);
            //return rating

            return rating;
        }).collect(Collectors.toList());


        singleuser.setRatings(ratinglist);
        return singleuser;
    }


}
