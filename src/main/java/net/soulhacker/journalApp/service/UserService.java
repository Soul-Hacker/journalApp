package net.soulhacker.journalApp.service;

import net.soulhacker.journalApp.entity.User;
import net.soulhacker.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();



    @Autowired
    private UserRepository userRepository;

    public User findByUserName(String UserName)
    {
        return userRepository.findByUserName(UserName);
    }
    public void deleteById(ObjectId id)
    {
         userRepository.deleteById(id);
    }
    public List<User> getAll()
    {
        return userRepository.findAll();
    }
    public Optional<User>findById(ObjectId id)
    {
        return userRepository.findById(id);
    }
    public void saveUser(User user)
    {
        userRepository.save(user);
    }
//    public void saveAdmin(User user)
public void saveAdmin(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList("USER", "ADMIN"));
    userRepository.save(user);
}
public boolean saveNewUser(User user) {
    try {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return true;
    } catch (Exception e) {
        return false;
    }
}

}
