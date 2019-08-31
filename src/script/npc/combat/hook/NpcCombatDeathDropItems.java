package script.npc.combat.hook;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.item.Item;
import com.palidino.osrs.model.item.ItemDef;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombatGlobalHooks;
import com.palidino.osrs.model.player.Player;
import com.palidino.util.Utils;

public class NpcCombatDeathDropItems implements NpcCombatGlobalHooks {
    public void deathDropItems(Npc npc, Player player, int index, boolean isSlayerTask, boolean isWildernessSlayerTask,
            Tile dropTile, int dropRateDivider, boolean hasRowCharge) {
        boolean allowSpecialDrops =
                (npc.getDef().getCombat().getDrop().hasDrops() && player.getController().canTeleport(false)
                        || npc.getController().inWilderness());
        int coinRate = 8;
        if (npc.getController().inMultiCombat() && !npc.getDef().getCombat().getKillCount().isSendMessage()) {
            coinRate = 32;
        }
        if (!Main.isSpawn() && allowSpecialDrops && Utils.randomE(coinRate) == 0) {
            double value =
                    Math.hypot(npc.getDef().getCombatLevel(), npc.getDef().getCombat().getHitpoints().getTotal());
            double multiplier = Math.min(16, Math.sqrt(value)) * 8 + Utils.randomI(8);
            double result = (int) Math.scalb(Math.log(value), 8);
            int coins = (int) (result * multiplier / dropRateDivider);
            if (player.isGameModeIronmanRelated()) {
                coins /= 2;
            }
            double coinMultiplier = 1;
            if (player.isPremiumMember()) {
                coinMultiplier += 0.25;
            }
            if (isWildernessSlayerTask) {
                coinMultiplier += 0.25;
            }
            coins *= coinMultiplier;
            if (coins > 0) {
                npc.getController().addMapItem(new Item(ItemId.COINS, coins), dropTile, player);
            }
        }
        if (!Main.isSpawn() && allowSpecialDrops
                && Utils.randomE(npc.getController().inMultiCombat() ? 100 : 50) == 0) {
            int half1Count = (int) Math.min(
                    player.getItemCount(ItemId.TOOTH_HALF_OF_KEY) + player.getItemCount(ItemId.TOOTH_HALF_OF_KEY_NOTED),
                    Item.MAX_AMOUNT);
            int half2Count = (int) Math.min(
                    player.getItemCount(ItemId.LOOP_HALF_OF_KEY) + player.getItemCount(ItemId.LOOP_HALF_OF_KEY_NOTED),
                    Item.MAX_AMOUNT);
            int keyHalfId;
            if (half1Count < half2Count) {
                keyHalfId = ItemId.TOOTH_HALF_OF_KEY;
            } else if (half2Count < half1Count) {
                keyHalfId = ItemId.LOOP_HALF_OF_KEY;
            } else {
                keyHalfId = Utils.randomI(1) == 0 ? ItemId.TOOTH_HALF_OF_KEY : ItemId.LOOP_HALF_OF_KEY;
            }
            if (player.isPremiumMember()) {
                keyHalfId = ItemDef.getNotedId(keyHalfId);
            }
            if (hasRowCharge && player.getInventory().canAddItem(keyHalfId, 1)) {
                player.getInventory().addItem(keyHalfId, 1);
            } else {
                npc.getController().addMapItem(new Item(keyHalfId, 1), dropTile, player);
            }
        }
        if (npc.getController().inWilderness() && npc.getDef().getCombat().getKillCount().isSendMessage()
                && Utils.randomE(5) == 0) {
            npc.getController().addMapItem(new Item(ItemId.MYSTERIOUS_EMBLEM, 1), dropTile, player);
        }
        if (npc.getController().inWilderness() && (npc.getDef().getCombat().getKillCount().isSendMessage()
                || isWildernessSlayerTask || npc.getDef().getName().contains("Revenant")) && Utils.randomE(300) == 0) {
            npc.getController().addMapItem(new Item(ItemId.MYSTERIOUS_EMBLEM_TIER_5, 1), dropTile, player);
        }
        if (isWildernessSlayerTask || npc.getDef().getName().contains("Revenant")) {
            if (player.getCombat().getBountyHunter().hasEmblem()) {
                if (Main.isEconomy()) {
                    player.getInventory().addOrDropItem(ItemId.BLOOD_MONEY, npc.getDef().getCombatLevel());
                }
                if (Utils.randomE(8) == 0) {
                    npc.getController().addMapItem(
                            new Item(Utils.randomI(1) == 0 ? ItemId.DARK_CRAB : ItemId.PRAYER_POTION_3, 1), dropTile,
                            player);
                }
            }
            if (npc.getMaxHitpoints() / 2 < 155 && Utils.randomE(155 - npc.getMaxHitpoints() / 2) == 0) {
                npc.getController().addMapItem(new Item(ItemId.MYSTERIOUS_EMBLEM, 1), dropTile, player);
            }
            if (npc.getMaxHitpoints() * 0.8 < 320
                    && Utils.randomE((int) (320 - npc.getMaxHitpoints() * 0.8) / 4) == 0) {
                npc.getController().addMapItem(new Item(ItemId.SLAYERS_ENCHANTMENT, 1), dropTile, player);
            }
        }
        if (npc.getController().inWilderness() && Utils.randomE(30) == 0 && !player.hasItem(ItemId.LOOTING_BAG)) {
            npc.getController().addMapItem(new Item(ItemId.LOOTING_BAG, 1), dropTile, player);
        }
    }
}
