package by.bsu.famcs.mathematica;

import com.wolfram.jlink.*;

/**
 * Run Wolfram Mathematica scripts
 */
public class ScriptRunner {

    private static final String KERNEL_LINK = "-linkmode launch -linkname '%s'";
    private static ScriptRunner scriptRunner;
    private String kernelPath;
    private String jLinkPath;
    private KernelLink kernelLink;

    private ScriptRunner(String kernelPath, String jLinkPath) throws MathLinkException {
        this.kernelPath = kernelPath;
        this.jLinkPath = jLinkPath;
        kernelLink = MathLinkFactory.createKernelLink(String.format(KERNEL_LINK, kernelPath));
        System.setProperty("com.wolfram.jlink.libdir", jLinkPath);
    }

    public static ScriptRunner getInstance() {
        return scriptRunner;
    }

    private static void init(String kernelPath, String jLinkPath) throws MathLinkException {
        scriptRunner = new ScriptRunner(kernelPath, jLinkPath);
    }
}