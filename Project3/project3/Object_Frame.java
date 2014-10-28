package project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;




public class Object_Frame
{
  private String name;
  private String shape;
  private int sides = 0;
  private String size = "no-size";
  private Fill fill = null;
  private int angle = 0;
  private String inside = "";
  private String outside = "";
  private String above = "";
  private String below = "";
  private String left_of = "";
  private String right_of = "";
  private String overlaps = "";
  private boolean horizontal_flip = false;
  private boolean vertical_flip = false;
  //private char corresponds_to;
  private HashMap<String, String> arrangements = new HashMap<>();
  //private 

  public Object_Frame(RavensObject object)
  {
	ArrayList<RavensAttribute> attributes = object.getAttributes();
	HashMap<String, String> attributesHash = this.putinHash(attributes);
	this.name = object.getName();
	this.shape = attributesHash.get("shape");
	
	if (shape.equals("circle"))
	{
		this.sides = 1;
	}
	else if (shape.contains("triangle") || shape.equals("Pac-Man"))
	{
		this.sides = 3;
	}
	else if (shape.equals("square") || shape.equals("diamond") || shape.equals("rectangle"))
	{
		this.sides = 4;
	}
	else if (shape.equals("half-arrow") )
	{
		this.sides = 5;
	}
	else if (shape.equals("arrow") )
	{
		this.sides = 7;
	}
	else if (shape.equals("plus"))
	{
		this.sides = 12;
	}
	
	if (attributesHash.get("size") != null)
	{
		this.size = attributesHash.get("size");
	}
	
	if (attributesHash.get("fill") != null)
	{
		this.fill = new Fill(attributesHash.get("fill"));
	}
	else
	{
		this.fill = new Fill("no");
	}
	
	if (attributesHash.get("inside") != null)
	{
		this.inside = attributesHash.get("inside");
		this.arrangements.put("inside", this.inside);
	}
	
	if (attributesHash.get("outside") != null)
	{
		this.outside = attributesHash.get("outside");
		this.arrangements.put("outside", this.outside);

	}
	
	if (attributesHash.get("above") != null)
	{
		this.above = attributesHash.get("above");
		this.arrangements.put("above", this.above);

	}
	
	if (attributesHash.get("below") != null)
	{
		this.below = attributesHash.get("below");
		this.arrangements.put("below", this.below);

	}
	
	if (attributesHash.get("left-of") != null)
	{
		this.left_of = attributesHash.get("left-of");
		this.arrangements.put("left-of", this.left_of);

	}
	
	if (attributesHash.get("right-of") != null)
	{
		this.right_of = attributesHash.get("right-of");
		this.arrangements.put("right-of", this.right_of);

	}
	
	if (attributesHash.get("overlaps") != null)
	{
		this.overlaps = attributesHash.get("overlaps");
		this.arrangements.put("overlaps", this.overlaps);

	}
	
	if (attributesHash.get("angle") != null)
	{
		this.angle = Integer.parseInt(attributesHash.get("angle"));
	}
	
	if (attributesHash.get("vertical-flip") != null && attributesHash.get("vertical-flip").equals("yes"))
	{
		this.vertical_flip = true;
	}
	
	if (attributesHash.get("horizontal-flip") != null && attributesHash.get("horizontal-flip").equals("yes"))
	{
		this.vertical_flip = true;
	}
	//Constructor
  }
  // Transform attribute array list into a hash map
	private HashMap<String, String> putinHash(ArrayList<RavensAttribute> attList)
	{
		HashMap<String, String> attsHash = new HashMap<>();
		for(int i = 0; i<attList.size(); i++)
		{
			attsHash.put(attList.get(i).getName(), attList.get(i).getValue());
		}
		
		return attsHash;
	}
	
	
	
	
	public double DetermineSimilarity(Object_Frame comparisonObject)
	{
		double similarity = 10.0;
		
		//private char name;
		  if (!(this.shape.equals(comparisonObject.getShape())))
		  {
			  similarity = similarity - 3.5;
		  }
		  
		  if (!(this.size.equals(comparisonObject.getSize())))
		  {
			  similarity = similarity - 2.75;

		  }
		  		  
		 double fillSimilarity = this.fill.compareFills(comparisonObject.getFill()) * 1.0 ;
		  
		  if (fillSimilarity != 1.0)
		  {
			  similarity = similarity - (1.0 - fillSimilarity);
		  }
		  
		  /*if (this.angle != comparisonObject.getAngle())
		  {
			  similarity = similarity - 1.0;

		  }*/
		  
		  double arrangementSimilarity = this.determineArrangementCorrespondence(comparisonObject) * 2.75;
		  
		  //System.out.println("Arrangement similarity: " + arrangementSimilarity);
		  
		  if (arrangementSimilarity != 2.75)
		  {
			  similarity = similarity - (2.75 - arrangementSimilarity);
		  }
		  similarity = similarity / 10.0;
		
		return similarity;
	}

	
	public String getInside()
	{
		return this.inside;
	}
	
