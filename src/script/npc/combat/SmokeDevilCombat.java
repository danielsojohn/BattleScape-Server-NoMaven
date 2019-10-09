package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.Main;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.Entity;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.HitpointsBar;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.item.Item;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.NpcCombatSlayer;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class SmokeDevilCombat extends NpcCombat {
    private static final NpcCombatDropTable SUPERIOR_DROP_TABLE = NpcCombatDropTable.builder().chance(4.05).log(true)
            .drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.IMBUED_HEART, 1, 1, 1)))
            .drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ETERNAL_GEM, 1, 1, 1)))
            .drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DUST_BATTLESTAFF, 1, 1, 3)))
            .drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MIST_BATTLESTAFF, 1, 1, 3))).build();
    private static final NpcCombatDropTable CURSED_DROP_TABLE =
            NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_1024).log(true)
                    .drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MYSTIC_SMOKE_STAFF)))
                    .drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TOME_OF_FIRE_EMPTY))).build();

    private Npc npc;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().clue(NpcCombatDrop.ClueScroll.ELITE, NpcCombatDropTable.CHANCE_1_IN_750)
                .clue(NpcCombatDrop.ClueScroll.HARD, NpcCombatDropTable.CHANCE_1_IN_128);
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_32768).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_CHAINBODY)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_512).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.OCCULT_NECKLACE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_RARE);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_2H_SWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_RANARR_WEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_LANTADYME)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_BAR)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.AIR_RUNE, 37)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SOUL_RUNE, 10)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_ARROW, 24)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_BOLTS, 15)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.FIRE_BATTLESTAFF)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_DAGGER)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.AIR_BATTLESTAFF)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLACK_DHIDE_VAMB)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RED_DHIDE_BODY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_FULL_HELM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_CHAINBODY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_IRIT_LEAF)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_AVANTOE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_KWUARM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_CADANTINE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_DWARF_WEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BURNT_PAGE, 20)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SMOKE_RUNE, 10, 40)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANT_BATTLEAXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MITHRIL_PLATESKIRT)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_GUAM_LEAF)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_MARRENTILL)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_TARROMIN)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_HARRALANDER)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 80, 800)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UGTHANKI_KEBAB, 1, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SHARK)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANTITE_BAR)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CROSSBOW_STRING)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STEEL_BAR, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAGIC_LOGS_NOTED, 5)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COAL_NOTED, 15)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ASHES)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.SMOKE_DEVIL_160);
        combat.hitpoints(NpcCombatHitpoints.total(185));
        combat.stats(NpcCombatStats.builder().attackLevel(140).rangedLevel(195).defenceLevel(275)
                .bonus(CombatBonus.DEFENCE_MAGIC, 600).bonus(CombatBonus.DEFENCE_RANGED, 44).build());
        combat.slayer(NpcCombatSlayer.builder().level(93).superiorId(NpcId.NUCLEAR_SMOKE_DEVIL_280).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.deathAnimation(3849).blockAnimation(3848);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.RANGED).subType(HitType.MAGIC).build());
        style.damage(NpcCombatDamage.maximum(20));
        style.animation(3847).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        var cursedCombat = NpcCombatDefinition.builder();
        cursedCombat.id(NpcId.CURSED_SMOKE_DEVIL_280_16006);
        cursedCombat.hitpoints(NpcCombatHitpoints.builder().total(400).bar(HitpointsBar.GREEN_RED_60).build());
        cursedCombat.stats(NpcCombatStats.builder().attackLevel(240).rangedLevel(295).defenceLevel(375)
                .bonus(CombatBonus.DEFENCE_MAGIC, 1200).bonus(CombatBonus.DEFENCE_RANGED, 88).build());
        cursedCombat.slayer(NpcCombatSlayer.builder().level(93).build());
        cursedCombat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        cursedCombat.deathAnimation(3849).blockAnimation(3848);
        cursedCombat.drop(drop.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.RANGED).subType(HitType.MAGIC).build());
        style.damage(NpcCombatDamage.maximum(30));
        style.animation(3847).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        cursedCombat.style(style.build());


        var superiorCombat = NpcCombatDefinition.builder();
        superiorCombat.id(NpcId.NUCLEAR_SMOKE_DEVIL_280);
        superiorCombat.hitpoints(NpcCombatHitpoints.builder().total(400).bar(HitpointsBar.GREEN_RED_60).build());
        superiorCombat.stats(NpcCombatStats.builder().attackLevel(240).rangedLevel(295).defenceLevel(375)
                .bonus(CombatBonus.DEFENCE_MAGIC, 1200).bonus(CombatBonus.DEFENCE_RANGED, 88).build());
        superiorCombat.slayer(NpcCombatSlayer.builder().level(93).experience(2400).build());
        superiorCombat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        superiorCombat.killCount(NpcCombatKillCount.builder().asName("Superior Slayer Creature").build());
        superiorCombat.deathAnimation(3849).blockAnimation(3848);
        superiorCombat.drop(drop.rolls(3).build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.RANGED).subType(HitType.MAGIC).build());
        style.damage(NpcCombatDamage.maximum(30));
        style.animation(3847).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        superiorCombat.style(style.build());


        return Arrays.asList(combat.build(), cursedCombat.build(), superiorCombat.build());
    }

    @Override
    public void spawnHook() {
        npc = getNpc();
    }

    @Override
    public boolean canBeAttackedHook(Entity opponent, boolean sendMessage, HitType hitType) {
        if (!(opponent instanceof Player)) {
            return false;
        }
        var player = (Player) opponent;
        if (!Main.isSpawn() && !player.getSkills().isAnySlayerTask(npc)) {
            if (sendMessage) {
                player.getGameEncoder().sendMessage("This can only be attacked on an appropriate Slayer task.");
            }
            return false;
        }
        return true;
    }

    @Override
    public List<Item> deathDropItemsGetItemsHook(Npc npc, Player player, Tile dropTile, int dropRateDivider, int roll,
            NpcCombatDropTable table, List<Item> items) {
        if (npc.getId() == NpcId.CURSED_SMOKE_DEVIL_280_16006) {
            if (!player.getSkills().isWildernessSlayerTask(npc)) {
                player.getGameEncoder().sendMessage("Without an assigned task, the loot turns to dust...");
                return null;
            }
            if (CURSED_DROP_TABLE.canDrop(npc, player)) {
                return CURSED_DROP_TABLE.getItems(npc, player, dropTile, dropRateDivider, roll);
            }
        }
        if (npc.getId() == NpcId.CURSED_SMOKE_DEVIL_280_16006 && SUPERIOR_DROP_TABLE.canDrop(npc, player)) {
            return SUPERIOR_DROP_TABLE.getItems(npc, player, dropTile, dropRateDivider, roll);
        }
        return items;
    }

    @Override
    public double dropTableChanceHook(Player player, int dropRateDivider, int roll, NpcCombatDropTable table) {
        var chance = table.getChance();
        if (npc.getId() == NpcId.CURSED_SMOKE_DEVIL_280_16006 && table == SUPERIOR_DROP_TABLE) {
            chance /= 32;
        }
        return chance;
    }
}
