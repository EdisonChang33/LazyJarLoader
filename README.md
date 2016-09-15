# LazyJarLoader
a simple demo to introduce loading jar dynamically on android platform

## Project Structure

#### app
app is a standard Android Application,which use dynamic jar as a library.

#### testjar

a simple 3rd party SDK just for test 
 
## How to use

(1)Copy the jar to the assets folder

(2)Read the fucking source code

## How to make jar

The sdk3rd_dex jar file should use __dx__  to convert to a jar includes __Dalvik Executable__ (__dex__) file 

The command:
dx --dex --output=classes.dex sdk.jar

aapt add sdk3rd_dex.jar classes.dex