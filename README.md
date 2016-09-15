# LazyJarLoader
a simple demo to introduce loading jar dynamically on android platform

+## Project Structure
 +
 +#### app
 +app is a standard Android Application,which use dynamic jar as a library.
 +
 +#### testjar
 +
 +a simple 3rd party SDK just for test 
 
 +## How to use
 +
 + copy the jar to the assets folder
 + Methods in IJarInvoker.java
 
 + public Object call(String cls, Object obj, String func, Class[] parameterTypes, Object[] objects) {
 +        try {
 +            ClsProxy clsProxy = getClsProxy(cls);
 +            if (clsProxy != null) {
 +                return clsProxy.call(obj, func, parameterTypes, objects);
 +            }
 +        } catch (Exception e) {
 +            e.printStackTrace();
 +        }
 +        return null;
 +    }
 
 +## How to make jar
 +
 +
 +The sdk3rd_dex jar file should use __dx__  to convert to a jar includes __Dalvik Executable__ (__dex__) file 
 +
 +The command:
 +
 +> dx --dex --output=classes.dex sdk.jar
 +> aapt add sdk3rd_dex.jar classes.dex