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
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatSlayer;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatEffect;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class CaveCrawler23Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256);
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_RARE);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BRONZE_BOOTS)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STRAWBERRY_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.WATERMELON_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_CADANTINE, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_LANTADYME, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_DWARF_WEED, 1, 2)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.NATURE_RUNE, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TOMATO_SEED, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SWEETCORN_SEED, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_HARRALANDER, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_RANARR_WEED, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_IRIT_LEAF, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_AVANTOE, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_KWUARM, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNICORN_HORN_DUST)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RED_SPIDERS_EGGS)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LIMPWURT_ROOT)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SNAPE_GRASS)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.WHITE_BERRIES)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.EARTH_RUNE, 9)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.FIRE_RUNE, 12)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.POTATO_SEED, 1, 4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ONION_SEED, 1, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CABBAGE_SEED, 1, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_GUAM_LEAF, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_MARRENTILL, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_TARROMIN, 1, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.VIAL_OF_WATER)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.EYE_OF_NEWT)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.CAVE_CRAWLER_23);
        combat.hitpoints(NpcCombatHitpoints.total(24));
        combat.stats(NpcCombatStats.builder().attackLevel(22).defenceLevel(18).bonus(CombatBonus.DEFENCE_STAB, 10).bonus(CombatBonus.DEFENCE_SLASH, 10).bonus(CombatBonus.DEFENCE_CRUSH, 5).bonus(CombatBonus.DEFENCE_MAGIC, 5).bonus(CombatBonus.DEFENCE_RANGED, 10).build());
        combat.slayer(NpcCombatSlayer.builder().level(10).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.deathAnimation(228);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.maximum(3));
        style.animation(227).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        style.effect(NpcCombatEffect.builder().poison(4).build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
