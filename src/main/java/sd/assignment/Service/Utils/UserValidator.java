package sd.assignment.Service.Utils;

import sd.assignment.Service.DTO.RegisterDTO;

public class UserValidator {

    public void validateRegister(RegisterDTO registerDTO) throws InvalidRegisterException {
        if (registerDTO != null) {
            if (registerDTO.getPassword() == null || registerDTO.getCheckpswd() == null || registerDTO.getPassword().length() < 8)
                throw new InvalidRegisterException("Invalid password!");
            if (!registerDTO.getPassword().equals(registerDTO.getCheckpswd()))
                throw new InvalidRegisterException("Passwords do not match!");
            if (registerDTO.getUsername() == null || registerDTO.getUsername().equals(""))
                throw new InvalidRegisterException("Invalid username!");
            try {
                if (registerDTO.getAge() == null || Integer.parseInt(registerDTO.getAge()) < 18)
                    throw new InvalidRegisterException("Invalid age!");
            } catch (NumberFormatException e) {
                throw new InvalidRegisterException("Invalid age!");
            }
        }
    }
}
