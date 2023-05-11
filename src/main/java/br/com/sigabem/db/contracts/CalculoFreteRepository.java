package br.com.sigabem.db.contracts;

import br.com.sigabem.db.entity.CalculoFrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoFreteRepository extends JpaRepository<CalculoFrete, Long> {
}
