package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.*;
import com.kennybowen.taskmanager.application.dtos.mapper.UserMapper;
import com.kennybowen.taskmanager.application.dtos.requests.LoginRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.RegisterUserRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.application.dtos.responses.PagedResult;
import com.kennybowen.taskmanager.application.dtos.responses.TokenResponseDto;
import com.kennybowen.taskmanager.application.dtos.responses.UserResponseDto;
import com.kennybowen.taskmanager.application.exceptions.BadRequestException;
import com.kennybowen.taskmanager.application.exceptions.NotFoundExceptionWithEmail;
import com.kennybowen.taskmanager.domain.entities.User;
import com.kennybowen.taskmanager.infrastructure.persistences.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository _userRepository;
    private final UserMapper userMapper;
    private final OtpService otpService;
    private final PasswordService passwordService;
    private final JwtService jwtService;
    private final ClaimService claimService;
    private final HashService hashService;

    @Override
    public ApiResponse<UserResponseDto> registerUser(RegisterUserRequestDto requestDto) {
        var email = requestDto.email();
        var password = requestDto.password();
        ApiResponse<UserResponseDto> response;
        User user;
        var emailExist = _userRepository.findByEmail(email);

        if (emailExist.isPresent())
        {
            throw new BadRequestException("Email already exist");
        }

        // generate a salt or hashPassword
        var passwordHash = passwordService.hashPassword(password);
        user = userMapper.toUser(requestDto);
        user.setIsActive(false);
        user.setPassword(passwordHash);
        user.setIsNewUser(true);

        user.setOtp(otpService.generateOtp());
        user.setOtpExpiry(otpService.getExpiryTime());

        User savedUser = _userRepository.save(user);

        // update the audit table

        // return a response
        response = new ApiResponse<>(
                true,
                "New user registered successfully",
                null,
                null,
                savedUser.getId()
        );

        return response;
    }

    @Override
    public TokenResponseDto loginUser(LoginRequestDto requestDto) {
        TokenResponseDto response;
        var email = requestDto.email();
        var password = requestDto.password();
        Long userId;
        var isMatch = false;

        var user = _userRepository.findByEmail(email).
                orElseThrow(() -> new NotFoundExceptionWithEmail("User not found, Please sign up or register."));

        userId = user.getId();

        log.info("Is user active {} , with email {}", user.getIsActive(), user.getEmail());
        System.out.println("Is user active " + user.getIsActive() + ", email = " + user.getEmail());

        if(!user.getIsActive()){
            if(user.getIsNewUser()) {
                throw new BadRequestException("Account not verified, Please check your email for verification link.");
            }

            log.info("Email {} has been deactivated.", user.getEmail());
            throw new BadRequestException("User has been de-activated.");
        }

        // confirm is password match
        var hashPassword = user.getPassword();
        if (!hashPassword.isEmpty()) {
            isMatch = passwordService.verifyPassword(password, hashPassword);
        }

        System.out.println("is login password match " + isMatch);
        if(!isMatch) {
            System.out.println("Invalid login password");
            log.info("Invalid login password");
            throw new BadRequestException("Invalid login password");
        }

        // call the audit service

        // generate an access token by user id
        //var token = generateAccessToken(userId);
        //user.setRegistrationToken(token);
        // update the token column for user

        // generate a new refreshToken by userId

        System.out.println("User id = " + userId);
        // call the audit service for successfulLogin

        response = new TokenResponseDto(
                userId.intValue(),
                true,
                "Login successfully",
                null,
                "",
                ""
        );

        return response;

    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return userMapper.toListDto(
                _userRepository.findAll()
        );
    }

    @Override
    public PagedResult<UserResponseDto> getAllUser(Pageable pageable) {
        Page<User> page = _userRepository.findAll(pageable);

        return new PagedResult<>(
                page.getContent()
                        .stream()
                        .map(userMapper::toDto)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext(),
                page.hasPrevious()
        );
    }

    private String generateAccessToken(Long userId) {

        var user = _userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        var claims = claimService.getUserClaims(user);

        return jwtService.generateAccessToken(
                user,
                claims
        );
    }

    private TokenResponseDto generateNewRefreshToken(Long userId) {

        var user = _userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return new TokenResponseDto(
                    null,
                    false,
                    "User not found",
                    null,
                    "",
                    ""
            );
        }

        String refreshToken =
                jwtService.generateRefreshToken();

        String hashedRefreshToken =
                hashRefreshToken(refreshToken);

        user.setRefreshToken(
                hashedRefreshToken
        );

        user.setRefreshTokenExpiryTime(
                LocalDateTime.now()
                        .plusDays(7)
        );

        _userRepository.save(user);

        return new TokenResponseDto(
                user.getId().intValue(),
                true,
                "Refresh token generated",
                null,
                "",
                refreshToken
        );
    }

    private String hashRefreshToken(String refreshToken) {
        return hashService.sha256(refreshToken);
    }

}
