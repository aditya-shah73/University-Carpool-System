import java.util.ArrayList;

public class Route
{
	public ArrayList<User> listOfRider;

	public Route(ArrayList<User> listOfRider)
	{
		this.listOfRider = listOfRider;
	}

	public ArrayList<User> getRouteFromHome()
	{
		return this.selectionSortDes(this.listOfRider);
	}

	// Descending Order of Rider, for going Home -> School
	private ArrayList<User> selectionSortDes(ArrayList<User> unsortedList)
	{
		ArrayList<User> sortedList = new ArrayList<>();
		for(int i = 0; i < unsortedList.size(); i++)
		{
			int highestIndex = i;
			for(int j = i+1; j < unsortedList.size(); j++)
			{
				if(unsortedList.get(j).getRegion() > unsortedList.get(i).getRegion())
				{
					highestIndex = j;
				}
			}
			sortedList.add(unsortedList.get(highestIndex));	
		}
		return sortedList;		
	}

	public ArrayList<User> getRouteFromSchool()
	{
		return this.selectionSortAsc(this.listOfRider);
	}

	// Ascending Order of Rider, for going school -> drop off
	private ArrayList<User> selectionSortAsc(ArrayList<User> unsortedList)
	{
		ArrayList<User> sortedList = new ArrayList<>();
		for(int i = 0; i < unsortedList.size(); i++)
		{
			int lowestIndex = i;
			for(int j = i+1; j < unsortedList.size(); j++)
			{
				if(unsortedList.get(j).getRegion() < unsortedList.get(i).getRegion())
				{
					lowestIndex = j;
				}
			}
			sortedList.add(unsortedList.get(lowestIndex));	
		}
		return sortedList;
	}
}