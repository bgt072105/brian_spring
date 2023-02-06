package com.nighthawk.team_backend.mvc.database.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
This class has an instance of Java Persistence API (JPA)
-- @Autowired annotation. Allows Spring to resolve and inject collaborating beans into our bean.
-- Spring Data JPA will generate a proxy instance
-- Below are some CRUD methods that we can use with our database
*/
@Service
@Transactional
public class AccountDetailsService implements UserDetailsService { // "implements" ties ModelRepo to Spring Security
    // Encapsulate many object into a single Bean (Account, Roles, and Scrum)
    @Autowired // Inject AccountJpaRepository
    private AccountJpaRepository AccountJpaRepository;
    @Autowired // Inject RoleJpaRepository
    private AccountRoleJpaRepository AccountRoleJpaRepository;
    @Autowired // Inject PasswordEncoder
    private PasswordEncoder passwordEncoder;

    /*
     * UserDetailsService Overrides and maps Account & Roles POJO into Spring Security
     */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Account Account = AccountJpaRepository.findByEmail(email); // setting variable user equal to the method finding the
                                                          // username in the database
        if (Account == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Account.getRoles().forEach(role -> { // loop through roles
            authorities.add(new SimpleGrantedAuthority(role.getName())); // create a SimpleGrantedAuthority by passed in
                                                                         // role, adding it all to the authorities list,
                                                                         // list of roles gets past in for spring
                                                                         // security
        });
        return new org.springframework.security.core.userdetails.User(Account.getEmail(), Account.getPassword(), authorities);
    }

    /* Account Section */

    public List<Account> listAll() {
        return AccountJpaRepository.findAllByOrderByNameAsc();
    }

    // custom query to find match to name or email
    public List<Account> list(String name, String email) {
        return AccountJpaRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(name, email);
    }

    // custom query to find anything containing term in name or email ignoring case
    public List<Account> listLike(String term) {
        return AccountJpaRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);
    }

    // custom query to find anything containing term in name or email ignoring case
    public List<Account> listLikeNative(String term) {
        String like_term = String.format("%%%s%%", term); // Like required % rappers
        return AccountJpaRepository.findByLikeTermNative(like_term);
    }

    public void save(Account Account) {
        Account.setPassword(passwordEncoder.encode(Account.getPassword()));
        AccountJpaRepository.save(Account);
    }

    public Account get(long id) {
        return (AccountJpaRepository.findById(id).isPresent())
                ? AccountJpaRepository.findById(id).get()
                : null;
    }

    public Account getByEmail(String email) {
        return (AccountJpaRepository.findByEmail(email));
    }

    public void delete(long id) {
        AccountJpaRepository.deleteById(id);
    }

    public void defaults(String password, String roleName) {
        for (Account Account : listAll()) {
            if (Account.getPassword() == null || Account.getPassword().isEmpty() || Account.getPassword().isBlank()) {
                Account.setPassword(passwordEncoder.encode(password));
            }
            if (Account.getRoles().isEmpty()) {
                AccountRole role = AccountRoleJpaRepository.findByName(roleName);
                if (role != null) { // verify role
                    Account.getRoles().add(role);
                }
            }
        }
    }

    /* Roles Section */

    public void saveRole(AccountRole role) {
        AccountRole roleObj = AccountRoleJpaRepository.findByName(role.getName());
        if (roleObj == null) { // only add if it is not found
            AccountRoleJpaRepository.save(role);
        }
    }

    public List<AccountRole> listAllRoles() {
        return AccountRoleJpaRepository.findAll();
    }

    public AccountRole findRole(String roleName) {
        return AccountRoleJpaRepository.findByName(roleName);
    }

    public void addRoleToAccount(String email, String roleName) { // by passing in the two strings you are giving the user
                                                               // that certain role
        Account Account = AccountJpaRepository.findByEmail(email);
        if (Account != null) { // verify Account
            AccountRole role = AccountRoleJpaRepository.findByName(roleName);
            if (role != null) { // verify role
                boolean addRole = true;
                for (AccountRole roleObj : Account.getRoles()) { // only add if user is missing role
                    if (roleObj.getName().equals(roleName)) {
                        addRole = false;
                        break;
                    }
                }
                if (addRole)
                    Account.getRoles().add(role); // everything is valid for adding role
            }
        }
    }

}