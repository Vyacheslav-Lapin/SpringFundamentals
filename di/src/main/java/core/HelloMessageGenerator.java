package core;

public interface HelloMessageGenerator extends MessageGenerator {
    @Override
    default String getMessage(){ return "Hello, " + toString() + "!"; }
}
