package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.util.PString;
import lombok.var;

public class FakeDropCommand implements Command {
    @Override
    public String getExample() {
        return "username,itemname,from. ex. ::fakedrop miika,blue partyhat,bloodier key";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        var split = message.split(",");
        var username = split[0];
        var player2 = player.getWorld().getPlayerByUsername(username);
        var itemName = split[1];
        var from = split[2];
        String msg = player2.getMessaging().getIconImage() + player2.getUsername() + " has received "
                + PString.aOrAn(itemName) + " " + itemName + " drop";
        if (from != null) {
            msg += " from " + from;
        }
        msg += "!";
        player.getWorld().sendNews(msg);
    }
}
