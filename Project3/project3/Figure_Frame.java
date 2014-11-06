package project3;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Make sure you have consistency with how you're naming your files, variables, and classes.  In particular, this class and its respective file should be called "FigureFrame" rather than "Figure_Frame".  Same goes for your other classes that break the convention
 */
public class Figure_Frame
{
	private String name;
    private ArrayList<Object_Frame> object_frames = new ArrayList<>();

    public Figure_Frame(RavensFigure figure)
    {
    	this.name = figure.getName();

        ArrayList<RavensObject> objects = figure.getObjects();
        // I'd also get into the habit of making your variable names as descriptive as possible.
        // I don't know much about the problem you're solving here, but it seems this should be called 'ravens_objects'
        // or something
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
