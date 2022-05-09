package sd.assignment.Service.Mappers;

import sd.assignment.Model.Utils.UserI;
import sd.assignment.Service.DTO.UserDTO;

//Structural DP: Adapter
public class UserAdapter implements Mapper<UserI, UserDTO> {

    private final String type;
    private final UserI userI;


    public UserAdapter(UserI userI, String type) {
        this.type = type;
        this.userI = userI;
    }


    public UserDTO convertToDTO() {
        return new UserDTO(userI.getId(),
                userI.getName(),
                type,
                userI.getRestaurant() != null ? userI.getRestaurant().getId() : null);

    }

    public UserI convertFromDTO(UserDTO userDTO) {
        return null;
    }
}
