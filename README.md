# ServerCalc
A Java SWT implementation of my original C++ ServerCalc
# How to build
Beyond the standard 'mvn package', you might have to make sure that artifactId of the org.eclipse.swt dependency matches the target system. The current pom works for a Windows system using a 64bit JRE. Changes will have to be made for differing OS's and JRE bit architectures.

http://stackoverflow.com/questions/292548/how-do-you-build-an-swt-application-with-maven/9985251#9985251 for more info.
