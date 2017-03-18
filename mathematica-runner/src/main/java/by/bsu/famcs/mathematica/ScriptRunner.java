package by.bsu.famcs.mathematica;

import com.wolfram.jlink.*;

import java.util.StringTokenizer;

/**
 * Run Wolfram Mathematica scripts
 */
public class ScriptRunner implements AutoCloseable{
    private static final String KERNEL_LINK = "\\MathKernel.exe";
    private static final String JLINK_PATH = "\\SystemFiles\\Links\\JLink";
    private static final String LAUNCH_LINK = "-linkmode launch -linkname '%s'";
    private KernelLink kernelLink;

    public ScriptRunner(String mathematicaHome) throws MathLinkException {
        System.setProperty("com.wolfram.jlink.libdir", mathematicaHome + JLINK_PATH);
        kernelLink = MathLinkFactory.createKernelLink(String.format(LAUNCH_LINK, mathematicaHome + KERNEL_LINK));
    }

    public void run(String dir, String filename) throws MathLinkException {
        kernelLink.discardAnswer();
        String expr = "UsingFrontEnd[NotebookImport[ToFileName[\"" + dir + "\", \"" + filename + "\"], \"Input\"->\"Text\"]]";
        kernelLink.evaluate(expr);
        kernelLink.waitForAnswer();
        Expr expression = kernelLink.getExpr();
        System.out.println(expression.toString());
        String x = expression.toString();
        x = x.substring(2, x.length() - 2).replace("\r\n", "");
        System.out.println("Result = " + x);
        System.out.println("====================");
        StringTokenizer tokenizer = new StringTokenizer(x, ";", false);
        String token;
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            kernelLink.evaluate("ToExpression[" + token + "]");
            kernelLink.waitForAnswer();
            System.out.println(kernelLink.getExpr().toString());
        }
    }

    public boolean test(String dir, String filename, String value) throws MathLinkException{
        String expr = "UsingFrontEnd[NotebookEvaluate[ToFileName[\"" + dir + "\", \"" + filename + "\"]]]";
        kernelLink.discardAnswer();
        kernelLink.evaluate(expr);
        kernelLink.waitForAnswer();
        Expr expression = kernelLink.getExpr();
        System.out.println("Result = " + expression.toString());
        kernelLink.close();
        return expression.toString().equals(value);
    }

    public void close() {
        if (kernelLink != null) {
            kernelLink.close();
        }
    }
}