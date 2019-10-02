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
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class GeneralGraardor624Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256);
        var dropTable = NpcCombatDropTable.builder().chance(0.02).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PET_GENERAL_GRAARDOR)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.29).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BANDOS_HILT)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.4);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CLUE_SCROLL_ELITE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.58).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GODSWORD_SHARD_1)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GODSWORD_SHARD_2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GODSWORD_SHARD_3)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(1.17).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BANDOS_CHESTPLATE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BANDOS_TASSETS)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BANDOS_BOOTS)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.NATURE_RUNE, 60, 70)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SNAPDRAGON_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAGIC_LOGS_NOTED, 15, 20)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUPER_RESTORE_4, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_SNAPDRAGON_NOTED, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_SWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_TORSTOL_NOTED, 1, 5)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_2H_SWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_PICKAXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_LONGSWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_PLATEBODY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANTITE_ORE_NOTED, 15, 20)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COAL_NOTED, 115, 120)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 19581, 21000)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BIG_BONES)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.GENERAL_GRAARDOR_624);
        combat.phrase("Death to our enemies!").phrase("Brargh!").phrase("Break their bones!").phrase("For the glory of the Big High War God!").phrase("Split their skulls!").phrase("We feast on the bones of our enemies tonight!").phrase("CHAAARGE!").phrase("Crush them underfoot!");
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(100).build());
        combat.hitpoints(NpcCombatHitpoints.total(255));
        combat.stats(NpcCombatStats.builder().attackLevel(280).magicLevel(80).rangedLevel(350).defenceLevel(250).bonus(CombatBonus.MELEE_ATTACK, 120).bonus(CombatBonus.ATTACK_RANGED, 100).bonus(CombatBonus.MELEE_DEFENCE, 90).bonus(CombatBonus.DEFENCE_MAGIC, 298).bonus(CombatBonus.DEFENCE_RANGED, 90).build());
        combat.aggression(NpcCombatAggression.builder().range(16).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().keepWithinDistance(1).singleTargetFocus(true).build());
        combat.killCount(NpcCombatKillCount.builder().sendMessage(true).build());
        combat.combatScript("GeneralGraardorCS").deathAnimation(7020).blockAnimation(7019);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_CRUSH));
        style.damage(NpcCombatDamage.maximum(60));
        style.animation(7018).attackSpeed(6);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(35));
        style.animation(7021).attackSpeed(6).attackRange(1);
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
