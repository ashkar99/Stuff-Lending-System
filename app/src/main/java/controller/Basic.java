package controller;

public final class Basic {
    private static controller.SystemController controller;
    private Basic (){
   
    }
    public static SystemController getInstance() {
        if (controller == null) {
            controller = new SystemController();
        }
        return controller;
    }

}
