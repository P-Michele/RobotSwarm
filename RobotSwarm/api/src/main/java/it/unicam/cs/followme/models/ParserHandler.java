package it.unicam.cs.followme.models;

import it.unicam.cs.followme.command.*;
import it.unicam.cs.followme.util.Point;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Handler for parsing commands.
 */
public class ParserHandler implements FollowMeParserHandler {

    private Environment environment;

    private Queue<Command> commands;

    private Stack<IterativeCommand> balancedCommands;

    /**
     * Constructs a ParserHandler with the specified environment.
     *
     * @param environment The environment in which the commands will be executed.
     */
    public ParserHandler(Environment environment){
        this.environment = environment;
    }

    /**
     * Prepares the parser for parsing .
     */
    @Override
    public void parsingStarted() {
        commands = new LinkedList<>();
        balancedCommands = new Stack<>();
    }

    /**
     *@throws IllegalArgumentException if the iterative commands are not balanced.
     *
     * Assigns the program to be executed to each entity.
     */
    @Override
    public void parsingDone() {
        if(!this.balancedCommands.isEmpty())
            throw new IllegalArgumentException("COMMANDS ARE NOT BALANCED");

        List<Entity> entities = this.environment.getEntities();
        for (Entity entity: entities) {
            entity.addProgram(this.commands);
            entity.perceive(this.environment);
        }
    }

    @Override
    public void moveCommand(double[] args) {
        MoveCommand move = new MoveCommand(new Point(args[0] , args[1]) , args[2]);
        this.check(move);
    }

    @Override
    public void moveRandomCommand(double[] args) {
        RandomMoveCommand moveRandom = new RandomMoveCommand(new Point(args[0] , args[2]) ,
                new Point(args[1] , args[3]) , args[4]);
        this.check(moveRandom);
    }

    @Override
    public void signalCommand(String label) {
        SignalCommand signal = new SignalCommand(label);
        this.check(signal);
    }

    @Override
    public void unsignalCommand(String label) {
        UnsignalCommand unsignal = new UnsignalCommand(label);
        this.check(unsignal);
    }

    @Override
    public void followCommand(String label, double[] args) {
        FollowCommand follow = new FollowCommand(label , args[0] , args[1]);
        this.check(follow);
    }

    @Override
    public void stopCommand() {
        StopCommand stop = new StopCommand();
        this.check(stop);
    }

    @Override
    public void continueCommand(int s) {
        ContinueCommand continueCommand = new ContinueCommand(s);
        this.check(continueCommand);
    }

    @Override
    public void repeatCommandStart(int n) {
        RepeatCommand repeatCommand = new RepeatCommand(n);
        this.check(repeatCommand);
        this.balancedCommands.add(repeatCommand);
    }

    @Override
    public void untilCommandStart(String label) {
        UntilCommand until = new UntilCommand(label);
        this.check(until);
        this.balancedCommands.add(until);
    }

    @Override
    public void doForeverStart() {
        ForeverCommand forever = new ForeverCommand();
        this.check(forever);
        this.balancedCommands.add(forever);
    }

    @Override
    public void doneCommand() {
        this.balancedCommands.pop();
    }

    /**
     *  @return true if there are no iterative commands to assign other commands to .
     */
    private boolean isInLoop(){
        return !this.balancedCommands.isEmpty();
    }

    /**
     * Adds a command to the list of the current iterative command.
     */
    private void addCommand(Command command){
        IterativeCommand iterativeCommand = this.balancedCommands.peek();
        iterativeCommand.addCommand(command);
    }

    /**
     * Allows commands to be inserted into the current iterative command if it exists
     * otherwise it continues the list of commands.
     */
    private void check(Command command){
        if(this.isInLoop()){
            this.addCommand(command);
        }else{
            this.commands.add(command);
        }
    }

}
