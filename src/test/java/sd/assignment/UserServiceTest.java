package sd.assignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;
import sd.assignment.Model.Customer;
import sd.assignment.Model.User;
import sd.assignment.Repository.AdminRepository;
import sd.assignment.Repository.CustomerRepository;
import sd.assignment.Repository.UserRepository;
import sd.assignment.Service.DTO.RegisterDTO;
import sd.assignment.Service.UserService;
import sd.assignment.Service.Utils.InvalidRegisterException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private final UserService userService = new UserService();

    private Customer customer;
    private User user;
    private RegisterDTO registerDTO;

    @Before
    public void setup() {
        registerDTO = new RegisterDTO("aa", "aaaaaaaa", "aaaaaaaa",
                "aa", "aa@gmail.com", String.valueOf(19));

        customer = new Customer();
        customer.setEmail("aa@gmail.com");
        customer.setName("aa");
        customer.setAge(19);

        user = new User();
        user.setUsername("aa");
        user.setPassword(UserService.ENCODER.encode("aaaaaaaa"));
        user.setType(false);

        customer.setUser(user);
    }

    @Test
    public void successfulRegister() {
        Mockito.doReturn(Optional.empty()).when(userRepository).findByUsername("aa");
        Mockito.doReturn(null).when(userRepository).save(user);
        Mockito.doReturn(null).when(customerRepository).save(customer);

        try {
            userService.register(registerDTO);
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = InvalidRegisterException.class)
    public void userAlreadyPresentRegister() throws InvalidRegisterException {
        Mockito.doReturn(Optional.of(user)).when(userRepository).findByUsername("aa");
        Mockito.doReturn(null).when(userRepository).save(user);
        Mockito.doReturn(null).when(customerRepository).save(customer);

        userService.register(registerDTO);
    }
}
