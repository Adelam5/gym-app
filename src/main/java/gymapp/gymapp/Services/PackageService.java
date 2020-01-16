package gymapp.gymapp.Services;

import gymapp.gymapp.Models.Package;
import gymapp.gymapp.Repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public List<Package> findAll(){
        return packageRepository.findAll();
    }

    public void save(Package apackage){
        packageRepository.save(apackage);
    }

    public Package findById(int id){
        return packageRepository.findById(id).get();
    }

    public void deleteById(int id){
        packageRepository.deleteById(id);
    }
}
