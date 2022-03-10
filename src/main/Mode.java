package main;

public enum Mode {
	ADD_FISH("�s�W��"), ADD_TURTLE("�s�W�Q�t"), REMOVE_SELECTED("�������"), REMOVE_ALL("��������"), FEED("�}��");
	
    private String description;

    private Mode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}