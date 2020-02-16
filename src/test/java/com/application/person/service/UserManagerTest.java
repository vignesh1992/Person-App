package com.application.person.service;

import static com.application.person.model.Authorities.ROLE_USER;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.application.person.model.User;
import com.application.person.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagerTest {

    @Autowired
    UserRepository userRepository;

    private UserManager userManager;

    @Before
    public void setup() {
        userManager = new UserManager(userRepository);
    }

    @Transactional
    @Test
    public void createUser_createsUserInRepository() {
        User user = User.builder().userName("user1").password("pwd")
                .roles(Arrays.asList(ROLE_USER)).build();

       assertEquals(userManager.createUser(user).getUserName(), user.getUserName());
        userRepository.flush();
    }

    @Transactional
    @Test
    public void getAllUsers_returnsAllUsers() {
        userRepository.save(User.builder().userName("user1").password("pwd")
                .roles(Arrays.asList(ROLE_USER)).build().toUserEntity());

        List<User> userList = userManager.getAllUsers();

        assertEquals(userList.size(), 1);
    }

}