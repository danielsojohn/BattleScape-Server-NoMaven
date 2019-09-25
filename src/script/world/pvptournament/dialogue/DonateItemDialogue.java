package script.world.pvptournament.dialogue;

import com.palidino.osrs.model.dialogue.DialogueAction;
import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.item.Item;
import com.palidino.osrs.model.player.Player;
import com.palidino.util.Utils;
import lombok.var;
import script.world.pvptournament.PvpTournament;

public class DonateItemDialogue extends SelectionDialogue {
    private Item item;

    public DonateItemDialogue(Player player, Item item) {
        this.item = item;
        var tournament = player.getWorld().getWorldEvent(PvpTournament.class);
        DialogueAction action = (childId, slot) -> {
            player.getWidgetManager().removeInteractiveWidgets();
            if (!tournament.donateItem(player, item.getId(), slot)) {
                return;
            }
            if (tournament.getMode() != null) {
                return;
            }
            new ChooseModeDialogue(player);
        };
        addOption("Give to place #1", action);
        addOption("Give to place #2", action);
        addOption("Give to place #3", action);
        addOption("Give to place #4", action);
        open(player);
    }

    @Override
    public void sendWidgetTextHook(Player player) {
        sendWidgetText(player, item.getName() + " x" + Utils.formatNumber(item.getAmount()));
    }
}
