package fr.inria.rsommerard.wdaelib;

public abstract class WDAEProtocol {

    public static final String HELLO = "HELLO";

    public static final String DISCOVER_PEERS = "DISCOVER_PEERS";
    public static final String STOP_DISCOVERY = "STOP_DISCOVERY";
    public static final String REQUEST_PEERS = "REQUEST_PEERS";

    public static final String PEER = "PEER";
    public static final String END_PEER = "ENDPEER";
}
