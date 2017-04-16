package net.philippr99.networklib.pipe;

import net.philippr99.networklib.CustomClientSocket;

/**
 * Created by chome on 4/15/17.
 */
public interface Handler<Input, Output> {
    public Output handle(CustomClientSocket socket, Input in);
}
