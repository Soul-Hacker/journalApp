package net.soulhacker.journalApp.service;

import net.soulhacker.journalApp.entity.User;
import net.soulhacker.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepository.findByUserName(username);
        if(user!=null)
        {
           UserDetails userdetails= org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])).build();
           return userdetails;
        }
      throw new UsernameNotFoundException("User not funnd with username: "+ username);
    }
}
