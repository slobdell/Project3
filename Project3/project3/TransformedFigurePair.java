package project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransformedFigurePair extends FigurePair

{

	private double easeOfTransformation;
	ArrayList<String> figureTransformations = new ArrayList<>();
	
	public TransformedFigurePair(Figure_Frame sourceFigure, Figure_Frame targetFigure)
	{
		super(sourceFigure, targetFigure);
		this.determineTransformations();   	
	}

	 public void printTransformations()
	 {
	    
		 for(String key: super.getCorrespondences().keySet())
		 {
			 super.getCorrespondences().get(key).printTransformations();

		 }
	    	
	  }
	 

	 


	 public boolean Match(TransformedFigurePair comparisonPair)
	 {
		boolean isMatch = false;
		int transformationMatchCounter = 0;
		FigurePair sourcePairing = new FigurePair(super.getSource(), comparisonPair.getSource());
		if (super.getSource().getObjects().size() == comparisonPair.getSource().getObjects().size())
		{
			for (int i = 0; i < super.getSource().getObjects().size(); i ++)
			{
				String givenSourceObjName = super.getSource().getObjects().get(i).getName();
				String comparisonSourceObjName = sourcePairing.getCorrespondences().get(givenSourceObjName).getGoal().getName();
				Correspondence comparisonCorrespondence = comparisonPair.getCorrespondences().get(comparisonSourceObjName);
				
				if(super.getCorrespondences().get(givenSourceObjName).transformationsEqual(comparisonCorrespondence))
				{
					 transformationMatchCounter++;
				}
			}
			
	
			 
			 if (transformationMatchCounter == super.getCorrespondences().size())
			 {
				 	isMatch = true;
			 }
		}
		 return isMatch;
	 }
	 
	
	 

	 
	 public int transformationSimilarity(TransformedFigurePair comparisonPair, HashMap<String, Correspondence> pairCorrespondences)
	 {
		boolean isMatch = false;
		int transformationMatchCounter = 0;

		 for(String key: super.getCorrespondences().keySet())
		 {
			 if (pairCorrespondences.get(key) != null)
			 {
				
				 String correspondingObject = pairCorrespondences.get(key).getGoal().getName();
				 //System.out.println(key + " to " + correspondingObject);
				 Correspondence comparisonCorrespondence = comparisonPair.getCorrespondences().get(correspondingObject);
				 if(super.getCorrespondences().get(key).transformationsEqual(comparisonCorrespondence))
				 {
					 transformationMatchCounter++;
				 }
				 else
				 {
					 //System.out.println("Object " + key + " is not a match!");
				 }
			 }
			 
	     }
		
		 return 	transformationMatchCounter;

	 }
	     

	   
	 private void determineTransformations()
	 {
	    	String goalName = super.getTarget().getName();
	    	HashMap <String, String> correspondences = new HashMap<>();
	    	Object_Frame currentObject;
	    	ArrayList<Transformation> transformations = null;


	    	
	    	for (int i = 0; i < super.getSource().getObjects().size(); i++)
	    	{
	    			currentObject = super.getSource().getObjects().get(i);
	    			transformations = null;
	    			if (super.getCorrespondences().get(currentObject.getName()) != null)
	    			{
	        			Object_Frame goalObject = super.getCorrespondences().get(currentObject.getName()).getGoal();

	    				transformations = currentObject.determineTransformations(goalObject);
	    				super.getCorrespondences().get(currentObject.getName()).setTransformations(transformations);
	    				
	    				if (transformations.isEmpty())
	    				{
	    					ArrayList<Transformation> singleTransformation = new ArrayList<>();
	    					singleTransformation.add(new Transformation(currentObject.getName(), goalObject.getName(), "unchanged"));
	    					super.getCorrespondences().get(currentObject.getName()).setTransformations(singleTransformation);

	    				}

	    			}
	    			else
	    			{

	    				
	    				ArrayList<Transformation> singleTransformation = new ArrayList<>();
	    				singleTransformation.add(new Transformation(currentObject.getName(), "--", "deleted"));
	    				Correspondence newCorrespondence = new Correspondence(super.getSource().getObjects().get(i), null, 1.0);
	    				newCorrespondence.setTransformations(singleTransformation);
	    				super.getCorrespondences().put(currentObject.getName(), newCorrespondence);

	    			}
	    			
	    		}
	    		//this.finalCorrespondences = transformedCorrespondences;
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

		    	for(String key: super.getCorrespondences().keySet())
				 {
					if(super.getCorrespondences().get(key).getGoal() != null)
					{
		    			if(super.getCorrespondences().get(key).getGoal().getName().equals(currentObject.getName()))
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
					super.getCorrespondences().put(currentObject.getName(), newCorrespondence);

				}   
	    	}
	    		//return finalCorrespondences;
	    }
	    
	    public int cumulativeEase(HashMap<String, ArrayList<Transformation>> transformationHashMap)
	    {
	    	int sum = 0;
	    	ArrayList<Transformation> transformations;
	    	Transformation transformation;
	    	for(int i = 0; i < super.getSource().getObjects().size(); i++)
	    	{
	    		transformations = transformationHashMap.get(super.getSource().getObjects().get(i));
	    		for (int j = 0; j < transformations.size(); j++)
	    		{
	    			transformation = transformations.get(j);
	    			sum += transformation.getEase();
	    		}
	    		
	    	}
	    	
	    	return sum;
	    }
	    
	 public double getEase()
	 {
		 return this.easeOfTransformation;
	 }
	 


	 
}
