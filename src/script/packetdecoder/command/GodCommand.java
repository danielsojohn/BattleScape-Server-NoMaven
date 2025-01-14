package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class GodCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        player.getMovement().setEnergy(999999999);
        player.setHitpoints(999999999);
        player.getPrayer().setPoints(999999999);
        for (var i = 0; i <= 6; i++) {
            player.getSkills().setLevel(i, 999999999);
            player.getGameEncoder().sendSkillLevel(i);
        }
        player.getGameEncoder().sendMessage("You feel like a god..");
    }
}
