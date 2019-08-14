package script.packetdecoder.widget;

import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.dialogue.DialogueEntry;
import com.palidino.osrs.model.dialogue.SelectionDialogueEntry;
import com.palidino.osrs.model.player.Equipment;
import com.palidino.osrs.model.player.Magic;
import com.palidino.osrs.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;

public class EquipmentWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.EQUIPMENT, WidgetId.EQUIPMENT_BONUSES, WidgetId.EQUIPMENT_BONUSES_INVENTORY
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {
        if (player.isLocked()) {
            return;
        }
        if (widgetId == WidgetId.EQUIPMENT || widgetId == WidgetId.EQUIPMENT_BONUSES) {
            var equipSlot = Equipment.Slot.getFromWidget(widgetId, childId);
            if (equipSlot != null) {
                if (index == 0) {
                    player.getController().unequip(equipSlot.ordinal());
                } else {
                    switch (player.getEquipment().getId(equipSlot)) {
                    case ItemId.SERPENTINE_HELM:
                    case ItemId.TANZANITE_HELM:
                    case ItemId.MAGMA_HELM:
                        player.getCharges().checkSerpentineHelm(equipSlot.ordinal() + 65536);
                        break;
                    case ItemId.AMULET_OF_GLORY:
                    case ItemId.AMULET_OF_GLORY_T:
                        player.openDialogue("amuletofglory", 0);
                        break;
                    case ItemId.AMULET_OF_GLORY_1:
                    case ItemId.AMULET_OF_GLORY_2:
                    case ItemId.AMULET_OF_GLORY_3:
                    case ItemId.AMULET_OF_GLORY_4:
                    case ItemId.AMULET_OF_GLORY_T1:
                    case ItemId.AMULET_OF_GLORY_T2:
                    case ItemId.AMULET_OF_GLORY_T3:
                    case ItemId.AMULET_OF_GLORY_T4:
                    case ItemId.AMULET_OF_ETERNAL_GLORY:
                        if (!player.getController().canTeleport(30, true)) {
                            break;
                        }
                        Tile gloryTeleport = null;
                        if (index == 1) {
                            gloryTeleport = new Tile(3087, 3491);
                        } else if (index == 2) {
                            gloryTeleport = new Tile(2915, 3152);
                        } else if (index == 3) {
                            gloryTeleport = new Tile(3085, 3249);
                        } else if (index == 4) {
                            gloryTeleport = new Tile(3293, 3177);
                        }
                        if (gloryTeleport == null) {
                            break;
                        }
                        player.getMovement().animatedTeleport(gloryTeleport, Magic.NORMAL_MAGIC_ANIMATION_START,
                                Magic.NORMAL_MAGIC_ANIMATION_END, Magic.NORMAL_MAGIC_GRAPHIC, null, 2);
                        player.getController().stopWithTeleport();
                        player.clearHits();
                        break;
                    case ItemId.ARDOUGNE_CLOAK_1:
                        if (!player.getController().canTeleport(30, true)) {
                            break;
                        }
                        Tile ardougneCloak1Teleport = null;
                        if (index == 1) {
                            ardougneCloak1Teleport = new Tile(2606, 3223);
                        }
                        if (ardougneCloak1Teleport == null) {
                            break;
                        }
                        player.getMovement().animatedTeleport(ardougneCloak1Teleport,
                                Magic.NORMAL_MAGIC_ANIMATION_START, Magic.NORMAL_MAGIC_ANIMATION_END,
                                Magic.NORMAL_MAGIC_GRAPHIC, null, 2);
                        player.getController().stopWithTeleport();
                        player.clearHits();
                        break;
                    case ItemId.ARDOUGNE_CLOAK_2:
                    case ItemId.ARDOUGNE_CLOAK_3:
                    case ItemId.ARDOUGNE_CLOAK_4:
                        if (!player.getController().canTeleport(30, true)) {
                            break;
                        }
                        Tile ardougneCloakTeleport = null;
                        if (index == 1) {
                            ardougneCloakTeleport = new Tile(2606, 3223);
                        } else if (index == 2) {
                            ardougneCloakTeleport = new Tile(2673, 3374);
                        }
                        if (ardougneCloakTeleport == null) {
                            break;
                        }
                        player.getMovement().animatedTeleport(ardougneCloakTeleport, Magic.NORMAL_MAGIC_ANIMATION_START,
                                Magic.NORMAL_MAGIC_ANIMATION_END, Magic.NORMAL_MAGIC_GRAPHIC, null, 2);
                        player.getController().stopWithTeleport();
                        player.clearHits();
                        break;
                    case ItemId.MAX_CAPE:
                        if (index == 1) {
                            player.getGameEncoder().sendMessage("Warriors guild");
                        } else if (index == 2) {
                            player.openDialogue(EquipmentMaxCapeDialogue.FISH.getEntry());
                        } else if (index == 3) {
                            player.getGameEncoder().sendMessage("Crafting guild");
                        } else if (index == 4) {
                            player.getGameEncoder().sendMessage("Tele to POH");
                        } else if (index == 5) {
                            player.openDialogue(EquipmentMaxCapeDialogue.PORTALS.getEntry());
                        } else if (index == 6) {
                            player.openDialogue(EquipmentMaxCapeDialogue.OTHER.getEntry());
                        } else if (index == 7) {
                            player.openDialogue("spellbooks", 1);
                        } else if (index == 8) {
                            player.openDialogue(EquipmentMaxCapeDialogue.FEATURES.getEntry());
                        }
                        break;
                    case ItemId.BINDING_NECKLACE:
                        player.getGameEncoder().sendMessage("Your Binding Necklace has "
                                + player.getSkills().getBindingNecklace() + " charges remaining.");
                        break;
                    case ItemId.RUBBER_CHICKEN:
                        player.setAnimation(1835);
                        break;
                    case ItemId.TOXIC_STAFF_OF_THE_DEAD:
                        player.getCharges().checkToxicStaff(equipSlot.ordinal() + 65536);
                        break;
                    case ItemId.TOXIC_BLOWPIPE:
                        player.getCharges().checkToxicBlowpipe(equipSlot.ordinal() + 65536);
                        break;
                    case ItemId.BRACELET_OF_ETHEREUM:
                    case ItemId.BRACELET_OF_ETHEREUM_UNCHARGED:
                        if (index == 1) {
                            player.getCharges().checkCharges(equipSlot.ordinal() + 65536);
                        } else if (index == 2) {
                            player.getCharges().setEthereumAutoAbsorb(!player.getCharges().getEthereumAutoAbsorb());
                            player.getGameEncoder().sendMessage(
                                    "Ether automatic absorption: " + player.getCharges().getEthereumAutoAbsorb());
                        }
                        break;
                    case ItemId.RING_OF_RECOIL:
                        player.getGameEncoder().sendMessage("You can inflict " + player.getCharges().getRingOfRecoil()
                                + " more points of damage before your ring will shatter.");
                        break;
                    case ItemId.RING_OF_WEALTH_I:
                        player.openDialogue("ringwealth", 0);
                        break;
                    case ItemId.MAGIC_CAPE:
                    case ItemId.MAGIC_CAPE_T:
                        player.openDialogue("spellbooks", 1);
                        break;
                    default:
                        player.getCharges().checkCharges(equipSlot.ordinal() + 65536);
                        break;
                    }
                }
            } else if (childId == 17) {
                player.getEquipment().openStats();
            } else if (childId == 21) {
                player.getCombat().openItemsKeptOnDeath();
            } else if (childId == 23) {
                player.getFamiliar().callPet(false);
            }
        } else if (widgetId == WidgetId.EQUIPMENT_BONUSES_INVENTORY) {
            switch (childId) {
            case 0:
                if (index == 0) {
                    player.getEquipment().equip(itemId, slot);
                }
                break;
            }
        }
    }
}


