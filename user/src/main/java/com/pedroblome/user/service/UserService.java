// package com.pedroblome.user.service;

// import java.util.List;
// import java.util.Optional;

// import com.pedroblome.user.model.User;
// import com.pedroblome.user.repository.UserRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class UserService extends JpaRepos{
//     @Autowired
//     private UserRepository userRepository;

//     public List<User> getAll() {
//         List<User> userList = userRepository.findAll();
//         return userList;

//     }
//     public User searchById(Long id){
//         Optional<User> user = userRepository.findById(id);
//         return user.get();
        
//     }
//     public void deleteById(Long id){
//         userRepository.deleteById(id);
//     }
//     // public void createUser(User user) {
//     //     if (user == null || user.getId() == null) {
//     //         throw new ResourceNotFoundException("Empty", "Missing Data Exception");
//     //     } else {
//     //         userRepository.save(user);
//     //     }
//     // }
    
    
   

// }
