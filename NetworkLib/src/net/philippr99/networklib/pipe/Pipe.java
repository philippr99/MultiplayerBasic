package net.philippr99.networklib.pipe;
import net.philippr99.networklib.CustomClientSocket;
import net.philippr99.networklib.intern.BufferSerializer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chome on 4/15/17.
 */
public class Pipe {

    private Map<String,Handler> handlers = new LinkedHashMap<String,Handler>();

    public Pipe()
    {

    }

    public Object handle(CustomClientSocket socket, Object element)
    {
        Object input = element;
        for(Handler h : handlers.values())
        {
            if(input == null)return null;
            input = h.handle(socket,input);
        }

        return input;
    }

    /**
     * Adding an handler accessible by the name
     * @param name
     * @param handler
     */
    public Pipe addHandler(String name, Handler handler)
    {
        handlers.put(name,handler);
        System.out.println("Added: "+handler.getClass().getName());
        return this;
    }
}
