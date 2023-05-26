package RerunScriptPlugin;

import plugin.Plugin;
import plugin.annotations.PluginMeta;
import plugin.api.*;

import java.util.Arrays;

@PluginMeta(
        author = "Hemuu",
        description = "Auto re-runs the bot script.",
        version = 1.2
)
public class plugin extends Plugin {
    private boolean isEnabled;
    private boolean hasReconnected;
    private String scriptName = null;

    @Override
    public void AfterLogin() {
        hasReconnected = true;
        System.out.println("AfterLogin called. Enabled: " + isEnabled + ". ScriptName: " + scriptName);
    }

    @Override
    public void OnLogin() {
        hasReconnected = true;
        System.out.println("OnLogin called. Enabled: " + isEnabled + ". ScriptName: " + scriptName);
    }

    @Override
    public void OnLogout() {
        hasReconnected = false;
        System.out.println("OnLogout called. Enabled: " + isEnabled + ". ScriptName: " + scriptName);
        isEnabled = false;
        scriptName = null;
        hasReconnected = false;
    }

    @Override
    public void Update() {
        if (!isEnabled) return;
        if (!hasReconnected) return;
        System.out.println("Update called. Running script: " + this.scriptName);
        API.DispatchCommand("::resumescript " + this.scriptName);
    }

    @Override
    public void ProcessCommand(String commandStr, String[] args) {
        if (commandStr.startsWith("::script")) {
            hasReconnected = false;
            isEnabled = true;
            System.out.println("Command received: " + commandStr);
            System.out.println("Args: " + Arrays.toString(args));
            scriptName = args.length > 0 ? args[0] : null;
        }
        if (commandStr.startsWith("::stopscript")) {
            System.out.println("Command received: " + commandStr);
            isEnabled = false;
            scriptName = null;
            hasReconnected = false;
        }
    }
}