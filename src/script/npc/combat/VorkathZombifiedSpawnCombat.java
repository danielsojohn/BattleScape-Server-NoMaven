package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.Entity;
import com.palidino.osrs.model.Hit;
import com.palidino.osrs.model.HitEvent;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatType;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class VorkathZombifiedSpawnCombat extends NpcCombat {
    private Npc npc;
    private int countdown1;
    private int countdown2;
    private Entity following;
    private int explosionDamage;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ZOMBIFIED_SPAWN_64);
        combat.hitpoints(NpcCombatHitpoints.total(38));
        combat.stats(NpcCombatStats.builder().attackLevel(82).defenceLevel(6).bonus(CombatBonus.ATTACK_STAB, 1)
                .bonus(CombatBonus.ATTACK_SLASH, 1).bonus(CombatBonus.ATTACK_CRUSH, 1)
                .bonus(CombatBonus.ATTACK_MAGIC, 1).bonus(CombatBonus.ATTACK_RANGED, 1)
                .bonus(CombatBonus.MELEE_DEFENCE, 3).bonus(CombatBonus.DEFENCE_MAGIC, -100)
                .bonus(CombatBonus.DEFENCE_RANGED, 3).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.type(NpcCombatType.UNDEAD).deathAnimation(7891);


        return Arrays.asList(combat.build());
    }

    @Override
    public void spawnHook() {
        npc = getNpc();
    }

    @Override
    public void restoreHook() {
        countdown1 = 2;
        countdown2 = 2;
        following = null;
        explosionDamage = 0;
    }

    @Override
    public void tickStartHook() {
        if (npc.getMovement().getFollowing() != null) {
            following = npc.getMovement().getFollowing();
        }
        if (!npc.isLocked() && !npc.withinMapDistance(following)) {
            npc.getCombat().timedDeath();
            return;
        }
        if (!npc.withinDistance(following, 1)) {
            return;
        }
        if (--countdown1 >= 0) {
            if (countdown1 == 1) {
                npc.getMovement().setFollowing(null);
                npc.getMovement().clear();
            } else if (countdown1 == 0) {
                explosionDamage = (int) (npc.getHitpoints() * 1.4);
                npc.getCombat().timedDeath(countdown2);
            }
        } else if (--countdown2 >= 0) {
            if (countdown2 == 0) {
                if (npc.withinDistance(following, 1)) {
                    var hitEvent = new HitEvent(0, following, new Hit(explosionDamage));
                    following.addHit(hitEvent);
                    following.getController().setMagicBind(0);
                }
            }
        }
    }

    @Override
    public double damageReceivedHook(Entity opponent, double damage, HitType hitType, HitType defenceType) {
        if (opponent instanceof Player && hitType == HitType.MAGIC && damage > 0) {
            var player = (Player) opponent;
            if (player.getMagic().getActiveSpell() != null
                    && player.getMagic().getActiveSpell().getName().contains("crumble undead")) {
                return npc.getHitpoints();
            }
        }
        return damage;
    }
}
