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
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatEffect;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class KingBlackDragon276Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256);
        var dropTable = NpcCombatDropTable.builder().chance(0.034).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PRINCE_BLACK_DRAGON)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.08).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRACONIC_VISAGE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.1).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_PICKAXE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.22);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CLUE_SCROLL_ELITE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(2.4).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_MED_HELM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.KBD_HEADS)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANT_KITESHIELD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LAW_RUNE, 30)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_BOLTS, 10, 25)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_DART_TIP, 5, 14)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_ARROWTIPS, 5, 14)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_JAVELIN_HEADS, 15)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SHARK, 4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GOLD_ORE_NOTED, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_LIMBS)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_BAR)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANTITE_BAR, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_TORSTOL_NOTED, 1, 5)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_LONGSWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANT_PLATEBODY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.FIRE_RUNE, 300)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.AIR_RUNE, 300)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLOOD_RUNE, 30)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.IRON_ARROW, 690)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.AMULET_OF_POWER)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.YEW_LOGS_NOTED, 150)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_BONES)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLACK_DRAGONHIDE, 2)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.KING_BLACK_DRAGON_276);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(16).build());
        combat.hitpoints(NpcCombatHitpoints.total(240));
        combat.stats(NpcCombatStats.builder().attackLevel(240).magicLevel(240).defenceLevel(240).bonus(CombatBonus.DEFENCE_STAB, 70).bonus(CombatBonus.DEFENCE_SLASH, 90).bonus(CombatBonus.DEFENCE_CRUSH, 90).bonus(CombatBonus.DEFENCE_MAGIC, 80).bonus(CombatBonus.DEFENCE_RANGED, 70).build());
        combat.aggression(NpcCombatAggression.builder().range(15).build());
        combat.killCount(NpcCombatKillCount.builder().sendMessage(true).build());
        combat.combatScript("KingBlackDragonCS").deathAnimation(92).blockAnimation(89);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.maximum(25));
        style.animation(80).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.maximum(25));
        style.animation(91).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(65));
        style.animation(81).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(65));
        style.animation(81).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        style.effect(NpcCombatEffect.builder().poison(8).build());
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(65));
        style.animation(81).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(65));
        style.animation(81).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
