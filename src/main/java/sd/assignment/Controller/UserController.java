package sd.assignment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.assignment.Service.DTO.LoginDTO;
import sd.assignment.Service.DTO.RegisterDTO;
import sd.assignment.Service.DTO.ResponseDTO;
import sd.assignment.Service.DTO.UserDTO;
import sd.assignment.Service.UserService;
import sd.assignment.Service.Utils.Severity;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        try {
            userService.register(registerDTO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage(), Severity.FAILURE));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("User registered!", Severity.SUCCESS));
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        UserDTO user;
        try {
            user = userService.login(loginDTO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage(), Severity.FAILURE));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("User registered!", Severity.SUCCESS, user));
    }
}
