import java.applet.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.List;

public class GetClient{

public static void main(String[] args){
        List<String> list = null;
        try
          { Registry registry = LocateRegistry.getRegistry();
      	    MandelbrotCalculator calc = (MandelbrotCalculator)registry.lookup("MandelbrotService");
            list = calc.getMessage(args[0]);
      	  }
        catch (Exception e)
          { System.err.println("Client exception: " + e.toString());
      	    e.printStackTrace();
      	  }
        if(list == null){
            System.out.println("there are no messages for " + args[0]);
        }else{
            for (String message: list){
                System.out.println(message);
            }
        }
    }
}