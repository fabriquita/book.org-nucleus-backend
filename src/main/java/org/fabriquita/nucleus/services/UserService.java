package org.fabriquita.nucleus.services;

import java.util.List;

import org.fabriquita.nucleus.models.Group;
import org.fabriquita.nucleus.models.Role;
import org.fabriquita.nucleus.models.User;
import org.fabriquita.nucleus.repositories.GroupRepository;
import org.fabriquita.nucleus.repositories.RoleRepository;
import org.fabriquita.nucleus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<User> list() {
        return Lists.newLinkedList(userRepository.findAll());
    }

    public User get(Long id) {
        return userRepository.findOne(id);
    }

    public User add(String name, String lastName, String userName, String password, Long groupId, Long roleId, String email) {
        User user = new User();
        Group group = null;
        Role role = null;
        if (groupId != null) {
            group = groupRepository.findOne(groupId);
        }
        if (roleId != null) {
            role = roleRepository.findOne(roleId);
        }
        user.setName(name);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setGroup(group);
        user.setRole(role);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User update(Long id, String name, Long groupId, Long roleId) {
        User user = userRepository.findOne(id);
        Group group = null;
        Role role = null;
        if (name != null) {
            user.setName(name);
        }
        if(groupId != null){
            group = groupRepository.findOne(groupId);
        }
        if(group != null){
            user.setGroup(group);
        }
        if (roleId != null) {
            role = roleRepository.findOne(roleId);
        }
        if (role != null) {
            user.setRole(role);
        }
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = userRepository.findOne(id);
        user.setArchived(true);
        userRepository.save(user);
    }

    public User getLogin(String name, String password) {
        User user = userRepository.findByNameAndPassword(name, password);
        if(user != null) {
            user.setLastLogin();
            userRepository.save(user);
        }
        return user;
    }

}
