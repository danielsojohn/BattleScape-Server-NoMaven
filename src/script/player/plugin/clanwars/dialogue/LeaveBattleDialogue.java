package script.player.plugin.clanwars.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import script.player.plugin.clanwars.ClanWarsPlugin;

public class LeaveBattleDialogue extends SelectionDialogue {
    public LeaveBattleDialogue(Player player, ClanWarsPlugin plugin) {
        addOption("Leave the Clan War.", (childId, slot) -> {
            if (!player.getController().getVariableBool("clan_wars")) {
                return;
            }
            player.getController().stop();
        });
        addOption("Nevermind.");
        open(player);
    }
}
