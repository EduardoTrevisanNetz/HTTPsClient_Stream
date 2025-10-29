package repository;

import java.io.IOException;
import java.util.List;
import java.io.File;
import service.Requests;
import config.RequestConfig;
import model.APIResponse;
import model.User;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiUserRepository implements UserRepository {
    private static final Requests requests = new Requests();

    public List<User> getAllUsers() throws IOException, InterruptedException {
        String path = requests.request(RequestConfig.SAVE_JSON);
        return new ObjectMapper().readValue(new File(path), APIResponse.class).getResults();
    }
}
