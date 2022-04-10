package sd.assignment.Service.Mappers;

import sd.assignment.Model.Customer;
import sd.assignment.Model.User;
import sd.assignment.Service.DTO.RegisterDTO;
import sd.assignment.Service.Utils.Encryptioner;


public class CustomerMapper implements Mapper<Customer, RegisterDTO> {

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

    public RegisterDTO convertToDTO() {
        return null;
    }
}
