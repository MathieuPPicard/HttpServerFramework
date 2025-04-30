package theVaultHunter0.Server.Checker;

import java.lang.reflect.Method;
import java.util.logging.ErrorManager;

public class DefaultChecker {

    //Used isDefaultExist and isDefaultFonctionExist to verify if the configuration was done.
    public static Method verifyDefault(){
        Class<?> defaultClass = isDefaultExist();
        if(defaultClass != null){
            return isDefaultFonctionExist(defaultClass);
        }
        return null;
    }

    //Create function that check if the class Default.Default exist.
    private static Class<?> isDefaultExist(){
        String packageName = "theVaultHunter0.Default";
        String className = "Default";
        try {
            return Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            System.out.println("Class default not found.");
            return null;
        }
    }

    //Create function that check if in the class Default.Default, defaultInit() exist
    //If yes run it
    private static Method isDefaultFonctionExist(Class<?> nClass){
        String methodName = "defaultInit";
        if(nClass != null){
            try{
                Method nMethod = nClass.getMethod(methodName);
                System.out.println("Default method exist");
                return nMethod;
            } catch (NoSuchMethodException e){
                System.out.println("Method default not found.");
                return null;
            }
        }
        return null;
    }
}
