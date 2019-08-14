package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.setting.SqlUserRank;
import com.palidino.util.Logger;
import lombok.var;

public class ShutdownCommand implements Command {
    @Override
    public String getExample() {
        return "minutes";
    }

    @Override
    public boolean canUse(Player player) {
        return player.isUsergroup(SqlUserRank.HEAD_MODERATOR) || player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        var minutes = Integer.parseInt(message);
        player.getWorld().startShutdown(minutes);
        Logger.println(player.getUsername() + " shut the server down with a countdown of " + minutes);
    }
}
