package sd.assignment.Service.DTO;

import sd.assignment.Service.Utils.Severity;

public class ResponseDTO {

    private String message;
    private Severity severity;
    private UserDTO userDTO;

    public ResponseDTO(String message, Severity severity) {
        this.message = message;
        this.severity = severity;
    }

    public ResponseDTO(String message, Severity severity, UserDTO userDTO) {
        this.message = message;
        this.severity = severity;
        this.userDTO = userDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
