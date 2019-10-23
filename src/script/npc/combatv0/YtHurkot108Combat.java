package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class YtHurkot108Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.YT_HURKOT_108);
        combat.hitpoints(NpcCombatHitpoints.total(60));
        combat.stats(NpcCombatStats.builder().attackLevel(140).magicLevel(120).rangedLevel(120).defenceLevel(60)
                .bonus(CombatBonus.DEFENCE_MAGIC, 100).bonus(CombatBonus.DEFENCE_RANGED, 100).build());
        combat.combatScript("YtHurKotCS").deathAnimation(2638).blockAnimation(2635);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MELEE_CRUSH);
        style.damage(NpcCombatDamage.maximum(14));
        style.animation(2637).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
