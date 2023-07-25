package com.snowball.board.domain.user.controller;

import com.snowball.board.common.exception.message.ExceptionMessage;
import com.snowball.board.config.UserPrincipleDto;
import com.snowball.board.domain.user.dto.GetInfoResponse;
import com.snowball.board.domain.user.model.User;
import com.snowball.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/user/edit")
    public String showUpdateForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipleDto userPrincipleDto = (UserPrincipleDto) authentication.getPrincipal();
        Long userId = userPrincipleDto.getId();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(ExceptionMessage.USER_NOT_FOUND.message()));
        GetInfoResponse getInfoResponse = GetInfoResponse.builder()
            .userName(user.getUsername())
            .nickName(user.getNickName())
            .email(user.getEmail())
            .userRole(user.getUserRole())
            .createdAt(user.getCreatedAt())
            .build();
        model.addAttribute("user", getInfoResponse);
        return "updateUser";
    }
}
