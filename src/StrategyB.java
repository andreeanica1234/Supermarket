
public class StrategyB implements Strategy{

	@Override
	public Item execute(WishList wishList) { 
		//returneaza produsul cel mai ieftin
		return wishList.getItem(0);
	}

}
