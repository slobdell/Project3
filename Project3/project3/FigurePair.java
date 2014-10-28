package project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FigurePair

{
	private Figure_Frame source, target;
	private HashMap<String, ArrayList<Correspondence>> allCorrespondences;
	private HashMap<String, Correspondence> finalCorrespondences;

	
	public FigurePair(Figure_Frame sourceFigure, Figure_Frame targetFigure)
	{
		
		this.source = sourceFigure;
		this.target = targetFigure;
		
		this.calculateSimilarities();
		this.setCorrespondences();
    	
	}
	
	 
	 public void printCorrespondences()
	 {
	    
		 for(String key: this.finalCorrespondences.keySet())
		 {
			 this.finalCorrespondences.get(key).printCorrespondence();
		 }
	    	
	    		
	    	
	 }
	 
	


	 

	 
	
	 private void setCorrespondences()
	  {
	    	Correspondence bestCorrespondence = null;
	    	ArrayList<Correspondence> correspondences = null; 
	    	HashMap<String, Correspondence> setCorrespondences = new HashMap <>();
	    	
	    	for (int i = 0; i < this.source.getObjects().size(); i++)
	    	{
	    		correspondences = this.allCorrespondences.get(this.source.getObjects().get(i).getName());
	    		
	    		if (correspondences != null && correspondences.size() > 0)
	    		{
	    			bestCorrespondence = findMaxCorrespondenceObj(correspondences);
	    			setCorrespondences.put(bestCorrespondence.getCurrent().getName(), bestCorrespondence);   			
	    		}
	    		
	    	}
	    	
	    	this.finalCorrespondences = setCorrespondences;
	
	    }
	 
	    public void calculateSimilarities()
	    {
	        HashMap<String, ArrayList<Correspondence>> correspondenceMap = new HashMap<>();

	    	ArrayList<Correspondence> correspondences = new ArrayList<>();
	    	ArrayList<Object_Frame> comparisonObjectFrames = this.target.getObjects();
	    	
	    	Object_Frame currentObjectFrame;
	    	Object_Frame comparisonObjectFrame;
	    	
	    	double temp_similarity = 0.0;	
	    	double max_similarity = 0.0;

	    	if (this.source.getObjects().size() > comparisonObjectFrames.size())
	    	{
	    		
		    		int deletionGoal = source.getObjects().size() - comparisonObjectFrames.size();
		    		//object gets deleted
		    		for (int i = 0; i < this.source.getObjects().size(); i++)
			    	{
			    		currentObjectFrame = source.getObjects().get(i);
			    		correspondences = new ArrayList<>();
			    		
			    		for(int j = 0; j < comparisonObjectFrames.size(); j++)
			    		{
			    			comparisonObjectFrame = comparisonObjectFrames.get(j);
			    			temp_similarity = currentObjectFrame.DetermineSimilarity(comparisonObjectFrame);
			    			Correspondence Corr = new Correspondence(currentObjectFrame, comparisonObjectFrame, temp_similarity);
			    			correspondences.add(Corr);
			    			
			    		}
			    		
			    		
			    		correspondenceMap.put(currentObjectFrame.getName(), correspondences);
			    		
			    	}
		    		
		    		for (int i = 0; i < deletionGoal; i++)
			    	{
		
		    			String objDelete = this.findMinAvgCorrespondenceFig(correspondenceMap);	
		    			correspondenceMap.remove(objDelete);
			    	}
	    	}
	    	else
	    	{
	    		//same number of objects
		    	for (int i = 0; i < this.source.getObjects().size(); i++)
		    	{
		    		currentObjectFrame = source.getObjects().get(i);
		    		correspondences = new ArrayList<>();
		    		
		    		for(int j = 0; j < comparisonObjectFrames.size(); j++)
		    		{
		    			comparisonObjectFrame = comparisonObjectFrames.get(j);
		    			temp_similarity = currentObjectFrame.DetermineSimilarity(comparisonObjectFrame);
		    			Correspondence Corr = new Correspondence(currentObjectFrame, comparisonObjectFrame, temp_similarity);
		    			correspondences.add(Corr);
		    			
		    		}
		    		
		    		
		    		//System.out.println("Putting an objects list of correspondences into the Hash Map");
		    		
		    		correspondenceMap.put(currentObjectFrame.getName(), correspondences);
		    		
		    	}
	    	}
	    	
	    	this.allCorrespondences = correspondenceMap;
	    	
	    }
	    

	    private Correspondence findMaxCorrespondenceObj(ArrayList<Correspondence> correspondences)
	    {
	    	Correspondence tempCorrespondence, maxCorrespondence;
	    	maxCorrespondence = correspondences.get(0);
	    	for (int i = 0; i < correspondences.size(); i++)
	    	{
	    		tempCorrespondence = correspondences.get(i);
	    		if (tempCorrespondence.greatherThan(maxCorrespondence))
	    		{
	    			maxCorrespondence = tempCorrespondence;
	    		}
	    	}
	    	
	    	return maxCorrespondence;
	    }
	    
	    private Correspondence findMinCorrespondenceObj(ArrayList<Correspondence> correspondences)
	    {
	    	Correspondence tempCorrespondence, minCorrespondence;
	    	minCorrespondence = correspondences.get(0);
	    	for (int i = 0; i < correspondences.size(); i++)
	    	{
	    		tempCorrespondence = correspondences.get(i);
	    		if (tempCorrespondence.lessThan(minCorrespondence))
	    		{
	    			minCorrespondence = tempCorrespondence;
	    		}
	    	}
	    	
	    	return minCorrespondence;
	    }
	    
	    private double findAvgCorrespondenceObj(ArrayList<Correspondence> correspondences)
	    {
	    	Correspondence tempCorrespondence; //, minCorrespondence;
	    	double sum = 0.0;
	    	//minCorrespondence = correspondences.get(0);
	    	for (int i = 0; i < correspondences.size(); i++)
	    	{
	    		tempCorrespondence = correspondences.get(i);
	    		sum += tempCorrespondence.getSimilarity();
	    	}
	    	return sum/correspondences.size();
	    }
	    private String findMinAvgCorrespondenceFig(HashMap<String, ArrayList<Correspondence>> correspondenceHashMap)
	    {
	    	double tempAvg, minAvg;
	    	minAvg = 1.0;
	    	String objMin = "";
	    	
	    	for (int i = 0; i < source.getObjects().size(); i++)
	    	{
	    		if (correspondenceHashMap.get(this.source.getObjects().get(i).getName()) != null)
	    		{
	        		tempAvg = findAvgCorrespondenceObj(correspondenceHashMap.get(this.source.getObjects().get(i).getName()));

	    		}
	    		else
	    		{
	    			tempAvg = 0;
	    		}
	    		if (tempAvg < minAvg)
	    		{
	    			minAvg = tempAvg;
	    			objMin = this.source.getObjects().get(i).getName();
	    		}
	    	}
	    	
	    	return objMin;
	    }
	    private Correspondence findMinCorrespondenceFig(HashMap<String, ArrayList<Correspondence>> correspondenceHashMap)
	    {
	       	Correspondence tempCorrespondence, minCorrespondence;
	    	ArrayList<Correspondence> correspondences = correspondenceHashMap.get(this.source.getObjects().get(0).getName());
	    	
	    	minCorrespondence = findMinCorrespondenceObj(correspondences);
	    	
	    	for (int i = 0; i < source.getObjects().size(); i++)
	    	{
	    		tempCorrespondence = findMinCorrespondenceObj(correspondenceHashMap.get(this.source.getObjects().get(i).getName()));
	    		if (tempCorrespondence.lessThan(minCorrespondence))
	    		{
	    			minCorrespondence = tempCorrespondence;
	    		}
	    	}
	    	//System.out.println ("Min Correspondence is " + minCorrespondence.getCurrent().getName() + " to " + minCorrespondence.getGoal().getName() + " with a " + minCorrespondence.getSimilarity());
	    	return minCorrespondence;
	    }
	    
	    

	   
	 
	    private void addObjects(Figure_Frame goalFrame)
	    {
	    	String goalName = goalFrame.getName();
	    	ArrayList<Object_Frame> goalObjects = goalFrame.getObjects();
	    	Object_Frame currentObject;
	    	boolean contains = false;

	    	for (int i = 0; i < goalObjects.size(); i++)
			{
	    		contains = false;
				currentObject = goalObjects.get(i);

		    	for(String key: this.finalCorrespondences.keySet())
				 {
					if(this.finalCorrespondences.get(key).getGoal() != null)
					{
		    			if(this.finalCorrespondences.get(key).getGoal().getName().equals(currentObject.getName()))
		    			{
		    				contains = true;
		    			}
					}
		    			
		    			
				}
		    	
		    	if (!contains)
				{
					ArrayList<Transformation> singleTransformation = new ArrayList<>();
					singleTransformation.add(new Transformation(currentObject.getName(), "", "added"));
					Correspondence newCorrespondence = new Correspondence(currentObject, null, 1.0);
					newCorrespondence.setTransformations(singleTransformation);
					this.finalCorrespondences.put(currentObject.getName(), newCorrespondence);

				}   
	    	}
	    		//return finalCorrespondences;
	    }
	    
	    
	 
	 
	 
	 public HashMap<String, Correspondence> getCorrespondences()
	 {
		 return this.finalCorrespondences;
	 }


	 
	 public Figure_Frame getTarget()
	 {
		 return this.target;
	 }
	 
	 public Figure_Frame getSource()
	 {
		 return this.source;
	 }

	 
}
