package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.util.RequestManager;
import com.palidino.setting.SqlUserRank;
import lombok.var;

public class UnmuteCommand implements Command {
    @Override
    public String getExample() {
        return "hours username_or_userid";
    }

    @Override
    public boolean canUse(Player player) {
        return player.isUsergroup(SqlUserRank.SUPPORT) || player.getRights() == Player.RIGHTS_MOD
                || player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String username) {
        var player2 = player.getWorld().getPlayerByUsername(username);
        if (player2 == null) {
            player.getGameEncoder().sendMessage("Unable to find user " + username + ".");
            return;
        }
        player2.getGameEncoder().sendMessage(player.getUsername() + " has unmuted you.");
        player2.getMessaging().setMuteTime(0, null);
        player.getGameEncoder().sendMessage(username + " has been unmuted.");
        player.getWorld().sendStaffMessage(
                "[<col=ff0000>Staff</col>] " + player.getUsername() + " has unmuted " + player2.getUsername() + ".");
        RequestManager.addPlayerLog("mute/0.txt", player.getLogName() + " unmuted " + player2.getLogName() + ".");
    }
}
