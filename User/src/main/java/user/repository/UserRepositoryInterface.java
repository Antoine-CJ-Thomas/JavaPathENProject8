package user.repository;

import user.model.User;

import java.util.List;

/**
 * This interface allows to implement user repository methods
 */
public interface UserRepositoryInterface {

    /**
     * Add an User in the User list
     * @param user : data of the User to add to the User list
     */
    public void addUser(User user);

    /**
     * Get an User in the User list
     * @param username : Name of the User to find in the User list
     * @return The User that was found
     */
    public User getUser(String username);

    /**
     * Get the User List
     * @return The User List
     */
    public List<User> getAllUser();
}