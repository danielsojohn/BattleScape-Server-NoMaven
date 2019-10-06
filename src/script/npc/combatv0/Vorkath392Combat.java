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
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.style.NpcCombatEffect;
import com.palidino.osrs.model.npc.combat.style.special.NpcCombatTargetTile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Vorkath392Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256);
        var dropTable = NpcCombatDropTable.builder().chance(0.06);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.JAR_OF_DECAY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.VORKI)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.06);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRACONIC_VISAGE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SKELETAL_VISAGE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.15);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGONBONE_NECKLACE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(1.53);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CLUE_SCROLL_ELITE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(2.0);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.VORKATHS_HEAD)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_RARE);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BATTLESTAFF_NOTED, 5, 15)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.WRATH_RUNE, 30, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAGIC_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SPIRIT_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAHOGANY_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PALM_TREE_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.YEW_SEED)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_BATTLEAXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_LONGSWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_PLATELEGS)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_PLATESKIRT)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DEATH_RUNE, 300, 500)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RED_DRAGONHIDE_NOTED, 15, 25)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLACK_DRAGONHIDE_NOTED, 15, 25)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SAPPHIRE_BOLT_TIPS, 25, 35)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.EMERALD_BOLT_TIPS, 25, 35)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUBY_BOLT_TIPS, 31)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DIAMOND_BOLT_TIPS, 30)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.AMETHYST_BOLT_TIPS, 27, 30)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGONSTONE_BOLT_TIPS, 7, 28)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_DART_TIP, 86, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_DART_TIP, 10, 50)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_ARROWTIPS, 27, 50)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGONSTONE_NOTED, 1, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PAPAYA_TREE_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.WATERMELON_SEED, 15)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RANARR_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SNAPDRAGON_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TORSTOL_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.WILLOW_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TEAK_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAPLE_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CALQUAT_TREE_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.WRATH_TALISMAN)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_BONES_NOTED, 7, 28)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(40.0);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_BOLTS_UNF, 50, 100)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_LONGSWORD, 2, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_KITESHIELD, 2, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CHAOS_RUNE, 650, 1000)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GREEN_DRAGONHIDE_NOTED, 25, 32)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLUE_DRAGONHIDE_NOTED, 20, 30)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANTITE_ORE_NOTED, 10, 30)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DIAMOND_NOTED, 10, 30)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CRUSHED_NEST_NOTED, 10, 30)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRAPES_NOTED, 250, 301)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAGIC_LOGS_NOTED, 50)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 37000, 81000)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MANTA_RAY_NOTED, 25, 55)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_BOLTS_UNF, 50, 100)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUPERIOR_DRAGON_BONES, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLUE_DRAGONHIDE, 2)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.VORKATH_392);
        combat.spawn(NpcCombatSpawn.builder().deathDelay(5).build());
        combat.hitpoints(NpcCombatHitpoints.builder().total(460).bar(HitpointsBar.GREEN_RED_120).build());
        combat.stats(NpcCombatStats.builder().attackLevel(560).magicLevel(148).rangedLevel(268).defenceLevel(164)
                .bonus(CombatBonus.MELEE_ATTACK, 14).bonus(CombatBonus.ATTACK_MAGIC, 148)
                .bonus(CombatBonus.ATTACK_RANGED, 96).bonus(CombatBonus.DEFENCE_STAB, 66)
                .bonus(CombatBonus.DEFENCE_SLASH, 126).bonus(CombatBonus.DEFENCE_CRUSH, 126)
                .bonus(CombatBonus.DEFENCE_MAGIC, 204).bonus(CombatBonus.DEFENCE_RANGED, 80).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.combatScript("vorkath").type(NpcCombatType.UNDEAD).deathAnimation(7949);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_SLASH));
        style.damage(NpcCombatDamage.maximum(16));
        style.animation(7951).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(32));
        style.animation(7952).attackSpeed(5);
        style.targetGraphic(new Graphic(1478, 124));
        style.projectile(NpcCombatProjectile.builder().id(1477).startHeight(30).build());
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(32));
        style.animation(7952).attackSpeed(5);
        style.targetGraphic(new Graphic(1480, 124));
        style.projectile(NpcCombatProjectile.builder().id(1479).startHeight(30).build());
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(80));
        style.animation(7952).attackSpeed(5);
        style.targetGraphic(new Graphic(157, 124));
        style.projectile(NpcCombatProjectile.builder().id(393).startHeight(30).build());
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(80));
        style.animation(7952).attackSpeed(5);
        style.targetGraphic(new Graphic(1472, 124));
        style.projectile(NpcCombatProjectile.builder().id(1470).startHeight(30).build());
        style.effect(NpcCombatEffect.builder().venom(6).build());
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(80));
        style.animation(7952).attackSpeed(5);
        style.targetGraphic(new Graphic(1473, 124));
        style.projectile(NpcCombatProjectile.builder().id(1471).startHeight(30).build());
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(80));
        style.animation(7957).attackSpeed(5);
        style.targetGraphic(new Graphic(157));
        style.projectile(
                NpcCombatProjectile.builder().id(1481).startHeight(30).curve(32).speedMinimumDistance(8).build());
        var targetTile = NpcCombatTargetTile.builder().adjacentHalfDamage(true);
        style.specialAttack(targetTile.build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
