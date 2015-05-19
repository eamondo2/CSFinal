
package com.game;


//imports

import com.game.util.inputHandler;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;



/**
 * Created by eamon on 5/17/2015.
 * The majority of the GLFW code is lifted from the lwjgl getting started guide, as GLFW is hard.
 */


//TODO: Make lighting a thing. Like walls with pools of light. Dunno.


public class gameMain {
    int WIDTH = 800;
    int HEIGHT = 800;
    //Where to begin?
	//beginnings of lwjgl implementation.
	private GLFWErrorCallback errorCall;
    private float rot = 0;
    private inputHandler keyCall;
    private long window;

	public static void main(String[] args) {
		System.out.println("Hello World!");
		new gameMain().run();


	}

	public void run() {
		System.out.println("HI THERE " + Sys.getVersion());

		try {
			init();
			loop();

			glfwDestroyWindow(window);
			keyCall.release();
		} finally {
			glfwTerminate();
			errorCall.release();
		}
	}

	public void init() {
		glfwSetErrorCallback(errorCall = errorCallbackPrint(System.err));
		if (glfwInit() != GL11.GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW");


		//configure window
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable



		// Create the window
		window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
        keyCall = new inputHandler();
        glfwSetKeyCallback(window, keyCall);


		// Get the resolution of the primary monitor
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Center our window
		glfwSetWindowPos(
				window,
				(GLFWvidmode.width(vidmode) - WIDTH) / 2,
				(GLFWvidmode.height(vidmode) - HEIGHT) / 2
		);

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);

		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);


    }

    //handles rendering, dispatches render calls to all objects that need to be rendered.
    //will have multiple layers for rendering gui/score and the world, and the background
    //background will be static, and the foreground will be animated. Layering is important, as it does rendering based on layers. Topmost is called last, so GUI last
    //and bottom called first, so background first.
    public void glSetup() {
        float ratio;
        ratio = WIDTH / (float) HEIGHT;
        glViewport(0, 0, WIDTH, HEIGHT);
        glClear(GL_COLOR_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-ratio, ratio, -1.f, 1.f, 10.f, -10.f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        //glTranslatef(0f, 0f, 10f);

    }

    public void render() {
        //back


        //mid
        glBegin(GL_TRIANGLES);
        glColor3f(1.f, 0.f, 0.f);
        glVertex3f(-0.6f, -0.4f, 0.f);
        glColor3f(0.f, 1.f, 0.f);
        glVertex3f(0.6f, -0.4f, 0.f);
        glColor3f(0.f, 0.f, 1.f);
        glVertex3f(0.f, 0.6f, 0.f);
        glEnd();
        //fore

    }

    //handles frame to frame logic
    public void update() {
        //input
        if (inputHandler.keys[GLFW_KEY_ESCAPE]) {
            glfwSetWindowShouldClose(window, GL_TRUE);
            System.out.println("ShouldClose");
        }
        if (inputHandler.keys[GLFW_KEY_A]) glTranslatef(-1, 0, 0);
        if (inputHandler.keys[GLFW_KEY_D]) glTranslatef(1, 0, 0);

        //logic

        //transforms
        //glTranslatef(0,0,rot);
        //rot= (rot<10? (rot+.1f):-rot);
        //System.out.println(rot);
    }

    public void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the ContextCapabilities instance and makes the OpenGL
		// bindings available for use.
		GLContext.createFromCurrent();
        glSetup();
        // Set the clear color
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
        glEnable(GL_DEPTH_TEST);
        while (glfwWindowShouldClose(window) == GL_FALSE) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer


            //logic calls
            update();
            //rendering goes here
            render();


			glfwSwapBuffers(window); // swap the color buffers


            // Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();


        }
	}

}
