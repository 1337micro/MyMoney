package src;

import java.util.ArrayList;
import java.util.List;


public class MyCards {

	public List<Cards> getCards() {
		return cards;
	}

	private List <Cards> cards;
	
	
	public MyCards() {
		this.cards= new ArrayList<>();
	}
	
	
	
}
