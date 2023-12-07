package pairmatching.view;

public final class SpacerPrinter {

    private boolean isFirstCall = true;

    public void print(final String msg) {
        if (!isFirstCall) {
            System.out.println();
        }
        isFirstCall = false;
        System.out.println(msg);
    }
}
