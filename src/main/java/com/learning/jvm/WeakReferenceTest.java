package com.learning.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    public static void main(String[] args) {
        Object referent = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        WeakReference weakReference1 = new WeakReference<>(referent);
//        WeakReference weakReference2 = new WeakReference<>(referent, referenceQueue);

        referent = null;
        System.gc();

        Object referent2 = weakReference1.get();
        System.out.println("after gc, reference get result: " + referent2);
    }
}
