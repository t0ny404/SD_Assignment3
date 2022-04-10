package sd.assignment.Service.Mappers;

import sd.assignment.Model.Admin;
import sd.assignment.Model.Customer;
import sd.assignment.Model.User;
import sd.assignment.Service.DTO.RegisterDTO;
import sd.assignment.Service.DTO.UserDTO;
import sd.assignment.Service.Utils.Encryptioner;

public class UserMapper {

    public Customer convertFromDTO(RegisterDTO registerDTO) {
        Customer customer = new Customer();
        customer.setEmail(registerDTO.getEmail());
        customer.setName(registerDTO.getName());
        customer.setAge(Integer.parseInt(registerDTO.getAge()));

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(Encryptioner.encrypt(registerDTO.getPassword()));
        user.setType(false);

        customer.setUser(user);

        return customer;
    }

    public UserDTO convertToDTO(Admin admin) {
        return new UserDTO(admin.getId(), admin.getName(), "Admin", admin.getRestaurant() != null ? admin.getRestaurant().getId() : null);
    }

    public UserDTO convertToDTO(Customer customer) {
        return new UserDTO(customer.getId(), customer.getName(), "Customer", null);
    }
}
