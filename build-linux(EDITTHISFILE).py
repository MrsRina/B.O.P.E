class Compile:
	def __init__(self):
		self.run()

	def run(self):
		import os	
		os.system("cd B.O.P.E && chmod +x gradlew && ./gradlew setupDecompWorkspace --stop && ./gradlew clean build")
		#os.system("cd B.O.P.E && gradlew runClient --stop && gradlew clean build")

		import shutil
		try:
			shutil.copyfile("B.O.P.E/build/libs/bope-0.4-all.jar", "/home/putyourhomeusernamehere/.minecraft/mods/B.O.P.E-0.4.jar") # change the putyourhomeusernamehere to your home folder like /home/hero
			print("Copiadokkk")
		except:
			print("Ta sem o lib fodase")

		import sys
		sys.exit()

Compile();