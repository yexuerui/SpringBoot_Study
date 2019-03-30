package Algorithm;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class Outer implements Serializable {

    private static volatile Outer instance;

    public Outer getInstance() {
        if (instance == null) {
            synchronized (Outer.class) {
                if (instance == null) {
                    instance = new Outer();
                }
            }
        }
        return instance;
    }

}
