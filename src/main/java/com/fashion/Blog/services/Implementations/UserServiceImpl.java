package com.fashion.Blog.services.Implementations;

import com.fashion.Blog.dto.FashionDto;
import com.fashion.Blog.dto.UserResponseDto;
import com.fashion.Blog.dto.UserSignupDto;
import com.fashion.Blog.enums.Role;
import com.fashion.Blog.exceptions.AlreadyExistsException;
import com.fashion.Blog.exceptions.NotFoundException;
import com.fashion.Blog.exceptions.NotNullException;
import com.fashion.Blog.model.ApiMessage;
import com.fashion.Blog.model.User;
import com.fashion.Blog.repository.UserRepository;
import com.fashion.Blog.services.UserService;
import com.fashion.Blog.util.Infornation;
import com.fashion.Blog.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;
    //private final FashionDto fashionDto;

    @Override
    public boolean isEmailExist(String email) {
        boolean status;
        status = userRepository.existsByEmail(email);
        return status;
    }

    @Override
    public FashionDto signup(UserSignupDto userSignupDto) {
        // Check if the email already exists
        boolean emailExistStatus = isEmailExist(userSignupDto.getEmail());
        if (emailExistStatus) {
            return FashionDto.builder()
                    .responseCode(Infornation.ACCOUNT_ALREADY_EXIST_CODE)
                    .responseMessage(Infornation.ACCOUNT_ALREADY_EXIST_MESSAGE)
                    .build();
        }
        User newUser = User.builder()
                .firstName(userSignupDto.getFirstName())
                .lastName(userSignupDto.getLastName())
                .email(userSignupDto.getEmail())
                .password(userSignupDto.getPassword())
                .role(userSignupDto.getRole())
                .gender(userSignupDto.getGender())
                .build();
        // Validate the role
        Role role = userSignupDto.getRole();
        if (role == null || !isValidRole(role)) {
            return FashionDto.builder()
                    .responseCode("003")
                    .responseMessage("Invalid Roles")
                    .build();
        }
        userRepository.save(newUser);
        return FashionDto.builder()
                .responseCode("002")
                .responseMessage("ddd")
                //.userResponseDto(null)
                .build();

    }

    private boolean isValidRole(Role role) {
        return role.equals(Role.ADMIN) || role.equals(Role.AUTHORIZED_USER) || role.equals(Role.UNKNOWN_USER);
    }


    @Override
    public FashionDto login(String email, String password){
        boolean isAdminExistStatus = userRepository.existsByEmailAndPassword(email, password);

        if (isAdminExistStatus == false){
            return FashionDto.builder()
                    .responseCode("04")
                    .responseMessage("hjjhbhg")
                    .build();
        }

        User user = userRepository.findByEmailAndPassword(email, password);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        httpSession.setAttribute("userId", user.getId());
       // ApiMessage apiMessage = responseManager.success(userResponseDto);

        return FashionDto.builder()
                .responseCode("05")
                .responseMessage("Successfully Login ")
                .build();
    }
}