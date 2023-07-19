package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.RoomDto;
import com.djroche.labelleEtoile.entities.RoomType;
import com.djroche.labelleEtoile.services.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public String getAllRooms(Model model) {
        List<RoomDto> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "index";
    }

    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id, Model model) {
        RoomDto roomDto = roomService.getRoomById(id);
        model.addAttribute("room", roomDto);
        return "roomList";
    }

    @PostMapping("/")
    public String createRoom(@ModelAttribute("roomDto") RoomDto roomDto) {
        roomService.createRoom(roomDto);
        return "redirect:/rooms";
    }

    @GetMapping("/api/{roomId}/availability")
    @ResponseBody
    public boolean isRoomAvailable(@PathVariable Long roomId, @RequestParam("dateIn") String dateIn, @RequestParam("dateOut") String dateOut) {
        LocalDate dateInParsed = LocalDate.parse(dateIn);
        LocalDate dateOutParsed = LocalDate.parse(dateOut);
        return roomService.isRoomAvailable(roomId, dateInParsed, dateOutParsed);
    }

    @GetMapping("/{roomId}/ready")
    @ResponseBody
    public boolean isRoomReady(@PathVariable Long roomId) {
        return roomService.isRoomReady(roomId);
    }

    @GetMapping("/{roomId}/booked")
    @ResponseBody
    public boolean isRoomBooked(@PathVariable Long roomId) {
        return roomService.isRoomBooked(roomId);
    }

    @GetMapping("/{roomId}/price")
    @ResponseBody
    public int getRoomPrice(@PathVariable Long roomId) {
        return roomService.getRoomPrice(roomId);
    }

    @GetMapping("/numGuests")
    @ResponseBody
    public int getNumberOfGuests(@RequestParam("roomType") String roomType) {
        RoomType roomTypeEnum = RoomType.valueOf(roomType);
        return roomService.getNumberOfGuests(roomTypeEnum);
    }
}