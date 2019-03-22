package base;

public abstract class BaseAbstractClass implements BaseScreen{

    private BaseScreen prefScreen;

    public void setPrefScreen(BaseScreen prefScreen) {
        this.prefScreen = prefScreen;
    }

    @Override
    public void quit() {
        prefScreen.openScreen();
    }
}
