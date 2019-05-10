package com.it.app.controller;


import com.it.app.dto.request.UserRegistrationRequestDto;
import com.it.app.dto.response.TokenResponseDto;
import com.it.app.model.Role;
import com.it.app.model.Student;
import com.it.app.model.User;
import com.it.app.service.RoleService;
import com.it.app.service.UserService;
import com.it.app.service.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * A Controller for authentication
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final UserService userService;
    private final RoleService roleService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    @GetMapping("/signIn")
    public TokenResponseDto authenticateUser(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenResponseDto(tokenService.generate(authentication));
    }

    @PostMapping("/refresh")
    public TokenResponseDto refreshToken(@RequestParam String token) {
        return new TokenResponseDto(tokenService.refresh(token));
    }

    @PostMapping("/signUp")
    public User registerUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        final Role role = roleService.findByName(userRegistrationRequestDto.getRole());
        User user = new User();
        if (userRegistrationRequestDto.getRole().equals("ROLE_STUDENT"))
            user = new Student();
        user.setUsername(userRegistrationRequestDto.getName());
        user.setPassword(encoder.encode(userRegistrationRequestDto.getPassword()));
        if (role != null && !userRegistrationRequestDto.getRole().equals("ROLE_MAKER")) {
            user.setRole(role);
        }
        return userService.save(user);
    }
}
