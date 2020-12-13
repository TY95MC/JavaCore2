package lesson_5;

public class Multi {
    static final int size = 10000000;
    static final int h = size / 2;

    static class Method1 extends Thread {
        @Override
        public void run() {
            System.out.println("method1 is running.");
            float[] arr = new float[size];
            long a = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                arr[i] = 1;
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println(System.currentTimeMillis() - a);
            System.out.println("method1 is over.");

        }
    }

    static class Method2 extends Thread {
        @Override
        public void run() {
            System.out.println("method2 is running.");
            float[] arr = new float[size];
            float[] a1 = new float[h];
            float[] a2 = new float[h];
            long a = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                arr[i] = 1;
            }
            System.arraycopy(arr, 0, a1, 0, h);
            System.arraycopy(arr, h, a2, 0, h);

                for (int i = 0; i < h; i++) {
                    a1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    a2[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, 0, arr, h, h);
            System.out.println(System.currentTimeMillis() - a);
            System.out.println("method2 is over.");
        }
    }

    public static void main(String[] args) {
            System.out.println("Program started.");
            Method1 method1 = new Method1();
            method1.start();
        try {
            method1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Method2 method2 = new Method2();
        method2.start();
        try {
            method2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Program stopped.");
    }
}


