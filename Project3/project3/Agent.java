package project3;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Your Agent for solving Raven's Progressive Matrices. You MUST modify this
 * file.
 *
 * You may also create and submit new files in addition to modifying this file.
 *
 * Make sure your file retains methods with the signatures:
 * public Agent()
 * public char Solve(RavensProblem problem)
 *
 * These methods will be necessary for the project's main method to run.
 *
 */
public class Agent {
    /**
     * The default constructor for your Agent. Make sure to execute any
     * processing necessary before your Agent starts solving problems here.
     *
     * Do not add any variables to this signature; they will not be used by
     * main().
     *
     */
    public Agent() {


    }
    /**
     * The primary method for solving incoming Raven's Progressive Matrices.
     * For each problem, your Agent's Solve() method will be called. At the
     * conclusion of Solve(), your Agent should return a String representing its
     * answer to the question: "1", "2", "3", "4", "5", or "6". These Strings
     * are also the Names of the individual RavensFigures, obtained through
     * RavensFigure.getName().
     *
     * In addition to returning your answer at the end of the method, your Agent
     * may also call problem.checkAnswer(String givenAnswer). The parameter
     * passed to checkAnswer should be your Agent's current guess for the
     * problem; checkAnswer will return the correct answer to the problem. This
     * allows your Agent to check its answer. Note, however, that after your
     * agent has called checkAnswer, it will *not* be able to change its answer.
     * checkAnswer is used to allow your Agent to learn from its incorrect
     * answers; however, your Agent cannot change the answer to a question it
     * has already answered.
     *
     * If your Agent calls checkAnswer during execution of Solve, the answer it
     * returns will be ignored; otherwise, the answer returned at the end of
     * Solve will be taken as your Agent's answer to this problem.
     *
     * @param problem the RavensProblem your agent should solve
     * @return your Agent's answer to this problem
     */
    public String Solve(RavensProblem problem) {
    	HashMap<String, RavensFigure> all_figures = problem.getFigures();
    	System.out.println("Problem #"+ problem.getName() + "...........................");

    	if(problem.getProblemType().equals("2x1"))
    	{

        	//ArrayList<Semantic_Network> possibleSolutions = new ArrayList<>();

        	Figure_Frame A_figure = new Figure_Frame(all_figures.get("A"));
        	Figure_Frame B_figure = new Figure_Frame(all_figures.get("B"));
        	Figure_Frame C_figure = new Figure_Frame(all_figures.get("C"));
        	String answer = "0";
        	// CONSTRUCT NODES FOR ALL OBJECTS IN FIGURES A AND B
        	//A_figure.calculateSimilarities(B_figure);
        	//A_figure.printCorrespondenceHashMap();
        	//A_figure.setCorrespondences();
        	//A_figure.printFinalCorrespondences();
        	//A_figure.determineTransformations(B_figure);
        	//A_figure.printTransformations();
        	TransformedFigurePair parentPair = new TransformedFigurePair(A_figure, B_figure);
        	//parentPair.printTransformations();


        	//HashMap<String, Correspondence> solutionCorrespondences = A_figure.pairParentFigs(C_figure);
        	/*for(String key: solutionCorrespondences.keySet())
        	{
   			 	solutionCorrespondences.get(key).printCorrespondence();
   		 	}  */
        	ArrayList<FigurePair> pairs = new ArrayList<>();
        	for (int i = 1; i <  7; i++)
        	{
        		Figure_Frame goalFigure = new Figure_Frame(all_figures.get(Integer.toString(i)));
        		TransformedFigurePair pair = new TransformedFigurePair(C_figure, goalFigure);
        		pairs.add(pair);
        		//System.out.println("Pair " + pair.getName() + ": ");
        		pair.printTransformations();



        		if (parentPair.Match(pair))
        		{
        			System.out.println("!!!!!!!Answer is " + i);
        			return Integer.toString(i);
        		}
        	}
        	/*double maxEase = 0;
    		double tempEase = 0;
    		FigurePair Solution = null;
        	for (int i = 0; i < pairs.size(); i++)
        	{
        		tempEase = pairs.get(i).getEase();
        		if(pairs.get(i).getEase() > parentPair.getEase())
        		{
        			maxEase = tempEase;
        			Solution = pairs.get(i);

        		}
        		if(Solution != null)
        		return Solution.getTarget().getName();
        	}*/

        	return "1";
    	}
        // it also looks like you're solving sort of an algorithmic problem, but in general I'd also try
        // and avoid hard coded strings throughout your code.  Try establishing constants somewhere that
        // equal this string if necessary, but otherwise you could use integers or enums to represent how
        // you're using strings
    	else if(problem.getProblemType().equals("2x2"))
    	{
    		Figure_Frame A_figure = new Figure_Frame(all_figures.get("A"));
        	Figure_Frame B_figure = new Figure_Frame(all_figures.get("B"));
        	Figure_Frame C_figure = new Figure_Frame(all_figures.get("C"));

        	TransformedFigurePair horizontalParentPair = new TransformedFigurePair(A_figure, B_figure);
			System.out.println("Known Horizontal Pair Transformations:");
			horizontalParentPair.printTransformations();

			TransformedFigurePair verticalParentPair = new TransformedFigurePair(A_figure, C_figure);
			System.out.println("Known Vertical Pair Transformations:");
			verticalParentPair.printTransformations();

        	ArrayList<FigurePair> horizontalpairs = new ArrayList<>();
        	ArrayList<FigurePair> verticalpairs = new ArrayList<>();

        	for (int i = 1; i <  7; i++)
        	{
        		Figure_Frame goalFigure = new Figure_Frame(all_figures.get(Integer.toString(i)));

        		TransformedFigurePair horizontalSolutionPair = new TransformedFigurePair(C_figure, goalFigure);
        		horizontalpairs.add(horizontalSolutionPair);
        		TransformedFigurePair verticalSolutionPair = new TransformedFigurePair(B_figure, goalFigure);
        		verticalpairs.add(verticalSolutionPair);


        		if (horizontalParentPair.Match(horizontalSolutionPair) && verticalParentPair.Match(verticalSolutionPair))
        		{
        			System.out.println("Solution Horizontal Pair Transformations:");
        			horizontalSolutionPair.printTransformations();
        			System.out.println("Solution Horizontal Pair Transformations:");
        			verticalSolutionPair.printTransformations();
        			System.out.println("!!!!EXACT MATCH!!!Answer is " + i);
        			return Integer.toString(i);
        		}

        	}
    	}

        /*	String horizontalsolution = "1";
        	String verticalsolution = "1";
        	int horizontalSolIndex = 0;
        	int verticalSolIndex = 0;

        	int maxSimilarity =0;
        	int tempSimilarity = 0;
        	horizontalsolution = "1";
        	verticalsolution = "1";
        	horizontalSolIndex = 0;
        	verticalSolIndex = 0;
        	ArrayList<FigurePair> horizontalMatches = new ArrayList<>();
        	ArrayList<FigurePair> verticalMatches = new ArrayList<>();

        	for (int i = 0; i < horizontalpairs.size(); i++)
        	{
        		tempSimilarity = horizontalPair.transformationSimilarity(horizontalpairs.get(i), horizontalSolutionCorrespondences);
        		if(tempSimilarity > maxSimilarity)
        		{
        			maxSimilarity = tempSimilarity;
        			horizontalsolution = horizontalpairs.get(i).getName();
        			horizontalMatches.add(horizontalpairs.get(i));

        		}
        		else if (tempSimilarity == maxSimilarity)
        		{
        			//System.out.println("Multiple options!!!");
        			horizontalMatches.add(horizontalpairs.get(i));

        		}
        	}
        	maxSimilarity =0;
        	tempSimilarity = 0;
        	for (int i = 0; i < verticalpairs.size(); i++)
        	{
        		tempSimilarity = verticalPair.transformationSimilarity(verticalpairs.get(i), verticalSolutionCorrespondences);
        		if(tempSimilarity > maxSimilarity)
        		{
        			maxSimilarity = tempSimilarity;
        			verticalsolution = verticalpairs.get(i).getName();
        			verticalMatches.add(verticalpairs.get(i));
        		}
        		else if (tempSimilarity == maxSimilarity)
        		{
        			//System.out.println("Multiple options!!!");
        			verticalMatches.add(verticalpairs.get(i));

        		}
        	}
        	if(horizontalMatches.size() == 1 && verticalMatches.size() == 1)
        	{
	        	if (horizontalMatches.get(0).getName().equals(verticalMatches.get(0).getName()))
	        	{
	        		System.out.println("Solution Horizontal Pair Transformations:");
	        		horizontalMatches.get(0).printTransformations();
        			System.out.println("Solution Horizontal Pair Transformations:");
        			verticalMatches.get(0).printTransformations();
	        		System.out.println("!!! BEST MATCH !!!!Answer is " + horizontalsolution);
	        		return horizontalMatches.get(0).getName();
	        	}
        	}
        	else
        	{
        		//choose one
        		String hozanswer = "1";
        		String veranswer = "1";
        		int hozID = 0;
        		int verID = 0;

            	for (int i = 0; i < horizontalMatches.size(); i++)
            	{
            		if(horizontalMatches.get(i).figTransMatch(horizontalParentPair))
            		{
            			hozanswer = horizontalMatches.get(i).getName();
            			hozID = i;
            		}
            	}

            	for (int i = 0; i < verticalMatches.size(); i++)
            	{
            		if(verticalMatches.get(i).figTransMatch(verticalPair))
            		{
            			veranswer = verticalMatches.get(i).getName();
            			verID = i;
            		}
            	}
            	if(hozanswer.equals(veranswer))
            	{
            		System.out.println("------------------------------------------Solution Horizontal Pair Figure Transformations:");
	        		horizontalMatches.get(hozID).printFigureTransformations();;
        			System.out.println("Solution Vertical Pair Figure Transformations:");
        			verticalMatches.get(verID).printFigureTransformations();
	        		System.out.println("!!! BEST MATCH !!!!Answer is " + hozanswer);
            		return hozanswer;
            	}*/



        /*
         * Your methods are also way too long.  Part of that is because you're doing algorithms instead of problems
         * that really require good architecture.  But otherwise you might try and adopt a rule of thumb to keep your
         * methods and functions shorter.  For me, if it gets to be longer than 5 lines, I try and see if there's any
         * way to decompose the problem more.  At the very least in this case, all of these if/else blocks should just
         * make a respective function call.
         *
         * On a similar note, I have no idea what's going on here.  Decomposing the problem should allow you to
         * name functions based on what it is that you're doing, which also helps avoid having to add comments.
         *
         * None of this stuff is anything anyone cares about back in college, but making your code readable
         * is arguably the most important thing for professional software engineering.
         */
    	else if (problem.getProblemType().equals("3x3"))
    	{


        	Figure_Group hozGroup1 = new Figure_Group(all_figures.get("A"),all_figures.get("B"), all_figures.get("C"));
        	Figure_Group hozGroup2 = new Figure_Group(all_figures.get("D"),all_figures.get("E"), all_figures.get("F"));

        	Figure_Group vertGroup1 = new Figure_Group(all_figures.get("A"),all_figures.get("D"), all_figures.get("G"));
        	Figure_Group vertGroup2 = new Figure_Group(all_figures.get("B"),all_figures.get("E"), all_figures.get("H"));

        	Figure_Group diagGroup1 = new Figure_Group(all_figures.get("C"),all_figures.get("E"), all_figures.get("G"));
            // sort of elaborating on above comments...it's clear that there's something really generic going on in
            // the above lines that I'm sure you could abstract into some kind of generic solution.  If this is code
            // that you want potential employers to look at, you need to clean up that sort of stuff.
        	for (int i = 1; i <  7; i++)
        	{

        		Figure_Group hozSolGroup = new Figure_Group(all_figures.get("G"),all_figures.get("H"), all_figures.get(Integer.toString(i)));
        		Figure_Group verSolGroup = new Figure_Group(all_figures.get("C"),all_figures.get("F"), all_figures.get(Integer.toString(i)));
        		Figure_Group diagSolGroup = new Figure_Group(all_figures.get("A"),all_figures.get("E"), all_figures.get(Integer.toString(i)));

        		if (hozGroup1.Match(hozSolGroup)&&
        			hozGroup2.Match(hozSolGroup)&&
        			vertGroup1.Match(verSolGroup)&&
        			vertGroup2.Match(verSolGroup)&&
        			diagGroup1.Match(diagSolGroup))
        		{
        			System.out.println("Found a match");
        			return  Integer.toString(i);
        		}
        	}

    	}


        return "1";
    }
}
