package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class CombatDummyCombat extends NpcCombat {
    private Npc npc;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.COMBAT_DUMMY);
        combat.hitpoints(NpcCombatHitpoints.total(1000));
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());


        return Arrays.asList(combat.build());
    }

    @Override
    public void spawnHook() {
        npc = getNpc();
    }

    @Override
    public void tickStartHook() {
        if (npc.isDead()) {
            npc.restore();
        }
        npc.setHitpoints(npc.getMaxHitpoints());
    }
}
