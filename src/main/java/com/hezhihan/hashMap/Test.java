package com.hezhihan.hashMap;

public class Test extends AbstractClass {
    @Override
    public void testMethod1(){
        System.out.println("67890");
    }
    public void main() throws IllegalAccessException, InstantiationException {
        AbstractClass abstractClass = AbstractClass.class.newInstance();
    }
}
