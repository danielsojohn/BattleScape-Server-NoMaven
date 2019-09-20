package script.packetdecoder.command;

import java.util.ArrayList;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.dialogue.Scroll;
import com.palidino.osrs.model.player.Player;
import com.palidino.setting.SqlUserRank;
import com.palidino.util.Utils;
import lombok.var;

public class StaffCommand implements Command {

    @Override
    public String getExample() {
        return "- Shows the current staff online.";
    }

    @Override
    public void execute(Player player, String message) {
        var lines = new ArrayList<String>();
        for (var staff : player.getWorld().getStaffPlayers()) {
            var rank = "";
            if (staff.isUsergroup(SqlUserRank.ADMINISTRATOR)) {
                rank = "Administrator";
            } else if (staff.isUsergroup(SqlUserRank.HEAD_MODERATOR)) {
                rank = "Head Moderator";
            } else if (staff.isUsergroup(SqlUserRank.MODERATOR)) {
                rank = "Moderator";
            } else if (staff.isUsergroup(SqlUserRank.SUPPORT)) {
                rank = "Junior Moderator";
            }
            lines.add(staff.getMessaging().getIconImage() + staff.getUsername() + " - " + rank);
        }
        Scroll.open(player, "Staff Members Online", Utils.toStringArray(lines));
    }
}
