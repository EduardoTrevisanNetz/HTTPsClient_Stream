package repository;

import model.User;

import java.io.IOException;
import java.util.List;

public interface UserRepository {
    public List<User> getAllUsers() throws IOException, InterruptedException;
}
