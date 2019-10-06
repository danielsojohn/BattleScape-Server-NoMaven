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
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class TektonEnragedCombat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(0.4);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNCUT_ONYX)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STINKHORN_MUSHROOM)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.OVERLOAD_PLUS_4, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.REVITALISATION_PLUS_4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PRAYER_ENHANCE_PLUS_4)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.TEKTON_ENRAGED);
        combat.hitpoints(NpcCombatHitpoints.total(400));
        combat.stats(NpcCombatStats.builder().attackLevel(390).magicLevel(205).defenceLevel(205)
                .bonus(CombatBonus.MELEE_ATTACK, 64).bonus(CombatBonus.DEFENCE_STAB, 280)
                .bonus(CombatBonus.DEFENCE_SLASH, 290).bonus(CombatBonus.DEFENCE_CRUSH, 180)
                .bonus(CombatBonus.DEFENCE_MAGIC, 600).bonus(CombatBonus.DEFENCE_RANGED, 600).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).bind(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.combatScript("TektonCS").deathAnimation(7495);
        combat.drop(drop.build());


        return Arrays.asList(combat.build());
    }
}
