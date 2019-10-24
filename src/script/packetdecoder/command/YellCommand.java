package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.util.RequestManager;
import com.palidino.rs.setting.SqlUserRank;
import com.palidino.util.PTime;
import lombok.var;

public class YellCommand implements Command {
    @Override
    public String getExample() {
        return "message";
    }

    @Override
    public boolean canUse(Player player) {
        return player.isUsergroup(SqlUserRank.DONATOR) || player.isUsergroup(SqlUserRank.TRIAL_MODERATOR)
                || player.getRights() == Player.RIGHTS_MOD || player.getRights() == Player.RIGHTS_ADMIN
                || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER);
    }

    @Override
    public void execute(Player player, String message) {
        var yellDelay = 0L;
        if (player.getMessaging().getYellDelay() > 0) {
            player.getGameEncoder().sendMessage("You need to wait "
                    + PTime.tickToSec(player.getMessaging().getYellDelay()) + " seconds before you can yell again.");
            return;
        }
        if (player.inJail()) {
            player.getGameEncoder().sendMessage("You can not yell while in jail..");
            return;
        }

        if (player.getMessaging().isMuted()) {
            player.getGameEncoder().sendMessage("You can not yell while muted.");
            return;
        }

        if (player.isUsergroup(SqlUserRank.TRIAL_MODERATOR) || player.isUsergroup(SqlUserRank.MODERATOR)
                || player.getRights() == Player.RIGHTS_ADMIN || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER)) {
            yellDelay = PTime.secToTick(5);
        } else if (player.isUsergroup(SqlUserRank.UBER_DONATOR)) {
            yellDelay = PTime.secToTick(5);
        } else if (player.isUsergroup(SqlUserRank.LEGENDARY_DONATOR)) {
            yellDelay = PTime.secToTick(15);
        } else if (player.isUsergroup(SqlUserRank.EXTREME_DONATOR)) {
            yellDelay = PTime.secToTick(30);
        } else if (player.isUsergroup(SqlUserRank.SUPER_DONATOR)) {
            yellDelay = PTime.secToTick(45);
        } else if (player.isUsergroup(SqlUserRank.DONATOR)) {
            yellDelay = PTime.secToTick(60);
        }
        player.getWorld().sendMessage("[<col=ff0000>Yell</col>] " + player.getMessaging().getIconImage()
                + player.getUsername() + ": " + message);
        player.getMessaging().setYellDelay((int) yellDelay);
        RequestManager.addPlayerLog("yell/" + player.getLogFilename(),
                "[" + player.getId() + "; " + player.getIP() + "] " + player.getUsername() + ": " + message);
    }
}
