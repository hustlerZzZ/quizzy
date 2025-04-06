package com.hustler.quizzy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hustler.quizzy.repository.UserRepository;
import org.springframework.security.core.userdetails.*;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.hustler.quizzy.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new CustomUserDetails(user);
    }
}
