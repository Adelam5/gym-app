package gymapp.gymapp.Services;

import gymapp.gymapp.Models.Employee;
import gymapp.gymapp.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee findById(int id){
        return employeeRepository.findById(id).get();
    }

    public void deleteById(int id){
        employeeRepository.deleteById(id);
    }
}
