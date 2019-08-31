package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Vanguard7527Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.OVERLOAD_PLUS_4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.REVITALISATION_PLUS_4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PRAYER_ENHANCE_PLUS_4)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ELDER_PLUS_4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.XERICS_AID_PLUS_4, 1, 2)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.VANGUARD_7527);
        combat.hitpoints(NpcCombatHitpoints.builder().total(133).alwaysShow(true).build());
        combat.stats(NpcCombatStats.builder().attackLevel(150).magicLevel(150).rangedLevel(150).defenceLevel(210).bonus(CombatBonus.MELEE_ATTACK, 20).bonus(CombatBonus.DEFENCE_STAB, 65).bonus(CombatBonus.DEFENCE_SLASH, 80).bonus(CombatBonus.DEFENCE_CRUSH, 100).bonus(CombatBonus.DEFENCE_MAGIC, 80).bonus(CombatBonus.DEFENCE_RANGED, 400).build());
        combat.aggression(NpcCombatAggression.builder().range(6).always(true).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).bind(true).build());
        combat.combatScript("VanguardCS").deathAnimation(7432);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.builder().maximum(16).prayerEffectiveness(0.5).build());
        style.animation(7441).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        style.attackCount(3);
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
