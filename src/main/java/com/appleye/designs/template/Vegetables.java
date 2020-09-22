package com.appleye.designs.template;

/**
 * @author Appleye
 * @createTime 2020-08-14 09:21
 */
public abstract class Vegetables {
    public final void doCooking() {
        preCooking();
        if (isPeel()) {
            peel();
        }
        cooking();
        putPlate();
    }

    private void preCooking() {
        System.out.println("Wash vegetables and light a fire!");
    }

    private void peel() {
        System.out.println("Peel!");
    }

    protected abstract void cooking();

    private void putPlate() {
        System.out.println("Put plate!");
    }


    //钩子方法
    protected boolean isPeel() {
        return true;
    }
}