	public String getOutside()
	{
		return this.outside;
	}
	public String getAbove()
	{
		return this.above;
	}
	public String getBelow()
	{
		return this.below;
	}
	public String getLeftOf()
	{
		return this.left_of;
	}
	public String getRightOf()
	{
		return this.right_of;
	}
	public String getOverlaps()
	{
		return this.overlaps;
	}
	
	public HashMap<String, String> getArrangements()
	{
		return this.arrangements;
	}
	public String getName()
	{
		return this.name;
	}
	
	public String getShape()
	{
		return this.shape;
	}
	
	public int getSides()
	{
		return this.sides;
		
	}
	
	public String getSize()
	{
		return this.size;
	}
	
	public Fill getFill()
	{
		return this.fill;
	}
	
	public int getAngle()
	{
		return this.angle;
	}
	
	public boolean getVerticalFlip()
	{
		return this.vertical_flip;
	}
	
	public boolean getHorizontalFlip()
	{
		return this.horizontal_flip;
	}
	public boolean checkArrangement(Object_Frame goalFrame)
	{
		if (this.inside.equals(goalFrame.getInside()) &&
			this.outside.equals(goalFrame.getOutside()) &&
			this.above.equals(goalFrame.getAbove()) &&
			this.below.equals(goalFrame.getBelow()) &&
			this.left_of.equals(goalFrame.getLeftOf()) &&
			this.right_of.equals(goalFrame.getRightOf()) &&
			this.overlaps.equals(goalFrame.getOverlaps()))
		{
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	public double determineArrangementCorrespondence(Object_Frame goalFrame)
	{
		HashMap<String, String> currentArrangements = this.arrangements;
		HashMap<String, String> goalArrangements = goalFrame.getArrangements();
		//currentArrange
		double numberMatching = 0;
		double totalArrangements = 0;
		//Iterator it;
		
		if (checkArrangement(goalFrame))
		{
			//System.out.println("Found arrangement equal for " + this.name + " to " + goalFrame.getName() + " relationship");
			return 1.0;
		}
		else
		{
			//System.out.println("Found arrangement NOT equal for " + this.name + " to " + goalFrame.getName() + " relationship");
			if (currentArrangements.size() >= goalArrangements.size())
			{
				
				totalArrangements = currentArrangements.size();
				for (String arrangementType : goalArrangements.keySet()) 
				{
					//System.out.println(arrangementType);
				    if(goalArrangements.get(arrangementType).equals(currentArrangements.get(arrangementType)));
				    {
				    	//System.out.println("Increase match");
				    	numberMatching++;
				    }
				} 
			}
			else
			{
				totalArrangements = goalArrangements.size();
				for (String arrangementType : currentArrangements.keySet()) 
				{
				    if(currentArrangements.get(arrangementType).equals(goalArrangements.get(arrangementType)));
				    {
				    	//System.out.println("Increase match");
				    	numberMatching++;
				    }
				} 
			}
			
			//System.out.println((double) numberMatching/(double) totalArrangements);
			
			return (numberMatching)/(totalArrangements);

			
		}
	}
	public ArrayList<Transformation> determineTransformations(Object_Frame goalFrame)
	{
		Transformation newTransformation;
		ArrayList<Transformation> transformations = new ArrayList<>();
		
			if (this.fill.compareFills(goalFrame.getFill()) != 1.0)
			{
				newTransformation = new Transformation(this.name, goalFrame.getName(), this.fill.typeofFillChange(goalFrame.getFill()));
				transformations.add(newTransformation);
				
			}
			
			
				if (!(this.size.equals(goalFrame.getSize())))
				{
					if(this.size.equals("large") && (goalFrame.getSize().equals("medium") || goalFrame.getSize().equals("small")))
					{
						newTransformation = new Transformation(this.name, goalFrame.getName(), "decrease-size");
						transformations.add(newTransformation);
						
					}
					else if(this.size.equals("medium") )
					{
						if (goalFrame.getSize().equals("small"))
						{
							//transformations.add("decrease-size");
							newTransformation = new Transformation(this.name, goalFrame.getName(), "decrease-size");
							transformations.add(newTransformation);
		
						}
						else if (goalFrame.getSize().equals("large"))
						{
							//transformations.add("increase-size");
							newTransformation = new Transformation(this.name, goalFrame.getName(), "increase-size");
							transformations.add(newTransformation);
						}
					}
					else if (this.size.equals("small") && (goalFrame.getSize().equals("medium") || goalFrame.getSize().equals("large")  ))
					{
						//transformations.add("increase-size");
						newTransformation = new Transformation(this.name, goalFrame.getName(), "increase-size");
						transformations.add(newTransformation);
					}
					else if(this.size.equals("very-large") && !(goalFrame.getSize().equals("very-large")))
					{
						newTransformation = new Transformation(this.name, goalFrame.getName(), "decrease-size");
						transformations.add(newTransformation);
						
					}
					else if(this.size.equals("very-small") && !(goalFrame.getSize().equals("very-small")))
					{
						newTransformation = new Transformation(this.name, goalFrame.getName(), "increase-size");
						transformations.add(newTransformation);
						
					}
					
				}
				
				
				if (this.angle != goalFrame.getAngle())
				{				
					
						if(Math.abs(this.angle-goalFrame.getAngle()) == 180 )
						{
							if (this.shape.contains("Pac-Man"))
							{
								newTransformation = new Transformation(this.name, goalFrame.getName(), "horizontal-reflection");
								transformations.add(newTransformation);
							}
						}
						else if ((Math.abs(this.angle-goalFrame.getAngle()) == 90) || (360-this.angle == 90))
						{
							if (this.shape.contains("Pac-Man"))
							{
								if(this.angle == 45 || this.angle == 315)
								{
									newTransformation = new Transformation(this.name, goalFrame.getName(), "horizontal-reflection");
									transformations.add(newTransformation);
								}
								else if(this.angle == 135)
								{
									newTransformation = new Transformation(this.name, goalFrame.getName(), "vertical-reflection");
									transformations.add(newTransformation);
								}
								
							}
							else if(this.shape.equals("right-triangle"))
							{
								if(this.angle == 180 || this.angle == 270)
								{
									if(this.angle-goalFrame.getAngle() > 0)
									{
										newTransformation = new Transformation(this.name, goalFrame.getName(), "horizontal-reflection");
										transformations.add(newTransformation);
									}
									else if(this.angle-goalFrame.getAngle() < 0)
									{
										newTransformation = new Transformation(this.name, goalFrame.getName(), "vertical-reflection");
										transformations.add(newTransformation);
									}
								}
								else if(this.angle == 90 || this.angle == 0)
								{
									if(this.angle-goalFrame.getAngle() > 0)
									{
										newTransformation = new Transformation(this.name, goalFrame.getName(), "vertical-reflection");
										transformations.add(newTransformation);
									}
									else if(this.angle-goalFrame.getAngle() < 0)
									{
										newTransformation = new Transformation(this.name, goalFrame.getName(), "horizontal-reflection");
										transformations.add(newTransformation);
									}
								}
							}
						}
						else if (Math.abs(this.angle-goalFrame.getAngle()) == 270)
						{
							if (this.shape.contains("Pac-Man"))
							{
								if(this.angle == 45)
								{
									newTransformation = new Transformation(this.name, goalFrame.getName(), "vertical-reflection");
									transformations.add(newTransformation);
								}
								
							}
						}
						else if(this.angle < goalFrame.getAngle())
						{
							//transformations.add();
							newTransformation = new Transformation(this.name, goalFrame.getName(), "rotate-left");
							transformations.add(newTransformation);
						}
						else if(this.angle > goalFrame.getAngle())
						{
							//transformations.add();
							newTransformation = new Transformation(this.name, goalFrame.getName(), "rotate-right");
							transformations.add(newTransformation);
						}
					}
				
				
				if (this.horizontal_flip != goalFrame.getHorizontalFlip()) 
				{
					//transformations.add();
					newTransformation = new Transformation(this.name, goalFrame.getName(),"horizontal-reflection");
					transformations.add(newTransformation);
				}
				
				if (this.vertical_flip != goalFrame.getVerticalFlip()) 
				{
					//transformations.add();
					newTransformation = new Transformation(this.name, goalFrame.getName(), "vertical-reflection");
					transformations.add(newTransformation);
				}
				
				if (!( this.shape.equals(goalFrame.getShape())))
				{
					//transformations.add();
					newTransformation = new Transformation(this.name, goalFrame.getName(), "change-shape");
					transformations.add(newTransformation);
	
				}
				
				if(this.sides > goalFrame.getSides())
				{
					newTransformation = new Transformation(this.name, goalFrame.getName(), "decrease-sides");
					transformations.add(newTransformation);
				}
				if(this.sides < goalFrame.getSides())
				{
					newTransformation = new Transformation(this.name, goalFrame.getName(), "increase-sides");
					transformations.add(newTransformation);
				}
			
			
			return transformations;
	}
}
