package fer.rassus.lab3.temperaturemicroservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingsRepository extends JpaRepository<Readings, Long> {
}
