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
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class DarkEnergyCore75Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ASHES)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.DARK_ENERGY_CORE_75);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(6000).build());
        combat.hitpoints(NpcCombatHitpoints.total(25));
        combat.stats(NpcCombatStats.builder().defenceLevel(20).bonus(CombatBonus.MELEE_DEFENCE, 10).bonus(CombatBonus.DEFENCE_MAGIC, -5).bonus(CombatBonus.DEFENCE_RANGED, 10).build());
        combat.aggression(NpcCombatAggression.builder().range(16).always(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).disableFollowingOpponent(true).build());
        combat.combatScript("DarkEnergyCoreCS");
        combat.drop(drop.build());


        return Arrays.asList(combat.build());
    }
}
