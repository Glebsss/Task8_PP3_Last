import Task_8_Solution.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Objects;


public class Requests {

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders httpHeaders = new HttpHeaders();
    private static final String URL = "http://94.198.50.185:7081/api/users";

    public Requests() {
        String sessionID = getAllUsers();
        httpHeaders.set("cookie", sessionID);
    }

    public String getAllUsers() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
        return String.join("|", Objects.requireNonNull(responseEntity.getHeaders().get("set-cookie")));
    }

    public String saveUser(Long id) {
        User user = new User(id, "James", "Brown", (byte) 23);
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        String s1 = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
        return s1;
    }

    public String updateUser(Long id) {
        User user = new User("Thomas", "Shelby", (byte) 34);
        user.setId(id);
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        String s2 = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody();
        return s2;
    }

    public String deleteUser(@PathVariable Long id) {
        HttpEntity<User> entity = new HttpEntity<>(httpHeaders);
        String s3 = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
//        new ResponseEntity<>(s3, HttpStatus.OK);
        return s3;
    }
}
