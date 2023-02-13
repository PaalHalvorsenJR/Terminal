package no.uib.inf101.terminal;

public interface Command {
    String run(String[] args);
    String getName();
}

class CmdEcho implements Command {
    @Override
    public String run(String[] args) {
        String result = "";
        for (String arg : args) {
            result += arg + " ";
        }
        return result;
    }

    @Override
    public String getName() {
        return "echo";
    }
}
