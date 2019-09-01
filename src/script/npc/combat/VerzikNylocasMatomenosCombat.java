package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import lombok.var;

public class VerzikNylocasMatomenosCombat extends NpcCombat {
    private Npc npc;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.NYLOCAS_MATOMENOS_115_8385);
        combat.spawn(NpcCombatSpawn.builder().animation(8098).build());
        combat.hitpoints(NpcCombatHitpoints.total(200));
        combat.stats(
                NpcCombatStats.builder().attackLevel(100).magicLevel(100).rangedLevel(100).defenceLevel(100).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.deathAnimation(8097);


        return Arrays.asList(combat.build());
    }

    @Override
    public void restoreHook() {
        npc = getNpc();
    }

    @Override
    public void tickStartHook() {
        if (npc.isLocked()) {
            return;
        }
        if (npc.getTotalTicks() == 20) {
            var remainingHitpoints = npc.getHitpoints();
            timedDeath();
            var verzikNpc = npc.getController().getNpc(NpcId.VERZIK_VITUR_1265);
            if (verzikNpc != null && !verzikNpc.isLocked()) {
                verzikNpc.adjustHitpoints(remainingHitpoints);
            }
        }
    }
}
