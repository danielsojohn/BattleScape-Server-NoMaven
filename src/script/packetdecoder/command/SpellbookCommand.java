package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.setting.SqlUserRank;

public class SpellbookCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER);
    }

    @Override
    public void execute(Player player, String message) {
        player.openDialogue("spellbooks", 0);
    }
}
