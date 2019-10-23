package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class JalMejjak250Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.JAL_MEJJAK_250);
        combat.hitpoints(NpcCombatHitpoints.total(80));
        combat.stats(NpcCombatStats.builder().defenceLevel(100).build());
        combat.aggression(NpcCombatAggression.builder().range(8).always(true).sameTarget(true).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().bypassMapObjects(true).build());
        combat.combatScript("JalMejJakCS").deathAnimation(2866).blockAnimation(2869);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().hitType(HitType.MAGIC).subHitType(HitType.HEAL).build());
        style.damage(NpcCombatDamage.maximum(23));
        style.animation(2868).attackSpeed(3);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
