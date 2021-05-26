package br.bandtec.com.projetofrancescolly.repositorio;

import br.bandtec.com.projetofrancescolly.dominio.PosicaoJogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosicaoJogadorRepository extends JpaRepository<PosicaoJogador, Integer> {
}
