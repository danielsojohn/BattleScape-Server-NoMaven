package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.util.RequestManager;
import lombok.var;

public class MuteCommand implements Command {
    @Override
    public String getExample() {
        return "hours username_or_userid";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_MOD || player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        var split = message.split(" ");
        var hours = Integer.parseInt(split[0]);
        if (hours > 48) {
            player.getGameEncoder().sendMessage("Max mute time is 48 hours.");
            return;
        }
        var username = message.substring(message.indexOf(split[0]) + split[0].length() + 1);
        var player2 = player.getWorld().getPlayerByUsername(username);
        if (player2 == null) {
            var userID = -1;
            try {
                userID = Integer.parseInt(username);
            } catch (Exception e) {
            }
            if (userID != -1) {
                player2 = player.getWorld().getPlayerById(userID);
            }
        }
        if (player2 == null) {
            player.getGameEncoder().sendMessage("Unable to find user " + username + ".");
            return;
        } else if (player == player2) {
            player.getGameEncoder().sendMessage("You can't mute yourself.");
            return;
        }
        player2.getGameEncoder().sendMessage(player.getUsername() + " has muted you for " + hours + " hours.");
        player2.getMessaging().setMuteTime(hours * 60, player.getUsername());
        player.getGameEncoder().sendMessage(username + " has been muted for " + hours + " hours.");
        player.getWorld().sendStaffMessage("[<col=ff0000>Staff</col>] " + player.getUsername() + " has muted "
                + player2.getUsername() + " for " + hours + " hours.");
        RequestManager.addPlayerLog("mute/0.txt",
                player.getLogName() + " muted " + player2.getLogName() + " for " + hours + " hours.");
    }
}
