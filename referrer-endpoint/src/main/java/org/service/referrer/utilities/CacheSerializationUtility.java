package org.service.referrer.utilities;

import java.io.*;


/**
 * @author Prasanna Kumar Rajendran
 * Utility class for serialization and deserialization
 *
 */



public class CacheSerializationUtility {
	
	public static Object deserialize(String filePath)  {	
		  
	        File file = null;
			FileInputStream stream=null;
			ObjectInputStream ostream = null;
			BufferedInputStream bstream =null;
			Object obj = null;
			try {
				file = new File(filePath);
				if(file.exists()){
					stream = new FileInputStream(file);
					bstream = new BufferedInputStream(stream);
						if(bstream!=null && bstream.available()>0){
							ostream = new ObjectInputStream(bstream);
							obj = ostream.readObject();
						}
				}
				else{
					return null;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				try {
					//if(stream!=null)
					//stream.close();
					if(ostream!=null)
					ostream.close();
					//if(bstream!=null)
					//bstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	        
	        return obj;
	}
	
	public static Object serialize(String filePath,Object obj)  {	
		  
        
		FileOutputStream stream=null;
		ObjectOutputStream ostream = null;
		BufferedOutputStream bstream =null;
		try {
			File f = new File(filePath);
			f.getParentFile().mkdirs();
			stream = new FileOutputStream(f );
			bstream = new BufferedOutputStream(stream);
			ostream = new ObjectOutputStream(bstream);
			ostream.writeObject(obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				//stream.close();
				ostream.close();
				//bstream.close();
			} catch (IOException e) {	
				e.printStackTrace();
			}
		}
		
        
        return obj;
}	
	

}
