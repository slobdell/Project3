package project3;

public class Transformation {

	private String current_object;
	private String goal_object;
	private String label;
	int ease;
	
	public Transformation(String current, String goal, String label)
	{
		this.label = label;
		this.current_object = current;
		this.goal_object = goal;
		determineEase();
	}
	
	
	private void determineEase()
	{
	
		if (this.label.equals("unchanged"))
		{
			this.ease = 10;
		}
		else if (this.label.equals("complete-fill"))
		{
			this.ease = 9;
		}
		else if (this.label.equals("complete-empty"))
		{
			this.ease = 8;
		}
		else if (this.label.equals("increase-size"))
		{
			this.ease = 7;
		}
		else if (this.label.equals("decrease-size"))
		{
			this.ease = 6;
		}
		else if (this.label.equals("rotate-left") || this.label.equals("rotate-right"))
		{
			this.ease = 5;
		}
		else if (this.label.equals("horizontal-reflection") || this.label.equals("vertical-reflection"))
		{
			this.ease = 4;
		}
		else if (this.label.equals("partial-fill") || this.label.equals("partial-empty"))
		{
			this.ease = 3;
		}
		else if (this.label.equals("deleted") || this.label.equals("added"))
		{
			this.ease = 2;
		}
		else if (this.label.equals("changed-shape"))
		{
			this.ease = 1;
		}
	}
	
	public String getLabel()
	{
		return this.label;
	}
	
	
	public int getEase ()
	{
		return this.ease;
	}
	public void print()
	{
		if(label.equals("deleted") || label.equals("unchanged"))
		{
			System.out.println("Object " + this.current_object + " [" + this.label + "] " );
		}
		else
		{
			System.out.println("Object " + this.current_object + " [" + this.label + "] " + this.goal_object);

		}
		
	}
	public boolean labelsEqual(Transformation comparisonLink)
	{
		if (this.label.equals(comparisonLink.getLabel()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
