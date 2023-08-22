public class InterFace {

    @FunctionalInterface
    public interface ProbaFace {
        public static final int i = 10;

        public static void main(String[] args) {
            System.out.println("interface");
        }

        public static void main2(String[] args) {
            System.out.println("interface");
        }

        void t();

        public static void t2() {
        }

        ;

    }

    public static class PClass implements ProbaFace {

        @Override
        public final void t() {
            new c();
        }

        private class c {
        }
    }

    public static void main(String[] args) {
        new PClass().new c();
    }

}
