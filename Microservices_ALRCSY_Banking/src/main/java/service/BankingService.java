package service;



import banking.Banking.ChargeCardRequest;
import banking.Banking.ChargeCardResponse;

public class BankingService  implements ICreditCard{
	 
		@Override
		public ChargeCardResponse charge(ChargeCardRequest creditCard) {
			String cardNumber=creditCard.getCardNumber();
			int amount=creditCard.getAmount();
			boolean success = (cardNumber.length() % 2) == 0 && amount > 0;
			return ChargeCardResponse.newBuilder().setSuccess(success).build();
		}
}
