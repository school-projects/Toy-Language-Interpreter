package View.Commands;

import Domain.Exception.MyExc;

public abstract class Command {
    private String key, description;
    public Command(String key, String description) { this.key = key; this.description = description;}
    public abstract void execute() throws MyExc;
    public String getKey(){return key;}
    public String getDescription(){return description;}
}