package project3;
import java.util.ArrayList;
import java.util.HashMap;


public class Fill
{
  boolean [][] fillMatrix = null;
  
  public Fill(String fill)
  {
    String fillHolder = "";
	  boolean[][] fillArray = new boolean[2][2];
		if (fill.equals("yes"))
		{
			for(int a = 0; a < fillArray.length; a++)
			{
			    for(int b = 0; b < fillArray[a].length; b++)
			    {
			    	fillArray[a][b] = true;
			    }
			}
		}
		else if(!(fill.equals("no"))) //partial fills or multiple partial fills
		{
			ArrayList<String> listofFills = this.parseFills(fill);
			for (int i = 0; i < listofFills.size(); i++)
			{
				fillHolder = listofFills.get(i);
				if (fillHolder.equals("top-half"))
				{
					fillArray[1][0] = true;
					fillArray[1][1] = true;
				}
				else if (fillHolder.equals("bottom-half"))
				{
					fillArray[0][0] = true;
					fillArray[0][1] = true;
				}
				else if (fillHolder.equals("left-half"))
				{
					fillArray[0][0] = true;
					fillArray[1][0] = true;
				}
				else if (fillHolder.equals("right-half"))
				{
					fillArray[1][1] = true;
					fillArray[0][1] = true;
				}
				else if(fillHolder.equals("top-left"))
				{
					fillArray[1][0] = true;
				}
				else if(fillHolder.equals("top-right"))
				{
					fillArray[1][1] = true;
				}
				else if(fillHolder.equals("bottom-left"))
				{
					fillArray[0][0] = true;
				}
				else if(fillHolder.equals("bottom-right"))
				{
					fillArray[0][1] = true;
				}


			}
		}
		this.fillMatrix = fillArray;
  }
  public String typeofFillChange(Fill comparisonFill)
  {
	  int emptyCount = 0;
	  int fillCount = 0;
	  boolean[][] comparisonMatrix = comparisonFill.getFillMatrix();
	  
	  if (this.fillMatrix[0][0] != comparisonMatrix[0][0])
	  {
		  if (this.fillMatrix[0][0] == true && comparisonMatrix[0][0] == false)
		  {
			 // bottomLeftChange = "empty";
			  emptyCount++;
		  }
		  else
		  {
			  //bottomLeftChange = "fill";
			  fillCount++;
		  }
	  }
	  
	  if (this.fillMatrix[0][1] != comparisonMatrix[0][1])
	  {
		  if (this.fillMatrix[0][1] == true && comparisonMatrix[0][1] == false)
		  {
			//  bottomRightChange = "empty";
			  emptyCount++;

		  }
		  else
		  {
			 // bottomRightChange = "fill";
			  fillCount++;

		  }	 
	  }
	  
	  if (this.fillMatrix[1][0] != comparisonMatrix[1][0])
	  {
		  if (this.fillMatrix[1][0] == true && comparisonMatrix[1][0] == false)
		  {
			//  topLeftChange = "empty";
			  emptyCount++;

		  }
		  else
		  {
			 // topLeftChange = "fill";
			  fillCount++;

		  }	 	  
		  
	  }
	  
	  if (this.fillMatrix[1][1] != comparisonMatrix[1][1])
	  {
		  if (this.fillMatrix[1][1] == true && comparisonMatrix[1][1] == false)
		  {
			 // topLeftChange = "empty";
			  emptyCount++;

		  }
		  else
		  {
			 // topLeftChange = "fill";
			  fillCount++;

		  }	 	  
		  
	  }
	  
	  
	  if (fillCount >= emptyCount)
	  {
		  if (fillCount == 4)
		  {
			  return "complete-fill";
		  }
		  else
		  {
			  return "partial-fill";
		  }
	  }
	  else
	  {
		  if (emptyCount == 4)
		  {
			  return "complete-empty";
		  }
		  else
		  {
			  return "complete-fill";
		  }
	  }
	  
	  
  }
  public double compareFills (Fill comparisonFill)
  {
	  double similarity = 0.0;
	  boolean[][] comparisonMatrix = comparisonFill.getFillMatrix();
	  
	  if (this.fillMatrix[0][0] == comparisonMatrix[0][0])
	  {
		  similarity = similarity + 0.25;
	  }
	  
	  if (this.fillMatrix[0][1] == comparisonMatrix[0][1])
	  {
		  similarity = similarity + 0.25;
	  }
	  
	  if (this.fillMatrix[1][0] == comparisonMatrix[1][0])
	  {
		  similarity = similarity + 0.25;
	  }
	  
	  if (this.fillMatrix[1][1] == comparisonMatrix[1][1])
	  {
		  similarity = similarity + 0.25;
	  }
	  
	  return similarity;
  }
  
  public boolean[][] getFillMatrix()
  {
	  return this.fillMatrix;
  }
	//Build a list of different fills listed
	private ArrayList<String> parseFills(String value)
	{
		int start = 0;
		int end = 0;
		String temp;
		ArrayList<String> fills = new ArrayList<>();
		for (int i = 0; i < value.length(); i++ )
		{
			if (value.charAt(i) == ',')
			{
				end = i;
				temp = value.substring(start, end);
				start = end+1;
				fills.add(temp);
			}
			else if (i == value.length())
			{
				end = value.length();
				temp = value.substring(start, end);
				fills.add(temp);
			}
		}
		return fills;
	}
  }
  
