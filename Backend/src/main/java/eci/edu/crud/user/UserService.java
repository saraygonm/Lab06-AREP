package eci.edu.crud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String rawPassword) throws NoSuchAlgorithmException {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        String hashedPassword = PasswordUtils.hashPassword(rawPassword);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String rawPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && PasswordUtils.checkPassword(rawPassword, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
}