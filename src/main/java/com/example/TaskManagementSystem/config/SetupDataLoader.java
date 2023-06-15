package com.example.TaskManagementSystem.config;

import com.example.TaskManagementSystem.model.Privilege;
import com.example.TaskManagementSystem.model.Role;
import com.example.TaskManagementSystem.model.User;
import com.example.TaskManagementSystem.repository.PrivilegeRepository;
import com.example.TaskManagementSystem.repository.RoleRepository;
import com.example.TaskManagementSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean areThePrivilegesAlreadySet = false;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (areThePrivilegesAlreadySet)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
            user.setFirstName("Nicholas");
            user.setLastName("Viegas");
            user.setPassword(passwordEncoder.encode("lhv764231"));
            user.setEmail("nicholasrv@live.com");
            user.setRoles(Arrays.asList(adminRole));
            user.setEnabled(true);
                userRepository.save(user);

                    areThePrivilegesAlreadySet = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name){
        Privilege privilege = privilegeRepository.findByName(name);
        if(privilege == null){
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, List<Privilege> privileges){
        Role role = roleRepository.findByName(name);
        if(role == null){
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
