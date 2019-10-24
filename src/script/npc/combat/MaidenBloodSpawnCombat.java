package script.npc.combat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.map.MapObject;
import com.palidino.osrs.model.map.TempMapObject;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.util.random.PRandom;
import lombok.var;

public class MaidenBloodSpawnCombat extends NpcCombat {
    private Npc npc;
    private List<TempMapObject> tempMapObjects = new ArrayList<>();

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.BLOOD_SPAWN_55);
        combat.hitpoints(NpcCombatHitpoints.total(120));
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.deathAnimation(8103);


        return Arrays.asList(combat.build());
    }

    @Override
    public void spawnHook() {
        npc = getNpc();
    }

    @Override
    public void restoreHook() {
        for (var tempMapObject : tempMapObjects) {
            tempMapObject.stop();
        }
    }

    @Override
    public void tickStartHook() {
        if (!npc.isLocked() && !npc.getController().hasSolidMapObject(npc)) {
            var tmo = new TempMapObject(30, npc.getController(),
                    new MapObject(32984, npc, 10, MapObject.getRandomDirection()));
            tempMapObjects.add(tmo);
            npc.getWorld().addEvent(tmo);
        }
        if (!npc.isLocked() && (PRandom.randomE(4) == 0 || !npc.getMovement().isRouting())) {
            npc.getMovement().clear();
            npc.getMovement().generateRandomPath();
        }
    }
}
