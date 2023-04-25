import java.applet.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.*;

public class StoreClient{

public static void main(String[] args){
      try
          { Registry registry = LocateRegistry.getRegistry();
      	    MandelbrotCalculator calc = (MandelbrotCalculator)registry.lookup("MandelbrotService");
            calc.insertMessage(args[0], args[1]);
      	  }
        catch (Exception e)
          { System.err.println("Client exception: " + e.toString());
      	    e.printStackTrace();
      	  }
    }
}