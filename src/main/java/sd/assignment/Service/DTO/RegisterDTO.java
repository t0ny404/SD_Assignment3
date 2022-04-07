package sd.assignment.Service.DTO;

public class RegisterDTO {

    private String username;
    private String password;
    private String checkpswd;
    private String name;
    private String email;
    private String age;

    public RegisterDTO(String username, String password, String checkpswd, String name, String email, String age) {
        this.username = username;
        this.password = password;
        this.checkpswd = checkpswd;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCheckpswd() {
        return checkpswd;
    }

    public void setCheckpswd(String checkpswd) {
        this.checkpswd = checkpswd;
    }
}
