package com.example.zzan.room.controller;

import com.example.zzan.global.dto.ResponseDto;
import com.example.zzan.global.security.UserDetailsImpl;
import com.example.zzan.room.dto.RoomRequestDto;
import com.example.zzan.room.dto.RoomResponseDto;
import com.example.zzan.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/main")
    public ResponseDto<List<RoomResponseDto>> getRooms(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return roomService.getRooms(pageable);
    }

//    @GetMapping("/room/chat/{roomId}")
//    public ResponseEntity<ResponseDto> getRoom() {
//    }

    @PostMapping(value = "/room", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseDto<RoomResponseDto> createRoom(@RequestPart RoomRequestDto roomRequestDto,
                                                   @RequestPart(value = "image", required = false) MultipartFile image,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return roomService.createRoom(roomRequestDto, image, userDetails.getUser());
    }

    @PutMapping(value = "/room/{roomId}"
//            , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public ResponseDto<RoomResponseDto> updateRoom(@PathVariable Long roomId,
                                                   @RequestPart RoomRequestDto roomRequestDto,
//                                                   @RequestPart(value = "image", required = false) MultipartFile image,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return roomService.updateRoom(roomId, roomRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseDto<RoomResponseDto> deleteRoom(@PathVariable Long roomId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return roomService.deleteRoom(roomId, userDetails.getUser());
    }
}
