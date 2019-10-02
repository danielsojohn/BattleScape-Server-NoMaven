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
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Zombie18_44Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_RARE);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RANARR_WEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.IRIT_LEAF)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.AVANTOE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.KWUARM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CADANTINE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LANTADYME)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DWARF_WEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MITHRIL_SCIMITAR)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LAW_RUNE, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BRAINDEATH_RUM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.EARTH_WARRIOR_CHAMPION_SCROLL)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.HARRALANDER)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MITHRIL_DAGGER)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.WATER_RUNE, 23)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BODY_RUNE, 6)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CHAOS_RUNE, 7)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COSMIC_RUNE, 5)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.KARAMTHULHU)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.IRON_ORE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUSTY_SCIMITAR)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BRONZE_AXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.IRON_AXE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GUAM_LEAF)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MARRENTILL)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TARROMIN)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STEEL_BOLTS, 2, 12)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.AIR_RUNE, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 38, 46)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.FISHING_BAIT, 10)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ZOMBIE_BONE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BRONZE_AXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.IRON_AXE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BONES)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ZOMBIE_18_44);
        combat.hitpoints(NpcCombatHitpoints.total(30));
        combat.stats(NpcCombatStats.builder().attackLevel(19).defenceLevel(16).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.type(NpcCombatType.UNDEAD).deathAnimation(5575).blockAnimation(5574);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_CRUSH));
        style.damage(NpcCombatDamage.maximum(3));
        style.animation(5581).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
