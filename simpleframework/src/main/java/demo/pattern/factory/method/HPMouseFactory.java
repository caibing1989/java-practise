package demo.pattern.factory.method;

import demo.pattern.factory.entity.HPMouse;
import demo.pattern.factory.entity.Mouse;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class HPMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new HPMouse();
    }
}
