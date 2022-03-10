package main;

public enum Mode {
	ADD_FISH("新增魚"), ADD_TURTLE("新增烏龜"), REMOVE_SELECTED("移除選取"), REMOVE_ALL("移除全部"), FEED("飼料");
	
    private String description;

    private Mode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}