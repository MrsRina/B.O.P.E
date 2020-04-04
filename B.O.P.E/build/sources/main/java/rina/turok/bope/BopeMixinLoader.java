package rina.turok.bope;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

public class BopeMixinLoader implements IFMLLoadingPlugin {
	public BopeMixinLoader() {
		// Gay:
		MixinBootstrap.init();

		// Inject the gay on:
		Mixins.addConfiguration("mixins.bope.json");

		// Set obfuscation like trap:
		MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[0];
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Nullable
	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}