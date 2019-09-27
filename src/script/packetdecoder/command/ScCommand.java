package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.world.JavaCord;
import com.palidino.setting.DiscordChannel;
import com.palidino.setting.SqlUserRank;

public class ScCommand implements Command {
    @Override
    public String getExample() {
        return "message to staff only";
    }

    @Override
    public boolean canUse(Player player) {
        return player.isUsergroup(SqlUserRank.SUPPORT) || player.getRights() == Player.RIGHTS_MOD
                || player.getRights() == Player.RIGHTS_ADMIN || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER);
    }

    @Override
    public void execute(Player player, String message) {
        player.getWorld().sendStaffMessage("<col=FF0000>[Staff] " + player.getMessaging().getIconImage()
                + player.getUsername() + ": " + message + "</col>");
        JavaCord.sendMessage(DiscordChannel.MODERATION,
                "[Staff chat] " + player.getMessaging().getIconImage() + player.getUsername() + ": " + message);
    }
}
