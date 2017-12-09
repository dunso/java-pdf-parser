import com.eclipsesource.v8.*;

import java.io.File;

public class Sample {

    public static void main(String[] args) {

        final NodeJS nodeJS = NodeJS.createNodeJS();
        final V8Object pafParser = nodeJS.require(new File(Sample.class.getResource("pdfparser.js").getPath()));
        V8 runtime = nodeJS.getRuntime();
        V8Function callback = new V8Function(runtime, (receiver, parameters) -> {
            Object o = parameters.get(0);
            System.out.println(o.toString());
            return null;
        });

        pafParser.executeJSFunction("getJson", resolveName(Sample.class.getResource("test.pdf").getPath()), callback);

        while (nodeJS.isRunning()) {
            nodeJS.handleMessage();
        }
        callback.release();
        pafParser.release();
        nodeJS.release();
    }

    private static String resolveName(String name) {
        if (name == null) {
            return name;
        }
        if (name.startsWith("/")) {
            name = name.substring(1);
        }
        return name;
    }
}