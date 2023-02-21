package net.horizonsend.ion.server.legacy.starshipweapon.multiblock

import net.horizonsend.ion.server.legacy.starshipweapon.primary.CthulhuBeamSubsystem
import net.horizonsend.ion.server.legacy.starshipweapon.primary.MiningLaserTier1Subsystem
import net.kyori.adventure.text.Component
import net.starlegacy.feature.multiblock.LegacyMultiblockShape
import net.starlegacy.feature.multiblock.starshipweapon.StarshipWeaponMultiblock
import net.starlegacy.feature.starship.active.ActiveStarship
import net.starlegacy.util.Vec3i
import org.bukkit.block.BlockFace

sealed class miningLaserMultiblockTier1 : StarshipWeaponMultiblock<MiningLaserTier1Subsystem>() {
	override fun createSubsystem(starship: ActiveStarship, pos: Vec3i, face: BlockFace): MiningLaserTier1Subsystem {
		val adjustedFace = getAdjustedFace(face)
		return MiningLaserTier1Subsystem(adjustedFace, starship, pos)
	}

	protected abstract fun getAdjustedFace(originalFace: BlockFace): BlockFace
}
object miningLaserTier1Top : miningLaserMultiblockTier1() {
	override fun getAdjustedFace(originalFace: BlockFace): BlockFace = BlockFace.UP
	override val name: String
		get() = TODO("Not yet implemented")
	override val signText: Array<Component?>
		get() = TODO("Not yet implemented")

	override fun LegacyMultiblockShape.buildStructure() {
		at(+0, +0, +0).noteBlock()
		at(+0, +1, +0).ironBlock()
		at(+0, +2, +0).lodestone()
	}
}
object miningLaserTier1Bottom : miningLaserMultiblockTier1() {
	override fun getAdjustedFace(originalFace: BlockFace): BlockFace = BlockFace.UP
	override val name: String
		get() = TODO("Not yet implemented")
	override val signText: Array<Component?>
		get() = TODO("Not yet implemented")

	override fun LegacyMultiblockShape.buildStructure() {
		at(+0, +0, +0).noteBlock()
		at(+0, +1, +0).ironBlock()
		at(+0, +2, +0).lodestone()
	}
}

