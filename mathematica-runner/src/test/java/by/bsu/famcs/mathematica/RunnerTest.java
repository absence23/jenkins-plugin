package by.bsu.famcs.mathematica;

import org.junit.Assert;
import org.junit.Test;

public class RunnerTest {

    @Test
    public void initTest() throws Exception {
        ScriptRunner.init("", "");
        ScriptRunner scriptRunner = ScriptRunner.getInstance();
        Assert.assertNotNull(scriptRunner);
    }
}
