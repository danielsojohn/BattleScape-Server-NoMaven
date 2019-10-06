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
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Starlight149Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(0.04).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SARADOMIN_SWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ARMADYL_CROSSBOW)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.29).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GODSWORD_SHARD_1)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GODSWORD_SHARD_2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GODSWORD_SHARD_3)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_128);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CLUE_SCROLL_HARD)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_RANARR_WEED_NOTED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUMMER_PIE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNICORN_HORN_NOTED, 6)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MONKFISH, 3)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STEEL_DART, 95, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LAW_RUNE, 7, 10)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STEEL_ARROW, 97, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 1300, 2000)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SNAPE_GRASS_NOTED, 5)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BONES)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.STARLIGHT_149);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(50).respawnWithId(NpcId.COMMANDER_ZILYANA_596)
                .respawnWithId(NpcId.GROWLER_139).respawnWithId(NpcId.BREE_146).build());
        combat.hitpoints(NpcCombatHitpoints.total(160));
        combat.stats(NpcCombatStats.builder().attackLevel(120).magicLevel(125).defenceLevel(120)
                .bonus(CombatBonus.MELEE_ATTACK, 60).bonus(CombatBonus.DEFENCE_STAB, 12)
                .bonus(CombatBonus.DEFENCE_SLASH, 14).bonus(CombatBonus.DEFENCE_CRUSH, 13)
                .bonus(CombatBonus.DEFENCE_MAGIC, 5).bonus(CombatBonus.DEFENCE_RANGED, 13).build());
        combat.aggression(NpcCombatAggression.builder().range(16).build());
        combat.killCount(NpcCombatKillCount.builder().asName("Commander Zilyana's bodyguard").build());
        combat.deathAnimation(6377).blockAnimation(6375);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_CRUSH));
        style.damage(NpcCombatDamage.maximum(15));
        style.animation(6376).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
