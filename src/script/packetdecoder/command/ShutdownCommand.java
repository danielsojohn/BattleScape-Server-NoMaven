package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.world.JavaCord;
import com.palidino.setting.DiscordChannel;
import com.palidino.setting.SqlUserRank;
import com.palidino.io.Logger;
import lombok.var;

public class ShutdownCommand implements Command {
    @Override
    public String getExample() {
        return "minutes";
    }

    @Override
    public boolean canUse(Player player) {
        return player.isUsergroup(SqlUserRank.SENIOR_MODERATOR) || player.getRights() == Player.RIGHTS_ADMIN
                || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER);
    }

    @Override
    public void execute(Player player, String message) {
        var minutes = Integer.parseInt(message);
        player.getWorld().startShutdown(minutes);
        Logger.println(player.getUsername() + " shut the server down with a countdown of " + minutes + " minutes.");
        JavaCord.sendMessage(DiscordChannel.MODERATION,
                player.getUsername() + " shut the server down with a countdown of " + minutes + " minutes.");
    }
}
