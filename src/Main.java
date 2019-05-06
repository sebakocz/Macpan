import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public Main() {
        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize!");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
