package com.quarks.ecommerce.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.quarks.ecommerce.Ecommerce.entities.Item;
import com.quarks.ecommerce.Ecommerce.entities.Reservation;
import com.quarks.ecommerce.Ecommerce.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/item")
@Tag(name = "Item", description = "API for managing items in inventory.")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Operation(summary = "Create a new Item",
               description = "Add a new Item to the inventory.")
    @PostMapping
    public Item create(@RequestBody Item item) {
        if (item == null ||
            item.getName() == null ||
            item.getTotalQty() <= 0) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Invalid Item details.");
        }
        return itemService.addNewItem(item.getName(), item.getTotalQty()); 
    }
    

    @Operation(summary = "Get all Items",
               description = "Retrieve a list of all Items.")
    @GetMapping
    public List<Item> getAllItem(){
        return itemService.getAllItem();
    }
    

    @Operation(summary = "Get available Items",
               description = "Retrieve a list of Items where availableQty > 0.")
    @GetMapping("/getAvailableItems")
    public List<Item> getAvailableItems(){
        return itemService.getAvailableItems();
    }
    

    @Operation(summary = "Reserve Item",
               description = "Reserve a specified qty of an Item for a User.")
    @PatchMapping("/{itemId}/reserve")
    public Reservation reserve(@PathVariable Long itemId,
                                   @Parameter(description = "User's ID who is reserving the Item.")
                                   @RequestParam Long userId,
                                   @Parameter(description = "Quantity to reserve.")
                                   @RequestParam Integer qty) {
        if (qty <= 0) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Quantity must be greater than zero.");
        }
        return itemService.reserveItem(itemId, userId, qty);
    }
}
