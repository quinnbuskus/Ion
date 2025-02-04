package net.horizonsend.ion.server

import net.horizonsend.ion.server.IonServer.Companion.Ion
import org.bukkit.Bukkit
import org.bukkit.Keyed
import org.bukkit.Material
import org.bukkit.Material.AIR
import org.bukkit.Material.AMETHYST_SHARD
import org.bukkit.Material.BELL
import org.bukkit.Material.BLACK_WOOL
import org.bukkit.Material.BLUE_WOOL
import org.bukkit.Material.BROWN_WOOL
import org.bukkit.Material.CYAN_WOOL
import org.bukkit.Material.GOLD_BLOCK
import org.bukkit.Material.GRAY_WOOL
import org.bukkit.Material.GREEN_WOOL
import org.bukkit.Material.HONEYCOMB
import org.bukkit.Material.IRON_BLOCK
import org.bukkit.Material.LEATHER
import org.bukkit.Material.LIGHT_BLUE_WOOL
import org.bukkit.Material.LIGHT_GRAY_WOOL
import org.bukkit.Material.LIME_WOOL
import org.bukkit.Material.MAGENTA_WOOL
import org.bukkit.Material.NETHERITE_AXE
import org.bukkit.Material.NETHERITE_HOE
import org.bukkit.Material.NETHERITE_PICKAXE
import org.bukkit.Material.NETHERITE_SHOVEL
import org.bukkit.Material.NETHERITE_SWORD
import org.bukkit.Material.NETHER_WART
import org.bukkit.Material.NETHER_WART_BLOCK
import org.bukkit.Material.OAK_LOG
import org.bukkit.Material.OCHRE_FROGLIGHT
import org.bukkit.Material.ORANGE_WOOL
import org.bukkit.Material.PEARLESCENT_FROGLIGHT
import org.bukkit.Material.PINK_WOOL
import org.bukkit.Material.PRISMARINE
import org.bukkit.Material.PRISMARINE_BRICKS
import org.bukkit.Material.PRISMARINE_CRYSTALS
import org.bukkit.Material.PURPLE_WOOL
import org.bukkit.Material.RED_WOOL
import org.bukkit.Material.SADDLE
import org.bukkit.Material.SEA_LANTERN
import org.bukkit.Material.SHROOMLIGHT
import org.bukkit.Material.SLIME_BALL
import org.bukkit.Material.STICK
import org.bukkit.Material.STRING
import org.bukkit.Material.TRIPWIRE
import org.bukkit.Material.VERDANT_FROGLIGHT
import org.bukkit.Material.WARPED_FUNGUS_ON_A_STICK
import org.bukkit.Material.WHITE_WOOL
import org.bukkit.Material.YELLOW_WOOL
import org.bukkit.NamespacedKey
import org.bukkit.inventory.FurnaceRecipe
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

val forbiddenCraftingItems = enumSetOf(
	WARPED_FUNGUS_ON_A_STICK, NETHERITE_PICKAXE, NETHERITE_SHOVEL, NETHERITE_SWORD, NETHERITE_AXE, NETHERITE_HOE
)

fun initializeCrafting() {
	// Prismarine Bricks
	furnaceRecipe("prismarine_bricks", PRISMARINE_BRICKS, PRISMARINE, 1f, 200)

	// Bell
	shapedRecipe("bell", BELL) {
		shape("sos", "igi", "ggg")

		setIngredient('g', GOLD_BLOCK)
		setIngredient('i', IRON_BLOCK)
		setIngredient('o', OAK_LOG)
		setIngredient('s', STICK)
	}

	// Wool -> String
	val wool = arrayOf(
		LIGHT_BLUE_WOOL, LIGHT_GRAY_WOOL, MAGENTA_WOOL, ORANGE_WOOL, PURPLE_WOOL, YELLOW_WOOL, BLACK_WOOL, BROWN_WOOL,
		GREEN_WOOL, WHITE_WOOL, BLUE_WOOL, CYAN_WOOL, GRAY_WOOL, LIME_WOOL, PINK_WOOL, RED_WOOL
	)

	for (material in wool) {
		shapelessRecipe(material.name.lowercase(), ItemStack(STRING, 4), arrayOf(material))
	}

	// Saddle
	shapedRecipe("saddle", SADDLE) {
		shape("lll", "tat")

		setIngredient('l', LEATHER)
		setIngredient('t', TRIPWIRE)
		setIngredient('a', AIR)
	}

	// Ochre Froglight
	shapedRecipe("ochre_froglight", OCHRE_FROGLIGHT) {
		shape("xhx", "hlh", "xhx")

		setIngredient('h', HONEYCOMB)
		setIngredient('l', SHROOMLIGHT)
		setIngredient('x', AIR)
	}

	// Verdant Froglight
	shapedRecipe("verdant_froglight", VERDANT_FROGLIGHT) {
		shape("xsx", "sls", "xsx")

		setIngredient('s', SLIME_BALL)
		setIngredient('l', SHROOMLIGHT)
		setIngredient('x', AIR)
	}

	// Pearlescent Froglight
	shapedRecipe("pearlescent_froglight", PEARLESCENT_FROGLIGHT) {
		shape("xax", "ala", "xax")

		setIngredient('a', AMETHYST_SHARD)
		setIngredient('l', SHROOMLIGHT)
		setIngredient('x', AIR)
	}

	// Prismarine Crystals
	shapelessRecipe("prismarine_crystals", ItemStack(PRISMARINE_CRYSTALS, 4), arrayOf(SEA_LANTERN))

	// Nether Wart Block -> Nether Warts
	shapelessRecipe("nether_warts", ItemStack(NETHER_WART, 9), arrayOf(NETHER_WART_BLOCK))

	// Remove Unwanted Vanilla Recipes
	for (material in forbiddenCraftingItems) {
		for (recipe in Bukkit.getRecipesFor(ItemStack(material))) {
			if (recipe is Keyed) Bukkit.removeRecipe(recipe.key)
		}
	}
}

private fun furnaceRecipe(name: String, result: Material, source: Material, experience: Float, cookingTime: Int) {
	Bukkit.addRecipe(FurnaceRecipe(NamespacedKey(Ion, name), ItemStack(result), source, experience, cookingTime))
}

private fun shapedRecipe(name: String, result: Material, execute: ShapedRecipe.() -> Unit) {
	val recipe = ShapedRecipe(NamespacedKey(Ion, name), ItemStack(result))
	execute(recipe)
	Bukkit.addRecipe(recipe)
}

private fun shapelessRecipe(name: String, result: ItemStack, ingredients: Array<Material>) {
	val recipe = ShapelessRecipe(NamespacedKey(Ion, name), result)
	for (ingredient in ingredients) recipe.addIngredient(ingredient)
	Bukkit.addRecipe(recipe)
}
