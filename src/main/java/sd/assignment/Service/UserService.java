package sd.assignment.Service;

import sd.assignment.Model.Customer;
import sd.assignment.Model.User;
import sd.assignment.Model.Utils.NoUser;
import sd.assignment.Model.Utils.UserI;
import sd.assignment.Repository.AdminRepository;
import sd.assignment.Repository.CustomerRepository;
import sd.assignment.Repository.UserRepository;
import sd.assignment.Service.DTO.RegisterDTO;
import sd.assignment.Service.DTO.UserDTO;
import sd.assignment.Service.Mappers.UserAdapter;
import sd.assignment.Service.Mappers.CustomerMapper;
import sd.assignment.Service.Utils.Encoder;
import sd.assignment.Service.Utils.InvalidRegisterException;
import sd.assignment.Service.Utils.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type User service, handling all the logic for the all types of users (admin and customer).
 */
@Service
public class UserService implements UserDetailsService {

    /**
     * The constant ENCODER, which encodes and check passwords.
     */
    public static final Encoder ENCODER = new Encoder();

    private final Logger logger = LogManager.getLogger();
    private final UserValidator userValidator = new UserValidator();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;


    /**
     * Validates and registers and user with a unique username (only a customer can be registered), and adds it
     * in the user and customer tables from the database
     *
     * @param registerDTO the registerDTO representing the desired user to add
     * @throws InvalidRegisterException the invalid register exception
     */
    public void register(RegisterDTO registerDTO) throws InvalidRegisterException {
        userValidator.validateRegister(registerDTO);
        Customer customer = new CustomerMapper().convertFromDTO(registerDTO);

        logger.info("A new customer registered {}", customer.getName());

        if (userRepository.findByUsername(customer.getUser().getUsername()).isPresent()) {
            logger.error("Username {} is already taken", customer.getUser().getUsername());
            throw new InvalidRegisterException("User already exists!");
        }

        userRepository.save(customer.getUser());
        customerRepository.save(customer);
    }

    /**
     * Gets current logged in user from the Spring Security and checks it is stored in the database,
     * then converts it in UserDTO using the Adapter Design Pattern
     *
     * @param user the user from Spring security
     * @return the current user user UserDTO
     */
    public UserDTO getCurrentUser(org.springframework.security.core.userdetails.User user) {
        if (user == null) {
            logger.warn("No current user");
            return new UserAdapter(new NoUser(), "").convertToDTO();
        }

        Optional<User> currentUser = userRepository.findByUsername(user.getUsername());

        UserI userI;
        String type = "Customer";
        if (currentUser.isEmpty() || currentUser.get().getType() == null) {
            userI = new NoUser();
            logger.warn("No current user");
        } else if (currentUser.get().getType()) {
            userI = adminRepository.findByUser(currentUser.get());
            type = "Admin";
        }
        else userI = customerRepository.findByUser(currentUser.get());

        logger.info("Current user: {}", type);

        return new UserAdapter(userI, type).convertToDTO();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("");

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(),
                AuthorityUtils.createAuthorityList(user.get().getType() ? "Admin" : "Customer"));
    }
}
