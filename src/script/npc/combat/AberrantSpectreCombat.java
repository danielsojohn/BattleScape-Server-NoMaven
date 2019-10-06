package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.HitpointsBar;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.item.Item;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.NpcCombatSlayer;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.player.Player;
import lombok.var;
import script.map.area.CatacombsOfKourendArea;

public class AberrantSpectreCombat extends NpcCombat {
	private static final NpcCombatDropTable SUPERIOR_DROP_TABLE = NpcCombatDropTable.builder().chance(1.07).log(true)
			.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.IMBUED_HEART, 1, 1, 1)))
			.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ETERNAL_GEM, 1, 1, 1)))
			.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DUST_BATTLESTAFF, 1, 1, 3)))
			.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MIST_BATTLESTAFF, 1, 1, 3))).build();
	private static final NpcCombatDropTable TOTEM_DROP_TABLE =
			NpcCombatDropTable.builder().chance(0.33).order(NpcCombatDropTable.Order.RANDOM_UNIQUE)
					.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DARK_TOTEM_BASE)))
					.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DARK_TOTEM_MIDDLE)))
					.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DARK_TOTEM_TOP))).build();
	private static final NpcCombatDropTable SHARD_DROP_TABLE = NpcCombatDropTable.builder().chance(0.49)
			.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_SHARD))).build();

	private Npc npc;

	@Override
	public List<NpcCombatDefinition> getCombatDefinitions() {
		var drop = NpcCombatDrop.builder().rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256)
				.clue(NpcCombatDrop.ClueScroll.HARD, NpcCombatDropTable.CHANCE_1_IN_128);
		var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_512);
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MYSTIC_ROBE_BOTTOM_DARK)));
		drop.table(dropTable.build());
		dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_RARE);
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DWARF_WEED_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SNAPDRAGON_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TORSTOL_SEED)));
		drop.table(dropTable.build());
		dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CADANTINE_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.KWUARM_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.POISON_IVY_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LANTADYME_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_CADANTINE, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_LANTADYME, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_DWARF_WEED, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STEEL_AXE)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MITHRIL_KITESHIELD)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LAVA_BATTLESTAFF)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ADAMANT_PLATELEGS)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_FULL_HELM)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 460)));
		drop.table(dropTable.build());
		dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BELLADONNA_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CACTUS_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.IRIT_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TOADFLAX_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.AVANTOE_SEED)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_GUAM_LEAF, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_MARRENTILL, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_TARROMIN, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_HARRALANDER, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_RANARR_WEED, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_IRIT_LEAF, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_AVANTOE, 1, 3)));
		dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_KWUARM, 1, 3)));
		drop.table(dropTable.build());


		var normalCombat = NpcCombatDefinition.builder();
		normalCombat.id(NpcId.ABERRANT_SPECTRE_96);
		normalCombat.hitpoints(NpcCombatHitpoints.total(90));
		normalCombat.stats(NpcCombatStats.builder().magicLevel(105).defenceLevel(90)
				.bonus(CombatBonus.MELEE_DEFENCE, 20).bonus(CombatBonus.DEFENCE_RANGED, 15).build());
		normalCombat.slayer(NpcCombatSlayer.builder().level(60).superiorId(NpcId.ABHORRENT_SPECTRE_253).build());
		normalCombat.aggression(NpcCombatAggression.PLAYERS);
		normalCombat.type(NpcCombatType.UNDEAD).deathAnimation(1508).blockAnimation(1509);
		normalCombat.drop(drop.build());

		var style = NpcCombatStyle.builder();
		style.type(NpcCombatStyleType.MAGIC);
		style.damage(NpcCombatDamage.maximum(11));
		style.animation(1507).attackSpeed(4);
		style.targetGraphic(new Graphic(336, 300));
		style.projectile(NpcCombatProjectile.id(335));
		normalCombat.style(style.build());


		var catacombsCombat = NpcCombatDefinition.builder();
		catacombsCombat.id(NpcId.DEVIANT_SPECTRE_169);
		catacombsCombat.hitpoints(NpcCombatHitpoints.total(190));
		catacombsCombat.stats(NpcCombatStats.builder().magicLevel(205).defenceLevel(90)
				.bonus(CombatBonus.MELEE_DEFENCE, 80).bonus(CombatBonus.DEFENCE_RANGED, 85).build());
		catacombsCombat.slayer(NpcCombatSlayer.builder().level(60).superiorId(NpcId.REPUGNANT_SPECTRE_335).build());
		catacombsCombat.aggression(NpcCombatAggression.PLAYERS);
		catacombsCombat.killCount(NpcCombatKillCount.builder().asName("Aberrant spectre").build());
		catacombsCombat.type(NpcCombatType.UNDEAD).deathAnimation(1508).blockAnimation(1509);
		catacombsCombat.drop(drop.build());

		style = NpcCombatStyle.builder();
		style.type(NpcCombatStyleType.MAGIC);
		style.damage(NpcCombatDamage.maximum(21));
		style.animation(1507).attackSpeed(4);
		style.targetGraphic(new Graphic(336, 300));
		style.projectile(NpcCombatProjectile.id(335));
		catacombsCombat.style(style.build());


		var superiorCombat = NpcCombatDefinition.builder();
		superiorCombat.id(NpcId.ABHORRENT_SPECTRE_253);
		superiorCombat.hitpoints(NpcCombatHitpoints.builder().total(250).bar(HitpointsBar.GREEN_RED_60).build());
		superiorCombat.stats(NpcCombatStats.builder().magicLevel(300).defenceLevel(180)
				.bonus(CombatBonus.MELEE_DEFENCE, 40).bonus(CombatBonus.DEFENCE_RANGED, 30).build());
		superiorCombat.slayer(NpcCombatSlayer.builder().level(60).experience(2500).build());
		superiorCombat.aggression(NpcCombatAggression.PLAYERS);
		superiorCombat.killCount(NpcCombatKillCount.builder().asName("Superior Slayer Creature").build());
		superiorCombat.type(NpcCombatType.UNDEAD).deathAnimation(6369).blockAnimation(6370);
		superiorCombat.drop(drop.rolls(3).build());

		style = NpcCombatStyle.builder();
		style.type(NpcCombatStyleType.MAGIC);
		style.damage(NpcCombatDamage.maximum(31));
		style.animation(6368).attackSpeed(4);
		style.targetGraphic(new Graphic(336, 300));
		style.projectile(NpcCombatProjectile.id(335));
		superiorCombat.style(style.build());


		return Arrays.asList(normalCombat.build(), catacombsCombat.build(), superiorCombat.build());
	}

	@Override
	public void spawnHook() {
		npc = getNpc();
	}

	@Override
	public void deathDropItemsHook(Player player, int index, Tile dropTile) {
		if (npc.getArea().matches(CatacombsOfKourendArea.class)) {
            if (npc.getId() == NpcId.ABHORRENT_SPECTRE_253 || TOTEM_DROP_TABLE.canDrop(npc, player)) {
                TOTEM_DROP_TABLE.dropItems(npc, player, dropTile);
            }
            if (SHARD_DROP_TABLE.canDrop(npc, player)) {
                SHARD_DROP_TABLE.dropItems(npc, player, dropTile);
            }
        }
	}

	@Override
    public List<Item> deathDropItemsGetItemsHook(Npc npc, Player player, Tile dropTile, int dropRateDivider, int roll,
            NpcCombatDropTable table, List<Item> items) {
        if (npc.getId() == NpcId.ABHORRENT_SPECTRE_253 && SUPERIOR_DROP_TABLE.canDrop(npc, player)) {
            return SUPERIOR_DROP_TABLE.getItems(npc, player, dropTile, dropRateDivider, roll);
        }
        return items;
    }
}
