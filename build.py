class Compile:
	def __init__(self):
		self.run()

	def run(self):
		import os
		os.system("cd B.O.P.E && gradlew setupDecompWorkspace --stop && gradlew clean build")
		
		import shutil
		try:
			shutil.copyfile("B.O.P.E/build/libs/rina-bope.jar", os.getenv("APPDATA") + "\\.minecraft\\mods\\turok-b0.4-release.jar")
			os.system("start C:/Users/Public/Desktop/Minecraft_Launcher")
			print("Copiadokkk")
		except:
			print("Ta sem o lib fodase")

		import sys
		sys.exit()

Compile()