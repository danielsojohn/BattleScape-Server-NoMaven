package script.player.plugin.cluechest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.dialogue.Scroll;
import com.palidino.osrs.model.item.clue.ClueChestSet;
import com.palidino.osrs.model.item.clue.ClueChestSetEntry;
import com.palidino.osrs.model.item.clue.ClueChestType;
import com.palidino.osrs.model.map.MapObject;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.PlayerPlugin;
import com.palidino.util.Utils;
import lombok.var;
import script.player.plugin.cluechest.dialogue.TreasureChestDialogue;

public class ClueChestPlugin extends PlayerPlugin {
    private transient Player player;

    private Map<String, ClueChestSetEntry> beginnerClueChest;
    private Map<String, ClueChestSetEntry> easyClueChest;
    private Map<String, ClueChestSetEntry> mediumClueChest;
    private Map<String, ClueChestSetEntry> hardClueChest;
    private Map<String, ClueChestSetEntry> eliteClueChest;
    private Map<String, ClueChestSetEntry> masterClueChest;

    @Override
    @SuppressWarnings("rawtypes")
    public void loadLegacy(Map<String, Object> map) {
        if (map.containsKey("cluechest.beginnerClueChest")) {
            beginnerClueChest =
                    Utils.castMap((Map) map.get("cluechest.beginnerClueChest"), String.class, ClueChestSetEntry.class);
        }
        if (map.containsKey("cluechest.easyClueChest")) {
            easyClueChest =
                    Utils.castMap((Map) map.get("cluechest.easyClueChest"), String.class, ClueChestSetEntry.class);
        }
        if (map.containsKey("cluechest.mediumClueChest")) {
            mediumClueChest =
                    Utils.castMap((Map) map.get("cluechest.mediumClueChest"), String.class, ClueChestSetEntry.class);
        }
        if (map.containsKey("cluechest.hardClueChest")) {
            hardClueChest =
                    Utils.castMap((Map) map.get("cluechest.hardClueChest"), String.class, ClueChestSetEntry.class);
        }
        if (map.containsKey("cluechest.eliteClueChest")) {
            eliteClueChest =
                    Utils.castMap((Map) map.get("cluechest.eliteClueChest"), String.class, ClueChestSetEntry.class);
        }
        if (map.containsKey("cluechest.masterClueChest")) {
            masterClueChest =
                    Utils.castMap((Map) map.get("cluechest.masterClueChest"), String.class, ClueChestSetEntry.class);
        }
    }

    @Override
    public void login() {
        player = getPlayer();
    }

    @Override
    public boolean widgetOnMapObjectHook(int widgetId, int childId, int slot, int itemId, MapObject mapObject) {
        if (widgetId == WidgetId.INVENTORY && mapObject.getId() == 18808) {
            addItem(itemId);
            return true;
        }
        return false;
    }

    @Override
    public boolean mapObjectOptionHook(int index, MapObject mapObject) {
        switch (mapObject.getId()) {
        case 18808: // treasure chest
            new TreasureChestDialogue(player);
            return true;
        }
        return false;
    }

    public void open(ClueChestType type) {
        var chest = ClueChestSet.getClueChest(type);
        var myChest = getClueChest(type);
        var names = new ArrayList<String>();
        for (var set : chest.entrySet()) {
            var name = set.getKey();
            if (myChest.containsKey(name)) {
                name = "<str>" + name + "</str>";
            }
            names.add(name);
        }
        Scroll.open(player, type.getFormattedName() + "-level Treasure Trail rewards", names);
    }

    public void addItem(int id) {
        if (hasId(id)) {
            player.getGameEncoder().sendMessage("Your treasure chest already contains this item.");
            return;
        }
        for (var chestType : ClueChestType.values()) {
            var chest = ClueChestSet.getClueChest(chestType);
            var myChest = getClueChest(chestType);
            for (var set : chest.entrySet()) {
                if (!set.getValue().containsId(id)) {
                    continue;
                }
                var matches = new ArrayList<Integer>();
                for (var entry : set.getValue().getEntries()) {
                    var matchId = entry.getMatchId(player.getInventory());
                    if (matchId == -1) {
                        continue;
                    }
                    matches.add(matchId);
                }
                if (matches.size() != set.getValue().size()) {
                    player.getGameEncoder().sendMessage("You need the complete set to store this item.");
                    break;
                }
                for (var matchedId : matches) {
                    player.getInventory().deleteItem(matchedId);
                }
                myChest.put(set.getKey(), new ClueChestSetEntry(matches));
                player.getGameEncoder().sendMessage("Your item has been stored in the treasure chest.");
            }
        }
    }

    public boolean hasId(int id) {
        var chests = Utils.toList(beginnerClueChest, easyClueChest, mediumClueChest, hardClueChest, eliteClueChest,
                masterClueChest);
        for (var chest : chests) {
            if (chest == null) {
                continue;
            }
            for (var entry : chest.values()) {
                if (entry.containsId(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Map<String, ClueChestSetEntry> getClueChest(ClueChestType type) {
        if (type == ClueChestType.BEGINNER) {
            if (beginnerClueChest == null) {
                beginnerClueChest = new HashMap<>();
            }
            return beginnerClueChest;
        }
        if (type == ClueChestType.EASY) {
            if (easyClueChest == null) {
                easyClueChest = new HashMap<>();
            }
            return easyClueChest;
        }
        if (type == ClueChestType.MEDIUM) {
            if (mediumClueChest == null) {
                mediumClueChest = new HashMap<>();
            }
            return mediumClueChest;
        }
        if (type == ClueChestType.HARD) {
            if (hardClueChest == null) {
                hardClueChest = new HashMap<>();
            }
            return hardClueChest;
        }
        if (type == ClueChestType.ELITE) {
            if (eliteClueChest == null) {
                eliteClueChest = new HashMap<>();
            }
            return eliteClueChest;
        }
        if (type == ClueChestType.MASTER) {
            if (masterClueChest == null) {
                masterClueChest = new HashMap<>();
            }
            return masterClueChest;
        }
        return null;
    }
}
