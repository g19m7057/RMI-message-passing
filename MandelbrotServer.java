import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class MandelbrotServer implements MandelbrotCalculator
  { 
    Hashtable<String, LinkedList<String>> hashtable = new Hashtable<>(); //hash table to store the values

    public void insertMessage(String username, String values)
    {
      if(hashtable.containsKey(username)){
        LinkedList<String> list = hashtable.get(username);
        list.add(values);
      }
      else{
        LinkedList<String> list = hashtable.get(username);
        list.add(values);
        hashtable.put(username, list);
      }
    }

    public List<String> getMessage(String username)
    {
      return hashtable.get(username);
    }

    public byte[][] calculateMandelbrot (int xsize, int ysize, double x1, double x2, double y1, double y2)
      { 
        

        double x, y, xx, a, b = y1, da = x2/xsize, db = y2/ysize;
        byte[][] points = new byte[xsize][ysize];

        for (int i = 0; i < ysize; i++)
          { a = x1;
            for (int j = 0; j < xsize; j++)
              { byte n = 0;
                x = 0.0;
                y = 0.0;
                while ( (n < 100) && ( (x*x)+(y*y) < 4.0) )
                  { xx = x * x - y * y + a;
                    y = 2 * x * y + b;
                    x = xx;
                    n++;
                  }
                points[j][i] = n;
                a = a + da;
              }
            b = b + db;
          }
        return points;
      } // calculateMandelbrot

    public static void main (String args[])
      { try
          { MandelbrotServer server = new MandelbrotServer();
      	    MandelbrotCalculator stub = (MandelbrotCalculator)UnicastRemoteObject.exportObject(server, 0);

      	    // Bind the remote object's stub in the registry
      	    Registry registry = LocateRegistry.getRegistry();
      	    registry.bind("MandelbrotService", stub);

      	    System.err.println("Mandelbrot Server ready");
          }
        catch (Exception e)
          { System.err.println("Server exception: " + e.toString());
	          e.printStackTrace();
          }

      } // main

  } // class MandelbrotServer
