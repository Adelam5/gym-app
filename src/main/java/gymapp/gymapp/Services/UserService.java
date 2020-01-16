package gymapp.gymapp.Services;

import gymapp.gymapp.Models.User;
import gymapp.gymapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User findById(int id){
        return userRepository.findById(id).get();
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }



}
