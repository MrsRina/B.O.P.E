class Compile:
	def __init__(self):
		self.run()

	def run(self):
		import os	
		#os.system("cd B.O.P.E && gradlew setupDecompWorkspace --stop && gradlew clean build")
		os.system("cd B.O.P.E && gradlew runClient --stop && gradlew clean build")

		import shutil
		try:
			shutil.copyfile("B.O.P.E/build/libs/bope-0.1-all.jar", os.getenv("APPDATA") + "\\.minecraft\\mods\\bope-0.1.jar")
			os.system("start C:/Users/Public/Desktop/Minecraft_Launcher")
			print("Copiadokkk")
		except:
			print("Ta sem o lib fodase")

		import sys
		sys.exit()

Compile();