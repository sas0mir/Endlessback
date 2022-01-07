package com.example.Endlessback.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        //return List.of(new Player(1L,"Sas", "sas_mir@icloud.com", 1.0, new String[]{"Endless"}, LocalDate.of(2021, Month.SEPTEMBER, 15), "Sas_avatar1", 10L));
        return playerRepository.findAll();
    }

    public void addNewPlayer(Player player) {
        Optional<Player> playerOptional = playerRepository.findPlayerByEmail(player.getEmail());
        if(playerOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        playerRepository.save(player);
        System.out.println("SAVED-Player->" + player);
    }

    public void deletePlayer(Long playerId) {
        boolean exists = playerRepository.existsById(playerId);
        if (!exists) {
            throw new IllegalStateException("Player with id " + playerId + " does not exists");
        }
        playerRepository.deleteById(playerId);
    }

    @Transactional
    public void updatePlayer(long playerId, String name, String email) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new IllegalStateException("Player with id " + playerId + " does not exists"));
        if (name != null && name.length() > 0 && !Objects.equals(player.getName(), name)) {
            player.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(player.getEmail(), email)) {
            Optional<Player> playerOptional = playerRepository.findPlayerByEmail(email);
            if (playerOptional.isPresent()) {
                throw new IllegalStateException("EMAIL TAKEN");
            }
            player.setEmail(email);
        }
    }
}
