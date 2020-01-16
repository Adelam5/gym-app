package gymapp.gymapp.Repositories;

import gymapp.gymapp.Models.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {
}
