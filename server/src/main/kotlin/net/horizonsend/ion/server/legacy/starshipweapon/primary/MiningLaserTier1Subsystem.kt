package net.horizonsend.ion.server.legacy.starshipweapon.primary

import net.starlegacy.feature.starship.active.ActiveStarship
import net.starlegacy.feature.starship.subsystem.DirectionalSubsystem
import net.starlegacy.feature.starship.subsystem.weapon.WeaponSubsystem
import net.starlegacy.feature.starship.subsystem.weapon.interfaces.AutoWeaponSubsystem
import net.starlegacy.util.Vec3i
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class MiningLaserTier1Subsystem(
	override var face: BlockFace,
	starship: ActiveStarship,
	pos: Vec3i
) : WeaponSubsystem(starship, pos), DirectionalSubsystem, AutoWeaponSubsystem {
	override val powerUsage: Int = 1000
	override val range: Double = 200.0
	override fun getAdjustedDir(dir: Vector, target: Vector?): Vector {
		TODO("Not yet implemented")
	}

	override fun canFire(dir: Vector, target: Vector?): Boolean {
		TODO("Not yet implemented")
	}

	override fun isIntact(): Boolean {
		TODO("Not yet implemented")
	}

	override fun autoFire(target: Player, dir: Vector) {
		TODO("Not yet implemented")
	}
}
