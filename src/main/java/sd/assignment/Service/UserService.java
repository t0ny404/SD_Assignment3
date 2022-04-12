package sd.assignment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import sd.assignment.Model.Customer;
import sd.assignment.Model.User;
import sd.assignment.Model.Utils.NoUser;
import sd.assignment.Model.Utils.UserI;
import sd.assignment.Repository.AdminRepository;
import sd.assignment.Repository.CustomerRepository;
import sd.assignment.Repository.UserRepository;
import sd.assignment.Service.DTO.LoginDTO;
import sd.assignment.Service.DTO.RegisterDTO;
import sd.assignment.Service.DTO.UserDTO;
import sd.assignment.Service.Mappers.UserAdapter;
import sd.assignment.Service.Mappers.CustomerMapper;
import sd.assignment.Service.Utils.Encryptioner;
import sd.assignment.Service.Utils.InvalidLoginException;
import sd.assignment.Service.Utils.InvalidRegisterException;
import sd.assignment.Service.Utils.UserValidator;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;

    private final UserValidator userValidator = new UserValidator();

    public void register(RegisterDTO registerDTO) throws InvalidRegisterException {
        userValidator.validateRegister(registerDTO);
        Customer customer = new CustomerMapper().convertFromDTO(registerDTO);

        if (userRepository.findByUsername(customer.getUser().getUsername()).isPresent())
            throw new InvalidRegisterException("User already exists!");

        userRepository.save(customer.getUser());
        customerRepository.save(customer);
    }

    public UserDTO login(LoginDTO loginDTO) throws InvalidLoginException {
        Optional<User> opt = userRepository.findByUsername(loginDTO.getUsername());
        if (opt.isEmpty()) {
            throw new InvalidLoginException();
        }
        User user = opt.get();

        if (!Encryptioner.check(loginDTO.getPassword(), user.getPassword())) {
            throw new InvalidLoginException();
        }

        UserI userI;
        String type = "Customer";
        if (user.getType() == null)
            userI = new NoUser();
        else if (user.getType()) {
            userI = adminRepository.findByUser(user);
            type = "Admin";
        }
        else userI = customerRepository.findByUser(user);

        return new UserAdapter(userI, type).convertToDTO();
    }
}
