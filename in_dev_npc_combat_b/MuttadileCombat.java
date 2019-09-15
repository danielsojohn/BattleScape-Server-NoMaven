package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class MuttadileCombat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.MUTTADILE);
        combat.hitpoints(NpcCombatHitpoints.total(400));
        combat.stats(NpcCombatStats.builder().attackLevel(250).magicLevel(250).rangedLevel(250).defenceLevel(220).bonus(CombatBonus.MELEE_ATTACK, 88).bonus(CombatBonus.ATTACK_RANGED, 82).bonus(CombatBonus.DEFENCE_MAGIC, 75).build());
        combat.aggression(NpcCombatAggression.builder().range(16).always(true).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().disableFacingOpponent(true).disableFollowingOpponent(true).build());
        combat.combatScript("muttadile").deathAnimation(7426);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(30));
        style.attackSpeed(10).attackRange(16);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(30));
        style.attackSpeed(10).attackRange(16);
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
