package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.Entity;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import lombok.var;

public class AncestralGlyphCombat extends NpcCombat {
    private Npc npc;
    private int moveDelay;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ANCESTRAL_GLYPH);
        combat.hitpoints(NpcCombatHitpoints.total(600));
        combat.aggression(NpcCombatAggression.builder().range(8).always(true).sameTarget(true).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).bind(true).build());
        combat.focus(NpcCombatFocus.builder().disableFacingOpponent(true).disableFollowingOpponent(true).build());
        combat.deathAnimation(7569).blockAnimation(7568);


        return Arrays.asList(combat.build());
    }

    @Override
    public void restoreHook() {
        npc = getNpc();
        moveDelay = 2;
    }

    @Override
    public void tickStartHook() {
        if (npc.isLocked()) {
            return;
        }
        if (npc.getMovement().isRouting()) {
            return;
        }
        if (moveDelay > 0) {
            moveDelay--;
        }
        if (moveDelay != 0) {
            return;
        }
        moveDelay = 4;
        npc.getMovement().clear();
        if (npc.getY() == npc.getSpawnTile().getY()) {
            npc.getMovement().addMovement(npc.getSpawnTile().getX(), npc.getSpawnTile().getY() - 2);
        } else {
            if (npc.getX() < npc.getSpawnTile().getX()) {
                npc.getMovement().addMovement(npc.getSpawnTile().getX() + 13, npc.getSpawnTile().getY() - 2);
            } else {
                npc.getMovement().addMovement(npc.getSpawnTile().getX() - 13, npc.getSpawnTile().getY() - 2);
            }
        }
    }

    public boolean canBeAttackedHook(Entity opponent, boolean sendMessage, HitType hitType) {
        return opponent instanceof Npc && opponent.getId() == NpcId.TZKAL_ZUK_1400;
    }
}
