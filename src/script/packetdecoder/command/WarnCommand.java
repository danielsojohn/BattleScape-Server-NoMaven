package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.world.JavaCord;
import com.palidino.setting.DiscordChannel;
import com.palidino.setting.SqlUserRank;
import lombok.var;

public class WarnCommand implements Command {
    @Override
    public String getExample() {
        return "username";
    }

    @Override
    public boolean canUse(Player player) {
        return player.isUsergroup(SqlUserRank.TRIAL_MODERATOR) || player.getRights() == Player.RIGHTS_MOD
                || player.getRights() == Player.RIGHTS_ADMIN || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER);
    }

    @Override
    public void execute(Player player, String username) {
        var player2 = player.getWorld().getPlayerByUsername(username);
        if (player2 == null) {
            player.getGameEncoder().sendMessage("Unable to find user " + username + ".");
            return;
        }
        player2.getGameEncoder().sendMessage("<col=FF0000>[WARNING]: You have been given an official warning by "
                + player.getUsername() + ". Further instances will result in action being taken.");
        player.getGameEncoder().sendMessage(username + " has been warned.");
        if (!Main.isLocal()) {
            JavaCord.sendMessage(DiscordChannel.MODERATION, player.getUsername() + " has warned " + username);
        } else {
            JavaCord.sendMessage(DiscordChannel.LOCAL, player.getUsername() + "has warned " + username);
        }


    }
}
