
public class StrategyA implements Strategy{

	@Override
	public Item execute(WishList wishList) { 
		//returneaza produsul cu cel mai mare pret
		if(wishList.size()==0)
			return null;
		double pr = wishList.getItem(0).getPret();
		int index = 0;
		for (int i = 0; i<wishList.size(); i++){
			if (wishList.getItem(i).getPret() < pr){
				pr = wishList.getItem(i).getPret();
				index = i;
			}
		}
		return wishList.getItem(index);
	}

}
