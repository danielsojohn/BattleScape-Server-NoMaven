package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class FKickCommand implements Command {
    @Override
    public String getExample() {
        return "username";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String username) {
        var player2 = player.getWorld().getPlayerByUsername(username);
        if (player2 == null) {
            player.getGameEncoder().sendMessage("Unable to find user " + username + ".");
            return;
        }
        player2.unlock();
        player2.getGameEncoder().sendMessage(player.getUsername() + " has kicked you.");
        player2.getGameEncoder().sendLogout();
        player2.setVisible(false);
        player.getGameEncoder().sendMessage(username + " has been kicked.");
    }
}
