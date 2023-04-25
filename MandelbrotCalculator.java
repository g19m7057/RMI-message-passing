import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

/** This interface specifies the remote object
  * methods
  */
public interface MandelbrotCalculator extends Remote
  {
    public void insertMessage(String username, String values) throws RemoteException;
    public List<String> getMessage(String username) throws RemoteException;

    public byte[][] calculateMandelbrot (int xsize, int ysize, double x1, double x2, double y1, double y2) throws RemoteException;
  } // interface MandelbrotCalculator
