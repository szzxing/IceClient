package iceClient;

import PrinterInterface.PrinterPrx;
import PrinterInterface.PrinterPrxHelper;
import android.app.Activity;
import android.os.Bundle;


public class ClientActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		sendMsg();
	}
	
	private void sendMsg(){
		int status = 0;
		   Ice.Communicator ic = null;
		   try
		   {
		   ic = Ice.Util.initialize();
		    Ice.ObjectPrx base = ic
		      .stringToProxy("SimplePrinter:default -h 192.168.0.106 -p 8888");
		    PrinterPrx printer = PrinterPrxHelper.checkedCast(base);
		    if (printer == null)
		     throw new Error("Invalid proxy");
		    printer.printString("Hello World!");
		   }
		   catch (Ice.LocalException e)
		   {
		    e.printStackTrace();
		    status = 1;
		   }
		   catch (Exception e)
		   {
		    System.err.println(e.getMessage());
		    status = 1;
		   }
		   finally
		   {
		    if (ic != null)
		     ic.destroy();
		   }
		   System.exit(status);
		}

}
