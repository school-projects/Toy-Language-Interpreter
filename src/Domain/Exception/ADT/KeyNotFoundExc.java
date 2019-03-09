package Domain.Exception.ADT;

import Domain.Exception.MyExc;

public class KeyNotFoundExc extends ADTExc {
    public KeyNotFoundExc(String myMessage) {
        super(myMessage);
    }
}
