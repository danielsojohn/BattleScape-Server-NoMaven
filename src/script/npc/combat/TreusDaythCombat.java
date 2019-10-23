package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.player.Player;
import com.palidino.util.Utils;
import lombok.var;

public class TreusDaythCombat extends NpcCombat {
    private static final Tile[] MOVE_PATHS = {
        new Tile(2781, 4459), new Tile(2787, 4444), new Tile(2788, 4463), new Tile(2794, 4451), new Tile(2781, 4454)
    };

    private Npc npc;
    private boolean hasMoved;
    private int moveDelay;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.TREUS_DAYTH_95);
        combat.hitpoints(NpcCombatHitpoints.total(100));
        combat.stats(NpcCombatStats.builder().attackLevel(70).defenceLevel(100).bonus(CombatBonus.MELEE_DEFENCE, 5)
                .bonus(CombatBonus.DEFENCE_RANGED, 5).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.focus(NpcCombatFocus.builder().bypassMapObjects(true).build());
        combat.deathAnimation(5542).blockAnimation(5541);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MELEE_STAB);
        style.damage(NpcCombatDamage.maximum(15));
        style.animation(5540).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(15));
        style.animation(5545).attackSpeed(4).attackRange(24);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }

    @Override
    public void spawnHook() {
        npc = getNpc();
    }

    @Override
    public void tickStartHook() {
        if (npc.isLocked() || hasMoved && npc.getLastHitByDelay() > 10 || moveDelay-- > 0
                || npc.getMovement().isRouting()) {
            return;
        }
        hasMoved = true;
        npc.getCombat().clear();
        npc.getMovement().clear();
        npc.getMovement().addMovement(MOVE_PATHS[Utils.randomE(MOVE_PATHS.length)]);
        npc.setLock(npc.getMovement().getMoveListSize());
        moveDelay = npc.getLock() + Utils.randomI(16);
    }

    @Override
    public void deathDropItemsHook(Player player, int additionalPlayerLoopCount, Tile dropTile) {
        if (player.getCombat().getHauntedMine() == 2) {
            player.getCombat().setHauntedMine(3);
        }
    }
}
