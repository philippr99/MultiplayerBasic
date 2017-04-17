package net.philippr99.networking.server.scheduler;

/**
 * Created by chome on 4/16/17.
 */
public interface Callback<ReturnVal, ArgumentVal> {
    public ReturnVal callback(ArgumentVal arg);
}
