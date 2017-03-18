package by.bsu.famcs.mathematica;

import org.junit.Assert;
import org.junit.Test;

public class RunnerTest {
    private static final String MATHEMATICA_HOME = "D:\\InstalledSoft\\Utils\\WolframMathematica";

    @Test
    public void runTest() throws Exception {
        ScriptRunner scriptRunner = new ScriptRunner(MATHEMATICA_HOME);
        scriptRunner.run("D:\\Documents", "test.nb");
        scriptRunner.close();
    }
    @Test
    public void testTest() throws Exception {
        ScriptRunner scriptRunner = new ScriptRunner(MATHEMATICA_HOME);
        Assert.assertTrue(scriptRunner.test("D:\\Documents", "test.nb", "4"));
        scriptRunner.close();
    }
}
