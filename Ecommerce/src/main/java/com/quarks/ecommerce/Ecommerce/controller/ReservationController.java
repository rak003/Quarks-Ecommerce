package com.quarks.ecommerce.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.quarks.ecommerce.Ecommerce.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/reservation")
@Tag(name = "Reservation", description = "API for managing reservations.")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Operation(summary = "Cancel a Reservation",
               description = "Cancel a reservation for a particular User and Item.")
    @PutMapping("/cancel")
    public ResponseEntity<String> cancelReservation(
            @Parameter(description = "User's ID who made the reservation.")
            @RequestParam Long userId,

            @Parameter(description = "ID of the Item for which reservation was made.")
            @RequestParam Long itemId,

            @Parameter(description = "Quantity to cancel.")
            @RequestParam Integer qnt,

            @Parameter(description = "If true, cancel all reservations for this Item by this User.")
            @RequestParam Boolean isAll) {

        if (qnt <= 0 && !isAll) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Quantity must be greater than zero if isAll is false.");
        }
        
        reservationService.cancelReservation(userId, itemId, qnt, isAll);
        return new ResponseEntity<>("Reservation Canceled", HttpStatus.OK);
    }
}

