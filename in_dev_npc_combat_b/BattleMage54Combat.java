package script.npc.combat;

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
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class BattleMage54Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256);
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_128);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CLUE_SCROLL_HARD)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.79).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MASTER_WAND)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.INFINITY_TOP)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.INFINITY_HAT)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.INFINITY_BOOTS)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.INFINITY_GLOVES)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.INFINITY_BOTTOMS)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAGES_BOOK)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_64);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CLUE_SCROLL_MEDIUM)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MITHRIL_CHAINBODY)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STAFF_OF_FIRE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANT_MED_HELM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DEATH_RUNE, 3)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STEEL_AXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STEEL_FULL_HELM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CHAOS_RUNE, 12)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GOLD_ORE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.JUG_OF_WINE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BONES)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.BATTLE_MAGE_54);
        combat.hitpoints(NpcCombatHitpoints.total(100));
        combat.stats(NpcCombatStats.builder().magicLevel(60).bonus(CombatBonus.ATTACK_MAGIC, 16).bonus(CombatBonus.DEFENCE_MAGIC, 16).build());
        combat.aggression(NpcCombatAggression.builder().range(3).build());
        combat.combatScript("battlemage").deathAnimation(836).blockAnimation(415);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(811).attackSpeed(5).attackRange(7);
        style.targetGraphic(new Graphic(78));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
