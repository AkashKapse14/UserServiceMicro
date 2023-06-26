package com.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private int ratingId;
    private String  userId;
    private String  hotelId;
    private int rating;
    private String feedback;

    private Hotel hotel;
}
