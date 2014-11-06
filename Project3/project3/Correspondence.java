package project3;

import java.util.ArrayList;

//import project1.Transformation_Link;

public class Correspondence {
	private Object_Frame current;
	private Object_Frame goal;
	private double correspondenceStrength;
	private ArrayList<Transformation> transformations = new ArrayList<>();
	private double avgTransformationEase = 0.0;

	public Correspondence (Object_Frame currentObj, Object_Frame goalObj, double strength)
	{
		this.current = currentObj;
		this.goal = goalObj;
		this.correspondenceStrength = strength;
	}

	public Object_Frame getGoal()
	{
		return this.goal;
	}
	public Object_Frame getCurrent()
	{
		return this.current;
	}
	public double getSimilarity()
	{
		return this.correspondenceStrength;
	}

	public void printCorrespondence()
	{

		System.out.println("Object " + this.current.getName() + " corresponds to " + this.goal.getName() + " with " + this.correspondenceStrength);
	}

	public boolean greatherThan(Correspondence comparisonCorrespondence)
	{
		if (this.correspondenceStrength > comparisonCorrespondence.getSimilarity())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean lessThan(Correspondence comparisonCorrespondence)
	{
        // for cases like this you could simplify:
        //
        // return this.correspondenceStrength < comparisonCorrespondence.getSimilarity()
        //
        // less is more.  The fewer lines the better...true in the vast majority of cases
		if (this.correspondenceStrength < comparisonCorrespondence.getSimilarity())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public ArrayList<Transformation> getTransformations()
	{
		return this.transformations;
	}
	public void setTransformations(ArrayList<Transformation> incomingTransformations)
	{
		this.transformations = incomingTransformations;
		this.avgTransformationEase = this.deteremineTransformationEase();

	}

	public double deteremineTransformationEase()
	{
		double sum = 0.0;
		for(int i = 0; i < this.transformations.size(); i++)
		{
			sum += this.transformations.get(i).getEase();
		}
		//this.avgTransformationEase = sum/this.transformations.size();
		return sum/this.transformations.size();
	}
	public void printTransformations()
	{
		for (int i = 0; i < this.transformations.size(); i++)
		{
			transformations.get(i).print();
		}
	}
	public boolean transformationsEqual(Correspondence comparisonCorrespondence)
	{
		ArrayList <Transformation> comparisonTransformations = comparisonCorrespondence.getTransformations();

		int transformationMatchCounter = 0;
		boolean transformationMatch = false;
		if (this.transformations.size() == comparisonTransformations.size())
		{
			for (int i = 0; i < this.transformations.size(); i++)
			{
				for (int j = 0; j < comparisonTransformations.size(); j++)
				{
					if (this.transformations.get(i).getLabel().equals(comparisonTransformations.get(j).getLabel()))
					{
						transformationMatchCounter++;

					}
					else
					{
						if ((this.transformations.get(i).getLabel().contains("reflection") ||
							this.transformations.get(i).getLabel().contains("rotation")) &&
							comparisonCorrespondence.getCurrent().getShape().equals("circle"))
						{
							transformationMatchCounter++;
						}
						else if((this.transformations.get(i).getLabel().contains("reflection") ||
								this.transformations.get(i).getLabel().contains("rotation")) &&
								comparisonCorrespondence.getGoal().getShape().equals("circle"))
						{
							transformationMatchCounter++;
						}
						//System.out.println(this.transformations.get(i).getLabel() + " doesn't match " + comparisonTransformations.get(j).getLabel());
					}
				}
			}
			if (transformationMatchCounter == this.transformations.size())
			{
				transformationMatch = true;
			}
		}
		else
		{
			transformationMatch = false;
		}

			return transformationMatch;
	}

	public double getEase()
	{
		return this.avgTransformationEase;
	}

	public boolean containsTransformation(String tranformationType)
	{
		for (int i = 0; i < this.transformations.size(); i++)
		{
			if (this.transformations.get(i).getLabel().equals(tranformationType))
			{
				return true;
			}
		}

		return false;
	}
}
