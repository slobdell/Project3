package project3;

import java.util.ArrayList;
import java.util.HashMap;

public class Figure_Frame 
{
	private String name;
    private ArrayList<Object_Frame> object_frames = new ArrayList<>();
    
    public Figure_Frame(RavensFigure figure)
    {
    	this.name = figure.getName();
    	
        ArrayList<RavensObject> objects = figure.getObjects();
        Object_Frame object;
         
        for (int i = 0; i < objects.size(); i++)
        {
        	object = new Object_Frame(objects.get(i));
        	object_frames.add(object);
        }

    }
    
    public ArrayList<Object_Frame> getObjects()
    {
    	return this.object_frames;
    }
    

   
    
    public String getName()
    {
    	return this.name;
    }
    
   


}
