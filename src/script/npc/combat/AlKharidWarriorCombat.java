package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import lombok.var;

public class AlKharidWarriorCombat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().clue(NpcCombatDrop.ClueScroll.EASY, NpcCombatDropTable.CHANCE_1_IN_128);


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.AL_KHARID_WARRIOR_9);
        combat.hitpoints(NpcCombatHitpoints.total(19));
        combat.stats(NpcCombatStats.builder().attackLevel(7).defenceLevel(4).bonus(CombatBonus.ATTACK_STAB, 10)
                .bonus(CombatBonus.ATTACK_SLASH, 10).bonus(CombatBonus.ATTACK_CRUSH, 10)
                .bonus(CombatBonus.ATTACK_MAGIC, 10).bonus(CombatBonus.ATTACK_RANGED, 10)
                .bonus(CombatBonus.DEFENCE_STAB, 12).bonus(CombatBonus.DEFENCE_SLASH, 15)
                .bonus(CombatBonus.DEFENCE_CRUSH, 10).bonus(CombatBonus.DEFENCE_RANGED, 12).build());
        combat.deathAnimation(836).blockAnimation(1156);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MELEE_SLASH);
        style.damage(NpcCombatDamage.maximum(2));
        style.animation(451).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
