package me.srgantmoomoo.postman;

import me.srgantmoomoo.postman.command.CommandManager;
import me.srgantmoomoo.postman.config.Load;
import me.srgantmoomoo.postman.config.Save;
import me.srgantmoomoo.postman.module.ModuleManager;
import me.srgantmoomoo.postman.module.setting.SettingManager;
import net.fabricmc.api.ModInitializer;

//ocnarf
public class Main implements ModInitializer {
    int strong;
    int postman = strong;

    public final String MODID = "postman";
    public final String NAME = "postman";
    public final String VERSION = "4.0";

    public static Main INSTANCE;

    public Main() {
        INSTANCE = this;
    }

    public ModuleManager moduleManager;
    public SettingManager settingManager;
    public CommandManager commandManager;
    public ClickGui clickGui;
    public Save save;
    public Load load;

    @Override
    public void onInitialize() {
        moduleManager = new ModuleManager();
        settingManager = new SettingManager();
        commandManager = new CommandManager();
        clickGui = new ClickGui();

        load = new Load();
        save = new Save();
    }
}
