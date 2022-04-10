package sd.assignment.Model.Utils;

import sd.assignment.Model.Restaurant;

//Behavioral DP: NullObject
public class NoUser implements UserI {
    @Override
    public Integer getId() {
        return -1;
    }

    @Override
    public String getName() {
        return "User not found!";
    }

    @Override
    public Restaurant getRestaurant() {
        return null;
    }
}
