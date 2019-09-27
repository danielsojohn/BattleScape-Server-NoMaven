package script.player.plugin.clanwars.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import script.player.plugin.clanwars.ClanWarsPlugin;
import script.player.plugin.clanwars.Stages;

public class JoinBattleDialogue extends SelectionDialogue {
    public JoinBattleDialogue(Player player, ClanWarsPlugin plugin) {
        addOption("Join your clan's battle.", (childId, slot) -> {
            Stages.openJoin(player, plugin);
        });
        addOption("Observe your clan's battle.", (childId, slot) -> {
            Stages.openView(player, plugin);
        });
        addOption("Cancel.");
        open(player);
    }
}
