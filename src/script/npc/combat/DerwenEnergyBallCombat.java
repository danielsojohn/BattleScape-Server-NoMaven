package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.Hit;
import com.palidino.osrs.model.HitEvent;
import com.palidino.osrs.model.HitMark;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import lombok.var;

public class DerwenEnergyBallCombat extends NpcCombat {
    private Npc npc;
    private Npc derwen;
    private int healDelay;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ENERGY_BALL);
        combat.hitpoints(NpcCombatHitpoints.total(20));
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());


        return Arrays.asList(combat.build());
    }

    @Override
    public void spawnHook() {
        npc = getNpc();
    }

    @Override
    public void restoreHook() {
        healDelay = 4;
    }

    @Override
    public void tickStartHook() {
        if (npc.isLocked() || --healDelay > 0) {
            return;
        }
        healDelay = 4;
        if (derwen == null) {
            derwen = npc.getController().getNpc(NpcId.DERWEN_235_7859);
        }
        if (derwen == null || derwen.isLocked()) {
            timedDeath();
            return;
        }
        var projectile = Graphic.Projectile.builder().id(1513).startTile(npc).endTile(derwen)
                .projectileSpeed(getProjectileSpeed(derwen)).build();
        sendMapProjectile(projectile);
        derwen.addHit(new HitEvent(projectile.getEventDelay(), derwen, npc, new Hit(5, HitMark.HEAL)));
    }
}
