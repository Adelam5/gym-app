package gymapp.gymapp.Services;

import gymapp.gymapp.Models.Position;
import gymapp.gymapp.Repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> findAll(){
        return positionRepository.findAll();
    }

    public void save(Position position){
        positionRepository.save(position);
    }

    public Position findById(int id){
        return positionRepository.findById(id).get();
    }

    public void deleteById(int id){
        positionRepository.deleteById(id);
    }
}
