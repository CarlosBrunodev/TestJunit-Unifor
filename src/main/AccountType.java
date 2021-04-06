package main;

public enum AccountType {
	CORRENTE("Corrente", 1),POUPANCA("Poupanca", 2);
	
	private int value;
	private String description;
	
	AccountType(String desc, int value){
		this.description = desc;
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public String getDescricao() {
		return description;
	}
	
	public static AccountType getAccountTypeByValue(int value) {
		for (final AccountType type : values()) {
			if(type.value == value) {
				return type;
			}
		}
		return null;
	}
	
	public static AccountType getAccountTypeByDescription(String descricao) {
		for(final AccountType type : values()) {
			if(type.description.equals(descricao)) {
				return type;
			}
		}
		return null;
	}
}
