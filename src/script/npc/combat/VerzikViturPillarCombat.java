package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.Hit;
import com.palidino.osrs.model.HitEvent;
import com.palidino.osrs.model.map.MapObject;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import lombok.var;

public class VerzikViturPillarCombat extends NpcCombat {
    private static final int MAP_OBJECT_ID_SUPPORTING_PILLAR = 32687;

    private Npc npc;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var pillar = NpcCombatDefinition.builder();
        pillar.id(NpcId.SUPPORTING_PILLAR).id(NpcId.COLLAPSING_PILLAR);
        pillar.hitpoints(NpcCombatHitpoints.builder().total(200).build());
        pillar.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        return Arrays.asList(pillar.build());
    }

    @Override
    public void spawnHook() {
        npc = getNpc();
        npc.getController().addMapObject(new MapObject(MAP_OBJECT_ID_SUPPORTING_PILLAR, npc, 10, 0));
    }

    @Override
    public void despawnHook() {
        removeMapObject();
        if (npc.getId() == NpcId.COLLAPSING_PILLAR) {
            for (var player : npc.getController().getPlayers()) {
                if (player.isLocked()) {
                    continue;
                }
                player.addHit(new HitEvent(0, player, new Hit(104)));
            }
        }
    }

    @Override
    public void applyDeadStartHook(int deathDelay) {
        removeMapObject();
        npc.setTransformationId(NpcId.COLLAPSING_PILLAR);
        npc.setAnimation(8052);
    }

    private void removeMapObject() {
        npc.getController().removeMapObject(new MapObject(MAP_OBJECT_ID_SUPPORTING_PILLAR, npc, 10, 0));
    }
}
