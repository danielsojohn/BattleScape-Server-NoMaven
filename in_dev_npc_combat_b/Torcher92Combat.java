package script.npc.combat;

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
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Torcher92Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.TORCHER_92);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(100).build());
        combat.hitpoints(NpcCombatHitpoints.total(142));
        combat.stats(NpcCombatStats.builder().magicLevel(91).defenceLevel(46).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.combatScript("PestControlCS").deathAnimation(3881).blockAnimation(3880);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).subType(HitType.RANGED).build());
        style.damage(NpcCombatDamage.builder().maximum(12).splashOnMiss(true).build());
        style.animation(3882).attackSpeed(4);
        style.castGraphic(new Graphic(646)).targetGraphic(new Graphic(648, 124));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
