dependencies {
	compileOnly(libs.waterfall) // Platform

	compileOnly(libs.bundles.nuvotifier) // Other Plugin

	// Included
	implementation(libs.acfBungee)
	implementation(libs.jda)
}

tasks.shadowJar { archiveFileName.set("../../../build/IonProxy.jar") }
