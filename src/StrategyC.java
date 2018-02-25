
public class StrategyC implements Strategy{

	@Override
	public Item execute(WishList wishList) {
		//returneaza ultimul element adaugat in cosul de dorinte
		return wishList.getLastAdded();
	}

}
