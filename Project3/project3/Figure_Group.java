package project3;

import java.util.ArrayList;

public class Figure_Group {
	
	TransformedFigurePair LeftPair;
	TransformedFigurePair RightPair;
	ArrayList<Transformation> Group_Transformations = new ArrayList<>();
	
	public Figure_Group(RavensFigure Figure1, RavensFigure Figure2, RavensFigure Figure3)
	{
		Figure_Frame Fig1 = new Figure_Frame(Figure1);
		Figure_Frame Fig2 = new Figure_Frame(Figure2);
		Figure_Frame Fig3 = new Figure_Frame(Figure3);
		
		LeftPair = new TransformedFigurePair(Fig1, Fig2);
		RightPair = new TransformedFigurePair(Fig2, Fig3);


	}
	
	public boolean Match(Figure_Group comparisonGroup)
	{
		if(this.LeftPair.Match(comparisonGroup.getLeftPair()) && this.RightPair.Match(comparisonGroup.getRightPair()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public TransformedFigurePair getLeftPair()
	{
		return this.LeftPair;
	}
	
	public TransformedFigurePair getRightPair()
	{
		return this.RightPair;
	}

}
