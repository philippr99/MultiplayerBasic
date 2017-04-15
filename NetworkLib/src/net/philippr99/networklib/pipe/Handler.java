package net.philippr99.networklib.pipe;

/**
 * Created by chome on 4/15/17.
 */
public interface Handler<Input, Output> {
    public Output handle(Input in);
}
