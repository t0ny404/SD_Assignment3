package sd.assignment.Service.Utils;

public class InvalidLoginException extends Exception {
    public InvalidLoginException() {
        super("Wrong username/password combination");
    }
}
