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
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class TzhaarHur74Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(0.1).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNCUT_ONYX)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_RARE);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNCUT_SAPPHIRE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNCUT_RUBY)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CHISEL)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.HAMMER)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.JUG)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.KNIFE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.POT)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TOKKUL, 1, 16)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.TZHAAR_HUR_74);
        combat.hitpoints(NpcCombatHitpoints.total(80));
        combat.stats(NpcCombatStats.builder().attackLevel(60).magicLevel(80).defenceLevel(60).build());
        combat.killCount(NpcCombatKillCount.builder().asName("TzHaar").build());
        combat.deathAnimation(2607).blockAnimation(2605);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_CRUSH));
        style.damage(NpcCombatDamage.maximum(9));
        style.animation(2609).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
