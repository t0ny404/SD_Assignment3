package sd.assignment.Service.DTO;

import sd.assignment.Service.Utils.Severity;

public class ResponseDTO {

    private String message;
    private Severity severity;
    private String session;

    public ResponseDTO(String message, Severity severity) {
        this.message = message;
        this.severity = severity;
    }

    public ResponseDTO(String message, Severity severity, String session) {
        this.message = message;
        this.severity = severity;
        this.session = session;
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
}
