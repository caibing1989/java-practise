package demo.pattern.factory.abstractf;

import demo.pattern.factory.entity.DellKeyboard;
import demo.pattern.factory.entity.DellMouse;
import demo.pattern.factory.entity.Keyboard;
import demo.pattern.factory.entity.Mouse;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class DellComputerFactory implements ComputerFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}