@AllArgsConstructor
@Getter
enum EquipmentMaxCapeDialogue {
    FISH(new SelectionDialogueEntry("Choose an Option", (player, index, childId, slot) -> {
        Tile maxCapeTele = null;
        if (slot == 0) {
            maxCapeTele = new Tile(3093, 3495);
        } else if (slot == 1) {
            maxCapeTele = new Tile(1233, 3565);
        } else if (slot == 2) {
            maxCapeTele = new Tile(1666, 10050);
        }
        if (!player.getController().canTeleport(true)) {
            return;
        }
        if (maxCapeTele == null) {
            return;
        }
        player.getMovement().animatedTeleport(maxCapeTele, Magic.NORMAL_MAGIC_ANIMATION_START,
                Magic.NORMAL_MAGIC_ANIMATION_END, Magic.NORMAL_MAGIC_GRAPHIC, null, 2);
        player.getController().stopWithTeleport();
        player.clearHits();
    }, "Fish", "Chambers of Xeric", "Catacombs of Kourend")),
    OTHER(new SelectionDialogueEntry("Choose an Option", (player, index, childId, slot) -> {
        Tile maxCapeTele = null;
        if (slot == 0) {
            maxCapeTele = new Tile(3093, 3495);
        } else if (slot == 1) {
            maxCapeTele = new Tile(1233, 3565);
        } else if (slot == 2) {
            maxCapeTele = new Tile(1666, 10050);
        }
        if (!player.getController().canTeleport(true)) {
            return;
        }
        if (maxCapeTele == null) {
            return;
        }
        player.getMovement().animatedTeleport(maxCapeTele, Magic.NORMAL_MAGIC_ANIMATION_START,
                Magic.NORMAL_MAGIC_ANIMATION_END, Magic.NORMAL_MAGIC_GRAPHIC, null, 2);
        player.getController().stopWithTeleport();
        player.clearHits();
    }, "Other", "Chambers of Xeric", "Catacombs of Kourend")),
    PORTALS(new SelectionDialogueEntry("Choose an Option", (player, index, childId, slot) -> {
        Tile maxCapeTele = null;
        if (slot == 0) {
            maxCapeTele = new Tile(3093, 3495);
        } else if (slot == 1) {
            maxCapeTele = new Tile(1233, 3565);
        } else if (slot == 2) {
            maxCapeTele = new Tile(1666, 10050);
        }
        if (!player.getController().canTeleport(true)) {
            return;
        }
        if (maxCapeTele == null) {
            return;
        }
        player.getMovement().animatedTeleport(maxCapeTele, Magic.NORMAL_MAGIC_ANIMATION_START,
                Magic.NORMAL_MAGIC_ANIMATION_END, Magic.NORMAL_MAGIC_GRAPHIC, null, 2);
        player.getController().stopWithTeleport();
        player.clearHits();
    }, "Portals", "Chambers of Xeric", "Catacombs of Kourend")),
    TELEPORTS(new SelectionDialogueEntry("Choose an Option", (player, index, childId, slot) -> {
        Tile maxCapeTele = null;
        if (slot == 0) {
            maxCapeTele = new Tile(3093, 3495);
        } else if (slot == 1) {
            maxCapeTele = new Tile(1233, 3565);
        } else if (slot == 2) {
            maxCapeTele = new Tile(1666, 10050);
        }
        if (!player.getController().canTeleport(true)) {
            return;
        }
        if (maxCapeTele == null) {
            return;
        }
        player.getMovement().animatedTeleport(maxCapeTele, Magic.NORMAL_MAGIC_ANIMATION_START,
                Magic.NORMAL_MAGIC_ANIMATION_END, Magic.NORMAL_MAGIC_GRAPHIC, null, 2);
        player.getController().stopWithTeleport();
        player.clearHits();
    }, "Teleports", "Chambers of Xeric", "Catacombs of Kourend")),
    FEATURES(new SelectionDialogueEntry("Choose an Option", (player, index, childId, slot) -> {
        Tile maxCapeTele = null;
        if (slot == 0) {
            maxCapeTele = new Tile(3093, 3495);
        } else if (slot == 1) {
            maxCapeTele = new Tile(1233, 3565);
        } else if (slot == 2) {
            maxCapeTele = new Tile(1666, 10050);
        }
        if (!player.getController().canTeleport(true)) {
            return;
        }
        if (maxCapeTele == null) {
            return;
        }
        player.getMovement().animatedTeleport(maxCapeTele, Magic.NORMAL_MAGIC_ANIMATION_START,
                Magic.NORMAL_MAGIC_ANIMATION_END, Magic.NORMAL_MAGIC_GRAPHIC, null, 2);
        player.getController().stopWithTeleport();
        player.clearHits();
    }, "Features", "Chambers of Xeric", "Catacombs of Kourend"));

    private DialogueEntry entry;
}
