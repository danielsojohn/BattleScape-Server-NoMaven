package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class TeleToMeCommand implements Command {
    @Override
    public String getExample() {
        return "username";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_MOD || player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String username) {
        var player2 = player.getWorld().getPlayerByUsername(username);
        if (player2 == null) {
            player.getGameEncoder().sendMessage("Unable to find user " + username + ".");
            return;
        } else if (player == player2) {
            player.getGameEncoder().sendMessage("You can't teleport to yourself.");
            return;
        } else if (player.getController().isInstanced()) {
            player.getGameEncoder().sendMessage("You can't teleport while in an instance.");
            return;
        } else if ((player.getController().inWilderness() || player.getController().inPvPWorld())
                && !(player.getRights() == Player.RIGHTS_ADMIN)) {
            player.getGameEncoder().sendMessage("You can't teleport while in the wilderness.");
            return;
        } else if (player2.getController().isInstanced()) {
            player.getGameEncoder().sendMessage(username + " is in an instance located at: " + player.getX() + ", "
                    + player.getY() + ", " + player.getHeight() + ".");
            return;
        }
        player2.getMovement().teleport(player);
    }
}
