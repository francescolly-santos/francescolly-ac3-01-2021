package br.bandtec.com.projetofrancescolly.repositorio;

import br.bandtec.com.projetofrancescolly.dominio.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
}
