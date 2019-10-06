package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.HitpointsBar;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatSlayer;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Cerberus318Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256);
        var dropTable = NpcCombatDropTable.builder().chance(0.034).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.HELLPUPPY)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.05).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.JAR_OF_SOULS)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(1.0);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CLUE_SCROLL_ELITE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(1.17).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PRIMORDIAL_CRYSTAL)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PEGASIAN_CRYSTAL)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ETERNAL_CRYSTAL)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SMOULDERING_STONE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_2H_SWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_HALBERD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLACK_DHIDE_BODY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_FULL_HELM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_AXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_PICKAXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_CHAINBODY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_PLATEBODY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLOOD_RUNE, 60)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_BOLTS_UNF, 40)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CANNONBALL, 50)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TORSTOL_SEED, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_TORSTOL_NOTED, 6)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_ORE_NOTED, 5)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.KEY_MASTER_TELEPORT, 3)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LAVA_BATTLESTAFF)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BATTLESTAFF_NOTED, 6)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PURE_ESSENCE_NOTED, 300)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.FIRE_RUNE, 300)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DEATH_RUNE, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SOUL_RUNE, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUPER_RESTORE_4, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COAL_NOTED, 120)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 10000, 20000)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNHOLY_SYMBOL)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUMMER_PIE, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ASHES_NOTED, 50)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_BONES_NOTED, 20)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.WINE_OF_ZAMORAK_NOTED, 15)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.FIRE_ORB_NOTED, 20)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNCUT_DIAMOND_NOTED, 5)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ASHES)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.CERBERUS_318);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(14).build());
        combat.hitpoints(NpcCombatHitpoints.builder().total(600).bar(HitpointsBar.GREEN_RED_100).build());
        combat.stats(NpcCombatStats.builder().attackLevel(220).magicLevel(220).rangedLevel(220).defenceLevel(100)
                .bonus(CombatBonus.MELEE_ATTACK, 50).bonus(CombatBonus.ATTACK_MAGIC, 50)
                .bonus(CombatBonus.ATTACK_RANGED, 50).bonus(CombatBonus.DEFENCE_STAB, 50)
                .bonus(CombatBonus.DEFENCE_SLASH, 100).bonus(CombatBonus.DEFENCE_CRUSH, 25)
                .bonus(CombatBonus.DEFENCE_MAGIC, 100).bonus(CombatBonus.DEFENCE_RANGED, 100).build());
        combat.slayer(NpcCombatSlayer.builder().level(91).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.killCount(NpcCombatKillCount.builder().sendMessage(true).build());
        combat.combatScript("CerberusCS").deathAnimation(4495).blockAnimation(4489);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_SLASH));
        style.damage(NpcCombatDamage.maximum(23));
        style.animation(4491).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(23));
        style.animation(4490).attackSpeed(4);
        style.targetGraphic(new Graphic(1244, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(23));
        style.animation(4490).attackSpeed(4);
        style.targetGraphic(new Graphic(1243, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
