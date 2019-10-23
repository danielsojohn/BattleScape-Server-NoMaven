package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Shifter76Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.SHIFTER_76);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(100).build());
        combat.hitpoints(NpcCombatHitpoints.total(106));
        combat.stats(NpcCombatStats.builder().attackLevel(76).defenceLevel(38).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.combatScript("PestControlCS").deathAnimation(3903).blockAnimation(3902);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MELEE_STAB);
        style.damage(NpcCombatDamage.maximum(8));
        style.animation(3901).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
